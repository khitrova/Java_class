package ru.khitrova.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void enshurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().preconditionalContact(
                    new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678").withEmail("test@email.test").withYear("1990").withGroup("test1"), true, true);
        }
    }

    @Test
    public void testContactModification() {


        Contacts before = app.contact().all();
        ContactData modifyContact = before.iterator().next();
        app.contact().editContact(modifyContact);
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("ChangedName").withLastName("LastName11");
        app.contact().fillContactForm(contact, false);
        app.contact().submitContactModification();
        app.goTo().homePage();


        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));


    }
}
