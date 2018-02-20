package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().checkContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true, true);
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeletion();
    }

    @Test //Удаление со страницы редактирования контакта
    public void testContactEditDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().checkContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true, true);
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditedContact();
    }
}
