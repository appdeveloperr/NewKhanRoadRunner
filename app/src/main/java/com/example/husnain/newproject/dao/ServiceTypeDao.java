package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.ServerMapping;
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
