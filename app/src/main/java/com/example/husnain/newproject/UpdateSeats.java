package com.example.husnain.newproject;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
 * Created by Husnain on 5/14/2018.
 */
//http://192.168.100.7:999/APIMain.aspx?type=UpdateSeatStatus&pSeat_Id=730917&pSeat_No=5&pPassenger_Name=jani&pContactNo=02651654&pCNIC=35201&pGender=Male&pSource_Id=54&pDestination_Id=113&pFare=450&%20pOperator_Id=%22%22&pBordingTerminal_Id=0&pMaskDate=%22%22&pMaskRoute=6&pMaskTerminalId=6&pMaskDepTime=18:00
public class UpdateSeats extends AppCompatActivity implements AsyncResponse {

    private static final String MY_PREF_NAME = "MyPrefsFile";
    private ArrayList<? extends SeatsInfo> SelectedSeats;
    private String starting_url,F_Name,CNIC,MobileNo;

    private String pDep_Time;
    private String fromCityId;
    private String toCityId;
    private String pMaskDate;
    private String pMaskRoute;
    private String pMaskTerminalId;

    private RadioGroup gender;
    private RadioButton selectedRadioButton;
    private EditText Name;
    private EditText Cnic;
    private EditText Phone;
    private TextView total_seats;
    private TextView total_price;

    private ListView listView;
    private ArrayList<Integer> seatList;
    private ArrayList<Integer> priceList;

    private TextView Cities;
    private TextView Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_seats);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Update Seats");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        SelectedSeats = new ArrayList<>();
        seatList = new ArrayList<>();
        priceList = new ArrayList<>();

        gender = (RadioGroup)findViewById(R.id.gender);
        Name = (EditText)findViewById(R.id.input_name);
        Cnic = (EditText)findViewById(R.id.input_cnic);
        Phone = (EditText)findViewById(R.id.input_phone);
        total_seats = (TextView)findViewById(R.id.total_seats);
        total_price = (TextView)findViewById(R.id.total_price);

        Cities = (TextView)findViewById(R.id.Cities);
        Date = (TextView)findViewById(R.id.Date);

        Bundle bundle = getIntent().getExtras();
        pMaskDate = bundle.getString("pMaskDate");
        pDep_Time = bundle.getString("pDep_Time");
        pMaskRoute = bundle.getString("pMaskRoute");
        pMaskTerminalId = bundle.getString("pMaskTerminalId");

        SelectedSeats = (ArrayList<? extends SeatsInfo>) getIntent().getSerializableExtra("SelectedSeats");

//***************************************************Show Total Seats and Fare *****************************************
        String seats = "";
        for (int i = 0 ; i<SelectedSeats.size();i++){

            seatList.add(SelectedSeats.get(i).getSeat_No());
            priceList.add((int) SelectedSeats.get(i).getFare());

            seats = seats + SelectedSeats.get(i).getSeat_No() + ",";
        }

//        total_seats.setText("Number of Seats: "+seatList.size());
        seats = seats.substring(0, seats.length()-1);
        total_seats.setText("Selected Seats: " + seats);

        int sum=0;
        for (int i = 0 ; i<priceList.size();i++){
            sum += priceList.get(i);
        }
        total_price.setText("Total Price: "+ sum);
        Log.e("List Getted...................=",SelectedSeats.get(0).getSeat_status());

        final SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        fromCityId = prefs.getString("FromCityID","");
        toCityId = prefs.getString("ToCityID","");
        starting_url = prefs.getString("Starting_IP","");
        String from = prefs.getString("FromCity","");
        String to = prefs.getString("ToCity","");
//**************************************************** Put information of User
        F_Name = prefs.getString("Name","");
        Name.setText(F_Name);
        CNIC = prefs.getString("CNIC","");
        Cnic.setText(CNIC);
        MobileNo = prefs.getString("MobileNo","");
        Phone.setText(MobileNo);

        Cities.setText(from + " to " + to);
        Global g = (Global) getApplication();
        Date.setText(g.getDate() + "  ("+pDep_Time+")");
//        Log.e("FromCity...................=",fromCityId);
//        Log.e("ToCity...................=",toCityId);
//********************************************************List View for Show Seats************************
//        listView = (ListView)findViewById(R.id.listView);
//        CustomAdapter customAdapter = new CustomAdapter();
//        listView.setAdapter(customAdapter);

    }

    //  ********************************************************** Adapter for LIst view for showing Seats Information **********
//    class CustomAdapter extends BaseAdapter
//    {
//
//        @Override
//        public int getCount() {
//            return seatList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            convertView = getLayoutInflater().inflate(R.layout.custom_seats_layout,null);
//
//            TextView seat = (TextView)convertView.findViewById(R.id.seat);
//            TextView price = (TextView)convertView.findViewById(R.id.price);
//
//            seat.setText(""+ seatList.get(position));
//            price.setText(""+ priceList.get(position));
//
//            return convertView;
//        }
//    }

    public void Continue_Booking(View view)
    {
        int CL = Cnic.getText().toString().length();
        int PL = Phone.getText().toString().length();
        if(Name.getText().toString().trim().equals(""))
        {
            Toast.makeText(this, "Please Enter Your Name!", Toast.LENGTH_SHORT).show();
        }
        else {
            if (CL != 13) //************************************************Check CNIC
            {
                Toast.makeText(this, "Please Enter Correct CNIC", Toast.LENGTH_SHORT).show();
            } else {//************************************************Check Gender
                if (gender.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
                } else {
                    int selectedId = gender.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    selectedRadioButton = (RadioButton) findViewById(selectedId);

//                Toast.makeText(this, selectedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();

                    if (PL != 11)//************************************************Check Phone
                    {
                        Toast.makeText(this, "Please Enter Correct Phone #", Toast.LENGTH_SHORT).show();
                    } else {
                        //***************************************Continue Booking
                        getVollyData();
                    }
                }
            }
        }
    }


    //*********************************************************** Get Data *************************************************//

    public void getVollyData() {
        Map<String, String> map = new HashMap<String, String>();

        for (int i=0;i<SelectedSeats.size();i++) {

            String url = "http://"+starting_url+":999/APIMain.aspx?type=UpdateSeatStatus&pSeat_Id="+SelectedSeats.get(i).getSeat_id()+"&pSeat_No="+SelectedSeats.get(i).getSeat_No()+"" +
                    "&pPassenger_Name="+Name.getText().toString()+"&pContactNo="+Phone.getText().toString()+"&pCNIC="+Cnic.getText().toString()+"&pGender="+selectedRadioButton.getText().toString()+"&pSource_Id="+fromCityId+"" +
                    "&pDestination_Id="+toCityId+"&pFare="+SelectedSeats.get(i).getFare()+"&%20pOperator_Id=%22%22&pBordingTerminal_Id=0&pMaskDate="+pMaskDate+"" +
                    "&pMaskRoute="+pMaskRoute+"&pMaskTerminalId="+pMaskTerminalId+"&pMaskDepTime="+pDep_Time+" ";

            VolleyRequest volleyRequest = new VolleyRequest();
            volleyRequest.delegate = this;
            volleyRequest.volleyRequestCall(map, this, Request.Method.GET, url);

        }
    }

    public void getData(String output)
    {
        try
        {
            JSONArray jsonArray;

            jsonArray = new JSONArray(output);

            for(int count=0;count<jsonArray.length();count++)
            {
                JSONObject JO = jsonArray.getJSONObject(count);

                String Result = JO.getString("Result");

                if(Result.trim().equals("SUCESSFULL"))
                {
                    Toast.makeText(this,"Seats Booked Sucessfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(this,"Seats Booked!",Toast.LENGTH_LONG).show();
                }
            }


        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {

        Log.e("Process Finish: ",output);
        getData(output);
    }

    @Override
    public void onError(String Error) {
        Toast.makeText(this,Error,Toast.LENGTH_SHORT).show();
    }

    //********************************************************************************************************************//

}