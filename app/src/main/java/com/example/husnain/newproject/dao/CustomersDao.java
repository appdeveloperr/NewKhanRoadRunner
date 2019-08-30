package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
