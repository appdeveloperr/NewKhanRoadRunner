package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.*;

import java.util.List;

@Dao
public interface ScheduleDao {

    @Insert
    void insert(Schedule schedule);

    @Query("DELETE FROM Schedule")
    void deleteAllSchedule();

    @Query("SELECT * FROM Schedule")
    LiveData<List<Schedule>> getAllSchedules();

    @Query("SELECT * from Schedule WHERE Schedule_Id = :scheduleId LIMIT 1")
    Schedule getScheduleByID(int scheduleId);

}
