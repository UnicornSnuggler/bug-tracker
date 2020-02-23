package main;

import java.util.Date;
import java.util.UUID;

public class Issue {
    public UUID id = UUID.randomUUID();
    public Date submitted = new Date();
    public Date updated = new Date();
    public UUID reporter;
    public UUID assignee = null;
    public IssueType type;
    public Priority priority = Priority.Low;
    public String title;
    public String description;
    public Status status = Status.Backlog;
    public String devNotes = "";

    public enum IssueType {
        Bug("Bug"),
        Request("Request"),
        Investigation("Investigation"),
        Technical_Debt("Technical Debt");

        String enumLongName;

        IssueType(String enumLongName){
            this.enumLongName = enumLongName;
        }

        public String getName(){
            return enumLongName;
        }
    }

    public enum Priority {
        Low("Low"),
        Medium("Medium"),
        High("High");

        String enumLongName;

        Priority(String enumLongName){
            this.enumLongName = enumLongName;
        }

        public String getName(){
            return enumLongName;
        }
    }

    public enum Status {
        Backlog("Backlog"),
        Ready_for_Development("Ready for Development"),
        In_Development("In Development"),
        Ready_for_Testing("Ready for Testing"),
        Testing("Testing"),
        Ready_for_Deployment("Ready for Deployment"),
        Deployed("Deployed"),
        Archived("Archived");

        String enumLongName;

        Status(String enumLongName){
            this.enumLongName = enumLongName;
        }

        public String getName(){
            return enumLongName;
        }
    }
}
