package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity (tableName = "Comission_Shift")
public class ComissionShift {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Decd_Id;
    private int User_Id;
    private double Amount;
    private int Book_Id;

    public ComissionShift(int Decd_Id, int User_Id, double Amount, int Book_Id) {
        this.Decd_Id = Decd_Id;
        this.User_Id = User_Id;
        this.Amount = Amount;
        this.Book_Id = Book_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDecd_Id() {
        return Decd_Id;
    }

    public int getUser_Id() {
        return User_Id;
    }

    public double getAmount() {
        return Amount;
    }

    public int getBook_Id() {
        return Book_Id;
    }
}
