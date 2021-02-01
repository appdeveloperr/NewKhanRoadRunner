package com.example.husnain.newproject.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Ticketing_Seat")
public class TicketingSeat implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int Ticketing_Seat_ID;
    private long Ticketing_Schedule_ID;
    private int Seat_No; //0
    private int Status; //0
    private String Issue_Date; // mobile date
    private int Issue_Terminal; // 0
    private int Issued_By; // Login User
    private int Source_ID;
    private int Destination_ID;
    private String Passenger_Name; // null
    private String Contact_No; // null
    private double Fare;
    private double Discount;
    private String Ticket_Sr_No; // null
    private int Is_bookedSold; //0
    private int Booked_User; // 0
    private int Is_SMS_Sent; // 0
    private String CNIC; //null
    private String Gender; // null
    private int Is_Transit; // 0
    private int Shift_User_Id; // 0
    private String PNR_No; //null
    private int Telenor; // 0
    private String PaymentDate; // null
    private int IsMissed; // 0
    private String ChangeTicket_No; //null
    private String CollectionPoint; // null
    private String ChangeType; // null
    private int Route_Sr_No; //0
    private int Operator_Id; //0
    private int Customer_Id; //0
    private int Points; //0
    private int Is_OnlinePrinted; // 0
    private int OnlinePrinter_UserId; //0
    private int OnlinePrint_Terminal_Id; //0
    private String OnlinePrint_Date; //null
    private String Invoice_Id; // null
    private int Is_Online; //0
    private Double Latitude; //null
    private Double Longitude; //null
    private boolean IsPushed;
    private boolean printed;
//
    public TicketingSeat(long Ticketing_Schedule_ID, int Seat_No, int Status, String Issue_Date, int Issue_Terminal, int Issued_By, int Source_ID, int Destination_ID, String Passenger_Name, String Contact_No, double Fare, double Discount, String Ticket_Sr_No, int Is_bookedSold, int Booked_User, int Is_SMS_Sent, String CNIC, String Gender, int Is_Transit, int Shift_User_Id, String PNR_No, int Telenor, String PaymentDate, int IsMissed, String ChangeTicket_No, String CollectionPoint, String ChangeType, int Route_Sr_No, int Operator_Id, int Customer_Id, int Points, int Is_OnlinePrinted, int OnlinePrinter_UserId, int OnlinePrint_Terminal_Id, String OnlinePrint_Date, String Invoice_Id, int Is_Online, Double Latitude, Double Longitude, boolean IsPushed) {
        this.Ticketing_Schedule_ID = Ticketing_Schedule_ID;
        this.Seat_No = Seat_No;
        this.Status = Status;
        this.Issue_Date = Issue_Date;
        this.Issue_Terminal = Issue_Terminal;
        this.Issued_By = Issued_By;
        this.Source_ID = Source_ID;
        this.Destination_ID = Destination_ID;
        this.Passenger_Name = Passenger_Name;
        this.Contact_No = Contact_No;
        this.Fare = Fare;
        this.Discount = Discount;
        this.Ticket_Sr_No = Ticket_Sr_No;
        this.Is_bookedSold = Is_bookedSold;
        this.Booked_User = Booked_User;
        this.Is_SMS_Sent = Is_SMS_Sent;
        this.CNIC = CNIC;
        this.Gender = Gender;
        this.Is_Transit = Is_Transit;
        this.Shift_User_Id = Shift_User_Id;
        this.PNR_No = PNR_No;
        this.Telenor = Telenor;
        this.PaymentDate = PaymentDate;
        this.IsMissed = IsMissed;
        this.ChangeTicket_No = ChangeTicket_No;
        this.CollectionPoint = CollectionPoint;
        this.ChangeType = ChangeType;
        this.Route_Sr_No = Route_Sr_No;
        this.Operator_Id = Operator_Id;
        this.Customer_Id = Customer_Id;
        this.Points = Points;
        this.Is_OnlinePrinted = Is_OnlinePrinted;
        this.OnlinePrinter_UserId = OnlinePrinter_UserId;
        this.OnlinePrint_Terminal_Id = OnlinePrint_Terminal_Id;
        this.OnlinePrint_Date = OnlinePrint_Date;
        this.Invoice_Id = Invoice_Id;
        this.Is_Online = Is_Online;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.IsPushed = IsPushed;
    }

    public TicketingSeat(int seat_No, String passenger_Name, double fare, int source_ID, int destination_ID) {
        Seat_No = seat_No;
        Passenger_Name = passenger_Name;
        Fare = fare;
        this.Source_ID = source_ID;
        this.Destination_ID = destination_ID;
    }

    public boolean isPrinted() {
        return printed;
    }

    public void setPrinted(boolean printed) {
        this.printed = printed;
    }

    public boolean getIsPushed() {
        return IsPushed;
    }

    public void setIsPushed(boolean pushed) {
        IsPushed = pushed;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double latitude) {
        Latitude = latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double longitude) {
        Longitude = longitude;
    }

    public void setTicketing_Seat_ID(int ticketing_Seat_ID) {
        this.Ticketing_Seat_ID = ticketing_Seat_ID;
    }

    public int getTicketing_Seat_ID() {
        return Ticketing_Seat_ID;
    }

    public long getTicketing_Schedule_ID() {
        return Ticketing_Schedule_ID;
    }

    public int getSeat_No() {
        return Seat_No;
    }

    public int getStatus() {
        return Status;
    }

    public String getIssue_Date() {
        return Issue_Date;
    }

    public int getIssue_Terminal() {
        return Issue_Terminal;
    }

    public int getIssued_By() {
        return Issued_By;
    }

    public int getSource_ID() {
        return Source_ID;
    }

    public int getDestination_ID() {
        return Destination_ID;
    }

    public String getPassenger_Name() {
        return Passenger_Name;
    }

    public String getContact_No() {
        return Contact_No;
    }

    public double getFare() {
        return Fare;
    }

    public String getTicket_Sr_No() {
        return Ticket_Sr_No;
    }

    public int getIs_bookedSold() {
        return Is_bookedSold;
    }

    public int getBooked_User() {
        return Booked_User;
    }

    public int getIs_SMS_Sent() {
        return Is_SMS_Sent;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getGender() {
        return Gender;
    }

    public int getIs_Transit() {
        return Is_Transit;
    }

    public int getShift_User_Id() {
        return Shift_User_Id;
    }

    public String getPNR_No() {
        return PNR_No;
    }

    public int getTelenor() {
        return Telenor;
    }

    public String getPaymentDate() {
        return PaymentDate;
    }

    public int getIsMissed() {
        return IsMissed;
    }

    public String getChangeTicket_No() {
        return ChangeTicket_No;
    }

    public String getCollectionPoint() {
        return CollectionPoint;
    }

    public String getChangeType() {
        return ChangeType;
    }

    public int getRoute_Sr_No() {
        return Route_Sr_No;
    }

    public int getOperator_Id() {
        return Operator_Id;
    }

    public int getCustomer_Id() {
        return Customer_Id;
    }

    public int getPoints() {
        return Points;
    }

    public int getIs_OnlinePrinted() {
        return Is_OnlinePrinted;
    }

    public int getOnlinePrinter_UserId() {
        return OnlinePrinter_UserId;
    }

    public int getOnlinePrint_Terminal_Id() {
        return OnlinePrint_Terminal_Id;
    }

    public String getOnlinePrint_Date() {
        return OnlinePrint_Date;
    }

    public String getInvoice_Id() {
        return Invoice_Id;
    }

    public int getIs_Online() {
        return Is_Online;
    }
}
