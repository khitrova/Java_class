package ru.khitrova.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void confirmCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getPhone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            if (!listGroup(contactData)) {
                new NavigationHelper(wd).gotoGroupPage();
                new GroupHelper(wd).createGroup(new GroupData("test1", null, null));
                createContact(contactData, creation);
            }
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        dropDownClick("//div[@id='content']/form/select[1]//option[3]");
        dropDownClick("//div[@id='content']/form/select[2]//option[7]");
        type(By.name("byear"), contactData.getYear());

    }

    private boolean listGroup(ContactData contactData) {
        try {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }

    public void gotoNewContact() {
        click(By.linkText("add new"));
    }

    public void editContact() {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void selectContact() {
        clickCheckBox("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input");
    }


    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void deleteEditedContact() {
        click(By.xpath("//div[@id='content']/form[2]/input[2]"));
    }

    public void confirmDeletion() {
        alertConfirm();
    }

    public void createContact(ContactData contactData, boolean create) {
        gotoNewContact();
        fillContactForm(contactData, create);
        confirmCreation();
    }


    public boolean isContactPresent() {
        return (isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input")));
    }


}
