package com.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/** The controller for calculator.fxml: all the button events are handled here. */
public class CalculatorController {

    @FXML private TextField display;

    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    // Reads the display as a number. Returns 0 if it shows "Error".
    private double readDisplay() {
        try {
            return Double.parseDouble(display.getText());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    // Linked to the digit buttons with onAction="#handleNumber"
    @FXML
    private void handleNumber(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        String digit = btn.getText();

        if (startNewNumber) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            display.setText(display.getText() + digit);
        }
    }

    // Linked to + - * /
    @FXML
    private void handleOperator(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        firstOperand = readDisplay();
        operator = btn.getText();
        startNewNumber = true;
    }

    // Linked to =
    @FXML
    private void handleEquals(javafx.event.ActionEvent event) {
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

    // Linked to C
    @FXML
    private void handleClear(javafx.event.ActionEvent event) {
        display.setText("0");
        firstOperand = 0;
        operator = "";
        startNewNumber = true;
    }
}
