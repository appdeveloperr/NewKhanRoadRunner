package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.BusCharges;

import java.util.List;

@Dao
public interface BusChargesDao {

    @Insert
    void insert(BusCharges BusCharges);

    @Query("DELETE FROM Table_Bus_Charges")
    void deleteAllBusCharges();

    @Query("SELECT * FROM Table_Bus_Charges")
    LiveData<List<BusCharges>> getAllBusCharges();

}
