package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.getContactHelper().gotoNewContact();
        app.getContactHelper().fillContactForm(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true);
        app.getContactHelper().confirmCreation();
        app.getContactHelper().returnContactList();
    }


}
