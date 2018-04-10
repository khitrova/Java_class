package ru.khitrova.rest.tests;

import org.testng.SkipException;
import ru.khitrova.rest.Model.Issue;
import ru.khitrova.rest.appManager.ApplicationManager;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public static final ApplicationManager app
            = new ApplicationManager();

    boolean isIssueOpen(int issueId) throws IOException {
        String state = app.issueA().getIssueState(issueId);
        return !(state.equals("Resolved") || state.equals("Closed"));

    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
