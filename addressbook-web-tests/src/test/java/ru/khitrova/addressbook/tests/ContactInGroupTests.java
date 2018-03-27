package ru.khitrova.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;
import ru.khitrova.addressbook.model.GroupData;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInGroupTests extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().preconditionalContact(
                    new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678")
                            .withEmail("test@email.test").withYear("1990"), true, true);
        }
        app.goTo().homePage();

    }

    @Test
    public void testContactAddToGroup() {
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();

        app.contact().addToGroup(contact.getId());
        app.goTo().homePage();

        Contacts after = app.db().contacts();
        assertThat(app.contact().count(), equalTo(before.size()));

        assertThat(after, equalTo(before));
        verifyContactListInUI();


    }

    @Test
    public void testContactDeleteFromGroup() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        GroupData group = app.db().groups().iterator().next();
        if (contact.getGroups().isEmpty()) {
            app.contact().addToGroup(contact.getId());
            app.goTo().homePage();
        }
        app.contact().removeFromGroup(contact.getId(), group);
        Contacts after = app.db().contacts();


        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }
}
