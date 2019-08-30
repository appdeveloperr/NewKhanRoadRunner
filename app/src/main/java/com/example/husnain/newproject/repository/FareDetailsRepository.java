package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.FareDetailsDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.FareDetails;

import java.util.List;

public class FareDetailsRepository {
    private FareDetailsDao dao;
    private LiveData<List<FareDetails>> live_data_list;

    public FareDetailsRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.fareDetailsDao();
        live_data_list = dao.getAllFareDetails();
    }

    public void insert(FareDetails obj){
        new InsertFareDetailsAsyncTask(dao).execute(obj);
    }

    public void deleteAll(){
        dao.deleteAllFareDetails();
    }

    public LiveData<List<FareDetails>> getAllFareDetails(){
        return live_data_list;
    }

    public LiveData<List<FareDetails>> getFares(int source_city_id, int dest_city_id, int  srv_type_id){
        return dao.getFare(source_city_id, dest_city_id, srv_type_id);
    }

    private static class InsertFareDetailsAsyncTask extends AsyncTask<FareDetails, Void ,Void>{
        private FareDetailsDao Dao;

        private InsertFareDetailsAsyncTask(FareDetailsDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(FareDetails... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
