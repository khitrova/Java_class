package ru.khitrova.rest.appManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import ru.khitrova.rest.Model.Issue;

import java.io.IOException;
import java.util.Set;

public class IssueAssuredHelper {


    private final ApplicationManager app;

    public IssueAssuredHelper(ApplicationManager app) {
        this.app = app;
    }


    public Set<Issue> getIssues() throws IOException {

        String json = RestAssured.get(" http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
        }.getType());
    }

    public int createIssue(Issue newIssue) throws IOException {


        String json =  RestAssured.given()
                .parameter("subject", newIssue.getSubject())
                .parameter("description", newIssue.getDescription())
                .post(" http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();

    }
}
