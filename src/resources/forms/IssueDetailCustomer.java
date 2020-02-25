package resources.forms;

import main.Issue;
import main.TerminalX;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IssueDetailCustomer extends JFrame {
    private JPanel IDCpanel;
    private JComboBox ComboIssue;
    private JTextField IssueID;
    private JButton logoutButton;
    private JTextArea textArea1;
    private JButton backButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JTextPane textPane1;
    private JLabel SubmittedDate;
    private JLabel SignedAsName;
    private JLabel UpdateNameDate;
    private JLabel PriorityLabel;


    public IssueDetailCustomer() {
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                TerminalX.logout();
            }
        });
    }
}
