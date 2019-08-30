package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.Users;
import com.example.husnain.newproject.repository.UsersRepository;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {
    private UsersRepository repository;
    private LiveData<List<Users>> listLiveData;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        repository = new UsersRepository(application);
        listLiveData = repository.getAllUsers();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(Users obj){
        repository.insert(obj);
    }

    public LiveData<List<Users>> getAll(){
        return listLiveData;
    }


    public LiveData<List<Users>> validateUser(String username,String pasw){
        LiveData<List<Users>> users = repository.validateUser(username,pasw);
        return users;
    }


}
