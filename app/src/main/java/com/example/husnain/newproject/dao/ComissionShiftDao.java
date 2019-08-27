package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.City;
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
