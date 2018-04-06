package ru.khitrova.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.khitrova.mantis.model.Issue;
import ru.khitrova.mantis.model.Project;

import javax.mail.Session;
import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;
    private Session soap;

    public SoapHelper(ApplicationManager app){

        this.app = app;
        soap = Session.getDefaultInstance(System.getProperties());
    }

    public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {

        MantisConnectPortType mc = getMantisConnect();
        ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("soap.login"), app.getProperty("soap.password"));
        return Arrays.asList(projects).stream()
                .map((p)->new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                    .getMantisConnectPort(new URL(app.getProperty("soap.url")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        String login = app.getProperty("soap.login");
        String pass = app.getProperty("soap.password")
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(login, pass, BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData= new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setCategory(categories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueId = mc.mc_issue_add(login, pass, issueData);
        IssueData newIssueData = mc.mc_issue_get(login, pass, issueId);
        return new Issue().withId(newIssueData.getId().intValue()).withSummary(newIssueData.getSummary())
                .withDescription(newIssueData.getDescription()).withProject(new Project()
                                        .withId(newIssueData.getProject().getId().intValue())
                                        .withName(newIssueData.getProject().getName()));

    }
}
