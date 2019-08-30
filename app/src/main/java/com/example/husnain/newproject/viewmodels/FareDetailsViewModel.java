package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.FareDetails;
import com.example.husnain.newproject.repository.FareDetailsRepository;

import java.util.List;

public class FareDetailsViewModel extends AndroidViewModel {
    private FareDetailsRepository repository;
    private LiveData<List<FareDetails>> listLiveData;

    public FareDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new FareDetailsRepository(application);
        listLiveData = repository.getAllFareDetails();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(FareDetails obj){
        repository.insert(obj);
    }

    public LiveData<List<FareDetails>> getAll(){
        return listLiveData;
    }

    public LiveData<List<FareDetails>> getFares(int source_city_id, int dest_city_id, int srv_type_id){
        return repository.getFares(source_city_id, dest_city_id, srv_type_id);
    }
}
