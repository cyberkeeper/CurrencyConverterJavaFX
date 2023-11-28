module com.example.currencyconverterjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens nclan.ac.ahart.currencyconverterjavafx to javafx.fxml;
    exports nclan.ac.ahart.currencyconverterjavafx;
}