package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.ScheduleDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Schedule;

import java.util.List;

public class ScheduleRepository {
    private ScheduleDao dao;
    private LiveData<List<Schedule>> live_data_list;

    public ScheduleRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.scheduleDao();
        live_data_list = dao.getAllSchedules();
    }

    public void deleteAll(){
        dao.deleteAllSchedule();
    }

    public void insert(Schedule obj){
        new InsertScheduleAsyncTask(dao).execute(obj);
    }

    public LiveData<List<Schedule>> getAllSchedules(){
        return live_data_list;
    }

    public Schedule getScheduleByID(int id){
        return dao.getScheduleByID(id);
    }

    private static class InsertScheduleAsyncTask extends AsyncTask<Schedule, Void ,Void>{
        private ScheduleDao Dao;

        private InsertScheduleAsyncTask(ScheduleDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Schedule... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
