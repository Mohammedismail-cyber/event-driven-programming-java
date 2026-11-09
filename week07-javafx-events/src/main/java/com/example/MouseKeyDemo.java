package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/** Activity 2 - mouse position tracking and arrow-key movement. */
public class MouseKeyDemo extends Application {

    private static final double WIDTH = 500;
    private static final double HEIGHT = 400;
    private static final double RADIUS = 20;
    private static final double STEP = 15; // how far to move per key press

    private double x = 250;
    private double y = 200;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.setStyle("-fx-background-color: #f4f4f4;");

        // The player character
        Circle player = new Circle(x, y, RADIUS, Color.BLUE);

        // A text label to show mouse coordinates
        Text mouseCoords = new Text(10, 20, "Mouse Coordinates: X=0, Y=0");
        root.getChildren().addAll(player, mouseCoords);

        // ---- MOUSE EVENTS ----
        root.setOnMouseMoved(e -> {
            mouseCoords.setText(String.format("Mouse Coordinates: X=%.0f, Y=%.0f",
                    e.getX(), e.getY()));
        });

        // Clicking the pane gives it keyboard focus
        root.setOnMousePressed(e -> root.requestFocus());

        // ---- KEY EVENTS ----
        // Key events need focus, so the pane must be focusable.
        root.setFocusTraversable(true);
        root.setOnKeyPressed(e -> {
            KeyCode key = e.getCode();
            if (key == KeyCode.UP)    y -= STEP;
            if (key == KeyCode.DOWN)  y += STEP;
            if (key == KeyCode.LEFT)  x -= STEP;
            if (key == KeyCode.RIGHT) x += STEP;

            // Keep the circle inside the window bounds
            x = Math.max(RADIUS, Math.min(x, WIDTH - RADIUS));
            y = Math.max(RADIUS, Math.min(y, HEIGHT - RADIUS));

            player.setCenterX(x);
            player.setCenterY(y);
            e.consume();
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Make sure the pane has focus as soon as the app starts
        primaryStage.setOnShown(e -> root.requestFocus());

        primaryStage.setTitle("Mouse and Key Events");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
