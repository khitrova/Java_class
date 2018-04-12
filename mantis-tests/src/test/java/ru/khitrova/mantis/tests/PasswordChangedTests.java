package ru.khitrova.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.mantis.appmanager.HttpSession;
import ru.khitrova.mantis.model.MailMessage;
import ru.khitrova.mantis.model.UsersData;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
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
        String name = findLoginFromDb(email);
        app.admin().loginAsAdmin();
       // String name = app.admin().getUserName();
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

    public String findLoginFromDb(String email) {
        Connection conn = null;
        String username= null;
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from mantis_user_table where email  LIKE '" + email+"'");
            UsersData users = new UsersData();

            while (rs.next()) {
                username = rs.getString("username");
                users.add(new UsersData().withEmail(rs.getString("username")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(users);




        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return username;
    }
}
