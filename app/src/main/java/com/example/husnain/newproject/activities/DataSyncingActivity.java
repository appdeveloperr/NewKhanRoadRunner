package com.example.husnain.newproject.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;
import com.example.husnain.newproject.entities.City;
import com.example.husnain.newproject.entities.FareDetails;
import com.example.husnain.newproject.entities.Route;
import com.example.husnain.newproject.entities.RouteDetail;
import com.example.husnain.newproject.entities.Schedule;
import com.example.husnain.newproject.entities.ScheduleDetail;
import com.example.husnain.newproject.entities.ServiceType;
import com.example.husnain.newproject.entities.Terminal;
import com.example.husnain.newproject.entities.Users;
import com.example.husnain.newproject.entities.Vehicle;
import com.example.husnain.newproject.viewmodels.CityViewModel;
import com.example.husnain.newproject.viewmodels.FareDetailsViewModel;
import com.example.husnain.newproject.viewmodels.RouteDetailViewModel;
import com.example.husnain.newproject.viewmodels.RouteViewModel;
import com.example.husnain.newproject.viewmodels.ScheduleDetailViewModel;
import com.example.husnain.newproject.viewmodels.ScheduleViewModel;
import com.example.husnain.newproject.viewmodels.ServiceTypeViewModel;
import com.example.husnain.newproject.viewmodels.TerminalViewModel;
import com.example.husnain.newproject.viewmodels.UsersViewModel;
import com.example.husnain.newproject.viewmodels.VehicleViewModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSyncingActivity extends AppCompatActivity implements AsyncResponse {

    private CityViewModel cityViewModel;
    private ServiceTypeViewModel serviceTypeViewModel;
    private TerminalViewModel terminalViewModel;
    private RouteViewModel routeViewModel;
    private RouteDetailViewModel routeDetailViewModel;
    private ScheduleViewModel scheduleViewModel;
    private ScheduleDetailViewModel scheduleDetailViewModel;
    private UsersViewModel usersViewModel;
    private FareDetailsViewModel fareDetailsViewModel;
    private VehicleViewModel vehicleViewModel;

    boolean IS_TO_DELETE = false;
    String TAG;
    ProgressDialog progressDialog;

    Button btnGetCities, btnGetServiceTypes, btnGetTerminals, btnGetRouteAll, btnGetRouteAllDetail, btnGetScheduleAll, btnGetScheduleAllDetail,
            btnGetUsers, btnGetFareDetail, btnGetVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_syncing);

        initComponents();

        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        serviceTypeViewModel = ViewModelProviders.of(this).get(ServiceTypeViewModel.class);
        terminalViewModel = ViewModelProviders.of(this).get(TerminalViewModel.class);
        routeViewModel = ViewModelProviders.of(this).get(RouteViewModel.class);
        routeDetailViewModel = ViewModelProviders.of(this).get(RouteDetailViewModel.class);
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        scheduleDetailViewModel = ViewModelProviders.of(this).get(ScheduleDetailViewModel.class);
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);
        fareDetailsViewModel = ViewModelProviders.of(this).get(FareDetailsViewModel.class);
        vehicleViewModel = ViewModelProviders.of(this).get(VehicleViewModel.class);


        cityViewModel.getAll().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(@Nullable List<City> cities) {
                Log.e("Cities List:","Size " + cities.size());
            }
        });

        serviceTypeViewModel.getAll().observe(this, new Observer<List<ServiceType>>() {
            @Override
            public void onChanged(@Nullable List<ServiceType> serviceTypes) {
                Log.e("Srv Type List:","Size " + serviceTypes.size());
            }
        });

        terminalViewModel.getAll().observe(this, new Observer<List<Terminal>>() {
            @Override
            public void onChanged(@Nullable List<Terminal> terminals) {
                Log.e("Terminal List:","Size " + terminals.size());
            }
        });

        routeViewModel.getAll().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(@Nullable List<Route> routes) {
                Log.e("Route List:","Size " + routes.size());
            }
        });
        routeDetailViewModel.getAll().observe(this, new Observer<List<RouteDetail>>() {
            @Override
            public void onChanged(@Nullable List<RouteDetail> routeDetails) {
                Log.e("Route Detail List:","Size " + routeDetails.size());
            }
        });
        scheduleViewModel.getAll().observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(@Nullable List<Schedule> schedules) {
                Log.e("Schedule List:","Size " + schedules.size());
            }
        });
        scheduleDetailViewModel.getAll().observe(this, new Observer<List<ScheduleDetail>>() {
            @Override
            public void onChanged(@Nullable List<ScheduleDetail> scheduleDetails) {
                Log.e("Schedule Detail List:","Size " + scheduleDetails.size());
            }
        });

        usersViewModel.getAll().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(@Nullable List<Users> users) {
                Log.e("Users List:","Size " + users.size());
            }
        });

        fareDetailsViewModel.getAll().observe(this, new Observer<List<FareDetails>>() {
            @Override
            public void onChanged(@Nullable List<FareDetails> fareDetails) {
                Log.e("Fare Detail List:","Size " + fareDetails.size());
            }
        });

        vehicleViewModel.getAll().observe(this, new Observer<List<Vehicle>>() {
            @Override
            public void onChanged(@Nullable List<Vehicle> vehicles) {
                Log.e("Vehicles List:","Size " + vehicles.size());
            }
        });
    }

    public void initComponents(){
        btnGetCities = (Button) findViewById(R.id.btnGetCities);
        btnGetServiceTypes = (Button) findViewById(R.id.btnGetServiceTypes);
        btnGetTerminals = (Button) findViewById(R.id.btnGetTerminals);
        btnGetRouteAll = (Button) findViewById(R.id.btnGetRouteAll);
        btnGetRouteAllDetail = (Button) findViewById(R.id.btnGetRouteAllDetail);
        btnGetScheduleAll = (Button) findViewById(R.id.btnGetScheduleAll);
        btnGetScheduleAllDetail = (Button) findViewById(R.id.btnGetScheduleAllDetail);
        btnGetUsers = (Button) findViewById(R.id.btnGetUsers);
        btnGetFareDetail = (Button) findViewById(R.id.btnGetFareDetail);
        btnGetVehicles = (Button) findViewById(R.id.btnGetVehicles);
    }

    public void SyncingClicks(View view){
        IS_TO_DELETE = false;
        int id = view.getId();
        switch (id){
            case R.id.btnGetCities:
                fetchCitiesVolley();
                break;
            case R.id.btnGetServiceTypes:
                fetchServiceTypeVolley();
                break;
            case R.id.btnGetTerminals:
                fetchTerminalsVolley();
                break;
            case R.id.btnGetRouteAll:
                fetchRoutesVolley();
                break;
            case R.id.btnGetRouteAllDetail:
                fetchRoutesDetailVolley();
                break;
            case R.id.btnGetScheduleAll:
                fetchScheduleVolley();
                break;
            case R.id.btnGetUsers:
                fetchUsersVolley();
                break;
            case R.id.btnGetFareDetail:
                fetchFareListVolley();
                break;
            case R.id.btnGetScheduleAllDetail:
                fetchScheduleDetailVolley();
                break;
            case R.id.btnGetVehicles:
                fetchVehiclesVolley();
                break;
        }
    }

    public void fetchCitiesVolley(){
        TAG = Constants.TAGS.TAG_GET_CITIES;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_CITIES);
    }

    public void fetchServiceTypeVolley(){
        TAG = Constants.TAGS.TAG_GET_SERVICE_TYPES;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_SERVICE_TYPES);
    }

    public void fetchTerminalsVolley(){
        TAG = Constants.TAGS.TAG_GET_TERMINALS;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_TERMINALS);
    }

    public void fetchRoutesVolley(){
        TAG = Constants.TAGS.TAG_GET_ROUTE_ALL;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_ROUTE_ALL);
    }

    public void fetchRoutesDetailVolley(){
        TAG = Constants.TAGS.TAG_GET_ROUTE_ALL_DETAIL;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_ROUTE_ALL_DETAIL);
    }

    public void fetchScheduleVolley(){
        TAG = Constants.TAGS.TAG_GET_SCHEDULE_ALL;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_SCHEDULE_ALL);
    }

    public void fetchScheduleDetailVolley(){
        TAG = Constants.TAGS.TAG_GET_SCHEDULE_ALL_DETAIL;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_SCHEDULE_ALL_DETAIL);
    }

    public void fetchUsersVolley(){
        TAG = Constants.TAGS.TAG_GET_ALL_USERS;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_ALL_USERS);
    }

    public void fetchFareListVolley(){
        TAG = Constants.TAGS.TAG_GET_FARE_LIST;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_FARE_LIST);
    }

    public void fetchVehiclesVolley(){
        TAG = Constants.TAGS.TAG_GET_ALL_VEHICLES;
        Map<String, String> map = new HashMap<String, String>();
        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCallwithProgressBar(map, this, Request.Method.GET, Constants.URLS.GET_VEHICLE_LIST);
    }


    @Override
    public void processFinish(String output) {
        if(TAG.equals(Constants.TAGS.TAG_GET_CITIES))
            GetCitySucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_SERVICE_TYPES))
            GetServiceTypeSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_TERMINALS))
            GetTerminalsSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_ROUTE_ALL))
            GetRouteSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_ROUTE_ALL_DETAIL))
            GetRouteDetailScucceded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_SCHEDULE_ALL))
            GetScheduleSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_SCHEDULE_ALL_DETAIL))
            GetScheduleDetailSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_ALL_USERS))
            GetUsersSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_FARE_LIST))
            GetFareDetailSucceeded(output);
        else if (TAG.equals(Constants.TAGS.TAG_GET_ALL_VEHICLES))
            GetVehicleSucceeded(output);
    }

    @Override
    public void onError(String Error) {
        Log.e("OnError:", Error);
        Toast.makeText(this, Error, Toast.LENGTH_LONG).show();

    }

    public void GetTerminalsSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray != null && jsonArray.length()>0){
                terminalViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    Terminal terminal = gson.fromJson(jsonObject.toString(), Terminal.class);
                    terminalViewModel.insert(terminal);
                }
                btnGetTerminals.setText("Get Terminals " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_TERMINALS,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_TERMINALS,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetServiceTypeSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);

            if(jsonArray !=null && jsonArray.length() > 0){
                serviceTypeViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    ServiceType serviceType = gson.fromJson(jsonObject.toString(), ServiceType.class);
                    serviceTypeViewModel.insert(serviceType);
                }
                btnGetServiceTypes.setText("Get Service Types " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_SERVICE_TYPES,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_SERVICE_TYPES,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetCitySucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray !=null && jsonArray.length() > 0 ){
                cityViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    City city = gson.fromJson(jsonObject.toString(), City.class);
                    cityViewModel.insert(city);
                }
                btnGetCities.setText("Get Cities " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_CITIES,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_CITIES,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetRouteSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray !=null && jsonArray.length()>0){
                routeViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    Route route = gson.fromJson(jsonObject.toString(), Route.class);
                    routeViewModel.insert(route);
                }
                btnGetRouteAll.setText("Get Routes " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_ROUTE_ALL,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_ROUTE_ALL,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetRouteDetailScucceded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray != null && jsonArray.length()>0){
                routeDetailViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    RouteDetail routeDetail = gson.fromJson(jsonObject.toString(), RouteDetail.class);
                    routeDetailViewModel.insert(routeDetail);
                }
                btnGetRouteAllDetail.setText("Get Routes Detail " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_ROUTE_ALL_DETAIL,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_ROUTE_ALL_DETAIL,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetScheduleSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray != null && jsonArray.length() > 0){
                scheduleViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    Schedule schedule = gson.fromJson(jsonObject.toString(), Schedule.class);
                    scheduleViewModel.insert(schedule);
                }
                btnGetScheduleAll.setText("Get Schedules " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_SCHEDULE_ALL,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_SCHEDULE_ALL,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetScheduleDetailSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray != null && jsonArray.length() > 0){
                scheduleDetailViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    ScheduleDetail scheduleDetail = gson.fromJson(jsonObject.toString(), ScheduleDetail.class);
                    scheduleDetailViewModel.insert(scheduleDetail);
                }
                btnGetScheduleAllDetail.setText("Get Schedule Detail " + "(Total: "  + jsonArray.length() + ")");
            }
            else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_SCHEDULE_ALL_DETAIL,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_SCHEDULE_ALL_DETAIL,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetUsersSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if( jsonArray != null && jsonArray.length() > 0){
                usersViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    Users users = gson.fromJson(jsonObject.toString(), Users.class);
                    usersViewModel.insert(users);
                }
                btnGetUsers.setText("Get Users " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_ALL_USERS,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_ALL_USERS,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetFareDetailSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if(jsonArray !=null && jsonArray.length() > 0){
                fareDetailsViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    FareDetails fareDetails = gson.fromJson(jsonObject.toString(), FareDetails.class);
                    fareDetailsViewModel.insert(fareDetails);
                }
                btnGetFareDetail.setText("Get Fares " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_FARE_LIST,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_FARE_LIST,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void GetVehicleSucceeded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);
            if( jsonArray != null && jsonArray.length() > 0){
                vehicleViewModel.deleteAll();
                ShowProgressWithNumbers(jsonArray.length());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Gson gson = new Gson();
                    Vehicle vehicle = gson.fromJson(jsonObject.toString(), Vehicle.class);
                    vehicleViewModel.insert(vehicle);
                }
                btnGetVehicles.setText("Get Vehicles " + "(Total: "  + jsonArray.length() + ")");
            } else {
                Toast.makeText(this,getResources().getString(R.string.msg_zero_record) + Constants.TAGS.TAG_GET_ALL_VEHICLES,Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this,"Server data not fetched successfully." + Constants.TAGS.TAG_GET_ALL_VEHICLES,Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void ShowProgressWithNumbers(final int max){
        progressDialog = new ProgressDialog(DataSyncingActivity.this);
        progressDialog.setMax(max);
        progressDialog.setMessage("Inserting Data....");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog
                            .getMax()) {
                        Thread.sleep(max);
                        handle.sendMessage(handle.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog
                                .getMax()) {
                            progressDialog.dismiss();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.incrementProgressBy(1);
        }
    };

}
