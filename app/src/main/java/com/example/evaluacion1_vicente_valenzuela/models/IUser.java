package com.example.evaluacion1_vicente_valenzuela.models;

import java.util.Date;

public interface IUser {
     long getId();
     String getUsername();
     String getFirstName();
     String getLastName();
     String getEmail();
     Date getBirthday() ;
     String getPassword() ;

}
