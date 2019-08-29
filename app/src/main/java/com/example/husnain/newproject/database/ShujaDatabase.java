package com.example.husnain.newproject.database;

import android.app.Service;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.husnain.newproject.SeatsInfo;
import com.example.husnain.newproject.dao.BusChargesDao;
import com.example.husnain.newproject.dao.CityDao;
import com.example.husnain.newproject.dao.ComissionShiftDao;
import com.example.husnain.newproject.dao.CustomersDao;
import com.example.husnain.newproject.dao.EmployeeDao;
import com.example.husnain.newproject.dao.FareDetailsDao;
import com.example.husnain.newproject.dao.RouteDao;
import com.example.husnain.newproject.dao.RouteDetailDao;
import com.example.husnain.newproject.dao.ScheduleDao;
import com.example.husnain.newproject.dao.ScheduleDetailDao;
import com.example.husnain.newproject.dao.SeatsInfoDao;
import com.example.husnain.newproject.dao.ServerMappingDao;
import com.example.husnain.newproject.dao.ServiceTypeDao;
import com.example.husnain.newproject.dao.ServiceTypeExtraFareDao;
import com.example.husnain.newproject.dao.TerminalDao;
import com.example.husnain.newproject.dao.TicketingScheduleDao;
import com.example.husnain.newproject.dao.TicketingSeatDao;
import com.example.husnain.newproject.dao.UsersDao;
import com.example.husnain.newproject.dao.VehicleDao;
import com.example.husnain.newproject.entities.*;

@Database(entities =  {BusCharges.class, City.class, ComissionShift.class,
                        Customers.class, Employee.class, FareDetails.class,
                        Route.class, RouteDetail.class, Schedule.class,
                        ScheduleDetail.class, ServerMapping.class, ServiceType.class,
                        ServiceTypeExtraFare.class, Terminal.class, TicketingSchedule.class,
                        TicketingSeat.class, Users.class, Vehicle.class, SeatsInfo.class
                        }, version = 7)

public abstract class ShujaDatabase extends RoomDatabase {
    private static ShujaDatabase instance;

    public abstract CityDao cityDao();
    public abstract BusChargesDao busChargesDao();
    public abstract ComissionShiftDao comissionShiftDao();
    public abstract CustomersDao customersDao();
    public abstract EmployeeDao employeeDao();
    public abstract FareDetailsDao fareDetailsDao();
    public abstract RouteDao routeDao();
    public abstract RouteDetailDao routeDetailDao();
    public abstract ScheduleDao scheduleDao();
    public abstract ScheduleDetailDao scheduleDetailDao();
    public abstract ServerMappingDao serverMappingDao();
    public abstract ServiceTypeDao serviceTypeDao();
    public abstract ServiceTypeExtraFareDao serviceTypeExtraFareDao();
    public abstract TerminalDao terminalDao();
    public abstract TicketingScheduleDao ticketingScheduleDao();
    public abstract TicketingSeatDao ticketingSeatDao();
    public abstract UsersDao usersDao();
    public abstract VehicleDao vehicleDao();
    public abstract SeatsInfoDao seatsInfoDao();


    public static synchronized ShujaDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShujaDatabase.class, "shuja_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    };

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void , Void>{
        private BusChargesDao busChargesDao;
        private CityDao cityDao;
        private ComissionShiftDao comissionShiftDao;
        private CustomersDao customersDao;
        private EmployeeDao employeeDao;
        private FareDetailsDao fareDetailsDao;
        private RouteDao routeDao;
        private RouteDetailDao routeDetailDao;
        private ScheduleDao scheduleDao;
        private ScheduleDetailDao scheduleDetailDao;
        private ServerMappingDao serverMappingDao;
        private ServiceTypeDao serviceTypeDao;
        private ServiceTypeExtraFareDao serviceTypeExtraFareDao;
        private TerminalDao terminalDao;
        private TicketingSeatDao ticketingSeatDao;
        private UsersDao usersDao;
        private VehicleDao vehicleDao;



        private PopulateDBAsyncTask(ShujaDatabase db){
            cityDao = db.cityDao();
            busChargesDao = db.busChargesDao();
            comissionShiftDao = db.comissionShiftDao();
            customersDao = db.customersDao();
            employeeDao = db.employeeDao();
            fareDetailsDao = db.fareDetailsDao();
            routeDao = db.routeDao();
            routeDetailDao = db.routeDetailDao();
            scheduleDao = db.scheduleDao();
            scheduleDetailDao = db.scheduleDetailDao();
            serverMappingDao = db.serverMappingDao();
            serviceTypeDao = db.serviceTypeDao();
            serviceTypeExtraFareDao = db.serviceTypeExtraFareDao();
            terminalDao = db.terminalDao();
            ticketingSeatDao = db.ticketingSeatDao();
            usersDao = db.usersDao();
            vehicleDao = db.vehicleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
