package com.university;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Application {

    private ScheduledExecutorService scheduler;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 1. Load the main shell
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();

        // 2. Scene and Stage
        primaryStage.setTitle("University Management System");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        // 3. LO3: scheduler with ONE daemon thread (so closing the window ends the app)
        scheduler = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r, "scheduler-thread");
            t.setDaemon(true);
            return t;
        });

        // Dashboard auto-refresh: every 5 seconds
        controller.startAutoRefresh(scheduler);

        // Heartbeat: prints to the console every 5 seconds
        scheduler.scheduleAtFixedRate(
                () -> System.out.println("System Heartbeat: checking for updates at "
                        + new java.util.Date()),
                0, 5, TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        if (scheduler != null) {
            scheduler.shutdownNow(); // stop the scheduled tasks on exit
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
