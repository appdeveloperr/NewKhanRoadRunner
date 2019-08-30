package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.ServiceType;
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
