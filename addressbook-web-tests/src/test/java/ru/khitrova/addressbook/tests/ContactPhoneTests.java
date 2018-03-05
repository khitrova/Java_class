package ru.khitrova.addressbook.tests;

import org.testng.annotations.Test;
import ru.khitrova.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test(enabled = false)
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditedForm = app.contact().infoFromEditedForm(contact);

        assertThat(contact.getEmail(), equalTo(merged(contactInfoFromEditedForm)));
        assertThat(contact.getPhoneMobile(), equalTo(cleaned(contactInfoFromEditedForm.getPhoneMobile())));
        assertThat(contact.getPhoneWork(), equalTo(cleaned(contactInfoFromEditedForm.getPhoneWork())));

    }

    private String merged(ContactData contact) {
        return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
                .stream().filter((s)->!s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
