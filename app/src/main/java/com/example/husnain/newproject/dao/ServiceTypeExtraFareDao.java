package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.ServiceType;
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
