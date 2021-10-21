package com.example.evaluacion1_vicente_valenzuela.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.evaluacion1_vicente_valenzuela.LoginActivity;
import com.example.evaluacion1_vicente_valenzuela.MainActivity;
import com.example.evaluacion1_vicente_valenzuela.dao.UserDao;
import com.example.evaluacion1_vicente_valenzuela.lib.BCrypt;
import com.example.evaluacion1_vicente_valenzuela.lib.EvaImcDatabase;
import com.example.evaluacion1_vicente_valenzuela.models.User;
import com.example.evaluacion1_vicente_valenzuela.models.UserEntity;
import com.example.evaluacion1_vicente_valenzuela.models.UserMapper;

import java.util.Date;



public class AuthController {
    private final String KEY_USER_ID = "userId";
    private final String KEY_EMAIL = "userEmail";
    private final String KEY_USERNAME = "userUsername";
    private final String KEY_FIRST_NAME = "userFirstName";
    private final String KEY_LAST_NAME = "userLastName";

    private UserDao userDao;
    private Context ctx;
    private SharedPreferences preferences;

    public AuthController(Context ctx) {
        this.ctx = ctx;
        int PRIVATE_MODE = 0;
        String PREF_NAME = "TodoAppPref";
        this.preferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        this.userDao = EvaImcDatabase.getInstance(ctx).userDao();
    }

    private void setUserSession(User user) {
        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putLong(KEY_USER_ID, user.getId());
        editor.putString(KEY_EMAIL, user.getEmail());

        //editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.apply();
    }

    public User getUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);
        String username = preferences.getString(KEY_USERNAME, "");
        String firstName = preferences.getString(KEY_FIRST_NAME, "");
        String lastName = preferences.getString(KEY_LAST_NAME, "");
        String email = preferences.getString(KEY_EMAIL,"");

        User user = new User(username, firstName, lastName, email, new Date());
        user.setId(id);

        return user;
    }

    public void checkUserSession() {
        long id = preferences.getLong(KEY_USER_ID, 0);

        if (id != 0) {
            Intent i = new Intent(ctx, MainActivity.class);
            ctx.startActivity(i);
            ((Activity) ctx).finish();

        }}


        public void register (User user){
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            //Log.d("Password", String.format("Pw: %s / Hash: %$", user.getPassword(), hashedPassword));
            user.setPassword(hashedPassword);

            UserEntity userEntity = new UserMapper(user).toEntity();

           userDao.insert(userEntity);

            Toast.makeText(ctx, String.format("Usuario %s registrado", user.getUsername()), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, LoginActivity.class);
            ctx.startActivity(i);
        }

        public void login (String username, String password){
           /* User user = new User("Vicente", "Valenzuela", "vicente@ciisa.cl", new Date());
            user.setPassword("$2a$10$BKb1biFA.4WzUBpmVKxu3eyK7bGU9t5/gMdWFRrA0pA8c09rKS.Ny");
            user.setId(1); */

            UserEntity userEntity = userDao.findByUser(username);
            if (userEntity == null){
                Toast.makeText(ctx, "Credenciales Invalidas", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = new UserMapper(userEntity).toBase();

            if (BCrypt.checkpw(password, user.getPassword())) {
                setUserSession(user);
                Toast.makeText(ctx, String.format("Bienvenido %s", username), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ctx, MainActivity.class);
                ctx.startActivity(i);
                ((Activity) ctx).finish();
            } else {
                Toast.makeText(ctx, String.format("La contraseña es incorrecta", username), Toast.LENGTH_SHORT).show();
            }
        }

        public void logout () {
            SharedPreferences.Editor editor = this.preferences.edit();
            editor.clear();
            editor.apply();
            Toast.makeText(ctx, "Cerrando Sesión", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ctx, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
            ((Activity) ctx).finish();


        }


}
