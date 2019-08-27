package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.ComissionShift;
import com.example.husnain.newproject.repository.ComissionShiftRepository;

import java.util.List;

public class ComissionShiftViewModel extends AndroidViewModel {
    private ComissionShiftRepository repository;
    private LiveData<List<ComissionShift>> listLiveData;

    public ComissionShiftViewModel(@NonNull Application application) {
        super(application);
        repository = new ComissionShiftRepository(application);
        listLiveData = repository.getAllComissionShift();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(ComissionShift obj){
        repository.insert(obj);
    }

    public LiveData<List<ComissionShift>> getAll(){
        return listLiveData;
    }
}
