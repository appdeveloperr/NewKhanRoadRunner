package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.ServiceTypeExtraFareDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.ServiceTypeExtraFare;

import java.util.List;

public class ServiceTypeExtraFareRepository {
    private ServiceTypeExtraFareDao dao;
    private LiveData<List<ServiceTypeExtraFare>> live_data_list;

    public ServiceTypeExtraFareRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.serviceTypeExtraFareDao();
        live_data_list = dao.getAllServiceTypeExtraFares();
    }

    public void deleteAll(){
        dao.deleteAllServiceTypeExtraFare();
    }

    public void insert(ServiceTypeExtraFare obj){
        new InsertCityAsyncTask(dao).execute(obj);
    }

    public LiveData<List<ServiceTypeExtraFare>> getAllBusCharges(){
        return live_data_list;
    }

    private static class InsertCityAsyncTask extends AsyncTask<ServiceTypeExtraFare, Void ,Void>{
        private ServiceTypeExtraFareDao Dao;

        private InsertCityAsyncTask(ServiceTypeExtraFareDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(ServiceTypeExtraFare... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
