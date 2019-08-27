package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.ComissionShift;
import com.example.husnain.newproject.entities.Customers;

import java.util.List;

@Dao
public interface CustomersDao {

    @Insert
    void insert(Customers Customers);

    @Query("DELETE FROM Customers")
    void deleteAllCustomers();

    @Query("SELECT * FROM Customers")
    LiveData<List<Customers>> getAllCustomers();

}
