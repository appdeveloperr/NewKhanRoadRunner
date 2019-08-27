package com.example.husnain.newproject;

/**
 * Created by Husnain on 4/27/2018.
 */

public class CityInfo {

    private int City_Id;
    private String City_Name;
    private String City_Abbr;
    private boolean Active;
    private int User_Id;
    private String Access_DateTime;
    private String Access_Sys_Name;
    private int Access_Terminal_Id;
    private String Destination_Color;

    public CityInfo(int city_Id, String city_Name, String city_Abbr, boolean active, int user_Id, String access_DateTime, String access_Sys_Name, int access_Terminal_Id, String destination_Color) {
        City_Id = city_Id;
        City_Name = city_Name;
        City_Abbr = city_Abbr;
        Active = active;
        User_Id = user_Id;
        Access_DateTime = access_DateTime;
        Access_Sys_Name = access_Sys_Name;
        Access_Terminal_Id = access_Terminal_Id;
        Destination_Color = destination_Color;
    }

    public void setCity_Id(int city_Id) {
        City_Id = city_Id;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }

    public void setCity_Abbr(String city_Abbr) {
        City_Abbr = city_Abbr;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public void setUser_Id(int user_Id) {
        User_Id = user_Id;
    }

    public void setAccess_Sys_Name(String access_Sys_Name) {
        Access_Sys_Name = access_Sys_Name;
    }

    public void setAccess_DateTime(String access_DateTime) {
        Access_DateTime = access_DateTime;
    }

    public void setAccess_Terminal_Id(int access_Terminal_Id) {
        Access_Terminal_Id = access_Terminal_Id;
    }

    public void setDestination_Color(String destination_Color) {
        Destination_Color = destination_Color;
    }


    public int getCity_Id() {
        return City_Id;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public String getCity_Abbr() {
        return City_Abbr;
    }

    public boolean isActive() {
        return Active;
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

    public String getDestination_Color() {
        return Destination_Color;
    }
}
