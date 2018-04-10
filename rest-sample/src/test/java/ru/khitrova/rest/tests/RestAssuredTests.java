package ru.khitrova.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.khitrova.rest.Model.Issue;

import java.io.IOException;
import java.util.Set;


public class RestAssuredTests extends TestBase{

    @BeforeClass
    public void init(){
        RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
    }

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(4);
        Set<Issue> oldIssues = app.issueA().getIssues();
        Issue newIssue = new Issue().withSubject("Test issue").withDescription("newTestIssue");
        int issueId = app.issueA().createIssue(newIssue);
        Set<Issue> newIssues = app.issueA().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        Assert.assertEquals(newIssues, oldIssues);
    }


}
