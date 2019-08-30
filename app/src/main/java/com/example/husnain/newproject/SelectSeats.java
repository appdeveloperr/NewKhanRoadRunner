package com.example.husnain.newproject;

import android.app.ProgressDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.Utils.Utile;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;
import com.example.husnain.newproject.viewmodels.SeatsInfoViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Husnain on 5/3/2018.
 */

public class SelectSeats extends AppCompatActivity implements AsyncResponse{

    ProgressDialog progressDialog;

    private static final String MY_PREF_NAME = "MyPrefsFile";
    //String starting_url;
    //Economy 49
    private Schedule_Info scheduleInfo;
    private final static String url_1 = "http://192.168.100.7:999/APIMain.aspx?type=SeatsByScheduleId&pDate=2018-05-15&pDep_Time=18:00&pSchedule_Id=4&fromCityId=54&toCityId=113&pMaskDate=%22%22&pMaskRoute=6&pMaskTerminalId=6";

    private final static String Check_Url = "http://110.36.208.66:999/APIMain.aspx?type=SeatsByScheduleId&pDate=2018-05-15&pDep_Time=18:00&pSchedule_Id=4&fromCityId=54&toCityId=113&pMaskDate=%22%22&pMaskRoute=6&pMaskTerminalId=6";
    private TextView showPrice;
    private TextView showSeats;
    private RecyclerView SRView;

//    //*************************************check
//    RelativeLayout layout;

    private Seats_Addapter_45 adapter;
    private ArrayList<SeatsInfo> List;
    private ArrayList<SeatsInfo> NewList;
    public static ArrayList<SeatsInfo> SelectedSeats = new ArrayList<>();;

    private String Bus_Type;
    String pDate;
    String pDep_Time;
    String pSchedule_Id;
    int fromCityId;
    int toCityId;
    String pMaskDate;
    //String pMaskRoute;
    String pMaskTerminalId;

    private int ch;
    private Handler handler;
    private ImageView Loading_Image;
    private float fare;

    private SeatsInfoViewModel seatsInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_seats);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Select Seats");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("New Khan Road Runners");
        progressDialog.setMessage("Please wait......");
        progressDialog.setCancelable(false);
        seatsInfoViewModel = ViewModelProviders.of(this).get(SeatsInfoViewModel.class);

        Bundle bundle = getIntent().getExtras();
        //pDate = bundle.getString("Date");
        fromCityId = getIntent().getExtras().getInt("fromCity");
        toCityId = getIntent().getExtras().getInt("toCity");
        fare = getIntent().getExtras().getFloat("fare");

        SRView = (RecyclerView)findViewById(R.id.SeatsRView);
        showPrice = (TextView)findViewById(R.id.showPrice);
        showSeats = (TextView)findViewById(R.id.showSeats);

        if(SelectedSeats.size()>0){
            showSeats.setText("Selected: "+ SelectedSeats.size());
            showPrice.setText("Price: "+ SelectedSeats.size() * SelectedSeats.get(0).getFare());
        }


        List = new ArrayList<SeatsInfo>();
        NewList = new ArrayList<>();

        actionbar.setTitle(Bus_Type);

        loadView();

    }


    public  void Refresh(View view)
    {
        loadView();
    }
    public void loadView(){

        if(Utile.isNetConnected(this)){
            handler = new Handler();

            Loading_Image = (ImageView)findViewById(R.id.loading_image);
            startRepeating();
            getVollyData();
        } else {
            populateData();
        }
    }

    public void thread(){


        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                populateData();
                            }
                        });

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
        };
        timer.start();

    }



//****************************************************** Loading Pictures ***********************************
    private void startRepeating()
    {
        /*Loading_Image.setVisibility(View.VISIBLE);
        ch = 0;
        runnable.run();*/

    }

    private void stopRepeating()
    {
        /*Loading_Image.setVisibility(View.GONE);
        handler.removeCallbacks(runnable);*/
        progressDialog.dismiss();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int i = ch % 4 + 1;
            if(i==1)
            {
                Loading_Image.setImageResource(R.drawable.a1);
            }
            else if (i==2)
            {
                Loading_Image.setImageResource(R.drawable.a2);
            }
            else if (i==3)
            {
                Loading_Image.setImageResource(R.drawable.a3);
            }
            else if (i==4)
            {
                Loading_Image.setImageResource(R.drawable.a4);
            }
            ch++;
            handler.postDelayed(this,500);
        }
    };
//****************************************************** Loading Pictures ***********************************

    public void Update_Seats(View view) {
        /*if (SelectedSeats.size() > 5)
        {
            Toast.makeText(this,"You can select only '5' seats, at once!",Toast.LENGTH_SHORT).show();
        }
        else*/
        if (Seats_Addapter_45.SeatId.size() > 0) {
            Intent intent = new Intent();
            /*intent.putExtra("pDep_Time", pDep_Time);
            intent.putExtra("pMaskDate", pMaskDate);
            intent.putExtra("pMaskRoute", pMaskRoute);
            intent.putExtra("pMaskTerminalId", pMaskTerminalId);*/

            intent.putExtra("SelectedSeats", SelectedSeats);
            setResult(Constants.ACTIVITY_CODES.SELECT_SEATS, intent);
            finish();
        } else {
            Toast.makeText(this, "Select at least one seat!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getVollyData() {
        Map<String, String> map = new HashMap<String, String>();

        Calendar c = Calendar.getInstance();

        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"  +  c.get(Calendar.DAY_OF_MONTH)  ;

        String URL = Constants.URLS.GET_SEAT_BY_SCHEDULE_ID + Utile.Fromatmdytoymd(Global.VoucherDate)  + "&pDep_Time=" + Global.VoucherTime + /*+ Global.VoucherTime*/
                "&pSchedule_Id=" + Global.VoucherScheduleId + "&fromCityId=" + fromCityId + "&toCityId=" + toCityId + "&pMaskDate="+ Utile.Fromatmdytoymd(Global.VoucherDate) +"&pMaskRoute=" + Global.VoucherServiceTypeId + "&pMaskTerminalId=1";

        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        progressDialog.show();
        volleyRequest.volleyRequestCall(map, this, Request.Method.GET, URL );
    }

    public void getData(String output)
    {
        //seatsInfoViewModel.DeleteAllRecords();
        //AppSession.put(Constants.SHARED_PREF.IS_INSERTED,false);
//        Toast.makeText(this,"getData start",Toast.LENGTH_LONG).show();
        /*output = "[{\"seat_id\":111,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":1,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":112,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":2,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":113,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":3,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":114,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":4,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":115,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":5,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":116,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":6,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":117,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":7,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":118,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":8,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":119,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":9,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":120,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":10,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":121,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":11,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":122,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":12,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":123,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":13,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":124,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":14,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":125,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":15,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":126,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":16,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":127,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":17,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":128,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":18,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":129,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":19,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":130,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":20,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":131,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":21,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":132,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":22,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":133,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":23,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":134,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":24,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":135,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":25,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":136,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":26,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":137,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":27,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":138,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":28,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":139,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":29,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":140,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":30,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":141,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":31,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":142,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":32,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":143,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":33,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":144,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":34,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":145,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":35,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":146,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":36,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":147,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":37,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":148,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":38,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":149,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":39,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":150,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":40,\"fare\":null,\"Gender\":\"\",\"Status\":1,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":151,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":41,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":152,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":42,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":153,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":43,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":154,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Reserved\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":44,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0},\n" +
                "{\"seat_id\":155,\"seat_name\":\"\",\"seat_type\":\"Seat\",\"seat_status\":\"Empty\",\"row_name\":\"A\",\"row_index\":0,\"col_index\":0,\"row_size\":0,\"col_size\":0,\"isAvailableForBooking\":0,\"AreaCategoryCod\":\"Regular\",\"Seat_No\":45,\"fare\":null,\"Gender\":\"\",\"Status\":4,\"Route_Sr_No\":0}]";*/
        try
        {
            if(output.contains("Empty") || output.contains("Reserved")){
                JSONArray jsonArray;

                jsonArray = new JSONArray(output);

                ArrayList<SeatsInfo> seatsInfoArrayList = new ArrayList<SeatsInfo>(seatsInfoViewModel.getAllList());
                for(int count=0;count<jsonArray.length();count++)
                {
                    JSONObject JO = jsonArray.getJSONObject(count);

                    int seat_id = JO.getInt("seat_id");
                    String seat_name = JO.getString("seat_name");
                    String seat_status = JO.getString("seat_status");
                    int Seat_No = JO.getInt("Seat_No");
                    String Gender = JO.getString("Gender");
                    float busfare = this.fare;
                    //String Gender = "Male";

                    SeatsInfo seatsInfo = new SeatsInfo(seat_id, seat_name, seat_status, Seat_No, busfare, Gender, Constants.SEAT_INS_TYPES.API, true);

                    if( AppSession.getBoolean(Constants.SHARED_PREF.IS_INSERTED) && seatsInfoArrayList.size() > 0 && (!seatsInfoArrayList.get(count).getSeat_status().equals(seatsInfo.getSeat_status()))
                            && (seatsInfoArrayList.get(count).getBookedBy().equals(Constants.SEAT_INS_TYPES.API))){

                        seatsInfoViewModel.update(seatsInfo);

                        if(Seats_Addapter_45.SeatId.size() > 0){
                            if (List.get(count).getBookedBy().equals(Constants.SEAT_INS_TYPES.API) &&
                                    Seats_Addapter_45.SeatId.contains(List.get(count).getSeat_id()) ) {
                                if(SelectedSeats.size() > 0) {
                                    SelectedSeats.remove(Seats_Addapter_45.SeatId.indexOf(List.get(count).getSeat_id()));
                                    Seats_Addapter_45.SeatId.remove(Seats_Addapter_45.SeatId.indexOf(List.get(count).getSeat_id()));

                                    showSeats.setText("Selected: "+ SelectedSeats.size());
                                    showPrice.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                                }
                            }
                        }

                    } else if(!AppSession.getBoolean(Constants.SHARED_PREF.IS_INSERTED)) {
                        seatsInfoViewModel.insert(seatsInfo);
                    }
                }

                AppSession.put(Constants.SHARED_PREF.IS_INSERTED,true);
                thread();
            } else {
                Toast.makeText(this,output,Toast.LENGTH_LONG).show();
            }

            //populateData();


            /*adapter = new Seats_Addapter_45(NewList, this, showSeats, showPrice, List, SelectedSeats);

            // Attach the adapter to the recyclerview to populate items
            SRView.setAdapter(adapter);
            // Set layout manager to position the items
            SRView.setLayoutManager(new LinearLayoutManager(this));*/

            //************************************************************************Recycler Views

            /*if(Bus_Type.trim().equals("Business Class 34"))
            {
                Seats_Addapter_34 seats_addapter_34;
                seats_addapter_34 = new Seats_Addapter_34(List, this, showSeats, showPrice, SelectedSeats);

                // Attach the adapter to the recyclerview to populate items
                SRView.setAdapter(seats_addapter_34);
                // Set layout manager to position the items
                SRView.setLayoutManager(new LinearLayoutManager(this));
            }
            else if (Bus_Type.trim().equals("Business Class 31"))
            {
                Seats_Addapter_31 seats_addapter_31;
                seats_addapter_31 = new Seats_Addapter_31(List, this, showSeats, showPrice, SelectedSeats);

                // Attach the adapter to the recyclerview to populate items
                SRView.setAdapter(seats_addapter_31);
                // Set layout manager to position the items
                SRView.setLayoutManager(new LinearLayoutManager(this));
            }
            else if (Bus_Type.trim().equals("Economy 44"))
            {
                Seats_Addapter_44 seats_addapter_44;
                seats_addapter_44 = new Seats_Addapter_44(NewList, this, showSeats, showPrice, List, SelectedSeats);

                // Attach the adapter to the recyclerview to populate items
                SRView.setAdapter(seats_addapter_44);
                // Set layout manager to position the items
                SRView.setLayoutManager(new LinearLayoutManager(this));
            }
            else if(Bus_Type.trim().equals("Economy 45")){
                adapter = new Seats_Addapter_45(NewList, this, showSeats, showPrice, List, SelectedSeats);

                // Attach the adapter to the recyclerview to populate items
                SRView.setAdapter(adapter);
                // Set layout manager to position the items
                SRView.setLayoutManager(new LinearLayoutManager(this));
            }
            else if (Bus_Type.trim().equals("Economy 49"))
            {
                adapter = new Seats_Addapter_45(NewList, this, showSeats, showPrice, List, SelectedSeats);

                // Attach the adapter to the recyclerview to populate items
                SRView.setAdapter(adapter);
                // Set layout manager to position the items
                SRView.setLayoutManager(new LinearLayoutManager(this));

//                Seats_Addapter_49 seats_addapter_49;
//                seats_addapter_49 = new Seats_Addapter_49(NewList, this, showSeats, showPrice, List, SelectedSeats);
//
//                // Attach the adapter to the recyclerview to populate items
//                SRView.setAdapter(seats_addapter_49);
//                // Set layout manager to position the items
//                SRView.setLayoutManager(new LinearLayoutManager(this));
            }*/

        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    public void populateData(){
        List.clear();
        NewList.clear();
        ArrayList<SeatsInfo> seatsInfoArrayList = new ArrayList<SeatsInfo>(seatsInfoViewModel.getAllList());
            for(int count=0;count<seatsInfoArrayList.size();count++)
            {
                seatsInfoArrayList.get(count).setFare(this.fare);
                if(seatsInfoArrayList.size()==50) {
                    if(count % 4 == 0 & count <= 44) {
                        NewList.add(seatsInfoArrayList.get(count));
                    }
                }
                else if(seatsInfoArrayList.size()==45) {
                    if(count % 4 == 0 & count <= 40) {
                        NewList.add(seatsInfoArrayList.get(count));
                    }
                } else if(seatsInfoArrayList.size()==44) {
                    if(count % 4 == 0 & count <= 40) {
                        NewList.add(seatsInfoArrayList.get(count));
                    }
                } else if(seatsInfoArrayList.size()==36) {
                    if(count % 4 == 0 & count <= 36) {
                        NewList.add(seatsInfoArrayList.get(count));
                    }
                } else if(seatsInfoArrayList.size()==32) {
                    if(count % 4 == 0 & count <= 32) {
                        NewList.add(seatsInfoArrayList.get(count));
                    }
                }
                List.add(seatsInfoArrayList.get(count));
            }
            if(seatsInfoArrayList.size()>44){
                adapter = new Seats_Addapter_45(NewList, this, showSeats, showPrice, List, SelectedSeats, true);
            } else {
                adapter = new Seats_Addapter_45(NewList, this, showSeats, showPrice, List, SelectedSeats, false);
            }

            SRView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            SRView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void processFinish(String output) {

        Log.e("Process Finish: ",output);
        //stopRepeating();
        getData(output);
    }

    @Override
    public void onError(String Error) {
        stopRepeating();
        Toast.makeText(this,Error,Toast.LENGTH_SHORT).show();
    }
}
