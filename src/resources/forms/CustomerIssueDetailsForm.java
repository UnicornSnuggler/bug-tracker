package resources.forms;

import java.io.IOException;
import main.Issue;
import main.Project;
import main.TerminalX;
import main.User;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

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

        User reporter = TerminalX.getUserByUUID(issue.reporter);
        Project reporterProject = TerminalX.getProjectByUUID(reporter.project);

        userLabel.setText("Signed in as " + name);

        reporterLabel.setText("<html><u style='color: blue'>" + reporter.name + "</u></html>");
        typeComboBox.setSelectedItem(issue.type.getName());
        idLabel.setText(issue.id.toString());
        titleTextField.setText(issue.title);
        descriptionTextArea.setText(issue.description);
        priorityLabel.setText(issue.priority.getName());
        statusLabel.setText(issue.status.getName());
        assigneeLabel.setText("Unassigned");
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

        if (issue.assignee != null) {
            User assignee = TerminalX.getUserByUUID(issue.assignee);
            Project assigneeProject = TerminalX.getProjectByUUID(assignee.project);

            assigneeLabel.setText("<html><u style='color: blue'>" + assignee.name + "</u></html>");
            assigneeLabel.setToolTipText(
                "<html>" +
                assignee.name + "<br />" +
                assignee.phoneNumber + "<br />" +
                assignee.type.getName() + " (" + assigneeProject.name + ")" + "<br />" +
                "<br />" +
                "OS: " + assignee.specifications.operatingSystem.getName() + "<br />" +
                "Java: " + assignee.specifications.javaVersion + "<br />" +
                "TerminalX: " + assignee.specifications.softwareVersion +
                "</html>"
            );
        };

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
            issue.updated = new Date();

            try {
                TerminalX.updateIssue(issue);
                TerminalX.openMenuForm();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
