package ru.khitrova.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.mantis.appmanager.HttpSession;
import ru.khitrova.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PasswordChangedTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException, MessagingException {
        String email = "test@localhost.localdomain";
        String password = "newpassword";

        app.admin().loginAsAdmin();
        String name = app.admin().getUserName();
        app.admin().resetUserPassword();

        List<MailMessage> mailMessages = app.mail().wailForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.admin().logout();
        app.profile().confirmLink(confirmationLink, password);

        HttpSession session = app.newSession();
        assertTrue(session.login(name, password));
        assertTrue(session.isLoggedInAs(name));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
