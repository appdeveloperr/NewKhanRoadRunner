package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.ScheduleDetail;

import java.util.List;

@Dao
public interface ScheduleDetailDao {

    @Insert
    void insert(ScheduleDetail scheduleDetail);

    @Query("DELETE FROM Schedule_Detail")
    void deleteAllScheduleDetail();

    @Query("SELECT * FROM Schedule_Detail")
    LiveData<List<ScheduleDetail>> getAllScheduleDetail();

}
