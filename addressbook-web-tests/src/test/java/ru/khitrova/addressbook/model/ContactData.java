package ru.khitrova.addressbook.model;

import java.util.Objects;

public class ContactData {
    private  String firstname;
    private  String lastname;
    private  String phone;
    private  String email;
    private  String year;
    private String group;
    private int id;
    private String home;
    private String work;
    private String mobile;
    private String adress;
    private String allPhones;



    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }




    public ContactData withId(int id){
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String name){
        this.firstname = name;
        return this;
    }
    public ContactData withLastName(String lastname){
        this.lastname = lastname;
        return this;
    }

    public ContactData withPhone(String phone){
        this.phone = phone;
        return this;
    }

    public ContactData withEmail(String email){
        this.email = email;
        return this;
    }

    public ContactData withYear(String year){
        this.year = year;
        return this;
    }

    public ContactData withGroup(String group){
        this.group = group;
        return this;
    }

    public ContactData withPhoneHome(String home) {
        this.home = home;
        return this;
    }

    public ContactData withPhoneWork(String work) {
        this.work = work;
        return this;
    }
    public ContactData withPhoneMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withAdress(String adress) {
        this.adress = adress;
        return this;
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

    public int getId() {
        return id;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public String getAddress() {
        return adress;
    }





    @Override
    public String toString() {
        return "ContactData{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstname, lastname, id);
    }


    public String getPhoneHome() {
        return home;
    }

    public String getPhoneMobile() {
        return mobile;
    }

    public String getPhoneWork() {
        return work;
    }


}
