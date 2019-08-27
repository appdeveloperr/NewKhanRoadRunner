package com.example.husnain.newproject;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Husnain on 5/2/2018.
 */
//"departureTime":"06:00","arrivalTime":"08:15","fare":550.00,"busType":"Business Class 34","stops":0,
// "status":0,"Seats":26,"status1":"OK","Schedule_Id":4,"MaskDate":"","MaskRouteCode":6,
// "MaskTerminalId":6,"ExcludedTerminalsList":"9,33","cancellationpolicy":"",
// "Amenities":"","DropingPoints":"","QuerydepartureTime":"06:00"},
public class Schedule_Info implements Serializable{

    private String departureTime;
    private String arrivalTime;
    private float fare;
    private String busType;
    private int Seats;
    private String status1;
    private int Schedule_Id;
    private String MaskDate;
    private String MaskRouteCode;
    private int MaskTerminalId;
    private String ExcludedTerminalsList;
    private String QuerydepartureTime;


    public Schedule_Info(String departureTime, String arrivalTime, float fare, String busType, int seats, String status1, int schedule_Id,String maskDate, String maskRouteCode, int maskTerminalId, String excludedTerminalsList, String querydepartureTime) {
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.fare = fare;
        this.busType = busType;
        Seats = seats;
        this.status1 = status1;
        Schedule_Id = schedule_Id;
        MaskDate = maskDate;
        MaskRouteCode = maskRouteCode;
        MaskTerminalId = maskTerminalId;
        ExcludedTerminalsList = excludedTerminalsList;
        QuerydepartureTime = querydepartureTime;
    }

    public void setMaskDate(String maskDate) {
        MaskDate = maskDate;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public void setSeats(int seats) {
        Seats = seats;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public void setSchedule_Id(int schedule_Id) {
        Schedule_Id = schedule_Id;
    }

    public void setMaskRouteCode(String maskRouteCode) {
        MaskRouteCode = maskRouteCode;
    }

    public void setMaskTerminalId(int maskTerminalId) {
        MaskTerminalId = maskTerminalId;
    }

    public void setExcludedTerminalsList(String excludedTerminalsList) {
        ExcludedTerminalsList = excludedTerminalsList;
    }

    public void setQuerydepartureTime(String querydepartureTime) {
        QuerydepartureTime = querydepartureTime;
    }

    public String getMaskDate() {
        return MaskDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public float getFare() {
        return fare;
    }

    public String getBusType() {
        return busType;
    }

    public int getSeats() {
        return Seats;
    }

    public String getStatus1() {
        return status1;
    }

    public int getSchedule_Id() {
        return Schedule_Id;
    }

    public String getMaskRouteCode() {
        return MaskRouteCode;
    }

    public int getMaskTerminalId() {
        return MaskTerminalId;
    }

    public String getExcludedTerminalsList() {
        return ExcludedTerminalsList;
    }

    public String getQuerydepartureTime() {
        return QuerydepartureTime;
    }
}
