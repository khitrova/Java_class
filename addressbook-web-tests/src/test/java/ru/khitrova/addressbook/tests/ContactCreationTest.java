package ru.khitrova.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{



    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        List<ContactData> before = app.contact().getContactList();
        app.contact().newContact();

        app.contact().checkContact(new ContactData(null, null, null, null, null, "test1"), true, false);
        ContactData contact = new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1");
        app.contact().createContact(contact, true);
        app.goTo().homePage();

        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);

        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);

        after.sort(byId);

        Assert.assertEquals(before, after);
    }


}
