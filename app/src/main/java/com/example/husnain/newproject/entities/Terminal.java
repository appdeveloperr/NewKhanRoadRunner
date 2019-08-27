package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Terminal")
public class Terminal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Terminal_id;
    private boolean Active;
    private String Terminal_Name;
    private String Terminal_Abbr;
    private String Terminal_Type;
    private String Terminal_Address;
    private int City_ID;
    private String Terminal_Phone;
    private String Terminal_Fax;
    private String Terminal_Email;
    private int User_Id;
    private String Access_DateTime;
    private String Access_SysName;
    private int Access_Terminal_Id;
    private int TerminalCom;

    public Terminal(int Terminal_id, boolean Active, String Terminal_Name, String Terminal_Abbr, String Terminal_Type, String Terminal_Address, int City_ID, String Terminal_Phone, String Terminal_Fax, String Terminal_Email, int User_Id, String Access_DateTime, String Access_SysName, int Access_Terminal_Id, int TerminalCom) {
        this.Terminal_id = Terminal_id;
        this.Active = Active;
        this.Terminal_Name = Terminal_Name;
        this.Terminal_Abbr = Terminal_Abbr;
        this.Terminal_Type = Terminal_Type;
        this.Terminal_Address = Terminal_Address;
        this.City_ID = City_ID;
        this.Terminal_Phone = Terminal_Phone;
        this.Terminal_Fax = Terminal_Fax;
        this.Terminal_Email = Terminal_Email;
        this.User_Id = User_Id;
        this.Access_DateTime = Access_DateTime;
        this.Access_SysName = Access_SysName;
        this.Access_Terminal_Id = Access_Terminal_Id;
        this.TerminalCom = TerminalCom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTerminal_id() {
        return Terminal_id;
    }

    public boolean getActive() {
        return Active;
    }

    public String getTerminal_Name() {
        return Terminal_Name;
    }

    public String getTerminal_Abbr() {
        return Terminal_Abbr;
    }

    public String getTerminal_Type() {
        return Terminal_Type;
    }

    public String getTerminal_Address() {
        return Terminal_Address;
    }

    public int getCity_ID() {
        return City_ID;
    }

    public String getTerminal_Phone() {
        return Terminal_Phone;
    }

    public String getTerminal_Fax() {
        return Terminal_Fax;
    }

    public String getTerminal_Email() {
        return Terminal_Email;
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

    public int getTerminalCom() {
        return TerminalCom;
    }
}
