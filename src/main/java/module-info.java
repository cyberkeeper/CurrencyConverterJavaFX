module com.example.currencyconverterjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.currencyconverterjavafx to javafx.fxml;
    exports com.example.currencyconverterjavafx;
}