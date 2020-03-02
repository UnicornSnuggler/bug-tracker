package resources.forms;

import java.io.IOException;
import main.Issue;
import main.TerminalX;

import javax.swing.*;
import java.awt.*;

public class EmployeeIssueDetailsForm extends JFrame {
    private JButton logoutButton;
    private JComboBox typeComboBox;
    private JTextArea descriptionTextArea;
    private JButton backButton;
    private JPanel content;
    private JLabel reporterLabel;
    private JLabel idLabel;
    private JLabel updatedLabel;
    private JLabel assigneeLabel;
    private JLabel userLabel;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextArea notesTextArea;
    private JTextField titleTextField;
    private JComboBox statusComboBox;
    private JComboBox priorityComboBox;
    private JButton assignButton;

    public EmployeeIssueDetailsForm(String name, Issue issue) {
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
    }
}
