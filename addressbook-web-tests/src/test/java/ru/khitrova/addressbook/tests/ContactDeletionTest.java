package ru.khitrova.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {

        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().checkContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true, true);
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeletion();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

    }
/*
    @Test //Удаление со страницы редактирования контакта
    public void testContactEditDeletion() {

        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().checkContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true, true);
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditedContact();
         List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

}*/
}
