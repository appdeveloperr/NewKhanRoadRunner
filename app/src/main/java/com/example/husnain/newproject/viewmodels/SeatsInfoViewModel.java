package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.SeatsInfo;
import com.example.husnain.newproject.repository.SeatsInfoRepository;

import java.util.List;

public class SeatsInfoViewModel extends AndroidViewModel {
    private SeatsInfoRepository repository;
    private LiveData<List<SeatsInfo>> listLiveData;

    public SeatsInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new SeatsInfoRepository(application);
        listLiveData = repository.getAllSeatsInfo();
    }

    public List<SeatsInfo> getUnPushedData(){
        return repository.getUnPushedData();
    }

    public void insert(SeatsInfo obj){
        repository.insert(obj);
    }

    public void update(SeatsInfo obj){
        repository.update(obj);
    }

    public void updateIsPushed(SeatsInfo obj){
        repository.updateIsPushed(obj);
    }



    public List<SeatsInfo> SelectSeatsInfoByStatus(String Status){
        return repository.SelectSeatsInfoByStatus(Status);
    }

    public void DeleteAllRecords(){
        repository.deleteAll();
    }



//    public LiveData<List<SeatsInfo>> getAll(){
//        return listLiveData;
//    }


    public List<SeatsInfo> getAllList(){
        return repository.getAllSeatsInfoList();
    }

}
