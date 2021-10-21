package com.example.evaluacion1_vicente_valenzuela;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import com.example.evaluacion1_vicente_valenzuela.controllers.AuthController;
import com.example.evaluacion1_vicente_valenzuela.controllers.EvaluationController;
import com.example.evaluacion1_vicente_valenzuela.lib.TilValidator;
import com.example.evaluacion1_vicente_valenzuela.models.Evaluation;
import com.example.evaluacion1_vicente_valenzuela.models.User;
import com.example.evaluacion1_vicente_valenzuela.ui.DatePickerFragment;
import com.example.evaluacion1_vicente_valenzuela.utils.DateUtils;


public class NewEvaluationActivity extends AppCompatActivity {
    private TextInputLayout tilTitle, tilDate, tilWeight;
    private Button btnRegister, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_evaluation);

        tilTitle = findViewById(R.id.activity_new_evaluation_field_title);
        tilDate = findViewById(R.id.activity_new_evaluation_field_date);
        tilWeight = findViewById(R.id.activity_new_evaluation_field_weight);
        btnRegister = findViewById(R.id.activity_new_evaluation_btn_register);
        btnBack = findViewById(R.id.activity_new_evaluation_btn_back);

        tilDate.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilDate, new Date());
        });

        btnRegister.setOnClickListener(view -> {
            String title = tilTitle.getEditText().getText().toString();
            String date = tilDate.getEditText().getText().toString();
            String weight = tilWeight.getEditText().getText().toString();



            boolean validTitle = new TilValidator(tilTitle)
                    .required()
                    .strMin(3)
                    .isValid();
            boolean validDate = new TilValidator(tilDate)
                    .strMin(3)
                    .isValid();

            boolean validWeight = new TilValidator(tilWeight)
                    .strMin(3)
                    .isValid();


            if (!validTitle || !validDate || !validWeight) {
                return;
            }

            Date evaluationDateFormatted = DateUtils.unsafeParse(evaluationDate);
            double evaluationWeightFormatted = Double.parseDouble(evaluationWeight);

            AuthController authController = new AuthController(view.getContext());

            User user = authController.getUserSession();

            Evaluation evaluation = new Evaluation(title, date, weight, user.getId());

            EvaluationController controller = new EvaluationController(view.getContext());

            controller.register(evaluation);

            Toast.makeText(view.getContext(), "Registrar", Toast.LENGTH_SHORT).show();
        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });

    }
}