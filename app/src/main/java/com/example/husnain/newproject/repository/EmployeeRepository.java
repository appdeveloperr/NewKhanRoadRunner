package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.EmployeeDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Employee;

import java.util.List;

public class EmployeeRepository {
    private EmployeeDao dao;
    private LiveData<List<Employee>> live_data_list;

    public EmployeeRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.employeeDao();
        live_data_list = dao.getAllEmployee();
    }

    public void insert(Employee obj){
        new InsertEmployeeAsyncTask(dao).execute(obj);
    }

    public void deleteAll(){
        dao.deleteAllEmployee();
    }

    public LiveData<List<Employee>> getAllEmployee(){
        return live_data_list;
    }

    private static class InsertEmployeeAsyncTask extends AsyncTask<Employee, Void ,Void>{
        private EmployeeDao Dao;

        private InsertEmployeeAsyncTask(EmployeeDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Employee... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
