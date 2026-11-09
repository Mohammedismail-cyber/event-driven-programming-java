import javax.swing.*;
import java.awt.*;

/**
 * Activity 5 - Interactive Registration Form.
 * Hand-coded Swing version of the NetBeans drag-and-drop form.
 * Uses the same variable names and event handlers as the guide, so it runs in
 * VS Code with plain javac/java. To practise the Matisse version, rebuild the
 * UI in NetBeans and paste the three handler methods from this file.
 */
public class RegistrationForm extends JFrame {

    private JLabel titleLabel, nameLabel, emailLabel, passwordLabel, genderLabel,
            countryLabel, commentsLabel, statusLabel;
    private JTextField nameField, emailField;
    private JPasswordField passwordField;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck, musicCheck, readingCheck;
    private JComboBox<String> countryBox;
    private JTextArea commentsArea;
    private JButton submitButton, clearButton, exitButton;

    public RegistrationForm() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 10, 5, 10);
        gc.anchor = GridBagConstraints.WEST;

        // Title
        titleLabel = new JLabel("Registration Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
        add(titleLabel, gc);
        gc.gridwidth = 1;

        // Name
        nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(30);
        addRow(gc, 1, nameLabel, nameField);

        // Email
        emailLabel = new JLabel("Email:");
        emailField = new JTextField(30);
        addRow(gc, 2, emailLabel, emailField);

        // Password
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(30);
        addRow(gc, 3, passwordLabel, passwordField);

        // Gender
        genderLabel = new JLabel("Gender:");
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);
        addRow(gc, 4, genderLabel, genderPanel);

        // Interests
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        interestPanel.add(sportsCheck);
        interestPanel.add(musicCheck);
        interestPanel.add(readingCheck);
        addRow(gc, 5, new JLabel("Interests:"), interestPanel);

        // Country
        countryLabel = new JLabel("Country:");
        countryBox = new JComboBox<>(new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});
        addRow(gc, 6, countryLabel, countryBox);

        // Comments
        commentsLabel = new JLabel("Comments:");
        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        addRow(gc, 7, commentsLabel, new JScrollPane(commentsArea));

        // Buttons
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(80, 170, 80));
        submitButton.setForeground(Color.WHITE);
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(200, 60, 60));
        exitButton.setForeground(Color.WHITE);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);
        gc.gridx = 0; gc.gridy = 8; gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.CENTER;
        add(buttonPanel, gc);

        // Status
        statusLabel = new JLabel("Status: Ready");
        gc.gridy = 9;
        add(statusLabel, gc);

        // ---- EVENT HANDLERS ----
        submitButton.addActionListener(this::submitButtonActionPerformed);
        clearButton.addActionListener(this::clearButtonActionPerformed);
        exitButton.addActionListener(this::exitButtonActionPerformed);
    }

    private void addRow(GridBagConstraints gc, int row, JComponent label, JComponent field) {
        gc.gridy = row;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx = 0;
        add(label, gc);
        gc.gridx = 1;
        add(field, gc);
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter your name");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Please enter a valid email");
            statusLabel.setForeground(Color.RED);
            return;
        }
        if (password.length() < 6) {
            statusLabel.setText("Error: Password must be at least 6 characters");
            statusLabel.setForeground(Color.RED);
            return;
        }

        // Gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        String country = (String) countryBox.getSelectedItem();
        String comments = commentsArea.getText().trim();

        String message = "Registration Successful!\n\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Gender: " + gender + "\n"
                + "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n"
                + "Country: " + country + "\n"
                + "Comments: " + (comments.isEmpty() ? "None" : comments);

        JOptionPane.showMessageDialog(this, message,
                "Registration Complete", JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
