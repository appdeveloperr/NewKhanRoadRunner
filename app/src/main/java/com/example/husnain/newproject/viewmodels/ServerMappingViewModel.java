package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.ServerMapping;
import com.example.husnain.newproject.repository.ServerMappingRepository;

import java.util.List;

public class ServerMappingViewModel extends AndroidViewModel {
    private ServerMappingRepository repository;
    private LiveData<List<ServerMapping>> listLiveData;

    public ServerMappingViewModel(@NonNull Application application) {
        super(application);
        repository = new ServerMappingRepository(application);
        listLiveData = repository.getAllServerMapping();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(ServerMapping obj){
        repository.insert(obj);
    }

    public LiveData<List<ServerMapping>> getAll(){
        return listLiveData;
    }
}
