package com.example.evaluacion1_vicente_valenzuela.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = " users", indices ={@Index(value = "id", unique = true)})
public class UserEntity implements IUser{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "firstname")
    private String firstName;

    @ColumnInfo(name = "lastname")
    private String lastName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "birthday")
    private Date birthday;

    @ColumnInfo(name = "password")
    private String password;

    public UserEntity(long id, String username, String firstName, String lastName, String email, Date birthday, String password) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.password = password;
    }

    public long getId() {return id; }

    public String getUsername() { return username; }

    public String getFirstName() { return firstName; }

    public String getLastName() {return lastName; }

    public String getEmail() { return email; }

    public Date getBirthday() {  return birthday; }

    public String getPassword() {return password; }

}
