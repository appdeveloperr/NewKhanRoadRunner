package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.BusCharges;
import com.example.husnain.newproject.repository.BusChargesRepository;

import java.util.List;

public class BusChargesViewModel extends AndroidViewModel {
    private BusChargesRepository repository;
    private LiveData<List<BusCharges>> listLiveData;

    public BusChargesViewModel(@NonNull Application application) {
        super(application);
        repository = new BusChargesRepository(application);
        listLiveData = repository.getAllBusCharges();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(BusCharges obj){
        repository.insert(obj);
    }

    public LiveData<List<BusCharges>> getAll(){
        return listLiveData;
    }
}
