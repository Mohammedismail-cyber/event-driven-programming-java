package com.example;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Activity 4 - Capstone: a Service is a reusable Task.
 * It can be started, cancelled and restarted as many times as you like.
 */
public class ServiceDemo extends Application {

    // 1. A Service that can be restarted
    private static class DataSyncService extends Service<String> {
        @Override
        protected Task<String> createTask() {
            return new Task<>() {
                @Override
                protected String call() throws Exception {
                    // Simulate a long download / query
                    for (int i = 1; i <= 10; i++) {
                        if (isCancelled()) {
                            return null; // user clicked Cancel
                        }
                        Thread.sleep(500);
                        updateProgress(i, 10);
                    }
                    return "Data Synced Successfully!";
                }
            };
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Ready");

        // Spinner (looks busy) + a real progress bar bound to the service
        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setVisible(false);
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(250);

        Button syncBtn = new Button("Sync Data");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setDisable(true);

        DataSyncService service = new DataSyncService();
        progressBar.progressProperty().bind(service.progressProperty());

        // Start / restart
        syncBtn.setOnAction(e -> {
            service.restart(); // starts, or restarts after a finish / cancel
            statusLabel.setText("Syncing...");
            spinner.setVisible(true);
            syncBtn.setDisable(true);
            cancelBtn.setDisable(false);
        });

        // Cancel
        cancelBtn.setOnAction(e -> service.cancel());

        // These callbacks run on the JavaFX Application Thread
        service.setOnSucceeded(e -> {
            statusLabel.setText(service.getValue()); // the value call() returned
            spinner.setVisible(false);
            syncBtn.setDisable(false);
            cancelBtn.setDisable(true);
        });

        service.setOnCancelled(e -> {
            statusLabel.setText("Sync Cancelled.");
            spinner.setVisible(false);
            syncBtn.setDisable(false);
            cancelBtn.setDisable(true);
        });

        service.setOnFailed(e -> {
            statusLabel.setText("Sync Failed: " + service.getException().getMessage());
            spinner.setVisible(false);
            syncBtn.setDisable(false);
            cancelBtn.setDisable(true);
        });

        HBox buttons = new HBox(10, syncBtn, cancelBtn);
        buttons.setStyle("-fx-alignment: center;");
        VBox root = new VBox(20, buttons, spinner, progressBar, statusLabel);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Service Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
