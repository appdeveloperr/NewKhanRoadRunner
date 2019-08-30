package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
