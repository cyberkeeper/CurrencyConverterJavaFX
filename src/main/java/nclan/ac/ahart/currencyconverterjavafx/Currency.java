package nclan.ac.ahart.currencyconverterjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * The starting point for the application.
 * The class that extends the Application class is the primary entry point for any JavaFX-based application. It serves
 * as a container for the application's main logic and provides the framework for creating and managing the
 * application's user interface (UI).
 *
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
        //get the resource bundle
        ResourceBundle msgs = ResourceBundle.getBundle("converter");

        FXMLLoader fxmlLoader = new FXMLLoader(Currency.class.getResource("currencyPanel.fxml"),msgs);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(msgs.getString("title"));

        InputStream inputStream = getClass().getResourceAsStream("/images/Icon-Money.png");
        Image image = new Image(inputStream);
        stage.getIcons().add(image);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Not really needed as the JavaFX Launcher will take care of running the application. Good practice to have this
     * so that our Launcher class can call this to run the UI stage of the application.
     * @param args None expected.
     */
    public static void main(String[] args) {
        launch();
    }
}