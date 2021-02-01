package com.example.husnain.newproject.activities.cashdetail;

import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.City;
import com.example.husnain.newproject.entities.TicketingSeat;

public class CashDetail {

    private TicketingSeat seat;
    private City source;
    private City destination;
    private String sourceAbbr;
    private String destinationAbbr;
    private int seatNo;
    private double fare;
    private double discount;
    private double amount;

    public CashDetail(@NonNull TicketingSeat seat) {
        this.seat = seat;
        this.seatNo = seat.getSeat_No();
        this.fare = seat.getFare();
        this.discount = seat.getDiscount();
        this.amount = this.fare - this.discount;
    }

    public TicketingSeat getSeat() {
        return seat;
    }

    public City getSource() {
        return source;
    }

    public void setSource(@NonNull City source) {
        this.source = source;
        if (source != null) {
            this.sourceAbbr = source.getCity_Abbr();
        }
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(@NonNull City destination) {
        this.destination = destination;
        if (destination != null) {
            this.destinationAbbr = destination.getCity_Abbr();
        }
    }

    public String getSourceAbbr() {
        return sourceAbbr == null ? "" : sourceAbbr;
    }

    public void setSourceAbbr(String sourceAbbr) {
        this.sourceAbbr = sourceAbbr;
    }

    public String getDestinationAbbr() {
        return destinationAbbr == null ? "" : destinationAbbr;
    }

    public void setDestinationAbbr(String destinationAbbr) {
        this.destinationAbbr = destinationAbbr;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CharSequence getSeat_No() {
        return this.seatNo + "";
    }
}
