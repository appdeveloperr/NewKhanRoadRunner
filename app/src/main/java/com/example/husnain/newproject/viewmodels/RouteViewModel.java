package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;


import com.example.husnain.newproject.entities.Route;
import com.example.husnain.newproject.repository.RouteRepository;

import java.util.List;

public class RouteViewModel extends AndroidViewModel {
    private RouteRepository repository;
    private LiveData<List<Route>> listLiveData;

    public RouteViewModel(@NonNull Application application) {
        super(application);
        repository = new RouteRepository(application);
        listLiveData = repository.getAllRoute();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Route obj){
        repository.insert(obj);
    }

    public LiveData<List<Route>> getAll(){
        return listLiveData;
    }
}
