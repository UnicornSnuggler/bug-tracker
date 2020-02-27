package resources.forms;

import main.TerminalX;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JPanel content;
    private JLabel invalidLabel;

    public LoginForm() {
        setContentPane(content);

        submitButton.addActionListener(actionEvent -> {
            try {
                boolean result = TerminalX.verifyLogin(usernameField.getText(), new String(passwordField.getPassword()));

                if (!result) {
                    invalidLabel.setVisible(true);
                } else {
                    this.dispose();
                }
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        });

        KeyAdapter checkEnter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submitButton.doClick();
                }
            }
        };

        usernameField.addKeyListener(checkEnter);
        passwordField.addKeyListener(checkEnter);
        submitButton.addKeyListener(checkEnter);
    }
}
