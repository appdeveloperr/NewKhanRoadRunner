package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.entities.Employee;
import com.example.husnain.newproject.repository.EmployeeRepository;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeRepository repository;
    private LiveData<List<Employee>> listLiveData;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        repository = new EmployeeRepository(application);
        listLiveData = repository.getAllEmployee();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Employee obj){
        repository.insert(obj);
    }

    public LiveData<List<Employee>> getAll(){
        return listLiveData;
    }
}
