package resources.forms;

import main.Issue;
import main.TerminalX;

import javax.swing.*;
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

    public MenuForm(String name, ArrayList<Issue> issues) {
        setContentPane(content);
        userLabel.setText("Signed in as " + name);

        DefaultListModel model = new DefaultListModel();
        for (Issue issue : issues) {
            model.addElement(issue.title);
        }
        issueList.setModel(model);

        sortByComboBox.removeAllItems();
        sortByComboBox.addItem("ID");
        sortByComboBox.addItem("Status");
        sortByComboBox.addItem("Name");

        submitIssueButton.addActionListener(actionEvent -> {
            TerminalX.openSubmitIssueForm();
        });
        logOutButton.addActionListener(actionEvent -> {
            TerminalX.logout();
        });
        sortByComboBox.addActionListener(actionEvent -> {
            // Placeholder
        });
        showArchivedCheckBox.addActionListener(actionEvent -> {
            // Placeholder
        });
        issueList.addListSelectionListener(actionEvent -> {
            if (!issueList.isSelectionEmpty())
                viewIssueButton.setEnabled(true);
        });
        viewIssueButton.addActionListener(actionEvent -> {
            TerminalX.openIssueDetailsForm(issueList.getSelectedIndex());
        });
    }
}
