package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.ScheduleDetail;
import com.example.husnain.newproject.entities.ServerMapping;

import java.util.List;

@Dao
public interface ServerMappingDao {

    @Insert
    void insert(ServerMapping serverMapping);

    @Query("DELETE FROM ServerMapping")
    void deleteAllServerMapping();

    @Query("SELECT * FROM ServerMapping")
    LiveData<List<ServerMapping>> getAllServerMapping();

}
