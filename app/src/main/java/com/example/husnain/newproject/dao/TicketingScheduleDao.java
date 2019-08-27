package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.husnain.newproject.entities.Terminal;
import com.example.husnain.newproject.entities.TicketingSchedule;

import java.util.List;

@Dao
public interface TicketingScheduleDao {

    @Insert
    long insert(TicketingSchedule ticketingSchedule);

    @Query("DELETE FROM Ticketing_Schedule")
    void deleteAllTicketingSchedule();

    @Update
    void update(TicketingSchedule ticketingSchedule);

    @Query("SELECT * FROM Ticketing_Schedule")
    LiveData<List<TicketingSchedule>> getAllTicketingSchedule();

    @Query("SELECT * FROM Ticketing_Schedule WHERE IsPushed = 0")
    List<TicketingSchedule> getUnPushedData();

    @Query("DELETE FROM Ticketing_Schedule")
    void DeleteAllTIcketingSchedule();

    @Query("SELECT * FROM Ticketing_Schedule WHERE Schedule_ID = :schedule_id AND Departure_Time = :dept_time AND TS_Date = :date AND ServiceType_Id = :srv_type_id AND Voucher_No = :voucher_no")
    LiveData<List<TicketingSchedule>> getSingleRow(int schedule_id, String dept_time,String date, int srv_type_id, long voucher_no);

    @Query("SELECT * FROM Ticketing_Schedule WHERE Schedule_ID = :schedule_id AND Departure_Time = :dept_time AND TS_Date = :date AND ServiceType_Id = :srv_type_id AND Voucher_No = :voucher_no")
    TicketingSchedule getPrevRecord(int schedule_id, String dept_time,String date, int srv_type_id, long voucher_no);

}
