package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.Vehicle;
import com.example.husnain.newproject.repository.VehicleRepository;

import java.util.List;

public class VehicleViewModel extends AndroidViewModel {
    private VehicleRepository repository;
    private LiveData<List<Vehicle>> listLiveData;

    public VehicleViewModel(@NonNull Application application) {
        super(application);
        repository = new VehicleRepository(application);
        listLiveData = repository.getAllVehicles();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Vehicle getVehicleByID(int id){
        return repository.getVehicleByID(id);
    }

    public void insert(Vehicle obj){
        repository.insert(obj);
    }

    public LiveData<List<Vehicle>> getAll(){
        return listLiveData;
    }
}
