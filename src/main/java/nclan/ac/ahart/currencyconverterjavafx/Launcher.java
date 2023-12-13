package nclan.ac.ahart.currencyconverterjavafx;

/**
 * A launcher class is not strictly mandatory, using a launcher class is a recommended practice for developing JavaFX
 * applications. It promotes code separation, simplifies customization, improves launch reliability, ensures
 * compatibility with Java modules, and enhances the overall developer experience.
 *
 * In summary use this class to kick off everything in the application including JavaFX code. This class might be
 * different depending on where and what OS the application is running on, so all that configuration can be kept
 * separate from the front end JavaFX GUI.
 *
 * Create a JAR file as normal and use this class rather than the JavaFX class as the main entry point.
 * Check "copy to the output directory and link via Manifest" and Press Ok.
 * Build artifact as normal.
 */
public class Launcher {
    public static void main(String[] args)
    {
        //call the main method within the Currency class. The Currency class extends Application class.
        Currency.main(args);
    }
}
