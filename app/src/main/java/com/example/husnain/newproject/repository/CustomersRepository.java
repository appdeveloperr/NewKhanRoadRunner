package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.CustomersDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Customers;

import java.util.List;

public class CustomersRepository {
    private CustomersDao dao;
    private LiveData<List<Customers>> live_data_list;

    public CustomersRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.customersDao();
        live_data_list = dao.getAllCustomers();
    }

    public void insert(Customers obj){
        new InsertCustomersAsyncTask(dao).execute(obj);
    }

    public void deleteAll(){
        dao.deleteAllCustomers();
    }

    public LiveData<List<Customers>> getAllCustomers(){
        return live_data_list;
    }

    private static class InsertCustomersAsyncTask extends AsyncTask<Customers, Void ,Void>{
        private CustomersDao Dao;

        private InsertCustomersAsyncTask(CustomersDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Customers... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
