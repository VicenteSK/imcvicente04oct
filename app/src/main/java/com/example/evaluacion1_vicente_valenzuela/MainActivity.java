package com.example.evaluacion1_vicente_valenzuela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evaluacion1_vicente_valenzuela.controllers.AuthController;
import com.example.evaluacion1_vicente_valenzuela.controllers.EvaluationController;
import com.example.evaluacion1_vicente_valenzuela.lib.TilValidator;
import com.example.evaluacion1_vicente_valenzuela.models.Evaluation;
import com.example.evaluacion1_vicente_valenzuela.models.User;
import com.example.evaluacion1_vicente_valenzuela.ui.DatePickerFragment;
import com.example.evaluacion1_vicente_valenzuela.ui.EvaluationAdapter;
import com.example.evaluacion1_vicente_valenzuela.utils.DateUtils;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TextView tvTitle, tvClearFilter;
    private ListView lvAllEvaluations;
    private TextInputLayout tilFrom, tilTo;
    private Button btnLogout, btnNewTask, btnFilter;
    private AuthController authController;
    private EvaluationController evaluationController;

    //private List<Evaluation> evaluationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authController = new AuthController(this);
        evaluationController = new EvaluationController(this);

        tvTitle = findViewById(R.id.activity_main_tv_title);
        tvClearFilter = findViewById(R.id.activity_main_tv_clear_filter);
        lvAllEvaluations = findViewById((R.id.activity_main_lv_all_evaluations));
        tilFrom = findViewById(R.id.activity_main_til_from);
        tilTo = findViewById(R.id.activity_main_til_to);
        btnLogout = findViewById(R.id.activity_main_btn_logout);
        btnNewEvaluation = findViewById(R.id.activity_main_btn_new_evaluation);
        btnFilter = findViewById(R.id.activity_main_btn_filter);

        tilFrom.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilFrom, new Date());
        });

        tilTo.getEditText().setOnClickListener(view -> {
            DatePickerFragment.showDatePickerDialog(this, tilTo, new Date());
        });

        User user = authController.getUserSession();
        tvTitle.setText(String.format( "Evaluaciones de %s", user.getFirstName()));


        List<Evaluation> evaluationList = evaluationController.getAll();
        this.setAllEvaluationAdapter(evaluationList);

        btnNewEvaluation.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), NewEvaluationActivity.class);
            view.getContext().startActivity(i);
        });

        btnLogout.setOnClickListener(view -> { authController.logout(); });

        btnFilter.setOnClickListener(view -> {
            String fromStr = tilFrom.getEditText().getText().toString();
            String toStr = tilTo.getEditText().getText().toString();

            boolean validFrom = new TilValidator(tilFrom)
                    .required()
                    .date()
                    .dateBefore(DateUtils.unsafeParse(toStr))
                    .isValid();
            boolean validTo = new TilValidator(tilTo)
                    .required()
                    .date()
                    .dateAfter(DateUtils.unsafeParse(fromStr))
                    .isValid();

            if (validFrom && validTo) {
                Date from = DateUtils.unsafeParse(fromStr);
                Date to = DateUtils.unsafeParse(toStr);

                List<Evaluation> evaluationRangeList = evaluationController.getRange(from, to);
                this.setAllEvaluationAdapter(evaluationRangeList);
            }
        });

        tvClearFilter.setOnClickListener(view -> {
            tilFrom.getEditText().setText("");
            tilTo.getEditText().setText("");
            this.setAllEvaluationAdapter(evaluationList);
        });
    }

        @Override
        protected void onResume() {
            super.onResume();
            List<Evaluation> evaluationList = evaluationController.getAll();
            this.setAllEvaluationAdapter(evaluationList);
        }

        private void setAllEvaluationAdapter(List<Evaluation> evaluationList) {
        EvaluationAdapter adapter = new EvaluationAdapter(this, evaluationList);
        lvAllEvaluations.setAdapter(adapter);

        lvAllEvaluations.setOnItemClickListener(((adapterView, view, index, id ) ->{
            Evaluation evaluation = evaluationList.get(index);

            Intent i = new Intent(view.getContext(), EvaluationDetailActivity.class);
            i.putExtra("evaluation", evaluation );
            view.getContext().startActivity(i);
        }));


        btnLogout.setOnClickListener(view ->{ authController.logout();
        });

        }
    }
