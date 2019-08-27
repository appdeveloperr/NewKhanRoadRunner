package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Vehicle")
public class Vehicle {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Vehicle_id;
    private String Veh_Code;
    private boolean Active;
    private String Veh_Type;
    private String Veh_Name;
    private int Seats;
    private boolean IsCommissioned;
    private String Comm_Owner;
    private String Comm_Contact_Person;
    private String Comm_Contact_No;
    private double Commission_Rate;
    private String Registration_No;
    private String Engine_No;
    private String Chasis_No;
    private String Model;
    private String Maker;
    private int Driver_ID;
    private String Driver_Name;
    private String Remarks;
    private int User_Id;
    private String Access_DateTime;
    private String Access_SysName;
    private int Access_Terminal_Id;

    public Vehicle(int Vehicle_id, String Veh_Code, boolean Active, String Veh_Type, String Veh_Name, int Seats, boolean IsCommissioned, String Comm_Owner, String Comm_Contact_Person, String Comm_Contact_No, double Commission_Rate, String Registration_No, String Engine_No, String Chasis_No, String Model, String Maker, int Driver_ID, String Driver_Name, String Remarks, int User_Id, String Access_DateTime, String Access_SysName, int Access_Terminal_Id) {
        this.Vehicle_id = Vehicle_id;
        this.Veh_Code = Veh_Code;
        this.Active = Active;
        this.Veh_Type = Veh_Type;
        this.Veh_Name = Veh_Name;
        this.Seats = Seats;
        this.IsCommissioned = IsCommissioned;
        this.Comm_Owner = Comm_Owner;
        this.Comm_Contact_Person = Comm_Contact_Person;
        this.Comm_Contact_No = Comm_Contact_No;
        this.Commission_Rate = Commission_Rate;
        this.Registration_No = Registration_No;
        this.Engine_No = Engine_No;
        this.Chasis_No = Chasis_No;
        this.Model = Model;
        this.Maker = Maker;
        this.Driver_ID = Driver_ID;
        this.Driver_Name = Driver_Name;
        this.Remarks = Remarks;
        this.User_Id = User_Id;
        this.Access_DateTime = Access_DateTime;
        this.Access_SysName = Access_SysName;
        this.Access_Terminal_Id = Access_Terminal_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicle_id() {
        return Vehicle_id;
    }

    public String getVeh_Code() {
        return Veh_Code;
    }

    public boolean getActive() {
        return Active;
    }

    public String getVeh_Type() {
        return Veh_Type;
    }

    public String getVeh_Name() {
        return Veh_Name;
    }

    public int getSeats() {
        return Seats;
    }

    public boolean getIsCommissioned() {
        return IsCommissioned;
    }

    public String getComm_Owner() {
        return Comm_Owner;
    }

    public String getComm_Contact_Person() {
        return Comm_Contact_Person;
    }

    public String getComm_Contact_No() {
        return Comm_Contact_No;
    }

    public double getCommission_Rate() {
        return Commission_Rate;
    }

    public String getRegistration_No() {
        return Registration_No;
    }

    public String getEngine_No() {
        return Engine_No;
    }

    public String getChasis_No() {
        return Chasis_No;
    }

    public String getModel() {
        return Model;
    }

    public String getMaker() {
        return Maker;
    }

    public int getDriver_ID() {
        return Driver_ID;
    }

    public String getDriver_Name() {
        return Driver_Name;
    }

    public String getRemarks() {
        return Remarks;
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
}
