import javax.swing.*;
import java.awt.*;
import java.util.Date;

// BUG 3 (fixed): the original called timeLabel.setText() from a background
// thread. Swing is not thread-safe, so this can cause glitches or crashes.
// FIX: send each label update to the EDT with SwingUtilities.invokeLater().
// Extras: 'running' is volatile, the thread is a daemon, and there is a Stop button.
public class Bug3_WrongThread extends JFrame {

    private JLabel timeLabel;
    private JButton startButton;
    private JButton stopButton;
    private volatile boolean running = false;

    public Bug3_WrongThread() {
        setTitle("Bug 3 - Wrong Thread (fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("Time: ");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16));

        startButton = new JButton("Start Clock");
        stopButton = new JButton("Stop Clock");
        stopButton.setEnabled(false);

        startButton.addActionListener(e -> {
            if (running) return;       // do not start a second thread
            running = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);

            Thread clock = new Thread(() -> {
                while (running) {
                    String text = "Time: " + new Date();

                    // FIX: update the GUI on the EDT
                    SwingUtilities.invokeLater(() -> timeLabel.setText(text));

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            });
            clock.setDaemon(true);
            clock.start();
        });

        stopButton.addActionListener(e -> {
            running = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        add(timeLabel);
        add(startButton);
        add(stopButton);
        setSize(420, 150);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug3_WrongThread().setVisible(true);
        });
    }
}
