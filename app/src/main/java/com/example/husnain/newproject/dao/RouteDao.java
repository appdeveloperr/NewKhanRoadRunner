package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.Route;

import java.util.List;

@Dao
public interface RouteDao {

    @Insert
    void insert(Route Route);

    @Query("DELETE FROM Route")
    void deleteAllRoutes();

    @Query("SELECT * FROM Route")
    LiveData<List<Route>> getAllRoutes();

}
