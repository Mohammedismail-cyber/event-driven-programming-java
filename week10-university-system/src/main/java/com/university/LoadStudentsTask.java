package com.university;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * LO2 - Producer/Consumer: a Task that loads students on a BACKGROUND thread
 * and hands the finished list back to the UI thread.
 */
public class LoadStudentsTask extends Task<ObservableList<Student>> {

    // Runs entirely on a background thread
    @Override
    protected ObservableList<Student> call() throws Exception {
        ObservableList<Student> students = FXCollections.observableArrayList();

        String sql = "SELECT student_id, registration_number, first_name, last_name "
                + "FROM students ORDER BY student_id";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("registration_number"),
                        rs.getString("first_name"),
                        rs.getString("last_name")));
            }
        }
        return students;
    }
}
