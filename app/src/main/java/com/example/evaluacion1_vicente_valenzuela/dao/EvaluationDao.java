package com.example.evaluacion1_vicente_valenzuela.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import com.example.evaluacion1_vicente_valenzuela.models.EvaluationEntity;

@Dao
public interface EvaluationDao {
    @Query("SELECT id, title, date, weight, user_id FROM evaluations WHERE user_id = :userId")
    List<EvaluationEntity> findAll (long userId);

    @Query("SELECT id, title, date, weight, user_id FROM evaluations WHERE user_id = :userId AND due BETWEEN :from AND :to")
    List<EvaluationEntity> findByRange (Date from, Date to, long userId);

    @Insert
    long insert(EvaluationEntity evaluation);

    @Query("DELETE FROM evaluations WHERE id = :id")
    void delete(long id);

}
