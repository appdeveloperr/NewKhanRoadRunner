package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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

    @Query("SELECT * FROM Terminal WHERE Terminal_id = :terminal")
    Terminal getTerminalById(int terminal);

}
