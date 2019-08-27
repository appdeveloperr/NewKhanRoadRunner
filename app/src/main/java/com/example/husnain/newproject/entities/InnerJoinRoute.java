package com.example.husnain.newproject.entities;

public class InnerJoinRoute {
    private int City_ID;
    private String  City_Name;
    private String City_Abbr;
    int Sr_No;

    public  InnerJoinRoute(int City_ID, String City_Name, String City_Abbr, int Sr_No){
        this.City_ID = City_ID;
        this.City_Name = City_Name;
        this.City_Abbr = City_Abbr;
        this.Sr_No = Sr_No;
    }

    public int getCity_ID() {
        return City_ID;
    }

    public void setCity_ID(int city_ID) {
        City_ID = city_ID;
    }

    public String getCity_Name() {
        return City_Name;
    }

    public void setCity_Name(String city_Name) {
        City_Name = city_Name;
    }

    public String getCity_Abbr() {
        return City_Abbr;
    }

    public void setCity_Abbr(String city_Abbr) {
        City_Abbr = city_Abbr;
    }

    public int getSr_No() {
        return Sr_No;
    }

    public void setSr_No(int sr_No) {
        Sr_No = sr_No;
    }
}
