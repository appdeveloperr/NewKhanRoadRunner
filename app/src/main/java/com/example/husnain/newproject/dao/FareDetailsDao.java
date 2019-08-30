package com.example.husnain.newproject.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.husnain.newproject.entities.FareDetails;

import java.util.List;

@Dao
public interface FareDetailsDao {

    @Insert
    void insert(FareDetails fareDetails);

    @Query("DELETE FROM Fare_Details")
    void deleteAllFareDetails();

    @Query("SELECT * FROM Fare_Details")
    LiveData<List<FareDetails>> getAllFareDetails();

    @Query("SELECT * \n" +
            "FROM Fare_Details \n" +
            "Where Source_City_ID = :source_city_id and Destination_city_id = :dest_city_id and ServiceType_Id = :srv_type_id")
    LiveData<List<FareDetails>> getFare(int source_city_id, int dest_city_id, int srv_type_id);



}
