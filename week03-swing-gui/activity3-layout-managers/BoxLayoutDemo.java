import javax.swing.*;
import java.awt.*;

// Bonus demo: the fourth layout manager named in the guide (BoxLayout)
public class BoxLayoutDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("BoxLayout Demo (Y_AXIS)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 350);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            for (int i = 1; i <= 5; i++) {
                JButton b = new JButton("Item " + i);
                b.setAlignmentX(Component.LEFT_ALIGNMENT);
                panel.add(b);
                panel.add(Box.createVerticalStrut(10)); // invisible spacer
            }

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
