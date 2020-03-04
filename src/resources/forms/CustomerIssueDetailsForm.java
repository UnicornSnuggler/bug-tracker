package resources.forms;

import java.io.IOException;
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
        typeComboBox.setSelectedItem(issue.type.getName());
        idLabel.setText(issue.id.toString());
        titleTextField.setText(issue.title);
        descriptionTextArea.setText(issue.description);
        priorityLabel.setText(issue.priority.getName());
        statusLabel.setText(issue.status.getName());
        assigneeLabel.setText(issue.assignee != null ? issue.assignee.toString() : "Unassigned");
        notesTextArea.setText(issue.devNotes);
        updatedLabel.setText(issue.updated.toString());

        logoutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });

        backButton.addActionListener(actionEvent -> {
            TerminalX.openMenuForm();
        });

        deleteButton.addActionListener(actionEvent -> {
            try {
                TerminalX.deleteIssue(issue);
                TerminalX.openMenuForm();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        updateButton.addActionListener(actionEvent -> {
            issue.type = Enum.valueOf(Issue.IssueType.class, typeComboBox.getSelectedItem().toString().replace(' ', '_'));
            issue.title = titleTextField.getText();
            issue.description = descriptionTextArea.getText();

            try {
                TerminalX.updateIssue(issue);
                TerminalX.openMenuForm();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
