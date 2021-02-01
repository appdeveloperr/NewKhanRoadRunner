package com.example.husnain.newproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by Husnain on 5/3/2018.
 */
@Entity(tableName = "Seats_Info")
public class SeatsInfo implements Serializable{

//    private int id;

    private int seat_id;
    private String seat_name;
    private String seat_status;
    @PrimaryKey()
    private int Seat_No;
    private float fare;
    private String Gender;
    private String BookedBy;
    private boolean IsPushed;

    public SeatsInfo(int seat_id, String seat_name, String seat_status, int Seat_No, float fare, String Gender, String BookedBy, boolean IsPushed) {
        this.seat_id = seat_id;
        this.seat_name = seat_name;
        this.seat_status = seat_status;
        this.Seat_No = Seat_No;
        this.fare = fare;
        this.Gender = Gender;
        this.BookedBy = BookedBy;
        this.IsPushed = IsPushed;
    }

    public boolean getIsPushed() {
        return IsPushed;
    }

    public void setIsPushed(boolean pushed) {
        this.IsPushed = pushed;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }

    public String getBookedBy() {
        return BookedBy;
    }

    public void setBookedBy(String bookedBy) {
        BookedBy = bookedBy;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public void setSeat_name(String seat_name) {
        this.seat_name = seat_name;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
    }

    public void setSeat_No(int seat_No) {
        Seat_No = seat_No;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public String getSeat_name() {
        return seat_name;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public int getSeat_No() {
        return Seat_No;
    }

    public float getFare() {
        return fare;
    }

    public String getGender() {
        return Gender;
    }
}
