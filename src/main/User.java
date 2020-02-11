package main;

import java.util.UUID;

public class User {
    UUID id;
    AccountType type;
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
