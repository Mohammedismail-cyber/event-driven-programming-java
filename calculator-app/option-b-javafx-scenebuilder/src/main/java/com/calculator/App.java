package com.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Loads calculator.fxml (designed in Scene Builder) and shows it. */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/calculator/calculator.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Event-Driven Calculator (JavaFX)");
        primaryStage.setScene(new Scene(root, 350, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
