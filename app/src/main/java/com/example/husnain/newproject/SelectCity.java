package com.example.husnain.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Husnain on 4/26/2018.
 */

public class SelectCity extends AppCompatActivity implements AsyncResponse {

    private static final String MY_PREF_NAME = "MyPrefsFile";

    private static final String url = "http://192.168.100.7:999/APIMain.aspx?type=getSourceCities";

    private String City_url;

    private ArrayList<CityInfo> CityInfo;
//    private ArrayList<String> Cities;
    private City_Addapter adapter;
    private RecyclerView reView;
    private String check;
    private Toolbar toolbar;
    private MaterialSearchView materialSearchView;

    private int ch;
    private Handler handler;
    private ImageView Loading_Image;
    private ImageView Error_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        //**************************************** Tool Bar *********************************************//
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Select City");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        final SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        String starting_url = prefs.getString("Starting_IP","");
        Log.e("Public IP",starting_url);
        City_url = "http://"+starting_url.trim()+":999/APIMain.aspx?type=getSourceCities";

        reView = (RecyclerView)findViewById(R.id.RView);
        CityInfo = new ArrayList<>();
//        Cities = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();
        check = bundle.getString("Check");

        //********************************************************* Handler for repeating images
        handler = new Handler();

        Loading_Image = (ImageView)findViewById(R.id.loading_image);
        Error_Image = (ImageView)findViewById(R.id.error_image);
        Error_Image.setVisibility(View.GONE);
        startRepeating();


        getDataVolley();

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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

//        getDataVolley();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                final ArrayList<CityInfo> filteredModelList = new ArrayList<CityInfo>();
                for (CityInfo model : CityInfo) {

                    final String text = model.getCity_Name().toUpperCase();
                    if (text.contains(newText.toUpperCase())) {
                        filteredModelList.add(model);
                    }
                }

                if(filteredModelList.size()>0){
                    adapter = new City_Addapter(filteredModelList,SelectCity.this,check);
                    reView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    reView.setLayoutManager(new LinearLayoutManager(SelectCity.this));
                    return true;
                } else{
                    return false;
                }

//                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

//**************************************************************************************************************//


    public void getDataVolley(){
        Map<String, String> map = new HashMap<String, String>();

        /*map.put("email", "hus@gmail.com");
        map.put("password", "amFhbmk=");*/
//        map.put(KEY_BLOOD_GROUP, spinner_blood_groups.getSelectedItem().toString());

        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCall(map,this, Request.Method.GET , City_url);
    }

    @Override
    public void processFinish(String output) {

        Toast.makeText(this,output,Toast.LENGTH_SHORT);
        Log.e("Process Finish: ",output);
        stopRepeating();
        getData(output);
    }

    @Override
    public void onError(String Error) {
        stopRepeating();
        Error_Image.setVisibility(View.VISIBLE);
//        Toast.makeText(this,Error,Toast.LENGTH_SHORT).show();
    }

    public void getData(String output)
    {
//        Toast.makeText(this,"getData start",Toast.LENGTH_LONG).show();
        try
        {
            JSONArray jsonArray;

            jsonArray = new JSONArray(output);

            for(int count=0;count<jsonArray.length();count++)
            {
                JSONObject JO = jsonArray.getJSONObject(count);
                int cityId = JO.getInt("City_Id");
                String cityName = JO.getString("City_Name");
                String cityabbr = JO.getString("City_Abbr");
                boolean active = JO.getBoolean("Active");
                int useid = JO.getInt("User_Id");
                String access_datetime = JO.getString("Access_DateTime");
                String access_sys_name = JO.getString("Access_Sys_Name");
                int access_terminal_id = JO.getInt("Access_Terminal_Id");
                String destination_color = JO.getString("Destination_Color");


//                Cities.add(cityName);

                CityInfo.add(new CityInfo(cityId,cityName,cityabbr,active,useid,access_datetime,access_sys_name,access_terminal_id,destination_color));

                //*****************************************Recycler View
                adapter = new City_Addapter(CityInfo,this,check);

                // Attach the adapter to the recyclerview to populate items
                reView.setAdapter(adapter);
                // Set layout manager to position the items
                reView.setLayoutManager(new LinearLayoutManager(this));
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("CityFrom","");
        intent.putExtra("Check","");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
//        super.onBackPressed();
    }
}
