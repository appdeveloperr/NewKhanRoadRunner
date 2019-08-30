package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;


import com.example.husnain.newproject.entities.Customers;
import com.example.husnain.newproject.repository.CustomersRepository;

import java.util.List;

public class CustomersViewModel extends AndroidViewModel {
    private CustomersRepository repository;
    private LiveData<List<Customers>> listLiveData;

    public CustomersViewModel(@NonNull Application application) {
        super(application);
        repository = new CustomersRepository(application);
        listLiveData = repository.getAllCustomers();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Customers obj){
        repository.insert(obj);
    }

    public LiveData<List<Customers>> getAll(){
        return listLiveData;
    }
}
