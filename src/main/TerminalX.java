package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import resources.forms.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class TerminalX {
    private static ArrayList<User> users;
    private static ArrayList<Project> projects;
    private static ArrayList<Issue> issues;
    public static User verifiedUser;
    private static JFrame screen;
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main (String[] args) throws IOException {
        users = gson.fromJson(new FileReader("./users.json"), new TypeToken<ArrayList<User>>(){}.getType());
        projects = gson.fromJson(new FileReader("./projects.json"), new TypeToken<ArrayList<Project>>(){}.getType());
        issues = gson.fromJson(new FileReader("./issues.json"), new TypeToken<ArrayList<Issue>>(){}.getType());

        System.out.println("Loaded the following users:");
        for (User user : users) {
            System.out.println(user.emailAddress + " (" + user.type.getName() + ")");
        }

        displayLogin();
    }

    public static void addIssue(Issue issue) throws IOException {
        issues.add(issue);

        FileWriter writer = new FileWriter("./issues.json");
        gson.toJson(issues, writer);
        writer.flush();
        writer.close();
    }

    public static void deleteIssue(Issue issue) throws IOException {
        issues.remove(issue);

        FileWriter writer = new FileWriter("./issues.json");
        gson.toJson(issues, writer);
        writer.flush();
        writer.close();
    }

    public static void updateIssue(Issue issue) throws IOException {
        FileWriter writer = new FileWriter("./issues.json");
        gson.toJson(issues, writer);
        writer.flush();
        writer.close();
    }

    private static ArrayList<Issue> getIssues() {
        ArrayList<Issue> userIssues;

        if (verifiedUser.type == User.AccountType.Customer) {
            userIssues = issues.stream().filter(issue -> issue.reporter.equals(verifiedUser.id)).collect(Collectors.toCollection(ArrayList::new));
        } else {
            userIssues = issues;
        }

        return userIssues;
    }

    private static void displayLogin() {
        screen = new JFrame("Login");
        screen.setContentPane(new LoginForm().getContentPane());
        screen.setMinimumSize(new Dimension(300, 200));
        screen.setMaximumSize(new Dimension(300, 200));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    private static void displayMenu() {
        screen = new JFrame("Main Menu");
        screen.setContentPane(new MenuForm(verifiedUser.name, getIssues()).getContentPane());
        screen.setMinimumSize(new Dimension(650, 500));
        screen.setMaximumSize(new Dimension(650, 500));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    private static void displaySubmitIssue() {
        screen = new JFrame("Submit Issue");
        screen.setContentPane(new SubmitIssueForm(verifiedUser.name).getContentPane());
        screen.setMinimumSize(new Dimension(650, 500));
        screen.setMaximumSize(new Dimension(650, 500));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    private static void displayIssueDetails(int index) {
        Issue issue = getIssues().get(index);
        Container content;

        if (verifiedUser.type == User.AccountType.Customer) {
            content = new CustomerIssueDetailsForm(verifiedUser.name, issue).getContentPane();
        } else {
            content = new EmployeeIssueDetailsForm(verifiedUser.name, issue).getContentPane();
        }

        screen = new JFrame("Issue Details");
        screen.setContentPane(content);
        screen.setMinimumSize(new Dimension(650, 500));
        screen.setMaximumSize(new Dimension(650, 500));
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.pack();
        screen.setVisible(true);
    }

    public static Project getProjectObj(UUID projectUUID) {
        Project ret = null;

        for (Project project : projects) {
            if (projectUUID.equals(project.id)) {
                ret = project;
                break;
            }
        }
        return ret;
    }

    public static User getUserObj(UUID userUUID) {
        User ret = null;

        for (User user : users) {
            if (userUUID.equals(user.id)) {
                ret = user;
                break;
            }
        }
        return ret;
    }

    public static void logout() {
        verifiedUser = null;
        screen.dispose();
        displayLogin();
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

    public static void openIssueDetailsForm(int index) {
        screen.dispose();
        displayIssueDetails(index);
    }

    public static void openMenuForm() {
        screen.dispose();
        displayMenu();
    }
}