package resources.forms;

import java.io.IOException;
import main.Issue;
import main.Project;
import main.TerminalX;
import main.User;

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

        User reporter = TerminalX.getUserObj(issue.reporter);
        Project reporterProject = TerminalX.getProjectObj(reporter.project);

        userLabel.setText("Signed in as " + name);

        reporterLabel.setText("<html><u style='color: blue'>" + reporter.name + "</u></html>");
        typeComboBox.setSelectedItem(issue.type.getName());
        idLabel.setText(issue.id.toString());
        titleTextField.setText(issue.title);
        descriptionTextArea.setText(issue.description);
        priorityComboBox.setSelectedItem(issue.priority.getName());
        statusComboBox.setSelectedItem(issue.status.getName());
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
            User assignee = TerminalX.getUserObj(issue.assignee);
            Project assigneeProject = TerminalX.getProjectObj(assignee.project);

            assigneeLabel.setText("<html><u style='color: blue'>" + assignee.name + "</u></html>");

            assigneeLabel.setToolTipText(
                "<html>" +
                assignee.emailAddress + "<br />" +
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
    }
}
