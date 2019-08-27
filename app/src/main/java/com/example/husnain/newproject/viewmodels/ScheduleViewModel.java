package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import com.example.husnain.newproject.entities.Schedule;
import com.example.husnain.newproject.entities.Users;
import com.example.husnain.newproject.repository.RouteRepository;
import com.example.husnain.newproject.repository.ScheduleRepository;

import java.util.List;

public class ScheduleViewModel extends AndroidViewModel {
    private ScheduleRepository repository;
    private LiveData<List<Schedule>> listLiveData;


    public ScheduleViewModel(@NonNull Application application) {
        super(application);
        repository = new ScheduleRepository(application);
        listLiveData = repository.getAllSchedules();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Schedule obj){
        repository.insert(obj);
    }

    public LiveData<List<Schedule>> getAll(){
        return listLiveData;
    }

    public Schedule getScheduleByID(int id){
        Schedule schedule = repository.getScheduleByID(id);
        return schedule;
    }
}
