package nclan.ac.ahart.currencyconverterjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.InputStream;
import javafx.scene.image.Image;

import java.io.IOException;
/**
 * The starting point for the application.
 * @author ahart
 */
public class Currency extends Application {
    /**
     * Responsible for creating the main stage, which is the top-level window of the JavaFX application. It sets the
     * stage's title, dimensions, and other essential properties.
     * @param stage provides various methods for managing the window's properties and behavior
     * @throws IOException if the fxml is not found an exception is thrown
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Currency.class.getResource("currencyPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FX Currency Converter");

        InputStream inputStream = getClass().getResourceAsStream("/images/Icon-Money.png");
        Image image = new Image(inputStream);
        //ImageView imageView = new ImageView(image);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Not really needed as the JavaFX Launcher will take care of running the application.
     * @param args None expected.
     */
    public static void main(String[] args) {
        launch();
    }
}