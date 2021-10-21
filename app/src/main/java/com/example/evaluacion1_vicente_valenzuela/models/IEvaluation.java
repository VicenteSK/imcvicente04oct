package com.example.evaluacion1_vicente_valenzuela.models;

import java.util.Date;

public interface IEvaluation {
    long getId();
    String getTitle();
    Date getDate();
    double getWeight();
    long getUserId();
}

