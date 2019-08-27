package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Employee")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Employee_Id;
    private int Is_Active;
    private int Accessed_By;
    private String Accessed_On;
    private String Full_Name;
    private int Employee_Type;
    private String Phone;


    public Employee(int Employee_Id, int Is_Active, int Accessed_By, String Accessed_On, String Full_Name, int Employee_Type, String Phone) {
        this.Employee_Id = Employee_Id;
        this.Is_Active = Is_Active;
        this.Accessed_By = Accessed_By;
        this.Accessed_On = Accessed_On;
        this.Full_Name = Full_Name;
        this.Employee_Type = Employee_Type;
        this.Phone = Phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getEmployee_Id() {
        return Employee_Id;
    }

    public int getIs_Active() {
        return Is_Active;
    }

    public int getAccessed_By() {
        return Accessed_By;
    }

    public String getAccessed_On() {
        return Accessed_On;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public int getEmployee_Type() {
        return Employee_Type;
    }

    public String getPhone() {
        return Phone;
    }
}


