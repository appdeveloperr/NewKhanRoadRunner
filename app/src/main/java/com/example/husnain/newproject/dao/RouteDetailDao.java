package com.example.husnain.newproject.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.entities.Route;
import com.example.husnain.newproject.entities.RouteDetail;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RouteDetailDao {

    @Insert
    void insert(RouteDetail routeDetail);

    @Query("DELETE FROM Route_Detail")
    void deleteAllRouteDetail();

    @Query("SELECT * FROM Route_Detail")
    LiveData<List<RouteDetail>> getAllRouteDetails();

    @Query("SELECT DISTINCT  Route_Detail.City_ID, City.City_Name, City.City_Abbr, Route_Detail.Sr_No\n" +
            "\tFROM Route_Detail INNER JOIN City \n" +
            "\t\tON Route_Detail.City_ID = City.City_ID\n" +
            "\tWHERE Route_Detail.Route_ID = :routeId")
    List<InnerJoinRoute> getRouteDetailsById(int routeId);
}
