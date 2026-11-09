import javax.swing.*;

// BUG 1 (fixed): the original slept for 5 seconds INSIDE the button handler,
// which runs on the EDT, so the whole window froze.
// FIX: do the long work on a background thread and update the GUI on the EDT.
public class Bug1_BlockingEDT extends JFrame {

    private JLabel statusLabel;
    private JButton processButton;

    public Bug1_BlockingEDT() {
        setTitle("Bug 1 - Blocking EDT (fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());

        statusLabel = new JLabel("Ready");
        processButton = new JButton("Process");

        processButton.addActionListener(e -> {
            statusLabel.setText("Working...");
            processButton.setEnabled(false);

            // Long operation on a BACKGROUND thread
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

                // GUI update back on the EDT
                SwingUtilities.invokeLater(() -> {
                    statusLabel.setText("Done!");
                    processButton.setEnabled(true);
                });
            }).start();
        });

        add(statusLabel);
        add(processButton);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug1_BlockingEDT().setVisible(true);
        });
    }
}
