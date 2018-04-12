package ru.khitrova.mantis.model;

public class UsersData {

    String username;
    String email;

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
