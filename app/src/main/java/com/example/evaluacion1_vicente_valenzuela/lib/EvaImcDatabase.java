package com.example.evaluacion1_vicente_valenzuela.lib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.evaluacion1_vicente_valenzuela.dao.UserDao;
import com.example.evaluacion1_vicente_valenzuela.models.UserEntity;
import com.example.evaluacion1_vicente_valenzuela.utils.Converters;

@Database(entities = {UserEntity.class}, version = 1)
@TypeConverters({Converters.class})

public abstract class EvaImcDatabase extends RoomDatabase {
    private static final String DB_NAME = "eva_imc_db";
    private static EvaImcDatabase instance;

    public static synchronized EvaImcDatabase getInstance(Context ctx){
        if (instance == null){
            instance = Room.databaseBuilder(ctx.getApplicationContext(), EvaImcDatabase.class, DB_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        }

        return instance;
    }

    public abstract UserDao userDao();
}
