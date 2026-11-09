import javax.swing.*;
import java.awt.*;

/**
 * Option A - the NetBeans (Matisse) version, written by hand.
 *
 * NetBeans' visual designer generates a long initComponents() method and a
 * .form file, which cannot be created without NetBeans. This class is the
 * equivalent: it uses the SAME variable names (display, buttonPanel, btn7 ...)
 * and the SAME handler names (btnNumberActionPerformed, btnOperatorActionPerformed,
 * btnEqualsActionPerformed, btnClearActionPerformed) as the guide, so you can:
 *   - run it as it is, or
 *   - rebuild the UI in NetBeans and paste the four handlers from
 *     handlers-to-paste.txt into your own CalculatorFrame.
 */
public class CalculatorFrame extends JFrame {

    // ---- State (instance variables at the top of the class) ----
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    // ---- Components (same names as in the NetBeans guide) ----
    private JTextField display;
    private JPanel buttonPanel;
    private JButton btn7, btn8, btn9, btnDivide;
    private JButton btn4, btn5, btn6, btnMultiply;
    private JButton btn1, btn2, btn3, btnSubtract;
    private JButton btn0, btnClear, btnEquals, btnAdd;

    public CalculatorFrame() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Event-Driven Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Display: Text 0, aligned RIGHT, not editable, Arial Bold 28, white
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setBackground(Color.WHITE);
        add(display, BorderLayout.NORTH);

        // Panel with a 4 x 4 Grid Layout and 5 px gaps
        buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Row 1
        btn7 = newButton("7");        btn8 = newButton("8");
        btn9 = newButton("9");        btnDivide = newButton("/");
        // Row 2
        btn4 = newButton("4");        btn5 = newButton("5");
        btn6 = newButton("6");        btnMultiply = newButton("*");
        // Row 3
        btn1 = newButton("1");        btn2 = newButton("2");
        btn3 = newButton("3");        btnSubtract = newButton("-");
        // Row 4
        btn0 = newButton("0");        btnClear = newButton("C");
        btnEquals = newButton("=");   btnAdd = newButton("+");

        // Colours: = is green, C is red
        btnEquals.setBackground(new Color(100, 200, 100));
        btnClear.setBackground(new Color(255, 150, 150));

        // ---- Wire the events (what Events > Action > actionPerformed does) ----
        for (JButton b : new JButton[]{btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9}) {
            b.addActionListener(this::btnNumberActionPerformed);
        }
        for (JButton b : new JButton[]{btnDivide, btnMultiply, btnSubtract, btnAdd}) {
            b.addActionListener(this::btnOperatorActionPerformed);
        }
        btnEquals.addActionListener(this::btnEqualsActionPerformed);
        btnClear.addActionListener(this::btnClearActionPerformed);

        // Add to the grid in order, row by row
        JButton[] order = {
            btn7, btn8, btn9, btnDivide,
            btn4, btn5, btn6, btnMultiply,
            btn1, btn2, btn3, btnSubtract,
            btn0, btnClear, btnEquals, btnAdd
        };
        for (JButton b : order) {
            buttonPanel.add(b);
        }
        add(buttonPanel, BorderLayout.CENTER);

        setSize(350, 450);
        setLocationRelativeTo(null);
    }

    private JButton newButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("Arial", Font.BOLD, 20));
        return b;
    }

    // Reads the display as a number. Returns 0 if it shows "Error".
    private double readDisplay() {
        try {
            return Double.parseDouble(display.getText());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    // ================= EVENT HANDLERS =================

    // Handler for NUMBER buttons (7, 8, 9, 4, 5, 6, 1, 2, 3, 0)
    private void btnNumberActionPerformed(java.awt.event.ActionEvent evt) {
        JButton clickedButton = (JButton) evt.getSource();
        String digit = clickedButton.getText();

        if (startNewNumber) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    // Handler for OPERATOR buttons (+, -, *, /)
    private void btnOperatorActionPerformed(java.awt.event.ActionEvent evt) {
        JButton clickedButton = (JButton) evt.getSource();
        firstOperand = readDisplay();
        operator = clickedButton.getText();
        startNewNumber = true;
    }

    // Handler for EQUALS button (=)
    private void btnEqualsActionPerformed(java.awt.event.ActionEvent evt) {
        double secondOperand = readDisplay();
        double result = 0;

        switch (operator) {
            case "+": result = firstOperand + secondOperand; break;
            case "-": result = firstOperand - secondOperand; break;
            case "*": result = firstOperand * secondOperand; break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    display.setText("Error");
                    startNewNumber = true;
                    return;
                }
                break;
            default:
                return;
        }

        display.setText(String.valueOf(result));
        startNewNumber = true;
    }

    // Handler for CLEAR button (C)
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        display.setText("0");
        firstOperand = 0;
        operator = "";
        startNewNumber = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame().setVisible(true));
    }
}
