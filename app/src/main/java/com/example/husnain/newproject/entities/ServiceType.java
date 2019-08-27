package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ServiceType")
public class ServiceType {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int ServiceType_Id;
    private String ServiceType_Name;
    private int SeatNo;

    public ServiceType(int ServiceType_Id, String ServiceType_Name, int SeatNo) {
        this.ServiceType_Id = ServiceType_Id;
        this.ServiceType_Name = ServiceType_Name;
        this.SeatNo = SeatNo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getServiceType_Id() {
        return ServiceType_Id;
    }

    public String getServiceType_Name() {
        return ServiceType_Name;
    }

    public int getSeatNo() {
        return SeatNo;
    }
}
