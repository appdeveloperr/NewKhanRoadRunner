package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Schedule")
public class Schedule {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Schedule_Id;
    private String Schedule_Code;
    private boolean Active;
    private String Schedule_Title;
    private String Sch_Date;
    private String Sch_wef;
    private int Route_id;
    private int User_Id;
    private String Access_DateTime;
    private String Access_SysName;
    private int Access_Terminal_Id;
    private int Comission;
    private int ExtraFare;

    public Schedule(int Schedule_Id, String Schedule_Code, boolean Active, String Schedule_Title, String Sch_Date, String Sch_wef, int Route_id, int User_Id, String Access_DateTime, String Access_SysName, int Access_Terminal_Id, int Comission, int ExtraFare) {
        this.Schedule_Id = Schedule_Id;
        this.Schedule_Code = Schedule_Code;
        this.Active = Active;
        this.Schedule_Title = Schedule_Title;
        this.Sch_Date = Sch_Date;
        this.Sch_wef = Sch_wef;
        this.Route_id = Route_id;
        this.User_Id = User_Id;
        this.Access_DateTime = Access_DateTime;
        this.Access_SysName = Access_SysName;
        this.Access_Terminal_Id = Access_Terminal_Id;
        this.Comission = Comission;
        this.ExtraFare = ExtraFare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchedule_Id() {
        return Schedule_Id;
    }

    public String getSchedule_Code() {
        return Schedule_Code;
    }

    public boolean getActive() {
        return Active;
    }

    public String getSchedule_Title() {
        return Schedule_Title;
    }

    public String getSch_Date() {
        return Sch_Date;
    }

    public String getSch_wef() {
        return Sch_wef;
    }

    public int getRoute_id() {
        return Route_id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public String getAccess_DateTime() {
        return Access_DateTime;
    }

    public String getAccess_SysName() {
        return Access_SysName;
    }

    public int getAccess_Terminal_Id() {
        return Access_Terminal_Id;
    }

    public int getComission() {
        return Comission;
    }

    public int getExtraFare() {
        return ExtraFare;
    }
}
