package main;

import java.util.UUID;

public class User {
    public UUID id = UUID.randomUUID();
    public AccountType type;
    String username;
    byte[] password;
    public String name;
    public String emailAddress;
    public String phoneNumber;
    public Specifications specifications;
    public UUID project;

    public enum AccountType {
        Customer("Customer"),
        Developer("Developer"),
        QA("QA");

        String enumLongName;

        AccountType(String enumLongName){
            this.enumLongName = enumLongName;
        }

        public String getName(){
            return enumLongName;
        }
    }
}
