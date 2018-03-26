package ru.khitrova.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;
import ru.khitrova.addressbook.model.GroupData;

public class ContactInGroupTests extends TestBase{

    @BeforeMethod
    public void checkPreconditions(){
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().preconditionalContact(
                    new ContactData().withFirstName("Name").withLastName("LastName").withPhone("89012345678")
                            .withEmail("test@email.test").withYear("1990"), true, true);
        }

    }

    @Test
    public void testContactAddToGroup(){
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        app.contact().selectContact(contact.getId());
        app.contact().addToGroup();

    }

    @Test
    public void testContactDeleteFromGroup(){

    }
}
