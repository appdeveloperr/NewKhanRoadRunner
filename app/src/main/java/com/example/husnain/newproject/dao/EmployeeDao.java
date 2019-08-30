package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
