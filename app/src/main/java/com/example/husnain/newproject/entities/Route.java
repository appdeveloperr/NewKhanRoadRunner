package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Route")
public class Route {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Route_Id;
    private boolean Active;
    private String Route_Code;
    private String Route_Name;
    private int User_Id;
    private String Access_DateTime;
    private String Access_Sys_Name;
    private int Access_Terminal_Id;

    public Route(int Route_Id, boolean Active, String Route_Code, String Route_Name, int User_Id, String Access_DateTime, String Access_Sys_Name, int Access_Terminal_Id) {
        this.Route_Id = Route_Id;
        this.Active = Active;
        this.Route_Code = Route_Code;
        this.Route_Name = Route_Name;
        this.User_Id = User_Id;
        this.Access_DateTime = Access_DateTime;
        this.Access_Sys_Name = Access_Sys_Name;
        this.Access_Terminal_Id = Access_Terminal_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoute_Id() {
        return Route_Id;
    }

    public boolean getActive() {
        return Active;
    }

    public String getRoute_Code() {
        return Route_Code;
    }

    public String getRoute_Name() {
        return Route_Name;
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
}
