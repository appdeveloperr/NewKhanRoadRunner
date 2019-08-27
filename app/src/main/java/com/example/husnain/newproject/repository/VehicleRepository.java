package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.VehicleDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Vehicle;

import java.util.List;

public class VehicleRepository {
    private VehicleDao dao;
    private LiveData<List<Vehicle>> live_data_list;

    public VehicleRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.vehicleDao();
        live_data_list = dao.getAllVehicles();
    }

    public void deleteAll(){
        dao.deleteAllVehicle();
    }

    public Vehicle getVehicleByID(int id){
        return dao.getVehicleByID(id);
    }

    public void insert(Vehicle obj){
        new InsertVehicleAsyncTask(dao).execute(obj);
    }

    public LiveData<List<Vehicle>> getAllVehicles(){
        return live_data_list;
    }

    private static class InsertVehicleAsyncTask extends AsyncTask<Vehicle, Void ,Void>{
        private VehicleDao Dao;

        private InsertVehicleAsyncTask(VehicleDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Vehicle... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
