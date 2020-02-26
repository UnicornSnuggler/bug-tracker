package resources.forms;

import main.Issue;
import main.TerminalX;

import javax.swing.*;
import java.awt.*;

public class CustomerIssueDetailsForm extends JFrame {
    private JPanel content;
    private JComboBox typeComboBox;
    private JButton logoutButton;
    private JTextArea descriptionTextArea;
    private JButton backButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JLabel reporterLabel;
    private JLabel userLabel;
    private JLabel updatedLabel;
    private JLabel priorityLabel;
    private JLabel statusLabel;
    private JLabel assigneeLabel;
    private JLabel idLabel;
    private JTextArea notesTextArea;
    private JTextField titleTextField;

    public CustomerIssueDetailsForm(String name, Issue issue) {
        content.setMaximumSize(new Dimension(650, 500));
        setContentPane(content);

        userLabel.setText("Signed in as " + name);

        reporterLabel.setText(issue.submitted.toString());
        typeComboBox.setSelectedItem(issue.type);
        idLabel.setText(issue.id.toString());
        titleTextField.setText(issue.title);
        descriptionTextArea.setText(issue.description);
        priorityLabel.setText(issue.priority.toString());
        statusLabel.setText(issue.status.toString());
        assigneeLabel.setText(issue.assignee != null ? issue.assignee.toString() : "Unassigned");
        notesTextArea.setText(issue.devNotes);
        updatedLabel.setText(issue.updated.toString());

        logoutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });

        backButton.addActionListener(actionEvent -> {
            TerminalX.openMenuForm();
        });
    }
}
