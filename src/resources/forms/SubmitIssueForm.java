package resources.forms;

import main.Issue;
import main.TerminalX;

import javax.swing.*;
import java.io.IOException;

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
            if (titleField.getText().isBlank() || descriptionArea.getText().isBlank()) {
                System.out.println("Invalid submission!");
            } else {
                Issue issue = new Issue();
                issue.reporter = TerminalX.verifiedUser.id;
                issue.type = Enum.valueOf(Issue.IssueType.class, issueTypeComboBox.getSelectedItem().toString().replace(' ', '_'));
                issue.title = titleField.getText();
                issue.description = descriptionArea.getText();

                try {
                    TerminalX.addIssue(issue);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TerminalX.openMenu();
            }
        });
        logOutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });
        backButton.addActionListener(actionEvent -> {
            TerminalX.openMenu();
        });
    }
}
