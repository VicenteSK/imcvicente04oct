package com.example.evaluacion1_vicente_valenzuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.evaluacion1_vicente_valenzuela.controllers.AuthController;
import com.example.evaluacion1_vicente_valenzuela.models.User;
import com.google.android.material.textfield.TextInputLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.evaluacion1_vicente_valenzuela.ui.DatePickerFragment;

public class RegisterActivity extends AppCompatActivity {
    private final String DATE_PATTERN = "yyyy-MM-dd";
    private TextInputLayout tilUsername, tilBirthday, tilFirstName, tilLastName, tilEmail, tilPassword;
    private Button btnRegister, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tilUsername = findViewById(R.id.activity_register_field_username);
        tilFirstName = findViewById(R.id.activity_register_field_first_name);
        tilLastName = findViewById(R.id.activity_register_field_last_name);
        tilEmail = findViewById(R.id.activity_register_field_email);
        tilPassword = findViewById(R.id.activity_register_field_password);
        tilBirthday = findViewById(R.id.activity_register_field_birthdate);
        btnRegister = findViewById(R.id.activity_register_btn_register);
        btnLogin = findViewById(R.id.activity_register_btn_login);


        tilBirthday.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilBirthday, new Date());
        });

        btnRegister.setOnClickListener(view -> {
            String username = tilUsername.getEditText().getText().toString();
            String firstName = tilFirstName.getEditText().getText().toString();
            String lastName = tilLastName.getEditText().getText().toString();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            String birthday = tilBirthday.getEditText().getText().toString();


            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

            Date birthdayDate = null;
            try {
                birthdayDate = dateFormatter.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            User user = new User (username, firstName, lastName, email, birthdayDate);
            user.setPassword(password);

            AuthController controller = new AuthController(view.getContext());

            controller.register(user);



        });

        btnLogin.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), LoginActivity.class);
            startActivity(i);

        });



    }
}