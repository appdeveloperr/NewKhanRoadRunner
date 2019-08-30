package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.BusChargesDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.BusCharges;

import java.util.List;

public class BusChargesRepository {
    private BusChargesDao dao;
    private LiveData<List<BusCharges>> live_data_list;

    public BusChargesRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.busChargesDao();
        live_data_list = dao.getAllBusCharges();
    }

    public void insert(BusCharges obj){
        new InsertBusChargesAsyncTask(dao).execute(obj);
    }

    public void deleteAll(){
        dao.deleteAllBusCharges();
    }

    public LiveData<List<BusCharges>> getAllBusCharges(){
        return live_data_list;
    }

    private static class InsertBusChargesAsyncTask extends AsyncTask<BusCharges, Void ,Void>{
        private BusChargesDao Dao;

        private InsertBusChargesAsyncTask(BusChargesDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(BusCharges... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
