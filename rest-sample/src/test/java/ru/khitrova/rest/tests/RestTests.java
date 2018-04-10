package ru.khitrova.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.khitrova.rest.Model.Issue;

import java.io.IOException;
import java.util.Set;


public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = app.issue().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("newTestIssue");
        int issueId = app.issue().createIssue(newIssue);
        Set<Issue> newIssues = app.issue().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(newIssues, oldIssues);
    }



}
