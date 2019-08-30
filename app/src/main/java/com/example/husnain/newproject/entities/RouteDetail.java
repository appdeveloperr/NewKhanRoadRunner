package com.example.husnain.newproject.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Route_Detail")
public class RouteDetail {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Route_Detail_ID;
    private int Route_id;
    private int Sr_No;
    private int City_ID;
    private String Travel_Time;
    private String Stay_Time;
    private double Fare;

    public RouteDetail(int Route_Detail_ID, int Route_id, int Sr_No, int City_ID, String Travel_Time, String Stay_Time, double Fare) {
        this.Route_Detail_ID = Route_Detail_ID;
        this.Route_id = Route_Detail_ID;
        this.Sr_No = Sr_No;
        this.City_ID = City_ID;
        this.Travel_Time = Travel_Time;
        this.Stay_Time = Stay_Time;
        this.Fare = Fare;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getRoute_Detail_ID() {
        return Route_Detail_ID;
    }

    public int getRoute_id() {
        return Route_id;
    }

    public int getSr_No() {
        return Sr_No;
    }

    public int getCity_ID() {
        return City_ID;
    }

    public String getTravel_Time() {
        return Travel_Time;
    }

    public String getStay_Time() {
        return Stay_Time;
    }

    public double getFare() {
        return Fare;
    }
}
