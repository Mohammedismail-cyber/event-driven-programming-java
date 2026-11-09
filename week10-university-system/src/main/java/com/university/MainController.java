package com.university;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** Navigation shell + auto-refreshing dashboard (LO3). */
public class MainController {

    @FXML private StackPane centerPane;

    // Dashboard widgets (updated every 5 seconds)
    private final Label totalStudentsLabel = new Label("Total students: ...");
    private final Label lastRefreshLabel = new Label("Last refreshed: -");
    private final Label dbStatusLabel = new Label("");
    private VBox dashboardView;

    @FXML
    public void initialize() {
        Label title = new Label("Welcome to the University Dashboard!");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        totalStudentsLabel.setStyle("-fx-font-size: 18px;");
        lastRefreshLabel.setStyle("-fx-text-fill: gray;");

        dashboardView = new VBox(15, title, totalStudentsLabel, lastRefreshLabel, dbStatusLabel);
        showDashboard();
    }

    @FXML
    private void showDashboard() {
        centerPane.getChildren().setAll(dashboardView);
    }

    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * LO3 - Scheduled task. Runs on the scheduler's background thread:
     * the database query happens there, and ONLY the label updates are sent
     * back to the JavaFX Application Thread with Platform.runLater().
     */
    public void startAutoRefresh(ScheduledExecutorService scheduler) {
        scheduler.scheduleAtFixedRate(this::refreshDashboard, 0, 5, TimeUnit.SECONDS);
    }

    private void refreshDashboard() {
        try {
            int count = StudentRepository.countStudents();
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            Platform.runLater(() -> {
                totalStudentsLabel.setText("Total students: " + count);
                lastRefreshLabel.setText("Last refreshed: " + time);
                dbStatusLabel.setText("");
            });
        } catch (Exception e) {
            // Catch everything: an uncaught exception would silently stop the schedule
            Platform.runLater(() -> {
                dbStatusLabel.setText("Database offline: " + e.getMessage());
                dbStatusLabel.setStyle("-fx-text-fill: red;");
            });
        }
    }
}
