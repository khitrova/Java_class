package ru.khitrova.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

   /* @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }*/

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost",now);
        String username = String.format("user%s", now);
        String password = "password";
        app.james().createUser(username, password);
        app.registration().start(username, email);
        //List<MailMessage> mailMessages = app.mail().wailForMail(2, 10000);
        List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(username, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

/*    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
    app.mail().stop();
    }*/
}
