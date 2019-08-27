package com.example.husnain.newproject;

import android.app.Application;

/**
 * Created by Husnain on 5/28/2018.
 */

public class Global extends Application {

    public static int Vehicle_ID = 0;

    public static int User_ID = 0;
    public static String User_Name = "";
    public static int NoOfSeats = 0;
    public static int VoucherServiceTypeId = 0;
    public static int VoucherScheduleId = 0;
    public static String VoucherTime = "";
    public static String VoucherDate = "";
    public static long VoucherNo = 0;
    public static long TicketingScheduleID = 0;
    public static String RouteName = "";
    public static String Vehicle_No = "";
    public static String Vehicle_Name = "";
    public static boolean IsSuperAdmin = false;
    public static boolean IsAdmin = false;


    public static double Lattitude = 0.0;
    public static double Longitude = 0.0;


    private boolean Departure;
    private boolean Fare;
    private boolean Business;
    private boolean Economy;
    private int Time;
    private String Date;

    public Global()
    {
        Departure = false;
        Fare = false;
        Business = false;
        Economy = false;
        Time = 0;
        Date="";
    }

    public Global(boolean departure, boolean fare, boolean business, boolean economy, int time,String date) {
        Departure = departure;
        Fare = fare;
        Business = business;
        Economy = economy;
        Time = time;
        Date = date;
    }

    public void setDeparture(boolean departure) {
        Departure = departure;
    }

    public void setFare(boolean fare) {
        Fare = fare;
    }

    public void setBusiness(boolean business) {
        Business = business;
    }

    public void setEconomy(boolean economy) {
        Economy = economy;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void setDate(String date) {
        Date = date;
    }

    public boolean isDeparture() {
        return Departure;
    }

    public boolean isFare() {
        return Fare;
    }

    public boolean isBusiness() {
        return Business;
    }

    public boolean isEconomy() {
        return Economy;
    }

    public int getTime() {
        return Time;
    }

    public String getDate() {
        return Date;
    }
}
