package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.Terminal;
import com.example.husnain.newproject.repository.TerminalRepository;

import java.util.List;

public class TerminalViewModel extends AndroidViewModel {
    private TerminalRepository repository;
    private LiveData<List<Terminal>> listLiveData;

    public TerminalViewModel(@NonNull Application application) {
        super(application);
        repository = new TerminalRepository(application);
        listLiveData = repository.getAllTerminal();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Terminal obj){
        repository.insert(obj);
    }

    public LiveData<List<Terminal>> getAll(){
        return listLiveData;
    }
}
