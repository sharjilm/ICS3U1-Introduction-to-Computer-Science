/*
 * U2_A4_FastFoodApp.java
 */

package u2_a4_fastfood;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class U2_A4_FastFoodApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new U2_A4_FastFoodView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of U2_A4_FastFoodApp
     */
    public static U2_A4_FastFoodApp getApplication() {
        return Application.getInstance(U2_A4_FastFoodApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(U2_A4_FastFoodApp.class, args);
    }
}
