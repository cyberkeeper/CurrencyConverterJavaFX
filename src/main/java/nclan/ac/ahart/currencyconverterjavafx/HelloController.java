package nclan.ac.ahart.currencyconverterjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label labelOutput;
    @FXML
    private ComboBox<String> cbFrom;
    @FXML
    private ComboBox<String> cbDest;
    @FXML
    private TextField txtFrom;

    //an array containing 3 currencies for conversion. Make it final as it won't be changed after being initialised.
    private final String[] currency = {"GBP", "EUR", "USD"};

    /* A 2D array for converting from one currency to any other currency. The first row takes the format
        GBP to GBP, GBP to EUR, GBP to USD
        EUR to GBP, EUR to EUR, EUR to USD
        USD to GBP, USD to EUR, USD to USD
     Other currencies could be added by expanding the currency and convert arrays.
     Make it final as it won't be changed after being initialised.
     */
    private final double[][] convert = {
            {1, 1.15, 1.3},
            {0.87, 1, 1.13},
            {0.77, 0.88, 1}};

    @FXML
    public void initialize() {
        // Create an ObservableList of fruit options
        ObservableList<String> currencyOptions = FXCollections.observableArrayList(currency);
        //ObservableList<String> currencyOptions = FXCollections.observableArrayList("GBP", "USD", "EUR");

        // Set the items of the ComboBox to the ObservableList
        cbFrom.setItems(currencyOptions);
        cbDest.setItems(currencyOptions);

        // Pre-select the first item in the ComboBox
        cbFrom.getSelectionModel().selectFirst();
        cbDest.getSelectionModel().select(1);
    }

    /**
     * This method contains the code the convert from one currency to another currency
     */
    @FXML
    private void convertCurrency() {
        //the try statement is required because the user might be an idiot and enter a non-number in the input field!
        try {
            //get the selected items from the combo boxes. The indexes are zero based like arrays.
            int from = cbFrom.getSelectionModel().getSelectedIndex();
            int dest = cbDest.getSelectionModel().getSelectedIndex();

            //get the amount to be converted
            double amount = Double.parseDouble(txtFrom.getText());

            //check if the user entered a number less than 0. If it was throw an exception
            if(amount < 0)
                throw new Exception("The initial amount must be greater than 0");

            //get the conversion rate
            double convRate = convert[from][dest];

            //do the conversion
            double result = convRate * amount;

            //update the screen with the result
            String output = String.format("You will receive %.2f %s", result, cbDest.getSelectionModel().getSelectedItem());
            labelOutput.setText(output);

            //create a string which contains the full information about the conversion that just took place. Output string to console.
            String logOutput = String.format("Converting %.2f %s to %s gives %.2f.",amount,cbFrom.getValue(),cbDest.getValue(), result);
            System.out.println(logOutput);
        }catch (NumberFormatException nfe)
        {
            //the Double.parseDouble throws this type of exception so deal with it.
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"The input must be a number", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }catch (Exception e)
        {
            //in the code above an exception is thrown if the initial amount entered is < 0, deal with it
            Alert alert = new Alert(Alert.AlertType.ERROR,"Conversion error", ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}