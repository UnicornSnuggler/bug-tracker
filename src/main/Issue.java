package main;

import java.util.Date;
import java.util.UUID;

public class Issue {
    public UUID id = UUID.randomUUID();
    public Date submitted;
    public Date updated;
    public UUID reporter;
    public UUID assignee;
    public IssueType type;
    public Priority priority;
    public String title;
    public String description;
    public Status status;
    public String devNotes;

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
