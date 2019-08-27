package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Ticketing_Schedule")
public class TicketingSchedule implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int Ticketing_Schedule_ID;
    private int Sr_No;
    private String TS_Date;
    private int Schedule_ID;
    private long Voucher_No;
    private int Voucher_Status;
    private int Voucher_Opened_By;
    private int Voucher_Closed_By;
    private String Voucher_Closed_Date;
    private String Departure_Time;
    private int Vehicle_ID;
    private String Driver_Name;
    private String Hostess_Name;
    private int User_Id;
    private String Access_DateTime;
    private String Access_Sys_Name;
    private int Access_Terminal_Id;
    private String Actual_Departure_Time;
    private String Guard;
    private int Is_Closed;
    private int Book_Id;
    private int Is_Pulled;
    private int ServiceType_Id;
    private boolean IsPushed;

    public TicketingSchedule(int Sr_No, String TS_Date, int Schedule_ID, long Voucher_No, int Voucher_Status, int Voucher_Opened_By, int Voucher_Closed_By, String Voucher_Closed_Date, String Departure_Time, int Vehicle_ID, String Driver_Name, String Hostess_Name, int User_Id, String Access_DateTime, String Access_Sys_Name, int Access_Terminal_Id, String Actual_Departure_Time, String Guard, int Is_Closed, int Book_Id, int Is_Pulled, int ServiceType_Id, boolean IsPushed) {
        this.Sr_No = Sr_No;
        this.TS_Date = TS_Date;
        this.Schedule_ID = Schedule_ID;
        this.Voucher_No = Voucher_No;
        this.Voucher_Status = Voucher_Status;
        this.Voucher_Opened_By = Voucher_Opened_By;
        this.Voucher_Closed_By = Voucher_Closed_By;
        this.Voucher_Closed_Date = Voucher_Closed_Date;
        this.Departure_Time = Departure_Time;
        this.Vehicle_ID = Vehicle_ID;
        this.Driver_Name = Driver_Name;
        this.Hostess_Name = Hostess_Name;
        this.User_Id = User_Id;
        this.Access_DateTime = Access_DateTime;
        this.Access_Sys_Name = Access_Sys_Name;
        this.Access_Terminal_Id = Access_Terminal_Id;
        this.Actual_Departure_Time = Actual_Departure_Time;
        this.Guard = Guard;
        this.Is_Closed = Is_Closed;
        this.Book_Id = Book_Id;
        this.Is_Pulled = Is_Pulled;
        this.ServiceType_Id = ServiceType_Id;
        this.IsPushed = IsPushed;
    }

    public boolean getIsPushed() {
        return IsPushed;
    }

    public void setIsPushed(boolean pushed) {
        IsPushed = pushed;
    }

    public void setTicketing_Schedule_ID(int ticketing_Schedule_ID) {
        this.Ticketing_Schedule_ID = ticketing_Schedule_ID;
    }

    public int getTicketing_Schedule_ID() {
        return Ticketing_Schedule_ID;
    }

    public int getSr_No() {
        return Sr_No;
    }

    public String getTS_Date() {
        return TS_Date;
    }

    public int getSchedule_ID() {
        return Schedule_ID;
    }

    public long getVoucher_No() {
        return Voucher_No;
    }

    public int getVoucher_Status() {
        return Voucher_Status;
    }

    public int getVoucher_Opened_By() {
        return Voucher_Opened_By;
    }

    public int getVoucher_Closed_By() {
        return Voucher_Closed_By;
    }

    public String getVoucher_Closed_Date() {
        return Voucher_Closed_Date;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public int getVehicle_ID() {
        return Vehicle_ID;
    }

    public String getDriver_Name() {
        return Driver_Name;
    }

    public String getHostess_Name() {
        return Hostess_Name;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public String getAccess_DateTime() {
        return Access_DateTime;
    }

    public String getAccess_Sys_Name() {
        return Access_Sys_Name;
    }

    public int getAccess_Terminal_Id() {
        return Access_Terminal_Id;
    }

    public String getActual_Departure_Time() {
        return Actual_Departure_Time;
    }

    public String getGuard() {
        return Guard;
    }

    public int getIs_Closed() {
        return Is_Closed;
    }

    public int getBook_Id() {
        return Book_Id;
    }

    public int getIs_Pulled() {
        return Is_Pulled;
    }

    public int getServiceType_Id() {
        return ServiceType_Id;
    }
}
