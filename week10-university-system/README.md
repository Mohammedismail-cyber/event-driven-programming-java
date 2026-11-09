# Final Project - University Management System (Event-Driven Architecture)

JavaFX 21 + MySQL + Maven. Java 17 or newer.

## 1. Create the database
Open MySQL Workbench, open `database/schema.sql`, and run it (lightning button).

## 2. Give the app your MySQL password
The password is read from an environment variable, so it is never pushed to GitHub.

    $env:UNI_DB_PASSWORD = "your_real_mysql_password"

(Optional: `UNI_DB_USER` if not `root`, and `UNI_DB_URL` if not localhost:3306.)

## 3. Run
    mvn clean javafx:run

or  `.\run.ps1 -Password "your_real_mysql_password"`

## What to try
1. The Dashboard shows "Total students" and refreshes every 5 seconds (LO3).
2. Click **Students**. "Save Student" stays disabled until all 3 fields are filled (binding).
3. Save a student: it is inserted by a background Task, the table refreshes, and the
   UI never freezes (LO2). Saving the same reg number twice shows a red error.
4. The console prints "System Heartbeat..." every 5 seconds.
5. Stop MySQL while the app runs: the dashboard shows "Database offline" (the app does not crash).

## Where each Learning Outcome lives
| LO | Where |
|---|---|
| LO1 EDA principles | UI classes never run SQL; only `DatabaseUtil`, `StudentRepository` and the Tasks do |
| LO2 Producer-Consumer | `LoadStudentsTask`, the save Task in `StudentController` |
| LO3 Scheduled tasks | `App` (ScheduledExecutorService), `MainController.startAutoRefresh` |
| LO4 Event communication | FXML `onAction` events, `saveButton.disableProperty().bind(...)`, Platform.runLater |

## Changes from the original guide
- `StudentView.fxml` imports `javafx.scene.layout.*` (the guide's version is missing the HBox import and would not load).
- `MainView.fxml` uses `prefWidth` and one-line styles (the guide's `-fx-pref-width` is not valid CSS and a line wrap broke `-fx-background-color`).
- The scheduler uses a daemon thread and is shut down in `stop()`. In the guide the app never exits after you close the window.
- The dashboard really auto-refreshes (student count + time) every 5 seconds; the guide only printed a console heartbeat.
- Tasks have `setOnFailed` handlers, so database errors show in the UI.
- Password from an environment variable instead of being typed into the code.
