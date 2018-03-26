package ru.khitrova.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {


    @BeforeMethod
    public void enshurePreconditions() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.contact().preconditionalContact(
                    new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678").withEmail("test@email.test").withYear("1990"), true, true);
        }
    }

    @Test
    public void testContactModification() {


        Contacts before = app.db().contacts();
        ContactData modifyContact = before.iterator().next();
        app.contact().editContact(modifyContact);
        ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("ChangedName").
                withLastName("LastName11").withEmail("new@test.xx").withPhoneWork("1231212").withPhoneMobile("000000").
                withPhoneHome("888888888").withAddress("newAddress").withEmail2("2@test.xx").withEmail3("3@test.xx");
        app.contact().fillContactForm(contact, false);
        app.contact().submitContactModification();
        app.goTo().homePage();


        Contacts after = app.db().contacts();

        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifyContact).withAdded(contact)));


    }
}
