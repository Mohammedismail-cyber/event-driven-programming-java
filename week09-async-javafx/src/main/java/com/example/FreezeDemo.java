package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Activity 1 - THE BAD WAY (on purpose).
 * Thread.sleep() runs on the JavaFX Application Thread, so the window freezes.
 */
public class FreezeDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Ready");
        Button freezeButton = new Button("Freeze the UI!");

        // BAD: the handler runs on the JavaFX Application Thread.
        freezeButton.setOnAction(e -> {
            System.out.println("Handler running on: " + Thread.currentThread().getName());
            statusLabel.setText("Processing...");   // never gets painted!
            try {
                Thread.sleep(5000); // simulates a 5-second database query or download
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            statusLabel.setText("Done!");
        });

        VBox root = new VBox(20, freezeButton, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("The Freeze Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
