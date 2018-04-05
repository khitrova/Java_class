package ru.khitrova.mantis.appmanager;

import org.openqa.selenium.By;

public class ProfileHelper extends HelperBase {

    public ProfileHelper(ApplicationManager app) {
        super(app);
    }

    public void confirmLink(String link, String password) {
        wd.get(link);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));

    }
}
