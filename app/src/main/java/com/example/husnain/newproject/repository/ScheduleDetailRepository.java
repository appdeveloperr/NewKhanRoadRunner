package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.ScheduleDetailDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.ScheduleDetail;

import java.util.List;

public class ScheduleDetailRepository {
    private ScheduleDetailDao dao;
    private LiveData<List<ScheduleDetail>> live_data_list;

    public ScheduleDetailRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.scheduleDetailDao();
        live_data_list = dao.getAllScheduleDetail();
    }

    public void deleteAll(){
        dao.deleteAllScheduleDetail();
    }

    public void insert(ScheduleDetail obj){
        new InsertScheduleDetailAsyncTask(dao).execute(obj);
    }

    public LiveData<List<ScheduleDetail>> getAllScheduleDetail(){
        return live_data_list;
    }

    private static class InsertScheduleDetailAsyncTask extends AsyncTask<ScheduleDetail, Void ,Void>{
        private ScheduleDetailDao Dao;

        private InsertScheduleDetailAsyncTask(ScheduleDetailDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(ScheduleDetail... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
