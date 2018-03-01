package ru.khitrova.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void newContact() {
        click(By.linkText("add new"));
    }

    public void editContact(int index) {
        click(By.xpath("//div/div[4]/form[2]/table/tbody/tr["+index+"]/td[8]/a/img"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void selectContact(int index) {
       wd.findElements(By.name("selected[]")).get(index).click();
    }
//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input

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
        newContact();
        fillContactForm(contactData, create);
        confirmCreation();
    }


    public boolean isContactPresent() {
        return (isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input")));
    }


    public void checkContact(ContactData contactData,boolean creation, boolean needNewContact ) {
        if (!isContactPresent()){
            newContact();
            if (!listGroup(contactData)) {
                new NavigationHelper(wd).groupPage();
                new GroupHelper(wd).create(new GroupData("test1", null, null));

            }
            if (needNewContact) {
                createContact(contactData, creation);
            }

        }
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
    //    List<WebElement> cells = element.findElements(By.tagName("td"));
            for (WebElement element : elements ) {

                String name = element.findElement(By.xpath(".//td[3]")).getText();
                String lastName = element.findElement(By.xpath(".//td[2]")).getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                ContactData contact = new ContactData(id, name, lastName, null,null,null,null);
                contacts.add(contact);


            }
            return contacts;

    }
}
