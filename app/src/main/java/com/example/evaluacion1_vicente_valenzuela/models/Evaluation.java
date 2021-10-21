package com.example.evaluacion1_vicente_valenzuela.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation implements Serializable, IEvaluation{
    private long id;
    private String title;
    private Date date;
    private double weight;

    public Evaluation(long id, Date date, double weight) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.weight = weight;
    }

    public Evaluation(String title, String date, String weight, long id) {
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    /*@Override
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    } */

    @Override
    public double getWeight() {
        return weight;
    }

    public String getStringWeight() {
        return Double.toString(weight);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}


