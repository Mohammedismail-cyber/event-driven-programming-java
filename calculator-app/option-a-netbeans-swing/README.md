# Option A - Swing calculator (NetBeans / Matisse style)

| File | Purpose |
|---|---|
| `CalculatorFrame.java` | Runnable hand-written version with the same variable and handler names as the NetBeans guide |
| `handlers-to-paste.txt` | The four event handlers to paste into your own NetBeans form |

## Run it (no NetBeans needed)
    javac -encoding UTF-8 CalculatorFrame.java
    java CalculatorFrame

## Build it visually in NetBeans (the drag-and-drop way)
1. File > New Project > Java with Ant > Java Application, name `CalculatorApp`, untick "Create Main Class".
2. Right-click the project > New > JFrame Form..., class name `CalculatorFrame`.
3. Properties: title `Event-Driven Calculator`, defaultCloseOperation `EXIT_ON_CLOSE`.
4. Drag a **Text Field** to the top: variable name `display`, text `0`, horizontalAlignment `RIGHT`, editable off, font Arial Bold 28, background white.
5. Drag a **Panel** below it: variable name `buttonPanel`, layout **Grid Layout** (4 rows, 4 columns, gaps 5).
6. Drag 16 **Buttons** into the grid and set variable name, text and font (Arial Bold 20):

   | Row | 1 | 2 | 3 | 4 |
   |---|---|---|---|---|
   | 1 | btn7 | btn8 | btn9 | btnDivide (/) |
   | 2 | btn4 | btn5 | btn6 | btnMultiply (*) |
   | 3 | btn1 | btn2 | btn3 | btnSubtract (-) |
   | 4 | btn0 | btnClear (C) | btnEquals (=) | btnAdd (+) |

7. Colours: `btnEquals` background (100, 200, 100); `btnClear` background (255, 150, 150).
8. Switch to **Source** view and paste the code from `handlers-to-paste.txt`.
9. Wire each button to its handler (see the bottom of that file), then press **F6**.

> Do not edit the grey "Generated Code" block: NetBeans rewrites it.
