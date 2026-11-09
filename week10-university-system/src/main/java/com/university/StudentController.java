package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

/** Handles the events of StudentView.fxml. Database work always runs in Tasks. */
public class StudentController {

    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    // The list the TableView displays
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    // Called automatically when the FXML loads
    @FXML
    public void initialize() {
        // 1. Connect the table columns to the Student properties
        colReg.setCellValueFactory(cell -> cell.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cell -> cell.getValue().firstNameProperty());
        colLast.setCellValueFactory(cell -> cell.getValue().lastNameProperty());

        // 2. Tell the TableView to use our list
        studentTable.setItems(studentList);

        // 3. Binding: Save is disabled while any field is empty
        saveButton.disableProperty().bind(
                regField.textProperty().isEmpty()
                        .or(firstNameField.textProperty().isEmpty())
                        .or(lastNameField.textProperty().isEmpty())
        );

        // 4. Load the initial data from the database
        loadStudents();
    }

    // Triggered when the user clicks "Save Student"
    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText().trim();
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();

        // Background Task to save the data (Producer-Consumer pattern)
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students "
                        + "(registration_number, first_name, last_name, email) VALUES (?, ?, ?, ?)";

                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, fName);
                    stmt.setString(3, lName);
                    stmt.setString(4, reg + "@university.edu"); // generated email
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        // Runs on the UI thread when the background task succeeds
        saveTask.setOnSucceeded(e -> {
            showStatus("Student saved successfully!", false);
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            loadStudents(); // refresh the table
        });

        // Runs on the UI thread if it fails (database down, duplicate reg number...)
        saveTask.setOnFailed(e -> showStatus(
                "Could not save: " + saveTask.getException().getMessage(), true));

        startBackground(saveTask);
    }

    // Loads students from the database in the background
    private void loadStudents() {
        LoadStudentsTask task = new LoadStudentsTask();

        task.setOnSucceeded(e -> {
            studentList.setAll(task.getValue());
            showStatus("Loaded " + studentList.size() + " students from database.", false);
        });

        task.setOnFailed(e -> showStatus(
                "Could not load students: " + task.getException().getMessage(), true));

        startBackground(task);
    }

    private void showStatus(String message, boolean error) {
        statusLabel.setText(message);
        statusLabel.setStyle(error ? "-fx-text-fill: red;" : "-fx-text-fill: blue;");
    }

    private void startBackground(Task<?> task) {
        Thread thread = new Thread(task);
        thread.setDaemon(true); // never keeps the app alive after the window closes
        thread.start();
    }
}
