package resources.forms;

import java.io.IOException;
import main.Issue;
import main.Project;
import main.TerminalX;
import main.User;

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

        User reporter = TerminalX.getUserObj(issue.reporter);
        Project reporterProject = TerminalX.getProjectObj(reporter.project);

        assigneeComboBox.removeAllItems();
        TerminalX.users.forEach(user -> {
            if (user.type != User.AccountType.Customer)
                assigneeComboBox.addItem(user.name);
        });

        if (issue.assignee != null)
            assigneeComboBox.setSelectedItem(TerminalX.getUserByUUID(issue.assignee).name);

        userLabel.setText("Signed in as " + name);

        reporterLabel.setText("<html><u style='color: blue'>" + reporter.name + "</u></html>");
        typeComboBox.setSelectedItem(issue.type.getName());
        idLabel.setText(issue.id.toString());
        titleTextField.setText(issue.title);
        descriptionTextArea.setText(issue.description);
        priorityComboBox.setSelectedItem(issue.priority.getName());
        statusComboBox.setSelectedItem(issue.status.getName());
        notesTextArea.setText(issue.devNotes);
        updatedLabel.setText(issue.updated.toString());

        reporterLabel.setToolTipText(
                "<html>" +
                reporter.emailAddress + "<br />" +
                reporter.phoneNumber + "<br />" +
                reporter.type.getName() + " (" + reporterProject.name + ")" + "<br />" +
                "<br />" +
                "OS: " + reporter.specifications.operatingSystem.getName() + "<br />" +
                "Java: " + reporter.specifications.javaVersion + "<br />" +
                "TerminalX: " + reporter.specifications.softwareVersion + "<br />" +
                "<br />" +
                "Reported On: " + issue.submitted.toString() +
                "</html>"
        );

        logoutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });

        backButton.addActionListener(actionEvent -> {
            TerminalX.openMenuForm();
        });

        updateButton.addActionListener(actionEvent -> {
            issue.title = titleTextField.getText();
            issue.description = descriptionTextArea.getText();
            issue.type = Enum.valueOf(Issue.IssueType.class, typeComboBox.getSelectedItem().toString().replace(' ', '_'));
            issue.devNotes = notesTextArea.getText();
            issue.assignee = TerminalX.getUUIDByName(assigneeComboBox.getSelectedItem().toString());
            issue.status = Enum.valueOf(Issue.Status.class, statusComboBox.getSelectedItem().toString().replace(' ', '_'));
            issue.priority = Enum.valueOf(Issue.Priority.class, priorityComboBox.getSelectedItem().toString().replace(' ', '_'));

            try {
                TerminalX.replaceIssue(issue);
            } catch (IOException e) {
                e.printStackTrace();
            }

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
