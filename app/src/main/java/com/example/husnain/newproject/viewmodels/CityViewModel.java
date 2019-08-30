package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.City;
import com.example.husnain.newproject.repository.CityRepository;

import java.util.List;

public class CityViewModel extends AndroidViewModel {
    private CityRepository repository;
    private LiveData<List<City>> allCities;

    public CityViewModel(@NonNull Application application) {
        super(application);
        repository = new CityRepository(application);
        allCities = repository.getAllCities();
    }

    public void insert(City city){
        repository.insert(city);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public LiveData<List<City>> getAll(){
        return allCities;
    }
}
