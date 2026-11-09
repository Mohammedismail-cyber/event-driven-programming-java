import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// BUG 4 (fixed): "Remove All" removed the buttons from the screen but the
// createdButtons list still held references to them, so they could never be
// garbage collected (a memory leak).
// FIX: also clear the list.
public class Bug4_MemoryLeak extends JFrame {

    private List<JButton> createdButtons = new ArrayList<>();
    private JPanel buttonPanel;

    public Bug4_MemoryLeak() {
        setTitle("Bug 4 - Memory Leak (fixed)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        buttonPanel = new JPanel(new FlowLayout());

        JButton addButton = new JButton("Add Button");
        addButton.addActionListener(e -> {
            JButton newButton = new JButton("Button " + createdButtons.size());
            createdButtons.add(newButton);
            buttonPanel.add(newButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        JButton removeButton = new JButton("Remove All");
        removeButton.addActionListener(e -> {
            buttonPanel.removeAll();
            createdButtons.clear();   // FIX: release the references
            buttonPanel.revalidate();
            buttonPanel.repaint();
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(addButton);
        controlPanel.add(removeButton);

        add(buttonPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        setSize(500, 300);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Bug4_MemoryLeak().setVisible(true);
        });
    }
}
