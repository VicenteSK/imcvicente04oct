package com.example.evaluacion1_vicente_valenzuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import com.example.evaluacion1_vicente_valenzuela.controllers.AuthController;
import com.example.evaluacion1_vicente_valenzuela.models.User;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    private TextInputLayout tilUsername, tilPassword;
    private AuthController authController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController(this);

        authController.checkUserSession();


        btnLogin = findViewById(R.id.activity_login_btn_login);
        btnRegister = findViewById(R.id.activity_login_btn_register);
        tilUsername = findViewById(R.id.activity_login_field_username);
        tilPassword = findViewById(R.id.activity_login_field_password);

        btnLogin.setOnClickListener(view -> {
                    Toast.makeText(view.getContext(), "Iniciando Sesión", Toast.LENGTH_SHORT).show();

                    String username = tilUsername.getEditText().getText().toString();
                    String password = tilPassword.getEditText().getText().toString();

                    boolean usernameValid = !username.isEmpty();
                    boolean passwordValid = !password.isEmpty();

                    if (!usernameValid) {
                        tilUsername.setError("El nombre de usuario no es correcto");
                    } else {
                        tilUsername.setError(null);
                        tilUsername.setErrorEnabled(false);
                    }

                    if (!passwordValid) {
                        tilPassword.setError("Campo requerido");
                    } else {
                        tilPassword.setError(null);
                        tilPassword.setErrorEnabled(false);
                    }

                    if (usernameValid && passwordValid) {
                        authController.login(username, password);
                    } else {
                        Toast.makeText(view.getContext(), "Campos Inválidos", Toast.LENGTH_SHORT).show();
                    }

                }
        );


        btnRegister.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), RegisterActivity.class);
            startActivity(i);
            finish();
        });
    }
}
