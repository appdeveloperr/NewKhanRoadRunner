package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.husnain.newproject.entities.TicketingSchedule;
import com.example.husnain.newproject.entities.TicketingSeat;

import java.util.List;

@Dao
public interface TicketingSeatDao {

    @Insert
    void insert(TicketingSeat ticketingSeat);

    @Query("DELETE FROM Ticketing_Seat")
    void deleteAllTicketingSeat();

    @Update
    void update(TicketingSeat ticketingSeat);

    @Delete
    void delete(TicketingSeat ticketingSchedule);

    @Query("DELETE FROM Ticketing_Seat")
    void DeleteAllTicketingSeats();

    @Query("SELECT * FROM Ticketing_Seat")
    LiveData<List<TicketingSeat>> getAllTicketingSeats();

    @Query("SELECT * FROM Ticketing_Seat WHERE IsPushed = 0")
    List<TicketingSeat> getUnPushedData();




}
