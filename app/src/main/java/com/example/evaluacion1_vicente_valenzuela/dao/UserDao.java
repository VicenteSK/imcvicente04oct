package com.example.evaluacion1_vicente_valenzuela.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.evaluacion1_vicente_valenzuela.models.UserEntity;

@Dao
public interface UserDao {
    @Query("SELECT id, username, firstname, lastname, email, birthday, password FROM ` users` WHERE username =:username LIMIT 1")
    UserEntity findByUser (String username);

    @Insert
    long insert(UserEntity user);
}
