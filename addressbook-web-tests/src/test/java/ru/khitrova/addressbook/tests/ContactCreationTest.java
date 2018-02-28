package ru.khitrova.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.appManager.GroupHelper;
import ru.khitrova.addressbook.appManager.NavigationHelper;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoNewContact();

        app.getContactHelper().checkContact(new ContactData(null, null, null, null, null, "test1"), true, false);
        ContactData contact = new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1");
        app.getContactHelper().createContact(contact, true);
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);

        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
