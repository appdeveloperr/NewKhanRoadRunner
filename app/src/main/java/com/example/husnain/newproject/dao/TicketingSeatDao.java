package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
