package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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
