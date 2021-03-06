package ru.khitrova.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;
import ru.khitrova.addressbook.model.GroupData;

import java.io.File;
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
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("work"), contactData.getPhoneWork());
        type(By.name("mobile"), contactData.getPhoneMobile());
       try {
            attach(By.name("photo"), contactData.getPhoto());
        } catch (NullPointerException ex) {
           return;
       }
        if (creation) {
        //   if (ContactData.getGroups().size() >0)
         //      Assert.assertTrue(ContactData.getGroups().size() ==0);
         //  new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        dropDownClick("//div[@id='content']/form/select[1]//option[3]");
        dropDownClick("//div[@id='content']/form/select[2]//option[7]");
        type(By.name("byear"), contactData.getYear());

    }

    private boolean listGroup(ContactData contactData) {
        try {
        //    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }

    public void newContact() {
        click(By.linkText("add new"));
    }

    public void editContact(int index) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", index)));
    }

    public void editContact(ContactData contact) {
        click(By.cssSelector("a[href='edit.php?id=" + contact.getId() + "']"));
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form[1]/input[22]"));
    }

    public void selectContact(int id) {

        wd.findElement(By.cssSelector(String.format("input[value='%s']", id))).click();

    }


    public void deleteContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void delete(ContactData contact) {
        selectContact(contact.getId());
        deleteContact();

    }

    private Contacts contactCache = null;

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


    public void preconditionalContact(ContactData contactData, boolean creation, boolean needNewContact) {

        newContact();
        if (!listGroup(contactData)) {
            new NavigationHelper(wd).groupPage();
            new GroupHelper(wd).create(new GroupData().withName("new_group"));

        }
        if (needNewContact) {
            createContact(contactData, creation);
        }


    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));

        for (WebElement element : elements) {

            String name = element.findElement(By.xpath(".//td[3]")).getText();
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            String address = element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            //String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            // ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastName).withPhoneHome(phones[0]).withPhoneMobile(phones[1]).withPhoneWork(phones[2]);
            ContactData contact = new ContactData().withId(id).withFirstName(name).withLastName(lastName).withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails);
            contacts.add(contact);


        }
        return contacts;

    }

    public ContactData infoFromEditedForm(ContactData contact) {
        editContact(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getText();
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).
                withPhoneHome(home).withPhoneMobile(mobile).withPhoneWork(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);

    }

    private void initContactmodificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='$s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("id"));
        cells.get(7).findElement(By.tagName("a")).click();

        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../ts[8]/a"))).click();
        wd.findElement(By.xpath(String.format("//tr[.//input[@value='@s']]/td[8]/a"))).click();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void addToGroup(int id, GroupData group ) {
        selectContact(id);

        dropDownClick(String.format("//div[@id='content']/form[2]/div[4]/select/option[@value='%s']",group.getId() ));
        click(By.xpath("//*[@id='content']/form[2]/div[4]/input"));
    }

    public void removeFromGroup(int id, GroupData group) {
        click(By.cssSelector(String.format("a[href='view.php?id=%s']", id)));
        click(By.cssSelector(String.format("a[href='./index.php?group=%s']", group.getId())));
        selectContact(id);
        click(By.xpath("//*[@id='content']/form[2]/div[3]/input"));

    }
}
