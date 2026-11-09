# Week 7 - Event Handling in JavaFX

A Maven project (JavaFX 21, Java 17+). Open this folder in VS Code.

| Activity | Class | What it shows |
|---|---|---|
| 1. Filters vs handlers | `EventPropagationDemo` | Capturing (filter) then bubbling (handler) order |
| 2. Mouse and key events | `MouseKeyDemo` | Mouse X/Y tracking, arrow keys move a circle |
| 3. Drag and drop | `DragDropDemo` | Dragboard, drag over/entered/exited/dropped |
| 4. Custom events | `LoginSuccessEvent` + `CustomEventDemo` | Fire and listen for your own event |

## Requirements
JDK 17+ (`java -version`) and Maven (`mvn -version`).

## Run
    mvn clean javafx:run                                         (runs EventPropagationDemo)
    mvn clean javafx:run "-Djavafx.mainClass=com.example.MouseKeyDemo"
    mvn clean javafx:run "-Djavafx.mainClass=com.example.DragDropDemo"
    mvn clean javafx:run "-Djavafx.mainClass=com.example.CustomEventDemo"

Or use the helper:  `.\run.ps1 DragDropDemo`
Keep the quotes around `-Djavafx.mainClass=...` in PowerShell.

## What to check
1. EventPropagationDemo: click the button; the console prints
   `1. [FILTER]`, `2. [HANDLER]` (button), `3. [HANDLER]` (root), in that order.
   Uncomment `e.consume();` in the filter and the button never gets the click.
2. MouseKeyDemo: move the mouse (coordinates update); click the window, then
   use the arrow keys.
3. DragDropDemo: drag "Drag Me" over the grey box (turns green) and release.
   Press Reset to try again.
4. CustomEventDemo: type a name and click Login; the label and console update.

## Notes on changes from the guide
- Emoji removed from "Drag Me" (can render as a box on Windows).
- The broken `-fx text-fill` style (a line wrap in the guide) is fixed to `-fx-text-fill`.
- MouseKeyDemo: clicking the window also gives it focus, and the bounds use constants.
- DragDropDemo: added a Reset button so you can repeat the drop.
