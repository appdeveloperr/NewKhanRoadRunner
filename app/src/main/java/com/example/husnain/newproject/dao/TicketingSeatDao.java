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

    //Issued_By
    @Query("SELECT * FROM Ticketing_Seat WHERE printed = 0 or printed = 1 and Issued_By = :userId ORDER BY Issue_Date")
    List<TicketingSeat> getMyTickets(int userId);


    @Query("UPDATE Ticketing_Seat SET printed = 1 WHERE printed = 0 and Ticketing_Seat_ID in (:ids)")
    void setPrinted(List<Integer> ids);
}
