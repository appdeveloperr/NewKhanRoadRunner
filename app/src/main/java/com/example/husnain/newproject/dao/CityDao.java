package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.City;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insert(City city);

    @Query("SELECT * FROM City")
    LiveData<List<City>> getAllCities();

    @Query("DELETE FROM City")
    void deleteAllCities();


}
