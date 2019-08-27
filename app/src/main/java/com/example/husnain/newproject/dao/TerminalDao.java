package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.ServiceTypeExtraFare;
import com.example.husnain.newproject.entities.Terminal;

import java.util.List;

@Dao
public interface TerminalDao {

    @Insert
    void insert(Terminal Terminal);

    @Query("DELETE FROM Terminal")
    void deleteAllTerminal();

    @Query("SELECT * FROM Terminal")
    LiveData<List<Terminal>> getAllTerminal();

}
