package main;

import java.util.Date;
import java.util.UUID;

public class Issue {
    UUID id;
    Date submitted;
    Date updated;
    User reporter;
    User assignee;
    IssueType type;
    Priority priority;
    String title;
    String description;
    Status status;
    String devNotes;

    public enum IssueType {
        Bug,
        Request,
        Investigation,
        Technical_Debt
    }

    public enum Priority {
        Low,
        Medium,
        High
    }

    public enum Status {
        Backlog,
        Ready_for_Development,
        In_Development,
        Ready_for_Testing,
        Testing,
        Ready_for_Deployment,
        Deployed,
        Archived
    }
}
