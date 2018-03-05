package ru.khitrova.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void enshurePreconditions(){
        app.goTo().homePage();
        app.contact().checkContact(
                new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678").withEmail("test@email.test").withYear("1990").withGroup("test1"), true, true);
    }

    @Test
    public void testContactDeletion() {


        Contacts before = app.contact().all();
        app.contact().selectContact(before.size()-1);
        app.contact().deleteContact();
        app.contact().confirmDeletion();

        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

    }
/*
    @Test //Удаление со страницы редактирования контакта
    public void testContactEditDeletion() {


        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditedContact();
         List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

}*/
}
