package ru.khitrova.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void enshurePreconditions(){
        app.goTo().homePage();
        app.contact().checkContact(new ContactData("Name", "LastName", "89012345678", "test@email.test", "1990", "test1"), true, true);
    }

    @Test
    public void testContactModification(){


        List<ContactData> before = app.contact().getContactList();
        int index = before.size()-1;
        app.contact().editContact(before.size()+1);
        ContactData contact = new ContactData(before.get(index).getId(),"NewName22", "LastName11", "89012345678", "test@email.test", "1990", "null");
        app.contact().fillContactForm(contact,false);
        app.contact().submitContactModification();
        app.goTo().homePage();


        List<ContactData> after = app.contact().getContactList();
        Assert.assertEquals(after.size(), before.size());
        System.out.println(before.get(index));
        System.out.println(before);
        System.out.println(after);
        before.remove(index);
        System.out.println(before);
        before.add(contact);

        System.out.println(before);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);

        after.sort(byId);
        System.out.println("sort");


        System.out.println(before);
        System.out.println(after);



        Assert.assertEquals(before, after);


    }
}
