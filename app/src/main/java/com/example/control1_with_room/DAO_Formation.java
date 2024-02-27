package com.example.control1_with_room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DAO_Formation {
    @Insert
    void ajouterFormation(Formation... f);
    @Delete
    void deletformation(Formation f);

    @Update
    void updatFormation(Formation f);
    @Query("SELECT * FROM Formation")
    List<Formation> getFormations();
    @Query("SELECT DISTINCT type FROM Formation")
    List<String> getTypes();
}
