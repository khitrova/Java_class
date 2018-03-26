package ru.khitrova.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void enshurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().preconditionalContact(
                    new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678")
                            .withEmail("test@email.test").withYear("1990"), true, true);
        }
    }

    @Test
    public void testContactDeletion() {


        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.contact().delete(deletedContact);
        app.contact().confirmDeletion();

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedContact)));

    }

    @Test(enabled = false) //Удаление со страницы редактирования контакта

    public void testContactEditDeletion() {


        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();

        app.contact().editContact(deletedContact);
        app.contact().deleteEditedContact();

        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() - 1));

        assertThat(after, equalTo(before.without(deletedContact)));

}
}
