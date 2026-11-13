package com.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Activity 3 - unidirectional binding: the label follows the text field. */
public class UnidirectionalDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Type your name here...");

        Label greetingLabel = new Label();

        // Extra: shows that a bound property cannot be set by hand
        Label info = new Label();
        Button tryButton = new Button("Try greetingLabel.setText(...)");

        VBox root = new VBox(20, nameField, greetingLabel, tryButton, info);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // ---- UNIDIRECTIONAL BINDING ----
        // While the field is empty show "Hello, Guest", otherwise greet by name.
        greetingLabel.textProperty().bind(
                Bindings.when(nameField.textProperty().isEmpty())
                        .then("Hello, Guest")
                        .otherwise(Bindings.concat("Hello, ", nameField.textProperty(), ", welcome!"))
        );

        tryButton.setOnAction(e -> {
            try {
                greetingLabel.setText("New Text");
            } catch (RuntimeException ex) {
                // "A bound value cannot be set."
                info.setText("Error: " + ex.getMessage());
            }
        });

        Scene scene = new Scene(root, 420, 260);
        primaryStage.setTitle("Unidirectional Binding");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
