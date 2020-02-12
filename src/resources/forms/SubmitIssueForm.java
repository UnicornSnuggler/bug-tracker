package resources.forms;

import main.TerminalX;

import javax.swing.*;

public class SubmitIssueForm extends JFrame {
    private JPanel content;
    private JButton logOutButton;
    private JComboBox<String> issueTypeComboBox;
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JButton submitButton;
    private JButton backButton;
    private JLabel userLabel;

    public SubmitIssueForm(String name) {
        setContentPane(content);
        userLabel.setText("Signed in as " + name);

        issueTypeComboBox.removeAllItems();
        issueTypeComboBox.addItem("Bug");
        issueTypeComboBox.addItem("Request");
        issueTypeComboBox.addItem("Investigation");
        issueTypeComboBox.addItem("Technical Debt");

        submitButton.addActionListener(actionEvent -> {
            // Placeholder
        });
        logOutButton.addActionListener(actionEvent -> {
            // Placeholder
        });
        backButton.addActionListener(actionEvent -> {
            TerminalX.openMenu();
        });
        logOutButton.addActionListener(actionEvent -> {
            // Placeholder
        });
    }
}
