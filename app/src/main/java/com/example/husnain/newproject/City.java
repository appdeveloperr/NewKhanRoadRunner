package com.example.husnain.newproject;

import android.app.Application;

/**
 * Created by Husnain on 4/30/2018.
 */

public class City extends Application {

    private String CityFrom;
    private String CityTo;

    public void setCityFrom(String cityFrom) {
        CityFrom = cityFrom;
    }

    public void setCityTo(String cityTo) {
        CityTo = cityTo;
    }

    public String getCityFrom() {
        return CityFrom;
    }

    public String getCityTo() {
        return CityTo;
    }
}
