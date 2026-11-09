package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/** Activity 4 - the Controller (the "C" in MVC) for login.fxml. */
public class LoginController {

    // @FXML injects the component with the matching fx:id from the FXML file
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label statusLabel;

    // Linked to the button through onAction="#handleLoginButtonAction"
    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Error: Fields cannot be empty.");
            statusLabel.setText("Error: Fields cannot be empty.");
            statusLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        System.out.println("Attempting login for: " + username);

        // In a real app, you would check the database here
        if (username.equals("admin") && password.equals("password123")) {
            System.out.println("Login Successful!");
            statusLabel.setText("Login Successful!");
            statusLabel.setStyle("-fx-text-fill: green;");
            usernameField.setText("");
            passwordField.setText("");
        } else {
            System.out.println("Login Failed!");
            statusLabel.setText("Login Failed!");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
