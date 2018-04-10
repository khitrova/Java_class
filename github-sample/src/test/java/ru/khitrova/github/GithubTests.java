package ru.khitrova.github;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub(" fe8993dbac471365bd2412aecf2cf3e2b61b7a46");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("khitrova", "Java_class")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String >().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
