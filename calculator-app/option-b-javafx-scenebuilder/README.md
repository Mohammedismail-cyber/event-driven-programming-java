# Option B - JavaFX calculator (Scene Builder + FXML)

A Maven project requiring JDK 25 and Maven, using JavaFX 25.

| File | Purpose |
|---|---|
| `src/main/resources/com/calculator/calculator.fxml` | The layout (open it in Scene Builder to drag and drop) |
| `src/main/java/com/calculator/CalculatorController.java` | Event handlers: `handleNumber`, `handleOperator`, `handleEquals`, `handleClear` |
| `src/main/java/com/calculator/App.java` | Loads the FXML and shows the window |

## Run
    mvn clean javafx:run

## Edit the design with Scene Builder
1. Install Scene Builder: https://gluonhq.com/products/scene-builder/
2. File > Open > `calculator.fxml`, drag, resize, restyle, then Save.
3. The controller is already linked (`fx:controller`) and every button already has its `onAction` method.
4. Check what changed in VS Code (it is plain XML).

## Build it yourself from scratch (the drag-and-drop way)
1. Drag a **VBox** (root). Drag a **TextField** into it: `fx:id` = `display`, uncheck Editable.
2. Drag a **GridPane** under it (4 rows, 4 columns, equal sizes).
3. Drag 16 **Buttons** into the cells and give each an `fx:id` and text (btn7, btn8, btn9, btnDivide ...).
4. In the **Code** section set **On Action**: digits -> `handleNumber`, operators -> `handleOperator`, `=` -> `handleEquals`, `C` -> `handleClear`.
5. In the Document panel set the controller: `com.calculator.CalculatorController`.
