package com.example.husnain.newproject.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Fare_Details")
public class FareDetails {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Fare_Id;
    private int Source_City_Id;
    private int Destination_City_Id;
    private double Fare_Amount;
    private int User_Id;
    private String Access_Date;
    private String Computer_Name;
    private int Terminal_Id;
    private double Ticket_Refund;
    private double Ticket_Change;
    private double Next_Departure;
    private int ServiceType_Id;
    private int Points;
    private int KM;


    public FareDetails(int Fare_Id, int Source_City_Id, int Destination_City_Id, double Fare_Amount, int User_Id, String Access_Date, String Computer_Name, int Terminal_Id, double Ticket_Refund, double Ticket_Change, double Next_Departure, int ServiceType_Id, int Points, int KM) {
        this.Fare_Id = Fare_Id;
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
        this.ServiceType_Id = ServiceType_Id;
        this.Points = Points;
        this.KM = KM;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getFare_Id() {
        return Fare_Id;
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

    public double getTicket_Refund() {
        return Ticket_Refund;
    }

    public double getTicket_Change() {
        return Ticket_Change;
    }

    public double getNext_Departure() {
        return Next_Departure;
    }

    public int getServiceType_Id() {
        return ServiceType_Id;
    }

    public int getPoints() {
        return Points;
    }

    public int getKM() {
        return KM;
    }
}
