package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ServerMapping")
public class ServerMapping {

    @PrimaryKey
    private int ID;
    private int Ticketing_Schedule_Id;
    private int User_Id;
    private int Terminal_Id;
    private String CurrentDate;

    public ServerMapping(int ID, int Ticketing_Schedule_Id, int User_Id, int Terminal_Id, String CurrentDate) {
        this.ID = ID;
        this.Ticketing_Schedule_Id = Ticketing_Schedule_Id;
        this.User_Id = User_Id;
        this.Terminal_Id = Terminal_Id;
        this.CurrentDate = CurrentDate;
    }


    public int getID() {
        return ID;
    }

    public int getTicketing_Schedule_Id() {
        return Ticketing_Schedule_Id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public int getTerminal_Id() {
        return Terminal_Id;
    }

    public String getCurrentDate() {
        return CurrentDate;
    }
}
