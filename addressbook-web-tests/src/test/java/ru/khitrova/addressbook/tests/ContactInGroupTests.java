package ru.khitrova.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;
import ru.khitrova.addressbook.model.Contacts;
import ru.khitrova.addressbook.model.GroupData;
import ru.khitrova.addressbook.model.Groups;


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
        Groups addedGroups = contact.getGroups();
        Groups existGroups = app.db().groups();
        Groups notAdded = new Groups();


        if (existGroups == addedGroups ) {
            app.goTo().groupPage();
            GroupData newGroup = new GroupData().withName("the_new_group");
            app.group().create(newGroup);
            existGroups = app.db().groups();
            GroupData group = newGroup.withId(existGroups.stream().mapToInt((g) -> (g.getId())).max().getAsInt());
        }
        for (GroupData group : existGroups)  {
            if (!addedGroups.contains(group)) {
                notAdded.add(group);
            }
        }
        GroupData group = notAdded.iterator().next();
        app.contact().addToGroup(contact.getId(), group);
        app.goTo().homePage();
        Groups updatedGroups = contact.getGroups();

        assertThat(app.contact().count(), equalTo(before.size()));

        assertThat(updatedGroups, equalTo(existGroups.withAdded(group)));
        verifyContactListInUI();


    }

    @Test
    public void testContactDeleteFromGroup() {
        app.goTo().homePage();
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        Groups addedGroups = contact.getGroups();
        GroupData group = app.db().groups().iterator().next();
        if (contact.getGroups().isEmpty()) {
            app.contact().addToGroup(contact.getId(), group);
            app.goTo().homePage();
            addedGroups = addedGroups.withAdded(group);
        }


        app.contact().removeFromGroup(contact.getId(), group);

        Groups updatedGroups = contact.getGroups();

        assertThat(updatedGroups, equalTo(addedGroups.without(group)));
        verifyContactListInUI();
    }
}
