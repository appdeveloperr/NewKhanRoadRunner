package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.husnain.newproject.SeatsInfo;

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

    @Query("Update Seats_Info set seat_status=:seat_status,BookedBy=:bookedby, IsPushed=:ispushed, Gender=:gender Where Seat_No=:seat_no")
    void updateSeatStatusBySeatNo(int seat_no, String seat_status, String bookedby, boolean ispushed, String gender);

    @Query("Select * From Seats_Info WHERE seat_status=:seat_status")
    List<SeatsInfo> SelectSeatsInfoByStatus(String seat_status);

    @Query("SELECT * FROM Seats_Info WHERE  IsPushed = 0")
    List<SeatsInfo> getUnPushedData();

}
