/*
 * A quiz application, that generates a quiz with 5 question picked at random from a question database.
 */
package q1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Published: 25/12/2021
 *
 * @author Saar
 */
public class App1 extends Application {

    /**
     * A generic main function to launch the app.
     * @param args Additional launch arguments - unused.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * A javaFX helper function that sets up the quiz interface.
     * @param primaryStage The javaFX stage to configure
     * @throws IOException if the app's .fxml file cannot be read.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app1.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Quiz");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
