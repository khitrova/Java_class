package ru.khitrova.addressbook.model;

import java.io.File;
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
    private String allEmails;
    private String email2;
    private String email3;
    private File photo;



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

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }


    public ContactData withEmail3(String email3) {
        this.email3 = email3;
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

    public ContactData withAddress(String adress) {
        this.adress = adress;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
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

    public String getEmail2() {
        return email2;
    }


    public String getEmail3() {
        return email3;
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

    public String getPhoneHome() {
        return home;
    }

    public String getPhoneMobile() {
        return mobile;
    }

    public String getPhoneWork() {
        return work;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return photo;
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




}
