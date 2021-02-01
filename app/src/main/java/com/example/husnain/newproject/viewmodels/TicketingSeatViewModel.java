package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.TicketingSeat;
import com.example.husnain.newproject.repository.TicketingSeatRepository;

import java.util.List;

public class TicketingSeatViewModel extends AndroidViewModel {
    private TicketingSeatRepository repository;
    private LiveData<List<TicketingSeat>> listLiveData;

    public TicketingSeatViewModel(@NonNull Application application) {
        super(application);
        repository = new TicketingSeatRepository(application);
        listLiveData = repository.getAllTicketingSeats();
    }

    public void insert(TicketingSeat obj){
        repository.insert(obj);
    }

    public void update(TicketingSeat obj){
        repository.update(obj);
    }

    public void DeleteAllRecords(){
        repository.DeleteAllRecords();
    }

    public LiveData<List<TicketingSeat>> getAll(){
        return listLiveData;
    }

    public List<TicketingSeat> getMyTickets(int userId){
        return this.repository.getMyTickets(userId);
    }


    public List<TicketingSeat> getUnPushedData(){
        return repository.getUnPushedData();
    }

    public void setPrinted(List<Integer> ids) {
        this.repository.setPrinted(ids);
    }
}
