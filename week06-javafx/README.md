# Week 6 - Introduction to Modern JavaFX

A Maven project (JavaFX 21, Java 17+). Open this folder in VS Code.

| Activity | Class / file | What it shows |
|---|---|---|
| 1. First window | `App` | Stage, Scene, Node; prints "JavaFX Application Thread" |
| 2. Layouts | `LayoutDemo` | HBox, VBox, GridPane, BorderPane |
| 3. Scene Builder + FXML | `src/main/resources/com/example/login.fxml` | Login form designed visually |
| 4. Controller | `FXMLApp` + `LoginController` | @FXML injection, onAction handler |
| 5. Capstone | `DashboardApp` | Interactive overview, settings, and stat cards |

## Requirements
- JDK 17 or newer (check with `java -version`)
- Maven (check with `mvn -version`)
- Scene Builder (optional): https://gluonhq.com/products/scene-builder/

## Run
    mvn clean javafx:run                                        (runs App)
    mvn clean javafx:run "-Djavafx.mainClass=com.example.LayoutDemo"
    mvn clean javafx:run "-Djavafx.mainClass=com.example.FXMLApp"
    mvn clean javafx:run "-Djavafx.mainClass=com.example.DashboardApp"

Or use the helper:  `.\run.ps1 DashboardApp`

Keep the quotes around `-Djavafx.mainClass=...` in PowerShell, otherwise the
dot in the name breaks the command.

## Test the login (Activity 4)
- Empty fields -> error message
- admin / password123 -> "Login Successful!"
- anything else -> "Login Failed!"

## Notes on changes from the guide
- The pom.xml reads the main class from `${javafx.mainClass}`, so the
  `-Djavafx.mainClass=...` command from the guide really works. A hard-coded
  mainClass would ignore it.
- login.fxml imports `javafx.geometry.Insets` (needed for the padding).
- The login result also shows in a label on screen, not only in the console.
- Sidebar buttons use plain text instead of emoji (they render as boxes on
  some Windows setups).
- The dashboard's Overview and Settings buttons switch pages; the display name
  setting applies for the current session, and Logout asks for confirmation.
