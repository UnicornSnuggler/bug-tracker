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
