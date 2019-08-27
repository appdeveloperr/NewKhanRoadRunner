package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.ServiceType;
import com.example.husnain.newproject.entities.ServiceTypeExtraFare;
import com.example.husnain.newproject.repository.ServiceTypeExtraFareRepository;
import com.example.husnain.newproject.repository.ServiceTypeRepository;

import java.util.List;

public class ServiceTypeExtraFareViewModel extends AndroidViewModel {
    private ServiceTypeExtraFareRepository repository;
    private LiveData<List<ServiceTypeExtraFare>> listLiveData;

    public ServiceTypeExtraFareViewModel(@NonNull Application application) {
        super(application);
        repository = new ServiceTypeExtraFareRepository(application);
        listLiveData = repository.getAllBusCharges();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(ServiceTypeExtraFare obj){
        repository.insert(obj);
    }

    public LiveData<List<ServiceTypeExtraFare>> getAll(){
        return listLiveData;
    }
}
