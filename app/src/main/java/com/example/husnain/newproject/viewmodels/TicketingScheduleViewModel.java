package com.example.husnain.newproject.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.example.husnain.newproject.entities.TicketingSchedule;
import com.example.husnain.newproject.repository.TicketingScheduleRepository;

import java.util.List;

public class TicketingScheduleViewModel extends AndroidViewModel {
    private TicketingScheduleRepository repository;
    private LiveData<List<TicketingSchedule>> listLiveData;

    public TicketingScheduleViewModel(@NonNull Application application) {
        super(application);
        repository = new TicketingScheduleRepository(application);
        listLiveData = repository.getAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void insert(TicketingSchedule obj){
        repository.insert(obj);
    }

    public void update(TicketingSchedule obj){
        repository.update(obj);
    }

    public void DeleteAllTicketingSchedules(){
        repository.DeleteAllTicketingSchedules();
    }


    public LiveData<List<TicketingSchedule>> getAll(){
        return listLiveData;
    }

    public List<TicketingSchedule> getUnPushedData(){
        return repository.getUnPushedData();
    }

    public LiveData<List<TicketingSchedule>> getSingleRow(int schedule_id, String dept_time,String date, int srv_type_id, long voucher_no){
        return repository.getSingleRow(schedule_id, dept_time, date, srv_type_id, voucher_no);
    }

    public TicketingSchedule getPrevRecord(int schedule_id, String dept_time,String date, int srv_type_id, long voucher_no){
        return repository.getPrevRecord(schedule_id, dept_time, date, srv_type_id, voucher_no);
    }

    public TicketingSchedule getPrevRecord(long Ticketing_Schedule_ID){
        return repository.getPrevRecord(Ticketing_Schedule_ID);
    }
}
