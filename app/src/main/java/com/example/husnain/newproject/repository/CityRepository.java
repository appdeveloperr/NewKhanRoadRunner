package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.CityDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.City;

import java.util.List;

public class CityRepository {
    private CityDao cityDao;
    private LiveData<List<City>> allCities;

    public CityRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        cityDao = database.cityDao();
        allCities = cityDao.getAllCities();
    }

    public void insert(City city){
        new InsertCityAsyncTask(cityDao).execute(city);
    }


    public void deleteAll(){
        cityDao.deleteAllCities();
    }

    public LiveData<List<City>> getAllCities(){
        return allCities;
    }

    private static class InsertCityAsyncTask extends AsyncTask<City, Void ,Void>{
        private CityDao cityDao;

        private InsertCityAsyncTask(CityDao cityDao){
            this.cityDao = cityDao;
        }

        @Override
        protected Void doInBackground(City... cities) {
            cityDao.insert(cities[0]);
            return null;
        }
    }
}
