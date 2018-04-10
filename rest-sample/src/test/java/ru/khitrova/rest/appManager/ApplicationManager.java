package ru.khitrova.rest.appManager;

public class ApplicationManager {

    private IssueHelper issueHelper;
    private IssueAssuredHelper issueAssuredHelper;

    public IssueHelper issue() {
        if (issueHelper == null) {
            issueHelper = new IssueHelper(this);
        }
        return issueHelper;
    }

    public IssueAssuredHelper issueA() {
        if (issueAssuredHelper == null) {
            issueAssuredHelper = new IssueAssuredHelper(this);
        }
        return issueAssuredHelper;
    }


}
