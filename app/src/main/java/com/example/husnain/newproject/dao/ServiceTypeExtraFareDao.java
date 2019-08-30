package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.ServiceTypeExtraFare;

import java.util.List;

@Dao
public interface ServiceTypeExtraFareDao {

    @Insert
    void insert(ServiceTypeExtraFare ServiceTypeExtraFare);

    @Query("DELETE FROM ServiceTypeExtraFare")
    void deleteAllServiceTypeExtraFare();

    @Query("SELECT * FROM ServiceTypeExtraFare")
    LiveData<List<ServiceTypeExtraFare>> getAllServiceTypeExtraFares();

}
