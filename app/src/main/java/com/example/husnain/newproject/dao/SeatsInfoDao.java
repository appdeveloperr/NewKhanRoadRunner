package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.husnain.newproject.SeatsInfo;
import com.example.husnain.newproject.entities.City;
import com.example.husnain.newproject.entities.TicketingSeat;

import java.util.List;

@Dao
public interface SeatsInfoDao {

    @Insert
    void insert(SeatsInfo seatsInfo);

    @Query("SELECT * FROM Seats_Info")
    LiveData<List<SeatsInfo>> getAllSeatsInfo();

    @Query("SELECT * FROM Seats_Info")
    List<SeatsInfo> getAllSeatsInfoList();

    @Query("DELETE FROM Seats_Info")
    void deleteAllSeatsInfo();

    @Update
    void update(SeatsInfo seatsInfo);

    @Query("Update Seats_Info set seat_status=:seat_status,BookedBy=:bookedby Where Seat_No=:seat_no")
    void updateSeatStatusBySeatNo(int seat_no, String seat_status, String bookedby);

    @Query("Select * From Seats_Info WHERE seat_status=:seat_status")
    List<SeatsInfo> SelectSeatsInfoByStatus(String seat_status);

}
