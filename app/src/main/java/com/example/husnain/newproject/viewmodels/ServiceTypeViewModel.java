package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.ServerMapping;
import com.example.husnain.newproject.entities.ServiceType;
import com.example.husnain.newproject.repository.ServerMappingRepository;
import com.example.husnain.newproject.repository.ServiceTypeRepository;

import java.util.List;

public class ServiceTypeViewModel extends AndroidViewModel {
    private ServiceTypeRepository repository;
    private LiveData<List<ServiceType>> listLiveData;

    public ServiceTypeViewModel(@NonNull Application application) {
        super(application);
        repository = new ServiceTypeRepository(application);
        listLiveData = repository.getAllServiceType();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(ServiceType obj){
        repository.insert(obj);
    }

    public LiveData<List<ServiceType>> getAll(){
        return listLiveData;
    }
}
