package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Activity 2 - THE FIX: run the slow work in a Task on a background thread.
 *
 * Two safe ways to touch the UI:
 *   1. Inside call() (background thread): use Platform.runLater(...)
 *   2. In setOnSucceeded / setOnFailed (already on the FX thread): update directly
 */
public class TaskDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Ready");
        Button startButton = new Button("Start Background Task");

        startButton.setOnAction(e -> {
            statusLabel.setText("Processing...");
            startButton.setDisable(true); // prevent double-clicking

            // 1. Create a background Task
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    // THIS RUNS IN THE BACKGROUND (off the UI thread)
                    System.out.println("call() running on: " + Thread.currentThread().getName());

                    Thread.sleep(2500);

                    // From here we must NOT touch the UI directly.
                    // Platform.runLater sends the update to the FX thread.
                    Platform.runLater(() -> statusLabel.setText("Halfway there..."));

                    Thread.sleep(2500);
                    return null;
                }
            };

            // 2. These callbacks already run on the JavaFX Application Thread,
            //    so no Platform.runLater is needed here.
            task.setOnSucceeded(event -> {
                System.out.println("onSucceeded running on: " + Thread.currentThread().getName());
                statusLabel.setText("Task Complete!");
                startButton.setDisable(false);
            });

            task.setOnFailed(event -> {
                statusLabel.setText("Task failed: " + task.getException().getMessage());
                startButton.setDisable(false);
            });

            // 3. Start the task on a new (daemon) thread
            Thread thread = new Thread(task);
            thread.setDaemon(true); // closing the window will not wait for the task
            thread.start();
        });

        VBox root = new VBox(20, startButton, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Task Demo - The Fix");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
