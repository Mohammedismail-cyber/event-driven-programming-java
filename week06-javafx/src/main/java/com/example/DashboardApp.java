package com.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/** Activity 5 - Capstone: a styled, interactive SaaS-style dashboard. */
public class DashboardApp extends Application {

    private static final String NAVIGATION_STYLE =
            "-fx-background-color: transparent; -fx-alignment: center-left; -fx-font-size: 14px;";
    private static final String SELECTED_NAVIGATION_STYLE =
            "-fx-background-color: #dce6f1; -fx-background-radius: 6; "
                    + "-fx-alignment: center-left; -fx-font-size: 14px; -fx-font-weight: bold;";

    private final BorderPane root = new BorderPane();
    private String displayName = "User";
    private Label accountLabel;
    private Button overviewButton;
    private Button settingsButton;

    @Override
    public void start(Stage primaryStage) {
        HBox topBar = new HBox(15);
        topBar.setPadding(new Insets(15, 20, 15, 20));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-background-color: #2c3e50;");

        Label title = new Label("My SaaS Dashboard");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", 20));

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        accountLabel = new Label(displayName);
        accountLabel.setTextFill(Color.WHITE);
        topBar.getChildren().addAll(title, spacer, accountLabel);

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(15));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #ecf0f1;");

        overviewButton = createNavigationButton("Overview");
        overviewButton.setOnAction(event -> showOverview());
        settingsButton = createNavigationButton("Settings");
        settingsButton.setOnAction(event -> showSettings());
        Button logoutButton = createNavigationButton("Logout");
        logoutButton.setOnAction(event -> confirmLogout(primaryStage));

        VBox sidebarSpacer = new VBox();
        VBox.setVgrow(sidebarSpacer, Priority.ALWAYS);
        sidebar.getChildren().addAll(overviewButton, settingsButton, sidebarSpacer, logoutButton);

        root.setTop(topBar);
        root.setLeft(sidebar);
        showOverview();

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Dashboard");
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createNavigationButton(String text) {
        Button button = new Button(text);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setStyle(NAVIGATION_STYLE);
        return button;
    }

    private void showOverview() {
        overviewButton.setStyle(SELECTED_NAVIGATION_STYLE);
        settingsButton.setStyle(NAVIGATION_STYLE);

        VBox content = new VBox(20);
        content.setPadding(new Insets(30));

        Label welcome = new Label("Welcome back, " + displayName + "!");
        welcome.setFont(Font.font("Arial", 24));

        Label subtitle = new Label("Here's a snapshot of your business.");
        subtitle.setStyle("-fx-text-fill: #667085; -fx-font-size: 14px;");

        HBox cards = new HBox(20);
        cards.getChildren().addAll(
                createCard("Revenue", "$12,450", "#e74c3c"),
                createCard("Users", "1,204", "#3498db"),
                createCard("Orders", "342", "#2ecc71")
        );
        for (javafx.scene.Node card : cards.getChildren()) {
            HBox.setHgrow(card, Priority.ALWAYS);
        }

        content.getChildren().addAll(welcome, subtitle, cards);
        root.setCenter(content);
    }

    private void showSettings() {
        overviewButton.setStyle(NAVIGATION_STYLE);
        settingsButton.setStyle(SELECTED_NAVIGATION_STYLE);

        VBox content = new VBox(15);
        content.setPadding(new Insets(30));

        Label heading = new Label("Settings");
        heading.setFont(Font.font("Arial", 24));

        Label description = new Label("Update the name shown on your dashboard.");
        description.setStyle("-fx-text-fill: #667085; -fx-font-size: 14px;");

        Label nameLabel = new Label("Display name (this session)");
        TextField nameField = new TextField(displayName);
        nameField.setPromptText("Enter a display name");
        nameField.setMaxWidth(320);

        Label status = new Label();
        Button saveButton = new Button("Save changes");
        saveButton.setDefaultButton(true);
        saveButton.setOnAction(event -> {
            String newName = nameField.getText().trim();
            if (newName.isEmpty()) {
                status.setText("Display name cannot be empty.");
                status.setStyle("-fx-text-fill: #c0392b;");
                nameField.requestFocus();
                return;
            }

            displayName = newName;
            accountLabel.setText(displayName);
            status.setText("Display name updated.");
            status.setStyle("-fx-text-fill: #218c54;");
        });

        content.getChildren().addAll(heading, description, nameLabel, nameField, saveButton, status);
        root.setCenter(content);
    }

    private void confirmLogout(Stage stage) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Log out");
        confirmation.setHeaderText("Close the dashboard?");
        confirmation.setContentText("Are you sure you want to exit?");
        confirmation.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> stage.close());
    }

    private VBox createCard(String title, String value, String color) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setPrefWidth(180);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10;");

        Label titleLabel = new Label(title);
        titleLabel.setTextFill(Color.WHITE);

        Label valueLabel = new Label(value);
        valueLabel.setTextFill(Color.WHITE);
        valueLabel.setFont(Font.font("Arial", 28));

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
