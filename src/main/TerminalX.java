package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import resources.forms.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;

public class TerminalX {
    private static ArrayList<User> users;

    public static void main (String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(Arrays.toString(Password.hashPassword("password")));

        Gson gson = new Gson();
        users = gson.fromJson(new FileReader("./users.json"), new TypeToken<ArrayList<User>>(){}.getType());

        System.out.println("Loaded the following users:");
        for (User user : users) {
            System.out.println(user.emailAddress);
        }

        displayLogin();
    }

    private static void displayLogin() {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginForm().getContentPane());
        frame.setMinimumSize(new Dimension(300, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static boolean verifyLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        boolean verified = false;

        for (User user : users) {
            if (username.equals(user.username) && Arrays.equals(Password.hashPassword(password), user.password)) {
                verified = true;
                break;
            }
        }

        System.out.println(verified ? "Login successful!" : "Login failed...");

        return verified;
    }
}