package com.example.husnain.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Husnain on 5/2/2018.
 */

public class Schedule extends AppCompatActivity implements AsyncResponse{

    private ImageView No_Record_Found;
    private ImageView Loading_Image;

    private static final String MY_PREF_NAME = "MyPrefsFile";
    String starting_url;

    private Global g;
    private int ch;

    private Handler handler;
    private ArrayList<Schedule_Info> List;
    private RecyclerView srView;
    private Schedule_Addapter adapter;
    private static final String Schedule_URL= "http://192.168.100.7:999/APIMain.aspx?type=getSchedules&fromCityId=$from&toCityId=$to&pdate=$dat";
    private String fromCity;
    private String toCity;
    private String pdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
//********************************************************* Handler for repeating images
        handler = new Handler();

        No_Record_Found = (ImageView)findViewById(R.id.No_Record_Found);
        Loading_Image = (ImageView)findViewById(R.id.loading_image);
        No_Record_Found.setVisibility(View.GONE);
        startRepeating();

        g = (Global)getApplication();

        List = new ArrayList<>();
        srView = (RecyclerView)findViewById(R.id.SRView);

        Bundle bundle = getIntent().getExtras();

        fromCity = bundle.getString("fromCity");
        toCity = bundle.getString("toCity");
        pdate = bundle.getString("pdate");


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Schedule for "+pdate);
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        final SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        starting_url = prefs.getString("Starting_IP","");

        //*****************************************************************************Move Recycler View Up / Down

            srView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        // Scrolling up

                    } else {
                        // Scrolling down
                        //***********************************************************Move if Filter set
                        Global g = (Global)getApplication();
//                        Log.e("Departure",""+g.isDeparture());
//                        Log.e("Fare",""+g.isFare());
//                        Log.e("Economy",""+g.isEconomy());
//                        Log.e("Business",""+g.isBusiness());
//                        Log.e("Time",""+g.getTime());

                        if(g.isDeparture() || g.isFare() || g.isEconomy() || g.isBusiness() || g.getTime()!=0)
                        {
                            Snackbar.make(recyclerView, "Dismiss Applied Filters!", Snackbar.LENGTH_LONG)
                                    .setAction("DISMISS", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Global g = (Global)getApplication();
                                            g.setTime(0);
                                            g.setEconomy(false);
                                            g.setBusiness(false);
                                            g.setDeparture(false);
                                            g.setFare(false);

                                            getVollyData();
                                        }
                                    }).show();
                        }
                    }
                }
            });

        //************************************************************************Volly

        getVollyData();
    }
//****************************************************** Loading Pictures ***********************************
    private void startRepeating()
    {
        Loading_Image.setVisibility(View.VISIBLE);
        ch = 0;
        runnable.run();
    }

    private void stopRepeating()
    {
        Loading_Image.setVisibility(View.GONE);
        handler.removeCallbacks(runnable);
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
    @Override
    protected void onPostResume() {
        getVollyData();
        super.onPostResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        getMenuInflater().inflate(R.menu.menu_pref,menu);

        MenuItem item = menu.findItem(R.id.preference);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.preference)
        {
            Intent intent = new Intent(this, Preference_For_Schedule.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    //*****************************************************************************Try this code by running
    public void getVollyData() {
        Map<String, String> map = new HashMap<String, String>();
//
//        map.put("fromCityId", "54");
//        map.put("toCityId", "113");
//        map.put("Pdate", "2018-05-03");
        String from = fromCity;//"54";Lahore
        String to = toCity;//"113";Faislabad
        String dat = pdate;//"2018-05-03";

        String url = "http://"+starting_url+":999/APIMain.aspx?type=getSchedules&fromCityId="+from+"&toCityId="+to+"&pdate="+dat;
//    Log.e("Login Detail: ","Email: " + input_email + " Pasw: " + encrypted_pass);
//        map.put(KEY_BLOOD_GROUP, spinner_blood_groups.getSelectedItem().toString());

        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCall(map, this, Request.Method.GET, url);
    }

    public void getData(String output)
    {
        List.clear();
//        Toast.makeText(this,"getData start",Toast.LENGTH_LONG).show();
        try
        {
            JSONArray jsonArray;

            jsonArray = new JSONArray(output);

//            this.departureTime = departureTime;
//            this.arrivalTime = arrivalTime;
//            this.fare = fare;
//            Seats = seats;
//            this.busType = busType;
//            this.status1 = status1;
//            Schedule_Id = schedule_Id;
//            MaskRouteCode = maskRouteCode;
//            QuerydepartureTime = querydepartureTime;

        for(int count=0;count<jsonArray.length();count++)
            {
                JSONObject JO = jsonArray.getJSONObject(count);

                String departureTime = JO.getString("departureTime");
                String arrivalTime = JO.getString("arrivalTime");
                float fare = JO.getInt("fare");
                int Seats = JO.getInt("Seats");
                String busType = JO.getString("busType");
                String status1 = JO.getString("status1");
                int Schedule_Id = JO.getInt("Schedule_Id");
                String MaskDate = JO.getString("MaskDate");
                String MaskRouteCode = JO.getString("MaskRouteCode");
                int MaskTerminalId = JO.getInt("MaskTerminalId");
                String ExcludedTerminalsList = JO.getString("ExcludedTerminalsList");
                String QuerydepartureTime = JO.getString("QuerydepartureTime");

                List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));

                No_Record_Found.setVisibility(View.GONE);

//              //*****************************************Recycler View
                adapter = new Schedule_Addapter(List, this, pdate, toCity, fromCity);

                // Attach the adapter to the recyclerview to populate items
                srView.setAdapter(adapter);
                // Set layout manager to position the items
                srView.setLayoutManager(new LinearLayoutManager(this));

            }

            Toast.makeText(this,List.size()+" Buses Found!",Toast.LENGTH_SHORT).show();

            Log.e("=====================================","No Record:"+List.size());
            if(List.size() == 0)
            {
                No_Record_Found.setVisibility(View.VISIBLE);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public void getDataForPreference(String output)
    {
        List.clear();

        try
        {
            JSONArray jsonArray;

            jsonArray = new JSONArray(output);

            for(int count=0;count<jsonArray.length();count++)
            {
                JSONObject JO = jsonArray.getJSONObject(count);

                String departureTime = JO.getString("departureTime");
                String arrivalTime = JO.getString("arrivalTime");
                float fare = JO.getInt("fare");
                int Seats = JO.getInt("Seats");
                String busType = JO.getString("busType");
                String status1 = JO.getString("status1");
                int Schedule_Id = JO.getInt("Schedule_Id");
                String MaskDate = JO.getString("MaskDate");
                String MaskRouteCode = JO.getString("MaskRouteCode");
                int MaskTerminalId = JO.getInt("MaskTerminalId");
                String ExcludedTerminalsList = JO.getString("ExcludedTerminalsList");
                String QuerydepartureTime = JO.getString("QuerydepartureTime");

//******************************************************************************* Preference with respect to Time**********

                String Dep_Time = String.valueOf(Character.getNumericValue(departureTime.charAt(0)))+
                        String.valueOf(Character.getNumericValue(departureTime.charAt(1)));

                if(busType.substring(0,7).trim().equals("Economy") && g.isEconomy())
                {
                    List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));
                }
                else if (busType.substring(0,8).trim().equals("Business") && g.isBusiness())
                {
                    List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));
                }
                else if(Integer.parseInt(Dep_Time) >= 6 && Integer.parseInt(Dep_Time)<=11 && g.getTime() == 1)
                {
                    Log.e("Departure Time: ","subah");
                    List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));
                }
                else if (Integer.parseInt(Dep_Time) >= 12 && Integer.parseInt(Dep_Time)<=17 && g.getTime() == 2)
                {
                    Log.e("Departure Time: ","dopahar");
                    List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));
                }
                else if (Integer.parseInt(Dep_Time) >= 18 && Integer.parseInt(Dep_Time)<=23 && g.getTime() == 3)
                {
                    Log.e("Departure Time: ","shaam");
                    List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));
                }
                else if (Integer.parseInt(Dep_Time) >= 0 && Integer.parseInt(Dep_Time)<=5 && g.getTime() == 4)
                {
                    Log.e("Departure Time: ","raat");
                    List.add(new Schedule_Info(departureTime,arrivalTime,fare,busType,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                            MaskTerminalId,ExcludedTerminalsList,QuerydepartureTime));
                }
//***************************************************************************************************************************

//                //*****************************************Recycler View
                adapter = new Schedule_Addapter(List,this,pdate,toCity,fromCity);

                // Attach the adapter to the recyclerview to populate items
                srView.setAdapter(adapter);
                // Set layout manager to position the items
                srView.setLayoutManager(new LinearLayoutManager(this));
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {

        Log.e("Process Finish: ",output);
        if(g.getTime() == 0 && !g.isBusiness() && !g.isEconomy()) {

            stopRepeating();

            getData(output);
        }
        else
        {
            stopRepeating();
            getDataForPreference(output);
        }
    }

    @Override
    public void onError(String Error) {
        stopRepeating();
        Toast.makeText(this,Error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
        super.onBackPressed();
    }
}
