package com.example.husnain.newproject.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.husnain.newproject.SeatsInfo;
import com.example.husnain.newproject.dao.SeatsInfoDao;
import com.example.husnain.newproject.database.ShujaDatabase;

import java.util.List;

public class SeatsInfoRepository {
    private SeatsInfoDao seatsInfoDao;
    private LiveData<List<SeatsInfo>> allseats;

    public SeatsInfoRepository(Application application){
        ShujaDatabase database = ShujaDatabase.getInstance(application);
        seatsInfoDao = database.seatsInfoDao();
        allseats = seatsInfoDao.getAllSeatsInfo();
    }


    public List<SeatsInfo> getUnPushedData(){
        return seatsInfoDao.getUnPushedData();
    }

    public List<SeatsInfo> SelectSeatsInfoByStatus(String SeatStatus){
        return seatsInfoDao.SelectSeatsInfoByStatus(SeatStatus);
    }

    public void update(SeatsInfo obj){
        new UpdateSeatInfoAsyncTask(seatsInfoDao).execute(obj);
    }

    public void updateIsPushed(SeatsInfo obj){
        new UpdateSeatServerStatus(seatsInfoDao).execute(obj);
    }

    public void insert(SeatsInfo seatsInfo){
        new InsertSeatsInfoAsyncTask(seatsInfoDao).execute(seatsInfo);
    }


    public void deleteAll(){
        seatsInfoDao.deleteAllSeatsInfo();
    }

    public LiveData<List<SeatsInfo>> getAllSeatsInfo(){
        return allseats;
    }

    public List<SeatsInfo> getAllSeatsInfoList(){
        return seatsInfoDao.getAllSeatsInfoList();
    }

    private static class InsertSeatsInfoAsyncTask extends AsyncTask<SeatsInfo, Void ,Void>{
        private SeatsInfoDao seatsInfoDao;

        private InsertSeatsInfoAsyncTask(SeatsInfoDao seatsInfoDao){
            this.seatsInfoDao = seatsInfoDao;
        }

        @Override
        protected Void doInBackground(SeatsInfo... seatsInfos) {
            seatsInfoDao.insert(seatsInfos[0]);
            return null;
        }
    }

    private static class UpdateSeatInfoAsyncTask extends AsyncTask<SeatsInfo, Void ,Void>{
        private SeatsInfoDao Dao;
        int SeatNo;

        private UpdateSeatInfoAsyncTask(SeatsInfoDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(SeatsInfo... seatsInfos) {
            //Dao.update(seatsInfos[0]);

            Dao.update(seatsInfos[0]);
//            Dao.updateSeatStatusBySeatNo(seatsInfos[0].getSeat_No(),seatsInfos[0].getSeat_status(), seatsInfos[0].getBookedBy(), seatsInfos[0].getIsPushed(),seatsInfos[0].getGender(),seatsInfos[0].getFare());
            return null;
        }
    }

    private static class UpdateSeatServerStatus extends AsyncTask<SeatsInfo, Void ,Void>{
        private SeatsInfoDao Dao;
        int SeatNo;

        private UpdateSeatServerStatus(SeatsInfoDao Dao){
            this.Dao = Dao;
        }

        @Override
        protected Void doInBackground(SeatsInfo... seatsInfos) {
            //Dao.update(seatsInfos[0]);

            Dao.updateSeatServerStatus(seatsInfos[0].getSeat_No(),seatsInfos[0].getIsPushed());
            return null;
        }
    }
}
