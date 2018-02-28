package ru.khitrova.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){

        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().checkContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true, true);
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact(before.size()-1);
        ContactData contact = new ContactData("NewName", "LastName11", "89012345678", "test@email.test", "1990", "null");
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }
}
