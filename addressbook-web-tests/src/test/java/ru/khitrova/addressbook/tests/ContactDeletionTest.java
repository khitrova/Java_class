package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        app.getContactHelper().confirmDeletion();
    }

    @Test //Удаление со страницы редактирования контакта
    public void testContactEditDeletion() {
        app.getContactHelper().editContact();
        app.getContactHelper().deleteEditedContact();
    }
}
