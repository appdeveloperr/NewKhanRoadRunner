package com.example.husnain.newproject.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.math.BigInteger;

@Entity (tableName = "Table_Bus_Charges")
public class BusCharges {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int Bus_Charges_ID;
    private int Ticketing_Schedule_Id;
    private double Hostess_Salary;
    private double Driver_Salary;
    private double Guard_Salary;
    private double Service_Charges;
    private double Cleaning_Charges;
    private double Hook_Charges;
    private double Bus_Charges;
    private double Refreshment;
    private double Toll;
    private double Commision;
    private int CashPaidToDriver;
    private double Terminal_Expense;
    private double Reward;
    private double Misc;
    private double DriverPaidAmount;
    private double TerminalCom;

    public BusCharges(int Bus_Charges_ID, int Ticketing_Schedule_Id, double Hostess_Salary, double Driver_Salary,
                      double Guard_Salary, double Service_Charges, double Cleaning_Charges, double Hook_Charges,
                      double Bus_Charges, double Refreshment, double Toll, double Commision, int CashPaidToDriver, double Terminal_Expense, double Reward, double Misc, double DriverPaidAmount, double TerminalCom) {
        this.Bus_Charges_ID = Bus_Charges_ID;
        this.Ticketing_Schedule_Id = Ticketing_Schedule_Id;
        this.Hostess_Salary = Hostess_Salary;
        this.Driver_Salary = Driver_Salary;
        this.Guard_Salary = Guard_Salary;
        this.Service_Charges = Service_Charges;
        this.Cleaning_Charges = Cleaning_Charges;
        this.Hook_Charges = Hook_Charges;
        this.Bus_Charges = Bus_Charges;
        this.Refreshment = Refreshment;
        this.Toll = Toll;
        this.Commision = Commision;
        this.CashPaidToDriver = CashPaidToDriver;
        this.Terminal_Expense = Terminal_Expense;
        this.Reward = Reward;
        this.Misc = Misc;
        this.DriverPaidAmount = DriverPaidAmount;
        this.TerminalCom = TerminalCom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBus_Charges_ID(int bus_Charges_ID) {
        Bus_Charges_ID = bus_Charges_ID;
    }

    public void setTicketing_Schedule_Id(int ticketing_Schedule_Id) {
        Ticketing_Schedule_Id = ticketing_Schedule_Id;
    }

    public void setHostess_Salary(double hostess_Salary) {
        Hostess_Salary = hostess_Salary;
    }

    public void setDriver_Salary(double driver_Salary) {
        Driver_Salary = driver_Salary;
    }

    public void setGuard_Salary(double guard_Salary) {
        Guard_Salary = guard_Salary;
    }

    public void setService_Charges(double service_Charges) {
        Service_Charges = service_Charges;
    }

    public void setCleaning_Charges(double cleaning_Charges) {
        Cleaning_Charges = cleaning_Charges;
    }

    public void setHook_Charges(double hook_Charges) {
        Hook_Charges = hook_Charges;
    }

    public void setBus_Charges(double bus_Charges) {
        Bus_Charges = bus_Charges;
    }

    public void setRefreshment(double refreshment) {
        Refreshment = refreshment;
    }

    public void setToll(double toll) {
        Toll = toll;
    }

    public void setCommision(double commision) {
        Commision = commision;
    }

    public void setCashPaidToDriver(int cashPaidToDriver) {
        CashPaidToDriver = cashPaidToDriver;
    }

    public void setTerminal_Expense(double terminal_Expense) {
        Terminal_Expense = terminal_Expense;
    }

    public void setReward(double reward) {
        Reward = reward;
    }

    public void setMisc(double misc) {
        Misc = misc;
    }

    public void setDriverPaidAmount(double driverPaidAmount) {
        DriverPaidAmount = driverPaidAmount;
    }

    public void setTerminalCom(double terminalCom) {
        TerminalCom = terminalCom;
    }

    public int getBus_Charges_ID() {
        return Bus_Charges_ID;
    }

    public int getTicketing_Schedule_Id() {
        return Ticketing_Schedule_Id;
    }

    public double getHostess_Salary() {
        return Hostess_Salary;
    }

    public double getDriver_Salary() {
        return Driver_Salary;
    }

    public double getGuard_Salary() {
        return Guard_Salary;
    }

    public double getService_Charges() {
        return Service_Charges;
    }

    public double getCleaning_Charges() {
        return Cleaning_Charges;
    }

    public double getHook_Charges() {
        return Hook_Charges;
    }

    public double getBus_Charges() {
        return Bus_Charges;
    }

    public double getRefreshment() {
        return Refreshment;
    }

    public double getToll() {
        return Toll;
    }

    public double getCommision() {
        return Commision;
    }

    public int getCashPaidToDriver() {
        return CashPaidToDriver;
    }

    public double getTerminal_Expense() {
        return Terminal_Expense;
    }

    public double getReward() {
        return Reward;
    }

    public double getMisc() {
        return Misc;
    }

    public double getDriverPaidAmount() {
        return DriverPaidAmount;
    }

    public double getTerminalCom() {
        return TerminalCom;
    }
}
