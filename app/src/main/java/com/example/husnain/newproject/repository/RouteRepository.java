package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.RouteDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Route;

import java.util.List;

public class RouteRepository {
    private RouteDao dao;
    private LiveData<List<Route>> live_data_list;

    public RouteRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.routeDao();
        live_data_list = dao.getAllRoutes();
    }

    public void insert(Route obj){
        new InsertRouteAsyncTask(dao).execute(obj);
    }

    public void deleteAll(){
        dao.deleteAllRoutes();
    }

    public LiveData<List<Route>> getAllRoute(){
        return live_data_list;
    }

    private static class InsertRouteAsyncTask extends AsyncTask<Route, Void ,Void>{
        private RouteDao Dao;

        private InsertRouteAsyncTask(RouteDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Route... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
