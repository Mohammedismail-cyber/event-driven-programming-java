package com.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

/** Activity 4 - bidirectional binding: slider and text field stay in sync. */
public class BidirectionalDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label instruction = new Label("Drag the slider or type a number:");
        Slider slider = new Slider(0, 100, 50);
        TextField ageField = new TextField("50");

        VBox root = new VBox(20, instruction, slider, ageField);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // ---- BIDIRECTIONAL BINDING ----
        // A converter is needed: the Slider holds a number, the TextField holds text.
        ageField.textProperty().bindBidirectional(
                slider.valueProperty(),
                new NumberStringConverter()
        );

        Scene scene = new Scene(root, 400, 220);
        primaryStage.setTitle("Bidirectional Binding");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
