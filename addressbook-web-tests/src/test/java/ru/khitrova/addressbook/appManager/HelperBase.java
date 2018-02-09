package ru.khitrova.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
    protected FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void dropDownClick(String locator) {
        if (!wd.findElement(By.xpath(locator)).isSelected()) {
            click(By.xpath(locator));
        }
    }

    protected void clickCheckBox(String id) {
        if (!wd.findElement(By.xpath(id)).isSelected()) {
            wd.findElement(By.xpath(id)).click();
        }
    }

    protected void alertConfirm() {
        wd.switchTo().alert().accept();
    }
}
