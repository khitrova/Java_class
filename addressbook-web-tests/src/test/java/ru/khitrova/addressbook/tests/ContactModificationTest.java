package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData("Name1", "LastName", "89012345678", "test@email.test", "1990"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnContactList();
    }
}
