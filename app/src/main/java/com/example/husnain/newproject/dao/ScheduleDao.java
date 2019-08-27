package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.RouteDetail;
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
