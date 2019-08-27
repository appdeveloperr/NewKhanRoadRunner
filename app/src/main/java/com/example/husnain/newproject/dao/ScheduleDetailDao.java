package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.Schedule;
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
