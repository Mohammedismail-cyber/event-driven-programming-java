package com.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** Activity 5 - Capstone: a form whose validation is done only with bindings. */
public class ReactiveFormApp extends Application {

    private static final int MAX_BIO = 150;

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(15);

        // UI components
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextArea bioField = new TextArea();
        bioField.setPrefRowCount(3);
        bioField.setWrapText(true);

        Label charCountLabel = new Label();
        Label previewLabel = new Label();
        previewLabel.setTextFill(Color.BLUE);
        previewLabel.setWrapText(true);
        previewLabel.setMaxWidth(320);

        Button submitBtn = new Button("Submit Registration");

        // Layout
        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(new Label("Bio:"), 0, 2);
        grid.add(bioField, 1, 2);
        grid.add(charCountLabel, 1, 3);
        grid.add(previewLabel, 1, 4);
        grid.add(submitBtn, 1, 5);

        // ============================================================
        // THE MAGIC OF BINDINGS
        // ============================================================

        // 1. LIVE CHARACTER COUNT
        charCountLabel.textProperty().bind(
                Bindings.length(bioField.textProperty()).asString()
                        .concat(" / " + MAX_BIO + " characters")
        );

        // 2. LIVE PREVIEW
        previewLabel.textProperty().bind(
                Bindings.concat("Hi, I am ", nameField.textProperty(), ". ", bioField.textProperty())
        );

        // 3. VALIDATION: the Submit button is disabled while the form is invalid.
        //    NOTE: the guide used emailField.textProperty().contains("@"), but
        //    StringProperty has no contains() method. createBooleanBinding is the
        //    correct way to build a custom condition.
        var emailMissingAt = Bindings.createBooleanBinding(
                () -> !emailField.getText().contains("@"),
                emailField.textProperty());

        submitBtn.disableProperty().bind(
                Bindings.isEmpty(nameField.textProperty())
                        .or(Bindings.isEmpty(emailField.textProperty()))
                        .or(emailMissingAt)
                        .or(Bindings.length(bioField.textProperty()).greaterThan(MAX_BIO))
        );

        // 4. SUBMIT ACTION (the only click handler in the whole form)
        submitBtn.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Registration Successful");
            alert.setContentText("Welcome, " + nameField.getText() + "!");
            alert.showAndWait();
        });

        Scene scene = new Scene(grid, 520, 450);
        primaryStage.setTitle("Reactive Registration Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
