package ru.khitrova.mantis.appmanager;

import org.openqa.selenium.By;

public class AdminToolsHelper extends HelperBase {

    public AdminToolsHelper(ApplicationManager app) {
        super(app);

    }

    public void resetUserPassword(){

        app.goTo().manage();
        app.goTo().manageUsers();
        app.goTo().click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=9']"));
        app.goTo().click(By.cssSelector("input[value='Reset Password']"));
    }

    public String getUserName(){
        app.goTo().manage();
        app.goTo().manageUsers();
        String name = wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=9']")).getText();
        return name;
    }

    public void loginAsAdmin() {
        wd.get(app.getProperty ("web.baseUrl") + "/login_page.php");
        type(By.name("username"),"administrator");
        type(By.name("password"), "root");
        click(By.cssSelector("input[value='Login']"));
    }

    public void logout() {
        app.goTo().click(By.cssSelector("a[href='/mantisbt-1.2.19/logout_page.php']"));
    }

}
