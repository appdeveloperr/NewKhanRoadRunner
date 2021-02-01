package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.dao.TicketingSeatDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.TicketingSeat;

import java.util.List;

public class TicketingSeatRepository {
    private TicketingSeatDao dao;
    private LiveData<List<TicketingSeat>> live_data_list;

    public TicketingSeatRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.ticketingSeatDao();
        live_data_list = dao.getAllTicketingSeats();
    }

    public void DeleteAllRecords(){
        //dao.DeleteAllTicketingSeats();
        new DeleteAllRecords(dao).execute();
    }



    public void insert(TicketingSeat obj){
        new InsertTicketingSeatAsyncTask(dao).execute(obj);
    }

    public void update(TicketingSeat obj){
        new UpdateTicketingSeatAsyncTask(dao).execute(obj);
    }

    public LiveData<List<TicketingSeat>> getAllTicketingSeats(){
        return live_data_list;
    }

    public List<TicketingSeat> getUnPushedData(){
        return dao.getUnPushedData();
    }

    public List<TicketingSeat> getMyTickets(int userId){
        return this.dao.getMyTickets(userId);
    }

    public void setPrinted(List<Integer> ids) {
        this.dao.setPrinted(ids);
    }

    private static class InsertTicketingSeatAsyncTask extends AsyncTask<TicketingSeat, Void ,Void>{
        private TicketingSeatDao Dao;

        private InsertTicketingSeatAsyncTask(TicketingSeatDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(TicketingSeat... objToAdd) {
            Dao.insert(objToAdd[0]);
            return null;
        }
    }

    private static class UpdateTicketingSeatAsyncTask extends AsyncTask<TicketingSeat, Void ,Void>{
        private TicketingSeatDao Dao;

        private UpdateTicketingSeatAsyncTask(TicketingSeatDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(TicketingSeat... objToAdd) {
            Dao.update(objToAdd[0]);
            return null;
        }
    }

    private static class DeleteAllRecords extends AsyncTask<TicketingSeat, Void ,Void>{
        private TicketingSeatDao Dao;

        private DeleteAllRecords(TicketingSeatDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(TicketingSeat... objToAdd) {
            Dao.DeleteAllTicketingSeats();
            return null;
        }
    }

}
