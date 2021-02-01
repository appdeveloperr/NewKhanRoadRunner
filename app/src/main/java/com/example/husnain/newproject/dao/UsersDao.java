package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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

    @Query("SELECT * FROM Users WHERE Upper(User_Name) = Upper(:username) and Password = :pasw")
    LiveData<List<Users>> validateUser(String username, String pasw);

}
