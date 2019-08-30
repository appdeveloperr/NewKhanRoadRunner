package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.ComissionShiftDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.ComissionShift;

import java.util.List;

public class ComissionShiftRepository {
    private ComissionShiftDao dao;
    private LiveData<List<ComissionShift>> live_data_list;

    public ComissionShiftRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.comissionShiftDao();
        live_data_list = dao.getAllCommisionShift();
    }

    public void deleteAll(){
        dao.deleteAllComissionShift();
    }

    public void insert(ComissionShift obj){
        new InsertComissionShiftAsyncTask(dao).execute(obj);
    }

    public LiveData<List<ComissionShift>> getAllComissionShift(){
        return live_data_list;
    }

    private static class InsertComissionShiftAsyncTask extends AsyncTask<ComissionShift, Void ,Void>{
        private ComissionShiftDao Dao;

        private InsertComissionShiftAsyncTask(ComissionShiftDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(ComissionShift... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
