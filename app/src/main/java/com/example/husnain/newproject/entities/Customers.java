package com.example.husnain.newproject.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Customers")
public class Customers {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Customer_Id;
    private String First_Name;
    private String Last_Name;
    private String EmailAddress;
    private String CNIC;
    private String MobileNo;
    private String DateofBirth;
    private String Address;
    private String AddedDate;
    private String Cust_Code;
    private int Is_SMS_Sent;
    private int SentForPrinting;
    private int Printed;
    private int Dispatched;

    public Customers(int Customer_Id, String First_Name, String Last_Name, String EmailAddress, String CNIC,
                     String MobileNo, String DateofBirth, String Address, String AddedDate, String Cust_Code, int Is_SMS_Sent,
                     int SentForPrinting, int Printed, int Dispatched) {
        this.Customer_Id = Customer_Id;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.EmailAddress = EmailAddress;
        this.CNIC = CNIC;
        this.MobileNo = MobileNo;
        this.DateofBirth = DateofBirth;
        this.Address = Address;
        this.AddedDate = AddedDate;
        this.Cust_Code = Cust_Code;
        this.Is_SMS_Sent = Is_SMS_Sent;
        this.SentForPrinting = SentForPrinting;
        this.Printed = Printed;
        this.Dispatched = Dispatched;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_Id() {
        return Customer_Id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getCNIC() {
        return CNIC;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public String getAddress() {
        return Address;
    }

    public String getAddedDate() {
        return AddedDate;
    }

    public String getCust_Code() {
        return Cust_Code;
    }

    public int getIs_SMS_Sent() {
        return Is_SMS_Sent;
    }

    public int getSentForPrinting() {
        return SentForPrinting;
    }

    public int getPrinted() {
        return Printed;
    }

    public int getDispatched() {
        return Dispatched;
    }
}
