package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.ScheduleDetail;
import com.example.husnain.newproject.repository.ScheduleDetailRepository;

import java.util.List;

public class ScheduleDetailViewModel extends AndroidViewModel {
    private ScheduleDetailRepository repository;
    private LiveData<List<ScheduleDetail>> listLiveData;

    public ScheduleDetailViewModel(@NonNull Application application) {
        super(application);
        repository = new ScheduleDetailRepository(application);
        listLiveData = repository.getAllScheduleDetail();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(ScheduleDetail obj){
        repository.insert(obj);
    }

    public LiveData<List<ScheduleDetail>> getAll(){
        return listLiveData;
    }
}
