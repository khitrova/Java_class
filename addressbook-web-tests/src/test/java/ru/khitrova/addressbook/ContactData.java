package ru.khitrova.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String phone;
    private final String email;
    private final String year;

    public ContactData(String firstname, String lastname, String phone, String email, String year) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.year = year;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getYear() {
        return year;
    }
}
