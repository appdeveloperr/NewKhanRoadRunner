package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;

import android.os.AsyncTask;

import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.dao.TicketingScheduleDao;
import com.example.husnain.newproject.database.ShujaDatabase;
import com.example.husnain.newproject.entities.TicketingSchedule;

import java.util.List;

public class TicketingScheduleRepository {
    private TicketingScheduleDao dao;
    private LiveData<List<TicketingSchedule>> live_data_list;


    public TicketingScheduleRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        dao = database.ticketingScheduleDao();
        live_data_list = dao.getAllTicketingSchedule();
    }

    public void deleteAll(){
        dao.deleteAllTicketingSchedule();
    }

    public void insert(TicketingSchedule obj){
        new InsertTicketingScheduleAsyncTask(dao).execute(obj);
    }

    public void update(TicketingSchedule obj){
        new UpdateTicketingScheduleAsyncTask(dao).execute(obj);
    }

    public void DeleteAllTicketingSchedules(){
        new DeleteAllSchedule(dao).execute();
    }

    public LiveData<List<TicketingSchedule>> getAll(){
        return live_data_list;
    }

    public List<TicketingSchedule> getUnPushedData(){
        return dao.getUnPushedData();
    }

    public LiveData<List<TicketingSchedule>> getSingleRow(int schedule_id, String dept_time,String date, int srv_type_id, long voucher_no){
        return dao.getSingleRow(schedule_id, dept_time, date, srv_type_id, voucher_no);
    }

    public TicketingSchedule getPrevRecord(int schedule_id, String dept_time, String date, int srv_type_id, long voucher_no){
        return dao.getPrevRecord(schedule_id, dept_time, date, srv_type_id, voucher_no);
    }

    public TicketingSchedule getPrevRecord(long Ticketing_Schedule_ID){
        return dao.getPrevRecord(Ticketing_Schedule_ID);
    }

    private static class InsertTicketingScheduleAsyncTask extends AsyncTask<TicketingSchedule, Void ,Long>{
        private TicketingScheduleDao Dao;

        private InsertTicketingScheduleAsyncTask(TicketingScheduleDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Long doInBackground(TicketingSchedule... objToAdd) {
//            objToAdd[0].setTicketing_Schedule_ID(System.currentTimeMillis());
            Dao.insert(objToAdd[0]);
            return objToAdd[0].getTicketing_Schedule_ID();
        }

        @Override
        protected void onPostExecute(Long ticketingScheduleId) {
            Global.TicketingScheduleID = ticketingScheduleId;
            AppSession.put(Constants.SHARED_PREF.TICKETING_SCHEDULE_ID, Global.TicketingScheduleID);
        }
    }

    private static class DeleteAllSchedule extends AsyncTask<Void, Void ,Void>{
        private TicketingScheduleDao Dao;



        private DeleteAllSchedule(TicketingScheduleDao Dao){
            this.Dao = Dao;

        }


        @Override
        protected Void doInBackground(Void... voids) {
            Dao.DeleteAllTIcketingSchedule();
            return null;

        }
    }


    private static class UpdateTicketingScheduleAsyncTask extends AsyncTask<TicketingSchedule, Void ,Void>{
        private TicketingScheduleDao Dao;

        private UpdateTicketingScheduleAsyncTask(TicketingScheduleDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(TicketingSchedule... objToAdd) {
            Dao.update(objToAdd[0]);
            return null;
        }
    }


}
