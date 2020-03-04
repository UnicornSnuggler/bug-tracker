package resources.forms;


import main.Issue;
import main.TerminalX;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MenuForm extends JFrame {
    private JButton submitIssueButton;
    private JButton logOutButton;
    private JComboBox<String> sortByComboBox;
    private JCheckBox showArchivedCheckBox;
    private JPanel content;
    private JButton viewIssueButton;
    private JLabel userLabel;
    private JList issueList;

    public void drawIssues(ArrayList<Issue> issues) {
        DefaultListModel model = new DefaultListModel();

        for (Issue issue : issues) {
            model.addElement(TerminalX.prettifyUUID(issue.id) + " " +
                    String.format("%21s", "(" + issue.status.getName() + ")") + " " +
                    issue.title);
        }

        issueList.setModel(model);
    }

    public MenuForm(String name, ArrayList<Issue> issues) {
        setContentPane(content);
        userLabel.setText("Signed in as " + name);

        drawIssues(issues);

        submitIssueButton.addActionListener(actionEvent -> {
            TerminalX.openSubmitIssueForm();
        });
        logOutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });
        sortByComboBox.addActionListener(actionEvent -> {
            TerminalX.SortMethod method = TerminalX.SortMethod.valueOf(sortByComboBox.getSelectedItem().toString());
            System.out.println("Sorting method: " + method.toString());
            drawIssues(TerminalX.getIssues(method));
        });
        showArchivedCheckBox.addActionListener(actionEvent -> {
            // Placeholder
        });
        issueList.addListSelectionListener(actionEvent -> {
            if (!issueList.isSelectionEmpty())
                viewIssueButton.setEnabled(true);
        });
        issueList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    TerminalX.openIssueDetailsForm(issueList.getSelectedIndex());
                }
            }
        });
        viewIssueButton.addActionListener(actionEvent -> {
            TerminalX.openIssueDetailsForm(issueList.getSelectedIndex());
        });
    }
}
