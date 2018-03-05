package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {


    @Test
    public void testContactCreation() {
        app.goTo().homePage();
        Contacts before = app.contact().all();

        app.contact().newContact();
        app.contact().checkContact(new ContactData().withGroup("test1"), true, false);
        ContactData contact = new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678").withEmail("test@email.test").withYear("1990").withGroup("test1");
        app.contact().createContact(contact, true);
        app.goTo().homePage();

        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g)->(g.getId())).max().getAsInt()))));
    }


}
