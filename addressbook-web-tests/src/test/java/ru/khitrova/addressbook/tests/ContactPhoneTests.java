package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test(enabled = false)
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditedForm = app.contact().infoFromEditedForm(contact);

        assertThat(contact.getPhoneHome(), equalTo(cleaned(contactInfoFromEditedForm.getPhoneHome())));
        assertThat(contact.getPhoneMobile(), equalTo(cleaned(contactInfoFromEditedForm.getPhoneMobile())));
        assertThat(contact.getPhoneWork(), equalTo(cleaned(contactInfoFromEditedForm.getPhoneWork())));

    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
