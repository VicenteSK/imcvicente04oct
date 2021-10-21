package com.example.evaluacion1_vicente_valenzuela.models;

import java.util.Date;

public class User implements IUser {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthday;
    private String password;

    public User(String username, String firstName, String lastName, String email, Date birthday) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;

    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
