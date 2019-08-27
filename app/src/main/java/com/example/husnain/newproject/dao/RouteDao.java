package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.FareDetails;
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
