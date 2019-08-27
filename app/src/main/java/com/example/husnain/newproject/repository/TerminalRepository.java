package com.example.husnain.newproject.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.TerminalDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.Terminal;

import java.util.List;

public class TerminalRepository {
    private TerminalDao dao;
    private LiveData<List<Terminal>> live_data_list;

    public TerminalRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.terminalDao();
        live_data_list = dao.getAllTerminal();
    }

    public void deleteAll(){
        dao.deleteAllTerminal();
    }

    public void insert(Terminal obj){
        new InsertTerminalAsyncTask(dao).execute(obj);
    }

    public LiveData<List<Terminal>> getAllTerminal(){
        return live_data_list;
    }

    private static class InsertTerminalAsyncTask extends AsyncTask<Terminal, Void ,Void>{
        private TerminalDao Dao;

        private InsertTerminalAsyncTask(TerminalDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(Terminal... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }
}
