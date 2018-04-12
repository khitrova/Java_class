package ru.khitrova.mantis.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "mantis_user_table")
public class UsersData {
    @Expose
    @Column(name = "username")
    String username;

    @Expose
    @Column(name = "email")
    String email;

    @Id
    @Column(name = "id")
    int id;

    public String getUsername() {
        return username;

    }

    public UsersData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersData withEmail(String email) {
        this.email = email;
        return this;

    }

    public void add(UsersData username) {
    }
}
