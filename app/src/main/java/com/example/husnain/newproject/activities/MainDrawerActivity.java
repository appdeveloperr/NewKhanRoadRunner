package com.example.husnain.newproject.activities;

import android.Manifest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.Interfaces.TicketingScheduleResponse;
import com.example.husnain.newproject.PrintPakage.PrintTicketActivity;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.Utils.Utile;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;
import com.example.husnain.newproject.entities.TicketingSchedule;
import com.example.husnain.newproject.entities.TicketingSeat;
import com.example.husnain.newproject.entities.Vehicle;
import com.example.husnain.newproject.viewmodels.ScheduleViewModel;
import com.example.husnain.newproject.viewmodels.SeatsInfoViewModel;
import com.example.husnain.newproject.viewmodels.TicketingScheduleViewModel;
import com.example.husnain.newproject.viewmodels.TicketingSeatViewModel;
import com.example.husnain.newproject.viewmodels.VehicleViewModel;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse,TicketingScheduleResponse {

    String TAG = "";

    TextView tvNavScheduleTitle,tvNavDepartureTime,tvNavVoucherNo,tvNavUserName, tvNavRouteName,
            tvNavVehicleNo, tvNavDestination;

    String TicketingScheduleJSONOutupt = "";
    String TicketingSeatJSONOutupt = "";
    TicketingSchedule ticketingSchedule;
    LiveData<List<TicketingSchedule>> liveData_ticketing_schedule;
    private TicketingScheduleViewModel ticketingScheduleViewModel;
    private TicketingSeatViewModel ticketingSeatViewModel;
    private ScheduleViewModel scheduleViewModel;
    private VehicleViewModel vehicleViewModel;

    private LocationManager locationManager;
    private String provider;

    private SeatsInfoViewModel seatsInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(MainDrawerActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        tvNavDepartureTime = (TextView) headerView.findViewById(R.id.tv_depttime_title);
        tvNavRouteName = (TextView) headerView.findViewById(R.id.tv_route_name);
        tvNavVehicleNo = (TextView) headerView.findViewById(R.id.tv_vehicle_no);
        tvNavVoucherNo = (TextView) headerView.findViewById(R.id.tv_voucher_no);



        tvNavUserName = (TextView) headerView.findViewById(R.id.tv_user_name);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                tvNavRouteName.setText("Route:" + Global.RouteName);
                tvNavDepartureTime.setText("Dept DateTime: " + Global.VoucherDate + " " + Global.VoucherTime);
                tvNavVoucherNo.setText("VoucherNo: " + Global.VoucherNo);
                tvNavUserName.setText("UserName: " + Global.User_Name);
                tvNavVehicleNo.setText("Vehicle: " + Global.Vehicle_Name + Global.Vehicle_No);

            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



            /** Called when a drawer has settled in a completely closed state. *//*
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            *//** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                session = new SessionManager(getApplicationContext());
                user = session.getUserDetails();
                profilepic.setImageBitmap(StringToBitMap(user.get(SessionManager.KEY_PROFILEPIC)));
                name.setText(user.get(SessionManager.KEY_NAME));
                lastsynced.setText(lastsynced());
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };*/

        ticketingSeatViewModel = ViewModelProviders.of(this).get(TicketingSeatViewModel.class);
        ticketingScheduleViewModel = ViewModelProviders.of(this).get(TicketingScheduleViewModel.class);
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        seatsInfoViewModel = ViewModelProviders.of(this).get(SeatsInfoViewModel.class);
        vehicleViewModel = ViewModelProviders.of(this).get(VehicleViewModel.class);

        /*ticketingScheduleViewModel.getAll().observe(this, new Observer<List<TicketingSchedule>>() {
            @Override
            public void onChanged(@Nullable List<TicketingSchedule> ticketingSchedules) {
                Log.e("Tick Sched List:","Size " + ticketingSchedules.size());
                if(ticketingSchedules.size()>0){
                    ticketingSchedule = ticketingSchedules.get(0);
                    String json = BuildTicketScheduleJSONArray(ticketingSchedules);

                    Log.e("Ticketing Schedule",json);

                }
            }
        });*/

       /* con = ConnectionHelper(Constants.SQL_SERVER.SQL_USER_NAME,Constants.SQL_SERVER.PASSWORD,
                Constants.SQL_SERVER.DB_NAME, Constants.SQL_SERVER.IP_ADDRESS);*/

        //extractAndSaveData("2,25,00:00,11/06/2019  10:23:39PM,7,9110619001 DEMO");


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> list = locationManager.getProviders(true);

        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //GPS
            provider = LocationManager.GPS_PROVIDER;
//        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
//            //NETWORK
//            provider = LocationManager.NETWORK_PROVIDER;

        } else {
            Utile.dialogEnableLocation(getResources().getString(R.string.app_name),this);
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {

            Log.e("location is not null: ","Lat: " + Global.Lattitude + "Long: " + Global.Longitude);

            Global.Lattitude = location.getLatitude();
            Global.Longitude = location.getLongitude();

            /*progressBar.setVisibility(View.GONE);
            ib_pin_location.setVisibility(View.VISIBLE);
            ib_pin_location.setBackground(getResources().getDrawable(R.drawable.ic_location_green));*/

        }
        /**
         *
         */
        Log.e("Manual Challan Act:"," LocationListener Started");
        locationManager.requestLocationUpdates(provider, 1, 1,
                locationListener);


        if( AppSession.getBoolean(Constants.SHARED_PREF.IS_VOUCHER_SCANNED)!=null && AppSession.getBoolean(Constants.SHARED_PREF.IS_VOUCHER_SCANNED)){
           SetGlobalValues();
        }
        //SetGlobalValues();
    }


    LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub

        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onLocationChanged(Location location) {
            // TODO Auto-generated method stub
            /*Global.Lattitude = String.valueOf(location.getLatitude());
            Global.Longitude = String.valueOf(location.getLongitude());

            Log.e("onLocationChange: ","Lat: " + Global.Lattitude + "Long: " + Global.Longitude);
*/
            /*progressBar.setVisibility(View.GONE);
            ib_pin_location.setVisibility(View.VISIBLE);
            ib_pin_location.setBackground(getResources().getDrawable(R.drawable.ic_location_green));*/

        }
    };

    public String BuildTicketScheduleJSONArray(List<TicketingSchedule> list){
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i< list.size(); i++){

            jsonArray.put(Utile.GenerateTicketScheduleJSONObject(list.get(i)));
        }
        return jsonArray.toString();
    }

    public String BuildTicketSeatJSONArray(List<TicketingSeat> list){
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i< list.size(); i++){
            jsonArray.put(Utile.GenerateTicketSeatJSONObject(list.get(i)));
        }
        return jsonArray.toString();
    }

    /*public JSONObject GenerateTicketScheduleJSONObject(TicketingSchedule ticketingSchedule){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.TICKETING_SCHEDULE_ID,
                    ticketingSchedule.getTicketing_Schedule_ID());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.SR_NO,
                    ticketingSchedule.getSr_No());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.TS_DATE,
                    ticketingSchedule.getTS_Date());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.SCHEDULE_ID,
                    ticketingSchedule.getSchedule_ID());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_NO,
                    ticketingSchedule.getVoucher_No());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_STATUS,
                    ticketingSchedule.getVoucher_Status());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_OPENED_BY,
                    ticketingSchedule.getVoucher_Opened_By());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_CLOSED_BY,
                    ticketingSchedule.getVoucher_Closed_By());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VOUCHER_CLOSED_DATE,
                    ticketingSchedule.getVoucher_Closed_Date());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.DEPARTURE_TIME,
                    ticketingSchedule.getDeparture_Time());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.VEHICLE_ID,
                    ticketingSchedule.getVehicle_ID());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.DRIVER_NAME,
                    ticketingSchedule.getDriver_Name());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.HOSTESS_NAME,
                    ticketingSchedule.getHostess_Name());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.USER_ID,
                    ticketingSchedule.getUser_Id());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACCESS_DATETIME,
                    ticketingSchedule.getAccess_DateTime());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACCESS_SYS_NAME,
                    ticketingSchedule.getAccess_Sys_Name());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACCESS_TERMINAL_ID,
                    ticketingSchedule.getAccess_Terminal_Id());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.ACTUAL_DEPARTURE_TIME,
                    ticketingSchedule.getActual_Departure_Time());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.GUARD,
                    ticketingSchedule.getGuard());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.IS_CLOSED,
                    ticketingSchedule.getIs_Closed());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.BOOK_ID,
                    ticketingSchedule.getBook_Id());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.IS_PULLED,
                    ticketingSchedule.getIs_Pulled());
            jsonObject.put(Constants.TICKETING_SCHEDULE_COL.SERVICETYPE_ID,
                    ticketingSchedule.getServiceType_Id());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public JSONObject GenerateTicketSeatJSONObject(TicketingSeat ticketingSeat){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(Constants.TICKETING_SEAT_COL.TICKETING_SEAT_ID,
                    ticketingSeat.getTicketing_Seat_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TICKETING_SCHEDULE_ID,
                    ticketingSeat.getTicketing_Schedule_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.SEAT_NO,
                    ticketingSeat.getSeat_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.STATUS,
                    ticketingSeat.getStatus());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISSUE_DATE,
                    ticketingSeat.getIssue_Date());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISSUE_TERMINAL,
                    ticketingSeat.getIssue_Terminal());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISSUED_BY,
                    ticketingSeat.getIssued_By());
            jsonObject.put(Constants.TICKETING_SEAT_COL.SOURCE_ID,
                    ticketingSeat.getSource_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.DESTINATION_ID,
                    ticketingSeat.getDestination_ID());
            jsonObject.put(Constants.TICKETING_SEAT_COL.PASSENGER_NAME,
                    ticketingSeat.getPassenger_Name());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CONTACT_NO,
                    ticketingSeat.getContact_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.FARE,
                    ticketingSeat.getFare());
            jsonObject.put(Constants.TICKETING_SEAT_COL.DISCOUNT,
                    ticketingSeat.getDiscount());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TICKET_SR_NO,
                    ticketingSeat.getTicket_Sr_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_BOOKEDSOLD,
                    ticketingSeat.getIs_bookedSold());
            jsonObject.put(Constants.TICKETING_SEAT_COL.BOOKED_USER,
                    ticketingSeat.getBooked_User());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_SMS_SENT,
                    ticketingSeat.getIs_SMS_Sent());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CNIC,
                    ticketingSeat.getCNIC());
            jsonObject.put(Constants.TICKETING_SEAT_COL.GENDER,
                    ticketingSeat.getGender());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_TRANSIT,
                    ticketingSeat.getIs_Transit());
            jsonObject.put(Constants.TICKETING_SEAT_COL.SHIFT_USER_ID,
                    ticketingSeat.getShift_User_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.PNR_NO,
                    ticketingSeat.getPNR_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TELENOR,
                    ticketingSeat.getTelenor());
            jsonObject.put(Constants.TICKETING_SEAT_COL.PAYMENTDATE,
                    ticketingSeat.getPaymentDate());
            jsonObject.put(Constants.TICKETING_SEAT_COL.TELENOR,
                    ticketingSeat.getTelenor());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ISMISSED,
                    ticketingSeat.getIsMissed());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CHANGETICKET_NO,
                    ticketingSeat.getChangeTicket_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.COLLECTIONPOINT,
                    ticketingSeat.getCollectionPoint());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CHANGETYPE,
                    ticketingSeat.getChangeType());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ROUTE_SR_NO,
                    ticketingSeat.getRoute_Sr_No());
            jsonObject.put(Constants.TICKETING_SEAT_COL.OPERATOR_ID,
                    ticketingSeat.getOperator_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.CUSTOMER_ID,
                    ticketingSeat.getCustomer_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.POINTS,
                    ticketingSeat.getPoints());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_ONLINEPRINTED,
                    ticketingSeat.getIs_OnlinePrinted());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ONLINEPRINTER_USERID,
                    ticketingSeat.getOnlinePrinter_UserId());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ONLINEPRINT_TERMINAL_ID,
                    ticketingSeat.getOnlinePrint_Terminal_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.ONLINEPRINT_DATE,
                    ticketingSeat.getOnlinePrint_Date());
            jsonObject.put(Constants.TICKETING_SEAT_COL.INVOICE_ID,
                    ticketingSeat.getInvoice_Id());
            jsonObject.put(Constants.TICKETING_SEAT_COL.IS_ONLINE,
                    ticketingSeat.getIs_Online());
            jsonObject.put(Constants.TICKETING_SEAT_COL.LATITUDE,
                    ticketingSeat.getLatitude());
            jsonObject.put(Constants.TICKETING_SEAT_COL.LONGITUDE,
                    ticketingSeat.getLongitude());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }*/

    public void scanVoucher(View view){
        //extractAndSaveData("2,25,00:00,11/06/2019  10:23:39PM,7,9110619001 DEMO");
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    public void PostTicketingScheduleData(String mRequestBody){
        if(Utile.isNetConnected(this)) {
            TAG = Constants.TAGS.TAG_SCHEDULE;
            VolleyRequest volleyRequest = new VolleyRequest();
            volleyRequest.delegate = this;
            volleyRequest.jsonVolleyRequest(this, mRequestBody, Request.Method.POST, Constants.URLS.POST_TICKETING_SCHEDULE);
        } else {
            Toast.makeText(this,getResources().getString(R.string.msg_no_internet),Toast.LENGTH_LONG).show();
        }
    }

    public void PostTicketingSeatData(String mRequestBody){
        if(Utile.isNetConnected(this)){
            TAG = Constants.TAGS.TAG_SEATS;
            VolleyRequest volleyRequest = new VolleyRequest();
            volleyRequest.delegate = this;
            volleyRequest.jsonVolleyRequest(this,mRequestBody, Request.Method.POST, Constants.URLS.POST_TICKETING_SEAT);
        } else {
            Toast.makeText(this,getResources().getString(R.string.msg_no_internet),Toast.LENGTH_LONG).show();
        }
    }

    public void PushDataToServer(){
        try{
            List<TicketingSchedule> ticketingSchedules = ticketingScheduleViewModel.getUnPushedData();

            if(ticketingSchedules.size()>0){
                ticketingSchedule = ticketingSchedules.get(0);
                TicketingScheduleJSONOutupt = BuildTicketScheduleJSONArray(ticketingSchedules);

                Log.e("Ticketing Schedule",TicketingScheduleJSONOutupt);
                PostTicketingScheduleData(TicketingScheduleJSONOutupt);
            } else {
                PushTicketingSeatDataToServer();
            }
        } catch (Exception e){
             Toast.makeText(this,"Something went wong during uploading data.", Toast.LENGTH_LONG).show();
        }


        /*ticketingScheduleViewModel.getUnPushedData().observe(this, new Observer<List<TicketingSchedule>>() {
            @Override
            public void onChanged(@Nullable List<TicketingSchedule> ticketingSchedules) {
                Log.e("Tick Sched List:","Size " + ticketingSchedules.size());
                if(ticketingSchedules.size()>0){
                    ticketingSchedule = ticketingSchedules.get(0);
                    TicketingScheduleJSONOutupt = BuildTicketScheduleJSONArray(ticketingSchedules);

                    Log.e("Ticketing Schedule",TicketingScheduleJSONOutupt);
                    PostTicketingScheduleData(TicketingScheduleJSONOutupt);
                } else {
                    PushTicketingSeatDataToServer();
                }
            }
        });*/


    }

    private void PushTicketingSeatDataToServer(){
        List<TicketingSeat> ticketingSeats = ticketingSeatViewModel.getUnPushedData();

        if(ticketingSeats.size()>0) {
            //ticketingSeats = ticketingSeats.get(0);
            TicketingSeatJSONOutupt = BuildTicketSeatJSONArray(ticketingSeats);
            Log.e("Ticketing Seat",TicketingSeatJSONOutupt);
            PostTicketingSeatData(TicketingSeatJSONOutupt);
        }

        /*ticketingSeatViewModel.getUnPushedData().observe(this, new Observer<List<TicketingSeat>>() {
            @Override
            public void onChanged(@Nullable List<TicketingSeat> ticketingSeats) {

                if(ticketingSeats.size()>0){
                    //ticketingSeats = ticketingSeats.get(0);
                    TicketingSeatJSONOutupt = BuildTicketSeatJSONArray(ticketingSeats);
                    Log.e("Ticketing Seat",TicketingSeatJSONOutupt);
                    PostTicketingSeatData(TicketingSeatJSONOutupt);
                }
            }
        });*/
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.action_upload_data){
            PushDataToServer();
            return true;
        } else if (id == R.id.action_delete_data){
           if(Global.User_Name.equals("admin")){
               ticketingSeatViewModel.DeleteAllRecords();
               ticketingScheduleViewModel.DeleteAllTicketingSchedules();
               Toast.makeText(this,"All Schedules and Seats are deleted.",Toast.LENGTH_LONG).show();
           } else {
               Toast.makeText(this,"You are not authorized to delete data.",Toast.LENGTH_LONG).show();
           }

            /*PushDataToServer();*/
           return  true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_app_info) {
            Intent i = new Intent(this,InfoActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {
            ShareVia();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void MainActivityClicks(View view){

        if(((AppSession.get(Constants.SHARED_PREF.VOUCHER_NO)).equals("blank_string_key"))){
            Utile.showAlertDialog("Please scan voucher to proceed.", this).show();
            /*Global.TicketingScheduleID != 0*/
        } else if(MainActivity.mConnector == null){
            Utile.showAlertDialog("Check Your Printer Connection.", this).show();
        } else if( !((AppSession.get(Constants.SHARED_PREF.VOUCHER_NO)).equals("blank_string_key")) && MainActivity.mConnector != null ){
            SetGlobalValues();
            Intent i = new Intent(this, PrintTicketActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            try{
                Log.e("IN Activity Result: ", result.getContents());

                Toast.makeText(this,result.getContents() , Toast.LENGTH_LONG).show();

                //String resul = "2,25,00:00,11/06/2019  10:23:39PM,7,9110619001 DEMO";
                extractAndSaveData(result.getContents());
            }catch (Exception e){
                Toast.makeText(this,"Invalid Pattern",Toast.LENGTH_LONG).show();
            }

        }else
            super.onActivityResult(requestCode, resultCode, data);
    }

    public void extractAndSaveData(String result){

        String[] str = (result.replace(" DEMO","")).split("\\^"); //result.getContents().split(",");
        Global.Vehicle_ID = (int) Double.parseDouble(str[0]);
        Global.VoucherScheduleId = (int) Double.parseDouble(str[1]);
        Global.VoucherTime = str[2];
        Global.VoucherDate = str[3];
        Global.VoucherServiceTypeId = (int) Float.parseFloat(str[4]);
        Global.VoucherNo = Long.parseLong(str[5]);

        int VehicleID = AppSession.getInt(Constants.SHARED_PREF.VEHICLE_ID);
        int ScheduleID = AppSession.getInt(Constants.SHARED_PREF.SCHEDULE_ID);
        String  VoucherDate = AppSession.get(Constants.SHARED_PREF.VOUCHER_DATE);
        String  VoucherTime = AppSession.get(Constants.SHARED_PREF.VOUCHER_TIME);
        int VoucherServiceTypeId = AppSession.getInt(Constants.SHARED_PREF.SERVICE_TYPE_ID);

        if( Global.Vehicle_ID != VehicleID ||
                Global.VoucherScheduleId != ScheduleID ||
                !Global.VoucherTime.equals(VoucherTime) ||
                !Global.VoucherDate.equals(VoucherDate) ||
                Global.VoucherServiceTypeId !=VoucherServiceTypeId ){
            seatsInfoViewModel.DeleteAllRecords();
            AppSession.put(Constants.SHARED_PREF.IS_INSERTED,false);
        }

        AppSession.put(Constants.SHARED_PREF.IS_VOUCHER_SCANNED, true);
        AppSession.put(Constants.SHARED_PREF.VEHICLE_ID, Global.Vehicle_ID );
        AppSession.put(Constants.SHARED_PREF.SCHEDULE_ID, Global.VoucherScheduleId);
        AppSession.put(Constants.SHARED_PREF.VOUCHER_TIME, Global.VoucherTime );
        AppSession.put(Constants.SHARED_PREF.VOUCHER_DATE,  Global.VoucherDate);
        AppSession.put(Constants.SHARED_PREF.SERVICE_TYPE_ID, Global.VoucherServiceTypeId );
        AppSession.put(Constants.SHARED_PREF.VOUCHER_NO, "" + Global.VoucherNo);

        GetSingleRow getSingleRow = new GetSingleRow(ticketingScheduleViewModel,Global.VoucherScheduleId, Global.VoucherTime, Global.VoucherDate, Global.VoucherServiceTypeId, Global.VoucherNo);
        getSingleRow.delegate = this;
        getSingleRow.execute();

        if(Utile.isNetConnected(this)){
            PushDataToServer();
        } else {
            Toast.makeText(this, "Internet is not connected. ", Toast.LENGTH_LONG).show();
        }

    }

    public void GetRouteAndVehicle(){
        try{
            Global.RouteName = scheduleViewModel.getScheduleByID(Global.VoucherScheduleId).getSchedule_Title();
            Vehicle vehicle = vehicleViewModel.getVehicleByID(Global.Vehicle_ID);
            Global.Vehicle_Name = vehicle.getVeh_Name();
            Global.Vehicle_No = "" + vehicle.getRegistration_No() ;
        } catch (Exception e){
            Utile.showAlertDialog("There is no schedule or vehicle against id",this).show();
        }

    }

    public void SetGlobalValues(){
        Global.Vehicle_ID = AppSession.getInt(Constants.SHARED_PREF.VEHICLE_ID);
        Global.VoucherScheduleId = AppSession.getInt(Constants.SHARED_PREF.SCHEDULE_ID);
        Global.VoucherTime = AppSession.get(Constants.SHARED_PREF.VOUCHER_TIME);
        Global.VoucherDate = AppSession.get(Constants.SHARED_PREF.VOUCHER_DATE);
        Global.VoucherServiceTypeId = AppSession.getInt(Constants.SHARED_PREF.SERVICE_TYPE_ID);
        if( !(AppSession.get(Constants.SHARED_PREF.VOUCHER_NO).equals("blank_string_key"))){
            Global.VoucherNo = Long.parseLong(AppSession.get(Constants.SHARED_PREF.VOUCHER_NO));
        }

        GetRouteAndVehicle();
    }

    private void InsertScheduleToServerSucceded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Gson gson = new Gson();
                TicketingSchedule ticketingSchedule = gson.fromJson(jsonObject.toString(), TicketingSchedule.class);
                ticketingSchedule.setIsPushed(true);
                ticketingScheduleViewModel.update(ticketingSchedule);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void InsertTicketingSeatToServerSucceded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Gson gson = new Gson();
                TicketingSeat ticketingSeat = gson.fromJson(jsonObject.toString(), TicketingSeat.class);
                ticketingSeat.setIsPushed(true);
                ticketingSeatViewModel.update(ticketingSeat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void processFinish(String output) {

        if(output.trim().equals("\"SUCCESS\"")){
            if(TAG.equals(Constants.TAGS.TAG_SCHEDULE)){
                InsertScheduleToServerSucceded(TicketingScheduleJSONOutupt);
                PushTicketingSeatDataToServer();
            } else if(TAG.equals(Constants.TAGS.TAG_SEATS)) {
                    InsertTicketingSeatToServerSucceded(TicketingSeatJSONOutupt);
                //ticketingScheduleViewModel
            }
        }


    }

    @Override
    public void onError(String Error) {
        Log.e("Error: ", Error);
        Toast.makeText(this,Error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSingleRowSucceded(TicketingSchedule output) {
        if(output==null){
            TicketingSchedule ticketingSchedule = new TicketingSchedule(0,Global.VoucherDate,Global.VoucherScheduleId,
                    Global.VoucherNo, 0,0,0,"",Global.VoucherTime,
                    Global.Vehicle_ID,"","", Global.User_ID,"","",0,
                    "","",0,0,0,Global.VoucherServiceTypeId,false);
            ticketingScheduleViewModel.insert(ticketingSchedule);

        } else {
            Log.e("Result: ", output.getDeparture_Time());
        }

    }

    private static class GetSingleRow extends AsyncTask<Void, Void ,TicketingSchedule> {
        public TicketingScheduleResponse delegate = null;
        private TicketingScheduleViewModel TSVM;

        int schedule_id; String dept_time;String date; int srv_type_id;
        long voucher_no;

        private GetSingleRow(TicketingScheduleViewModel TSVM,int schedule_id, String dept_time,String date, int srv_type_id, long voucher_no){
            this.TSVM = TSVM;
            this.schedule_id = schedule_id;
            this.dept_time = dept_time;
            this.date = date;
            this.srv_type_id = srv_type_id;
            this.voucher_no = voucher_no;
        }

        @Override
        protected TicketingSchedule doInBackground(Void... voids) {
            /*Dao.getPrevRecord();
            return Dao.getPrevRecord();*/
            return TSVM.getPrevRecord(schedule_id, dept_time, date, srv_type_id, voucher_no);
        }

        @Override
        protected void onPostExecute(TicketingSchedule aVoid) {
            super.onPostExecute(aVoid);
            delegate.getSingleRowSucceded(aVoid);
            if(aVoid!=null){
                Global.TicketingScheduleID =  aVoid.getTicketing_Schedule_ID();
            }
        }
    }


    public void ShareVia() {
        Intent sharingIntent = new Intent(
                Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "I am using this app. Give it a try.";

        sharingIntent.putExtra(Intent.EXTRA_TEXT,
                shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
