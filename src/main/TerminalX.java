package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import resources.forms.LoginForm;
import resources.forms.MenuForm;
import resources.forms.SubmitIssueForm;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;

public class TerminalX {
    private static ArrayList<User> users;
    private static ArrayList<Project> projects;
    private static ArrayList<Issue> issues;
    private static User verifiedUser;
    private static JFrame screen;

    public static void main (String[] args) throws IOException {
        Gson gson = new Gson();
        users = gson.fromJson(new FileReader("./users.json"), new TypeToken<ArrayList<User>>(){}.getType());
        projects = gson.fromJson(new FileReader("./projects.json"), new TypeToken<ArrayList<Project>>(){}.getType());
        issues = gson.fromJson(new FileReader("./issues.json"), new TypeToken<ArrayList<Issue>>(){}.getType());

        System.out.println("Loaded the following users:");
        for (User user : users) {
            System.out.println(user.emailAddress);
        }

        displayLogin();
    }

    private static void displayLogin() {
        screen = new JFrame("Login");
        screen.setContentPane(new LoginForm().getContentPane());
        screen.setMinimumSize(new Dimension(300, 200));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    private static void displayMenu() {
        ArrayList<Issue> issues = new ArrayList<>();

        screen = new JFrame("Main Menu");
        screen.setContentPane(new MenuForm(verifiedUser.name, issues).getContentPane());
        screen.setMinimumSize(new Dimension(650, 500));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    private static void displaySubmitIssue() {
        screen = new JFrame("Submit Issue");
        screen.setContentPane(new SubmitIssueForm(verifiedUser.name).getContentPane());
        screen.setMinimumSize(new Dimension(650, 500));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    public static boolean verifyLogin(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        for (User user : users) {
            if (username.equals(user.username) && Arrays.equals(Hasher.hash(password), user.password)) {
                verifiedUser = user;
                break;
            }
        }

        if (verifiedUser != null) {
            System.out.println(verifiedUser.name + " successfully logged in!");
            screen.dispose();
            displayMenu();
            return true;
        } else {
            System.out.println("Invalid credentials...");
            return false;
        }
    }

    public static void openSubmitIssueForm() {
        screen.dispose();
        displaySubmitIssue();
    }

    public static void openMenu() {
        screen.dispose();
        displayMenu();
    }
}