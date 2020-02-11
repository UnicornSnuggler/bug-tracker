package main;

import java.util.UUID;

public class User {
    UUID id = UUID.randomUUID();
    AccountType type;
    String username;
    byte[] password;
    String name;
    String emailAddress;
    String phoneNumber;
    Specifications specifications;
    Project project;

    public enum AccountType {
        Customer,
        Developer,
        QA
    }
}
