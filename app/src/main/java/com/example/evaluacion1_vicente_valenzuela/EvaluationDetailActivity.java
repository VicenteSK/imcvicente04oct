package com.example.evaluacion1_vicente_valenzuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.evaluacion1_vicente_valenzuela.controllers.EvaluationController;
import com.example.evaluacion1_vicente_valenzuela.models.Evaluation;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

import com.example.evaluacion1_vicente_valenzuela.ui.DatePickerFragment;

public class EvaluationDetailActivity extends AppCompatActivity {

    private TextView tvTitleId, tvTitle, tvDate, tvWeight, tvImc;
    private Button btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_detail);

        Evaluation evaluation =(Evaluation) getIntent().getSerializableExtra("evaluation");

        tvTitleId = findViewById(R.id.activity_evaluation_detail_tv_title_id);
        tvTitle = findViewById(R.id.activity_evaluation_detail_tv_title);
        tvDate = findViewById(R.id.activity_evaluation_detail_tv_date);
        tvWeight = findViewById(R.id.activity_evaluation_detail_tv_weight);
        //tvImc = findViewById(R.id.activity_evaluation_details_tv_imc);

        btnDelete = findViewById(R.id.activity_evaluation_detail_btn_delete);
        btnBack = findViewById(R.id.activity_evaluation_detail_btn_back);

        tvTitleId.setText(String.format("Detalles de la evaluacion %d", evaluation.getId()));
        tvTitle.setText(evaluation.getTitle());
        tvDate.setText(evaluation.getStringDate());
        tvWeight.setText(evaluation.getStringWeight());

        btnDelete.setOnClickListener(view -> {
            EvaluationController controller = new EvaluationController(view.getContext());
            controller.delete(evaluation.getId());
        });

        btnBack.setOnClickListener(view -> {
            super.onBackPressed();
        });

    }
}