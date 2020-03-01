package resources.forms;

import main.Issue;
import main.TerminalX;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EmployeeIssueDetailsForm extends JFrame {
    private Issue issue;
    private JButton logoutButton;
    private JComboBox typeComboBox;
    private JTextArea descriptionTextArea;
    private JButton backButton;
    private JPanel content;
    private JLabel reporterLabel;
    private JLabel idLabel;
    private JLabel updatedLabel;
    private JLabel userLabel;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea notesTextArea;
    private JTextField titleTextField;
    private JComboBox statusComboBox;
    private JComboBox priorityComboBox;
    private JComboBox assigneeComboBox;

    public EmployeeIssueDetailsForm(String name, Issue issue) {
        this.issue = issue;
        content.setMaximumSize(new Dimension(650, 500));
        setContentPane(content);

        userLabel.setText("Signed in as " + name);

        typeComboBox.removeAllItems();
        typeComboBox.addItem("Bug");
        typeComboBox.addItem("Request");
        typeComboBox.addItem("Investigation");
        typeComboBox.addItem("Technical Debt");

        reporterLabel.setText(issue.submitted.toString());
        typeComboBox.setSelectedItem(issue.type);
        idLabel.setText(issue.id.toString());
        titleTextField.setText(issue.title);
        descriptionTextArea.setText(issue.description);
        priorityComboBox.setSelectedItem(issue.priority.toString());
        statusComboBox.setSelectedItem(issue.status.toString());
        assigneeComboBox.setSelectedItem(issue.assignee.toString());
        notesTextArea.setText(issue.devNotes);
        updatedLabel.setText(issue.updated.toString());

        logoutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });

        backButton.addActionListener(actionEvent -> {
            TerminalX.openMenuForm();
        });

        updateButton.addActionListener(actionEvent -> {
            issue.type = Enum.valueOf(Issue.IssueType.class, typeComboBox.getSelectedItem().toString().replace(' ', '_'));
            issue.devNotes = notesTextArea.getText();
            issue.assignee = Enum.valueOf(Issue.Assignee.class, assigneeComboBox.getSelectedItem().toString().replace(' ', '_'));
            issue.status = Enum.valueOf(Issue.Status.class, statusComboBox.getSelectedItem().toString().replace(' ', '_'));
            issue.priority = Enum.valueOf(Issue.Priority.class, priorityComboBox.getSelectedItem().toString().replace(' ', '_'));
            try {
                TerminalX.replaceIssue(issue);
            } catch (IOException e) {
                e.printStackTrace();
            }

            TerminalX.openMenuForm();
        });

    }
}
