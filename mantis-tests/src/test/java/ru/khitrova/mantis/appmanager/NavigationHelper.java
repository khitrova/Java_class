package ru.khitrova.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);

    }

    public void manage(){
        click(By.xpath("/html/body/table[2]/tbody/tr/td[1]/a[7]"));
    }

    public void manageUsers(){
        click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    }
}
