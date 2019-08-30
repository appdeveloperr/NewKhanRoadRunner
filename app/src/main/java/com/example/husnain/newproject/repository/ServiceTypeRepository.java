package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.ServiceTypeDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.ServiceType;

import java.util.List;

public class ServiceTypeRepository {
    private ServiceTypeDao dao;
    private LiveData<List<ServiceType>> live_data_list;

    public ServiceTypeRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.serviceTypeDao();
        live_data_list = dao.getAllServiceTypes();
    }

    public void deleteAll(){
        dao.deleteAllServiceType();
    }

    public void insert(ServiceType obj){
        new InsertServiceTypeAsyncTask(dao).execute(obj);
    }

    public LiveData<List<ServiceType>> getAllServiceType(){
        return live_data_list;
    }

    private static class InsertServiceTypeAsyncTask extends AsyncTask<ServiceType, Void ,Void>{
        private ServiceTypeDao Dao;

        private InsertServiceTypeAsyncTask(ServiceTypeDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(ServiceType... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
