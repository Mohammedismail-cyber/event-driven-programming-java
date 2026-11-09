import javax.swing.*;
import java.awt.*;

// BUG 2 (fixed): the original never registered an ActionListener, so clicking
// the button did nothing.
// FIX: addActionListener(...) that increments the count and updates the label.
public class Bug2_MissingListener extends JFrame {

    private JLabel countLabel;
    private JButton incrementButton;
    private int count = 0;

    public Bug2_MissingListener() {
        setTitle("Bug 2 - Missing Listener (fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        countLabel = new JLabel("Count: 0");
        countLabel.setFont(new Font("Arial", Font.BOLD, 20));

        incrementButton = new JButton("Increment");

        // FIX: register a listener
        incrementButton.addActionListener(e -> {
            count++;
            countLabel.setText("Count: " + count);
        });

        add(countLabel);
        add(incrementButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug2_MissingListener().setVisible(true);
        });
    }
}
