package ru.khitrova.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String phone;
    private final String email;
    private final String year;
    private String group;

    public ContactData(String firstname, String lastname, String phone, String email, String year, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.year = year;
        this.group = group;
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

    public String getGroup() {
        return group;
    }
}
