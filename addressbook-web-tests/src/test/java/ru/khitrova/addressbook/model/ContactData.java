package ru.khitrova.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id;

    @Expose
    @Column(name = "firstname")
    private  String firstname;
    @Expose
    @Column(name = "lastname")
    private  String lastname;
    @Transient
    private  String phone;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private  String email;
    @Transient
    private  String year;


    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String home;
    @Column(name = "work")
    @Type(type = "text")
    private String work;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;





    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();



    public ContactData withPhoto(File photo) {

        this.photo = photo.getPath();
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

    public ContactData withAddress(String address) {
        this.address = address;
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




    public Groups getGroups() {
        return new Groups(groups);
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


    public int getId() {
        return id;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public String getAddress() {
        return address;
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

        return new File(photo);
    }
    public String getHome() {
        return home;
    }

    public String getWork() {
        return work;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +

                ", email='" + email + '\'' +
                ", home='" + home + '\'' +
                ", work='" + work + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(home, that.home) &&
                Objects.equals(work, that.work) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname, email, home, work, mobile, address, email2, email3);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
