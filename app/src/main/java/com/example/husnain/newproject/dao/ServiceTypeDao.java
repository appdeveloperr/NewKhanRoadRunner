package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.ServiceType;

import java.util.List;

@Dao
public interface ServiceTypeDao {

    @Insert
    void insert(ServiceType serviceType);

    @Query("DELETE FROM ServiceType")
    void deleteAllServiceType();

    @Query("SELECT * FROM ServiceType")
    LiveData<List<ServiceType>> getAllServiceTypes();

}
