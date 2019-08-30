package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.RouteDetailDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.entities.RouteDetail;

import java.util.List;

public class RouteDetailRepository {
    private RouteDetailDao dao;
    private LiveData<List<RouteDetail>> live_data_list;

    public RouteDetailRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.routeDetailDao();
        live_data_list = dao.getAllRouteDetails();
    }

    public void deleteAll(){
        dao.deleteAllRouteDetail();
    }

    public void insert(RouteDetail obj){
        new InsertCityAsyncTask(dao).execute(obj);
    }

    public LiveData<List<RouteDetail>> getAllBusCharges(){
        return live_data_list;
    }

    public List<InnerJoinRoute> getRouteDetailsById(int id){
        return dao.getRouteDetailsById(id);
    }

    private static class InsertCityAsyncTask extends AsyncTask<RouteDetail, Void ,Void>{
        private RouteDetailDao Dao;

        private InsertCityAsyncTask(RouteDetailDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(RouteDetail... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
