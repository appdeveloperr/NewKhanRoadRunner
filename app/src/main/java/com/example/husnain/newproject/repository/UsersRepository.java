package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.UsersDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Users;

import java.util.List;

public class UsersRepository {
    private UsersDao dao;
    private LiveData<List<Users>> live_data_list;
    Users users;

    public UsersRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.usersDao();
        live_data_list = dao.getAllUsers();
    }

    public void deleteAll(){
        dao.deleteAllUsers();
    }

    public void insert(Users obj){
        new InsertUserAsyncTask(dao).execute(obj);
    }

    private void asyncFinished(Users results) {
        users = results;
    }

    public LiveData<List<Users>> validateUser(String username,String pasw) {
        /*ValidateUserAsyncTask validateUserAsyncTask = new ValidateUserAsyncTask(dao);
        validateUserAsyncTask.delegate = this;
        validateUserAsyncTask.execute(username,pasw);*/
        return dao.validateUser(username,pasw);
    }

    public LiveData<List<Users>> getAllUsers(){
        return live_data_list;
    }

    private static class InsertUserAsyncTask extends AsyncTask<Users, Void ,Void>{
        private UsersDao Dao;

        private InsertUserAsyncTask(UsersDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Users... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }

    /*private static class ValidateUserAsyncTask extends AsyncTask<String, Users ,Users>{
        private UsersDao Dao;
        Users users;
        private UsersRepository delegate = null;
        private ValidateUserAsyncTask(UsersDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Users doInBackground(String... strings) {
            users = Dao.validateUser(strings[0],strings[1]);
            return users;
        }

        @Override
        protected void onPostExecute(Users users) {
            //super.onPostExecute(users);
            delegate.asyncFinished(users);
        }
    }*/
}
