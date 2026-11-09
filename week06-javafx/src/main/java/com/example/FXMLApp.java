package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Activity 4 - loads login.fxml and shows it. */
public class FXMLApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the FXML file (it also creates LoginController)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/login.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setTitle("JavaFX Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
