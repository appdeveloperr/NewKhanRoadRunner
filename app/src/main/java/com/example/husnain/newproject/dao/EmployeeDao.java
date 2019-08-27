package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.Customers;
import com.example.husnain.newproject.entities.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee Employee);

    @Query("DELETE FROM Employee")
    void deleteAllEmployee();

    @Query("SELECT * FROM Employee")
    LiveData<List<Employee>> getAllEmployee();

}
