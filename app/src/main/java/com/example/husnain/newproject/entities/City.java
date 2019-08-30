package com.example.husnain.newproject.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "City")
public class City {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int City_Id;

    private String City_Name;

    private String City_Abbr;

    private boolean Active;

    private int User_Id;

    private String Access_DateTime;

    private String Access_Sys_Name;

    private int Access_Terminal_Id;

    private String Destination_Color;


    public City(int City_Id, String City_Name, String City_Abbr, boolean Active, int User_Id, String Access_DateTime, String Access_Sys_Name, int Access_Terminal_Id, String Destination_Color) {
        this.City_Id = City_Id;
        this.City_Name = City_Name;
        this.City_Abbr = City_Abbr;
        this.Active = Active;
        this.User_Id = User_Id;
        this.Access_DateTime = Access_DateTime;
        this.Access_Sys_Name = Access_Sys_Name;
        this.Access_Terminal_Id = Access_Terminal_Id;
        this.Destination_Color = Destination_Color;

    }

    public int getId() {
        return id;
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

    public void setAccess_DateTime(String access_DateTime) {
        Access_DateTime = access_DateTime;
    }

    public void setAccess_Sys_Name(String access_Sys_Name) {
        Access_Sys_Name = access_Sys_Name;
    }

    public void setAccess_Terminal_Id(int access_Terminal_Id) {
        Access_Terminal_Id = access_Terminal_Id;
    }

    public void setDestination_Color(String destination_Color) {
        Destination_Color = destination_Color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity_Id() {
        return City_Id;
    }

    public String getCity_Name() {
        return this.City_Name;
    }

    public String getCity_Abbr() {
        return City_Abbr;
    }

    public boolean getActive() {
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
