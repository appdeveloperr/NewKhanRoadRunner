package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.entities.RouteDetail;

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
            "\tWHERE Route_Detail.Route_ID = :routeId\n" +
            "\tORDER BY Route_Detail.Sr_No")
    List<InnerJoinRoute> getRouteDetailsById(int routeId);
}
