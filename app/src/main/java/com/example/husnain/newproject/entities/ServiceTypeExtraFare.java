package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ServiceTypeExtraFare")
public class ServiceTypeExtraFare {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int Extra_Fare_Id;
    private int Source_City_Id;
    private int Destination_City_Id;
    private double Fare_Amount;
    private int User_Id;
    private String Access_Date;
    private String Computer_Name;
    private int Terminal_Id;
    private int Ticket_Refund;
    private int Ticket_Change;
    private int Next_Departure;
    private int KM;
    private int Points;
    private int ServiceType_Id;
    private int Seat_List;

    public ServiceTypeExtraFare(int Extra_Fare_Id, int Source_City_Id, int Destination_City_Id, double Fare_Amount, int User_Id, String Access_Date, String Computer_Name, int Terminal_Id, int Ticket_Refund, int Ticket_Change, int Next_Departure, int KM, int Points, int ServiceType_Id, int Seat_List) {
        this.Extra_Fare_Id = Extra_Fare_Id;
        this.Source_City_Id = Source_City_Id;
        this.Destination_City_Id = Destination_City_Id;
        this.Fare_Amount = Fare_Amount;
        this.User_Id = User_Id;
        this.Access_Date = Access_Date;
        this.Computer_Name = Computer_Name;
        this.Terminal_Id = Terminal_Id;
        this.Ticket_Refund = Ticket_Refund;
        this.Ticket_Change = Ticket_Change;
        this.Next_Departure = Next_Departure;
        this.KM = KM;
        this.Points = Points;
        this.ServiceType_Id = ServiceType_Id;
        this.Seat_List = Seat_List;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExtra_Fare_Id() {
        return Extra_Fare_Id;
    }

    public int getSource_City_Id() {
        return Source_City_Id;
    }

    public int getDestination_City_Id() {
        return Destination_City_Id;
    }

    public double getFare_Amount() {
        return Fare_Amount;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public String getAccess_Date() {
        return Access_Date;
    }

    public String getComputer_Name() {
        return Computer_Name;
    }

    public int getTerminal_Id() {
        return Terminal_Id;
    }

    public int getTicket_Refund() {
        return Ticket_Refund;
    }

    public int getTicket_Change() {
        return Ticket_Change;
    }

    public int getNext_Departure() {
        return Next_Departure;
    }

    public int getKM() {
        return KM;
    }

    public int getPoints() {
        return Points;
    }

    public int getServiceType_Id() {
        return ServiceType_Id;
    }

    public int getSeat_List() {
        return Seat_List;
    }
}
