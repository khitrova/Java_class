package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.appManager.GroupHelper;
import ru.khitrova.addressbook.appManager.NavigationHelper;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.GroupData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().gotoNewContact();
        app.getContactHelper().checkContact(new ContactData(null, null, null, null, null, "test1"), true, false);
        app.getContactHelper().createContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true);
        app.getNavigationHelper().gotoHomePage();
    }


}
