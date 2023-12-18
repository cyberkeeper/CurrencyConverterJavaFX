package nclan.ac.ahart.currencyconverterjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller class for the Currency Converter application.
 * The controller class plays a crucial role in JavaFX applications by acting as the bridge between the FXML markup that
 * defines the user interface (UI) and the application's logic. It handles user interactions, manages data, and updates
 * the UI based on changes in the data or user actions.
 * Any UI component that needs to be accessed here has to have the @FXML tag.
 * Any method that is called by an action listener on the UI need to have the @FXML tag.
 * Some websites say that this class needs to implement the Initializable interface but documentation says
 * that this is deprecated and to use the zero parameter version instead, so no interface.
 */
public class CurrencyController{
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
    //the text for what appears on screen is stored in a resource bundle.
    private ResourceBundle messages;

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

    /**
     * initialise the FX components.
     */
    @FXML
    public void initialize() {
        // Create an ObservableList of fruit options
        ObservableList<String> currencyOptions = FXCollections.observableArrayList(currency);

        messages = ResourceBundle.getBundle("converter");

        //set tool tip text
        txtFrom.setTooltip(new Tooltip(messages.getString("tipConvertAmount")));
        cbFrom.setTooltip(new Tooltip(messages.getString("tipSource")));
        cbDest.setTooltip(new Tooltip(messages.getString("tipDest")));

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
                throw new Exception(messages.getString("underZero"));

            //get the conversion rate
            double convRate = convert[from][dest];

            //do the conversion
            double result = convRate * amount;

            //create a currency number formatter
            NumberFormat currencyFormatter;
            //set the details of the currency number formatter with details of the appropriate currency
            if(cbDest.getValue().equals("EUR")) {
                //create a Locale with Spanish language and country Spain
                Locale aLocale = new Locale.Builder().setLanguage("es").setRegion("ES").build();
                currencyFormatter = NumberFormat.getCurrencyInstance(aLocale);
            }else if(cbDest.getValue().equals("USD")) {
                currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
            }else{
                currencyFormatter = NumberFormat.getCurrencyInstance(Locale.UK);
            }

            //format the converted amount to the correct format
            String strResult = currencyFormatter.format(result);

            //update the screen with the result
            String output = String.format(messages.getString("output"), strResult);
            labelOutput.setText(output);

            //create a string which contains the full information about the conversion that just took place. Output string to console.
            String logOutput = String.format(messages.getString("logOutput"),amount,cbFrom.getValue(),strResult);
            System.out.println(logOutput);
        }catch (NumberFormatException nfe)
        {
            //the Double.parseDouble throws this type of exception so deal with it.
            Alert alert = new Alert(Alert.AlertType.INFORMATION,messages.getString("notNum"), ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }catch (Exception e)
        {
            //in the code above an exception is thrown if the initial amount entered is < 0, deal with it
            Alert alert = new Alert(Alert.AlertType.ERROR,messages.getString("convErr"), ButtonType.OK);
            alert.setHeaderText(null);
            alert.showAndWait();
        }
    }
}