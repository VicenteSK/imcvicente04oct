package com.example.evaluacion1_vicente_valenzuela.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "evaluations")
public class EvaluationEntity implements IEvaluation{
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "weight")
    private double weight;

    @ColumnInfo(name = "user_id")
    private long userId;

    public EvaluationEntity(long id, String title, double weight , Date date, long userId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.weight = weight;
        this.userId = userId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Date getDate() { return date;   }

    @Override
    public double getWeight() { return weight; }

    @Override
    public long getUserId() {
        return userId;
    }
}

