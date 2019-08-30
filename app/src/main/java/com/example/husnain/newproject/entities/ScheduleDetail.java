package com.example.husnain.newproject.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Schedule_Detail")
public class ScheduleDetail {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Schedule_Detail_Id;
    private int Schedule_id;
    private int Route_Detail_ID;
    private int Sr_No;
    private int City_ID;
    private String Arrival_Time;
    private String Stay_Period;
    private String Departure_Time;
    private boolean Is_RegularTime;
    private int ServiceType_Id;
    private boolean Is_ExtraTime;

    public ScheduleDetail(int Schedule_Detail_Id, int Schedule_id, int Route_Detail_ID, int Sr_No, int City_ID, String Arrival_Time, String Stay_Period, String Departure_Time, boolean Is_RegularTime, int ServiceType_Id, boolean Is_ExtraTime) {
        this.Schedule_Detail_Id = Schedule_Detail_Id;
        this.Schedule_id = Schedule_id;
        this.Route_Detail_ID = Route_Detail_ID;
        this.Sr_No = Sr_No;
        this.City_ID = City_ID;
        this.Arrival_Time = Arrival_Time;
        this.Stay_Period = Stay_Period;
        this.Departure_Time = Departure_Time;
        this.Is_RegularTime = Is_RegularTime;
        this.ServiceType_Id = ServiceType_Id;
        this.Is_ExtraTime = Is_ExtraTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchedule_Detail_Id() {
        return Schedule_Detail_Id;
    }

    public int getSchedule_id() {
        return Schedule_id;
    }

    public int getRoute_Detail_ID() {
        return Route_Detail_ID;
    }

    public int getSr_No() {
        return Sr_No;
    }

    public int getCity_ID() {
        return City_ID;
    }

    public String getArrival_Time() {
        return Arrival_Time;
    }

    public String getStay_Period() {
        return Stay_Period;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public boolean getIs_RegularTime() {
        return Is_RegularTime;
    }

    public int getServiceType_Id() {
        return ServiceType_Id;
    }

    public boolean getIs_ExtraTime() {
        return Is_ExtraTime;
    }
}
