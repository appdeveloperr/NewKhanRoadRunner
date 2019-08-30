package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;


import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.entities.RouteDetail;
import com.example.husnain.newproject.repository.RouteDetailRepository;

import java.util.List;

public class RouteDetailViewModel extends AndroidViewModel {
    private RouteDetailRepository repository;
    private LiveData<List<RouteDetail>> listLiveData;

    public RouteDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new RouteDetailRepository(application);
        listLiveData = repository.getAllBusCharges();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(RouteDetail obj){
        repository.insert(obj);
    }

    public LiveData<List<RouteDetail>> getAll(){
        return listLiveData;
    }

    public List<InnerJoinRoute> getRouteDetailsById(int id){
        return repository.getRouteDetailsById(id);
    }
}
