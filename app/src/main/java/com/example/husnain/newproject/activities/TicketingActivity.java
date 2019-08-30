package com.example.husnain.newproject.activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.husnain.newproject.R;
import com.example.husnain.newproject.viewmodels.FareDetailsViewModel;
import com.example.husnain.newproject.viewmodels.RouteDetailViewModel;
import com.example.husnain.newproject.viewmodels.ScheduleViewModel;
import com.example.husnain.newproject.viewmodels.TicketingSeatViewModel;

import java.util.Calendar;

public class TicketingActivity extends AppCompatActivity {

    private ScheduleViewModel scheduleViewModel;
    private FareDetailsViewModel fareDetailsViewModel;
    private RouteDetailViewModel routeDetailViewModel;
    private TicketingSeatViewModel ticketingSeatViewModel;

    EditText et_date;
    int RouteId;
    /*public static InnerJoinRoute Source;
    public static InnerJoinRoute Destination;
    public static String TAG = "";*/
    Button btn_source,btn_dest, btn_fare;

    int source_id, dest_id, srv_type_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);
        //TAG = ""; Source = null; Destination = null;
        et_date = (EditText) findViewById(R.id.et_date_time);
        btn_fare = (Button) findViewById(R.id.btn_fare);
        Calendar calendar = Calendar.getInstance();

        btn_source = (Button) findViewById(R.id.btn_source_station);
        btn_dest = (Button) findViewById(R.id.btn_dest_station);
        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        routeDetailViewModel = ViewModelProviders.of(this).get(RouteDetailViewModel.class);
        fareDetailsViewModel = ViewModelProviders.of(this).get(FareDetailsViewModel.class);
        ticketingSeatViewModel = ViewModelProviders.of(this).get(TicketingSeatViewModel.class);

        et_date.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR) );


        /*scheduleViewModel.getScheduleByID(25).observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(@Nullable List<Schedule> schedules) {
                RouteId = 27;//schedules.get(0).getRoute_id();
                List<InnerJoinRoute> cities = (routeDetailViewModel.getRouteDetailsById(RouteId)).getValue();

            }
        });*/





        /*routeDetailViewModel.getRouteDetailsById(RouteId).observe(this, new Observer<List<InnerJoinRoute>>() {
            @Override
            public void onChanged(@Nullable List<InnerJoinRoute> cities) {
                //routeDetailViewModel.getRouteDetailsById(.get(0).getRoute_id());
            }
        });*/

        //routeDetailViewModel.getRouteDetailsById()

        scheduleViewModel.getScheduleByID(25);


    }

    public void TicketingScreenClicks(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_source_station:
                Intent i = new Intent(this,SelectCityActivity.class);
                //TAG = Constants.TAGS.TAG_SOURCE;
                startActivity(i);
                break;
            case R.id.btn_dest_station:
                Intent i2 = new Intent(this,SelectCityActivity.class);
                //TAG = Constants.TAGS.TAG_DESTINATION;
                startActivity(i2);
                break;
            case R.id.btn_print:
                break;
        }
    }


    /*@Override
    protected void onResume() {
        if(!TAG.equals("")){
            if(TAG.equals(Constants.TAGS.TAG_SOURCE)) {
                btn_source.setText(Source.getCity_Name());
            } else if(TAG.equals(Constants.TAGS.TAG_DESTINATION)){
                btn_dest.setText(Destination.getCity_Name());
            }
        }

        if(Source != null && Destination != null){
            source_id = Source.getCity_ID();
            dest_id = Destination.getCity_ID();
            srv_type_id = Global.VoucherServiceTypeId;
            fareDetailsViewModel.getFares(source_id, dest_id, srv_type_id).observe(this, new Observer<List<FareDetails>>() {
                @Override
                public void onChanged(@Nullable List<FareDetails> fareDetails) {
                    if(fareDetails.size()>0){
                        btn_fare.setText("" + fareDetails.get(0).getFare_Amount());
                    }

                }
            });
        }
        super.onResume();
    }
*/



}
