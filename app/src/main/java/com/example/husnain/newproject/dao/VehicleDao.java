package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.Users;
import com.example.husnain.newproject.entities.Vehicle;

import java.util.List;

@Dao
public interface VehicleDao {

    @Insert
    void insert(Vehicle vehicle);

    @Query("DELETE FROM Vehicle")
    void deleteAllVehicle();

    @Query("SELECT * FROM Vehicle")
    LiveData<List<Vehicle>> getAllVehicles();

    @Query("SELECT * FROM Vehicle WHERE Vehicle_id=:vehicleid")
    Vehicle getVehicleByID(int vehicleid);


}
