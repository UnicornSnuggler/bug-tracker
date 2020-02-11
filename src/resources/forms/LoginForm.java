package resources.forms;

import main.TerminalX;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JPanel content;

    public LoginForm() {
        setContentPane(content);
        submitButton.addActionListener(actionEvent -> {
            try {
                TerminalX.verifyLogin(usernameField.getText(), new String(passwordField.getPassword()));
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });
    }
}
