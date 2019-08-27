package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.TicketingSchedule;
import com.example.husnain.newproject.entities.TicketingSeat;
import com.example.husnain.newproject.entities.Users;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert
    void insert(Users users);

    @Query("DELETE FROM Users")
    void deleteAllUsers();

    @Query("SELECT * FROM Users ")
    LiveData<List<Users>> getAllUsers();

    @Query("SELECT * FROM Users WHERE User_Name = :username and Password = :pasw")
    LiveData<List<Users>> validateUser(String username, String pasw);

}
