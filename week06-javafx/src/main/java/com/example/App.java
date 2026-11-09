package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Activity 1 - First JavaFX window, built entirely in code.
 * Stage (window) -> Scene (content) -> Nodes (components).
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        // 1. Create Nodes (components)
        Label titleLabel = new Label("My First JavaFX App");
        Button clickButton = new Button("Click Me!");

        // Add an action to the button
        clickButton.setOnAction(e -> {
            titleLabel.setText("Button was clicked!");
            System.out.println("Button clicked on: " + Thread.currentThread().getName());
        });

        // 2. Create a layout pane and add nodes to it
        VBox root = new VBox(15); // 15 = spacing between elements
        root.getChildren().addAll(titleLabel, clickButton);

        // Center the elements
        root.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-background-color: #f0f0f0;");

        // 3. Create a Scene and attach the root layout
        Scene scene = new Scene(root, 400, 300);

        // 4. Set the Scene on the Stage (window)
        primaryStage.setTitle("JavaFX Programmatic App");
        primaryStage.setScene(scene);

        // 5. Show the window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
