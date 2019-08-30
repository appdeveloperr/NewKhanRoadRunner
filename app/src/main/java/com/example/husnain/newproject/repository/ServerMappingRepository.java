package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.ServerMappingDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.ServerMapping;

import java.util.List;

public class ServerMappingRepository {
    private ServerMappingDao dao;
    private LiveData<List<ServerMapping>> live_data_list;

    public ServerMappingRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.serverMappingDao();
        live_data_list = dao.getAllServerMapping();
    }

    public void deleteAll(){
        dao.deleteAllServerMapping();
    }

    public void insert(ServerMapping obj){
        new InsertCityAsyncTask(dao).execute(obj);
    }

    public LiveData<List<ServerMapping>> getAllServerMapping(){
        return live_data_list;
    }

    private static class InsertCityAsyncTask extends AsyncTask<ServerMapping, Void ,Void>{
        private ServerMappingDao Dao;

        private InsertCityAsyncTask(ServerMappingDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(ServerMapping... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
