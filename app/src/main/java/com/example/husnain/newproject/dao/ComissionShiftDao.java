package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.ComissionShift;

import java.util.List;

@Dao
public interface ComissionShiftDao {

    @Insert
    void insert(ComissionShift ComissionShift);

    @Query("DELETE FROM Comission_Shift")
    void deleteAllComissionShift();

    @Query("SELECT * FROM Comission_Shift")
    LiveData<List<ComissionShift>> getAllCommisionShift();



}
