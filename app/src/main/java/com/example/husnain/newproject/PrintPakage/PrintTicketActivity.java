package com.example.husnain.newproject.PrintPakage;

import android.Manifest;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.SeatsInfo;
import com.example.husnain.newproject.Seats_Addapter_45;
import com.example.husnain.newproject.SelectSeats;
import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;
import com.example.husnain.newproject.activities.MainActivity;
import com.example.husnain.newproject.PrintPakage.util.DataConstants;
import com.example.husnain.newproject.PrintPakage.util.DateUtil;
import com.example.husnain.newproject.PrintPakage.util.Util;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.Utils.JingPuPrinterUtils;
import com.example.husnain.newproject.Utils.PrinterCommands;
import com.example.husnain.newproject.Utils.Utile;

import com.example.husnain.newproject.activities.SelectCityActivity;
import com.example.husnain.newproject.entities.FareDetails;
import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.entities.Schedule;
import com.example.husnain.newproject.entities.TicketingSchedule;
import com.example.husnain.newproject.entities.TicketingSeat;
import com.example.husnain.newproject.viewmodels.FareDetailsViewModel;
import com.example.husnain.newproject.viewmodels.RouteDetailViewModel;
import com.example.husnain.newproject.viewmodels.ScheduleViewModel;
import com.example.husnain.newproject.viewmodels.SeatsInfoViewModel;
import com.example.husnain.newproject.viewmodels.TicketingScheduleViewModel;
import com.example.husnain.newproject.viewmodels.TicketingSeatViewModel;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintTicketActivity extends AppCompatActivity implements AsyncResponse {

    int VehicleID = -1;
    private boolean isChallanSaved = false;
    private P25Connector mConnector;
    String date,time;
    Button btn_discount;
    String Seat_Nos = "";
    String Seat_IDs = "";
    RelativeLayout rl_ticket_no;

    double[] location;
    boolean IsRecordInsertable = false;

    ArrayList<String> donors_list = new ArrayList<String>();
    List<SeatsInfo> EmptySeatsList;


    private LocationManager locationManager;
    private String provider;

    ArrayList<String> donation_type_list = new ArrayList<String>();
    SimpleDateFormat formatter;

    String ButtonTitle;



    private ScheduleViewModel scheduleViewModel;
    private FareDetailsViewModel fareDetailsViewModel;
    private RouteDetailViewModel routeDetailViewModel;
    private TicketingSeatViewModel ticketingSeatViewModel;
    private SeatsInfoViewModel seatsInfoViewModel;

    TextView tvScheduleTitlte,tvTime, tvVoucherNo;
    EditText et_date,et_no_of_seats,et_cash_received;
    ImageButton ib_select_seat;
    int RouteId;
    public static InnerJoinRoute Source;
    public static InnerJoinRoute Destination;
    public static String TAG = "";
    Button btn_source,btn_dest, btn_fare,btn_total_amount;

    int source_id, dest_id, srv_type_id;

    String TicketingScheduleJSONOutupt = "";
    String TicketingSeatJSONOutupt = "";
    String SeatsInfoJSONOutupt = "";
    private TicketingScheduleViewModel ticketingScheduleViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);
        TAG = ""; Source = null; Destination = null;
        SelectSeats.SelectedSeats.clear();
        Seats_Addapter_45.SeatId.clear();
        initComponents();
    }



    public void TicketingScreenClicks(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_source_station:
                Intent i = new Intent(this, SelectCityActivity.class);
                i.putExtra("RouteId",RouteId);
                TAG = Constants.TAGS.TAG_SOURCE;
                startActivity(i);
                break;
            case R.id.btn_dest_station:
                Intent i2 = new Intent(this,SelectCityActivity.class);
                i2.putExtra("RouteId",RouteId);
                TAG = Constants.TAGS.TAG_DESTINATION;
                startActivity(i2);
                break;
            case R.id.btn_print:
                break;
        }
    }

    public void initComponents(){

        et_date = (EditText) findViewById(R.id.et_date_time);
        btn_discount = (Button) findViewById(R.id.btn_discount);
        et_no_of_seats = (EditText) findViewById(R.id.et_no_of_tickets);
        et_cash_received = (EditText) findViewById(R.id.et_cash_received);
        btn_fare = (Button) findViewById(R.id.btn_fare);
        btn_total_amount = (Button) findViewById(R.id.btn_total_amount);
        ib_select_seat = (ImageButton) findViewById(R.id.ib_select_seat);


        tvScheduleTitlte= (TextView) findViewById(R.id.tv_sched_title);
        tvTime = (TextView) findViewById(R.id.tv_depttime);
        tvVoucherNo= (TextView) findViewById(R.id.tv_voucher_no);

        tvScheduleTitlte.setText(Global.RouteName);
        tvTime.setText(Global.VoucherDate);
        tvVoucherNo.setText("" + Global.VoucherNo);

        Calendar calendar = Calendar.getInstance();

        btn_source = (Button) findViewById(R.id.btn_source_station);
        btn_dest = (Button) findViewById(R.id.btn_dest_station);

        location = Utile.getLocation(this);




        et_no_of_seats.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TotalAmount(btn_fare.getText().toString(),et_no_of_seats.getText().toString(),btn_discount.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_cash_received.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TotalDiscount(btn_fare.getText().toString(),et_no_of_seats.getText().toString(),et_cash_received.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        routeDetailViewModel = ViewModelProviders.of(this).get(RouteDetailViewModel.class);
        fareDetailsViewModel = ViewModelProviders.of(this).get(FareDetailsViewModel.class);
        ticketingSeatViewModel = ViewModelProviders.of(this).get(TicketingSeatViewModel.class);
        seatsInfoViewModel = ViewModelProviders.of(this).get(SeatsInfoViewModel.class);
        ticketingScheduleViewModel = ViewModelProviders.of(this).get(TicketingScheduleViewModel.class);


        //et_date.setText(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR)   );

        long miliss = System.currentTimeMillis();
//        date = DateUtil.timeMilisToString(miliss, "dd/mm/yyyy");
        time = DateUtil.timeMilisToString(miliss, "hh:mm:ss");
        String date1 = DateUtil.dateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
        et_date.setText( date1 );


        ticketingSeatViewModel.getAll().observe(this, new Observer<List<TicketingSeat>>() {
            @Override
            public void onChanged(@Nullable List<TicketingSeat> ticketingSeats) {
                if(IsRecordInsertable){
                    //printReceipt(ticketingSeats.get(0));
                    IsRecordInsertable = false;
                }

                //routeDetailViewModel.getRouteDetailsById(.get(0).getRoute_id());
            }
        });

        //routeDetailViewModel.getRouteDetailsById()

        //scheduleViewModel.getScheduleByID(25);\

        Schedule schedule = scheduleViewModel.getScheduleByID(Global.VoucherScheduleId);
        if(schedule!=null){
            RouteId = schedule.getRoute_id();
        } else {
            Utile.showAlertDialog("There is no Route against this schedule id", this).show();
        }


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


    }

    public void ResetFields(){
        et_no_of_seats.setText("");
        et_cash_received.setText("");
        btn_discount.setText("");
        SelectSeats.SelectedSeats.clear();
        Seats_Addapter_45.SeatId.clear();

    }

    public void printParkingTicket(View view){
        //printReceipt();

        ButtonTitle = ((Button) view).getText().toString();

        if(ValidateInputFields()){
            if(Utile.isNetConnected(this)){

                long milis = System.currentTimeMillis();
                date = DateUtil.timeMilisToString(milis, "MMM dd, yyyy");
                time = DateUtil.timeMilisToString(milis, "hh:mm a");
            //*if(validateInputFields()){
                //SaveTicketVolleyRequest();
                //btn_save_and_print_challan.setEnabled(false);
            }//*
        }
    }

    private void printReceipt(){
        String title = "New Khan Road Runners";
        String contact = "UAN : 03-111-007-555";
        String email = "info@newkhan.pk";
        String contact2 = "+92 302 254 7214";
        String Address = "Head Office: Lahore";

        String datetimedata = Util.nameLeftValueRightJustify(date, time, DataConstants.RECEIPT_WIDTH) + "\n";
        String totalAmount = Util.nameLeftValueRightJustify("Total Amount:", btn_total_amount.getText().toString() + "/-", DataConstants.RECEIPT_WIDTH) + "\n";
        String Discount = Util.nameLeftValueRightJustify("Discount:", btn_discount.getText().toString() + "/-" , DataConstants.RECEIPT_WIDTH) + "\n";
        String CashReceived = Util.nameLeftValueRightJustify( "Cash Received:", et_cash_received.getText().toString() + "/-" , DataConstants.RECEIPT_WIDTH) + "\n";

        String PassengerCount = Util.nameLeftValueRightJustify("Total Seat(s):", "" + et_no_of_seats.getText().toString() , DataConstants.RECEIPT_WIDTH) + "\n";
        String SeatNos = Util.nameLeftValueRightJustify("Seat No:", "" + Seat_Nos , DataConstants.RECEIPT_WIDTH) + "\n";
        String SourceCity = Util.nameLeftValueRightJustify("Source:", Source.getCity_Name() , DataConstants.RECEIPT_WIDTH) + "\n";
        String DestCityTitle = Util.nameLeftValueRightJustify( "Destination:" , Destination.getCity_Name() , DataConstants.RECEIPT_WIDTH) + "\n";
        String VehicleNo = Util.nameLeftValueRightJustify( "Vehicle No:" , Global.Vehicle_No , DataConstants.RECEIPT_WIDTH) + "\n";
        //String DestCityTitleName = Util.nameLeftValueRightJustify("", Destination.getCity_Name() , DataConstants.RECEIPT_WIDTH) + "\n";

        //printNewLine();
        String thanks =  "\nPowered By: Logixity Soft " ;

        //String thanks2 =  "\nکو دینے کا شکریہ۔ وسلام " ;

        String StarsLine = "******************************";
        byte[] format = {33};
        byte[] center = new byte[]{ 0x1b, 0x61, 0x01 };

        //sendData(format);
        sendData(center);
        //sendData(hexStringToBytes("1B 45 01"));
        //printNewLine();
        //sendData(center);
        //printPhoto(R.drawable.ic_logo_newkhan_print);
        //printNewLine();
        //sendData(center);
        printText(StarsLine);
        //sendData(center);
        //printStringBitmap(StringToBitMap(title,30));
        printNewLine();
        printText(title);
        printNewLine();
        printText(contact);
        printNewLine();
        printText(email);
        printNewLine();
        printText(Address);
        //printStringBitmap(StringToBitMap(Address,26));
        printNewLine();
        printText(StarsLine);
        printNewLine();
        printText(datetimedata);
        //printStringBitmap(StringToBitMap(datetimedata,26));
        //printNewLine();
        printText(totalAmount);
        //printStringBitmap(StringToBitMap(totalAmount,26));
        //printNewLine();
        printText(Discount);
        //printStringBitmap(StringToBitMap(Discount,26));
        //printNewLine();
        printText(CashReceived);
        //printStringBitmap(StringToBitMap(CashReceived,26));
        //printNewLine();
        printText(PassengerCount);
        //printStringBitmap(StringToBitMap(PassengerCount,26));
        //printNewLine();
        printText(SeatNos);
        //printStringBitmap(StringToBitMap(SeatNos,26));
        //printNewLine();
        printText(SourceCity);
        //printStringBitmap(StringToBitMap(SourceCity,26));
        //printNewLine();
        printText(DestCityTitle);
        //printStringBitmap(StringToBitMap(DestCityTitle,26));
        //printNewLine();
        //printStringBitmap(StringToBitMap(DestCityTitleName,26));
        //printNewLine();
        printText(VehicleNo);
        printText(thanks);
        printText(Global.appVersion);

        //printStringBitmap(StringToBitMap(thanks,26));
        //printNewLine();
        //printNewLine();
        printNewLine();
        printNewLine();
        printNewLine();
        //this.finish();
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


    private void printText(String msg) {
        // Print normal text
        sendData(msg.getBytes());
    }

    //print new line
    private void printNewLine() {
        sendData(PrinterCommands.FEED_LINE);
    }

    public Bitmap StringToBitMap(String getContent,int size){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(size);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));
        float baseline = -paint.ascent();
        int width = (int) (paint.measureText(getContent) + 0.5f);
        int height = (int) (baseline + paint.descent() + 0.5f);
        Bitmap image = Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        canvas.drawColor(Color.WHITE);
        canvas.drawText(getContent, 0 , baseline, paint);
        return image;
    }

    public void printStringBitmap(Bitmap bmp) {
        try {
            if(bmp!=null){
                byte[] command = JingPuPrinterUtils.decodeBitmap(bmp);
                sendData(PrinterCommands.ESC_ALIGN_CENTER);
                sendData(command);
            }else{
                Log.e("Print Photo error", "the file isn't exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PrintTools", "the file isn't exists");
        }
    }

    //print photo
    public void printPhoto(int img) {
        try {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                    img);
            if(bmp!=null){
                byte[] command = JingPuPrinterUtils.decodeBitmap(bmp);

                sendData(command);
            }else{
                Log.e("Print Photo error", "the file isn't exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PrintTools", "the file isn't exists");
        }
    }

    private void sendData(byte[] bytes) {
        try {
            if (MainActivity.mConnector != null && (MainActivity.mConnector.isConnected())) {
                MainActivity.mConnector.sendData(bytes);
                /*try {
                mConnector.disconnect();
            } catch (P25ConnectionException e) {
                e.printStackTrace();
            }*/
            } else {
                Utile.showAlertDialog("Sorry! Printer is not conneted.",this).show();
            }
        } catch (P25ConnectionException e) {
            e.printStackTrace();
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        hexString = hexString.toLowerCase();
        String[] hexStrings = hexString.split(" ");
        byte[] bytes = new byte[hexStrings.length];

        for(int i = 0; i < hexStrings.length; ++i) {
            char[] hexChars = hexStrings[i].toCharArray();
            bytes[i] = (byte)(charToByte(hexChars[0]) << 4 | charToByte(hexChars[1]));
        }
        return bytes;
    }

    private static byte charToByte(char c) {
        return (byte)"0123456789abcdef".indexOf(c);
    }

    public void SaveTicketVolleyRequest(String url){
        Map<String, String> map = new HashMap<String, String>();

        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCall(map, this, Request.Method.GET, url );

    }



    private boolean ValidateInputFields(){
        if(btn_source.getText().toString().equals("")){
            Utile.DisplayLongToast(this,"Source is not selected");
            return false;
        } else if(btn_dest.getText().toString().equals("")){
            Utile.DisplayLongToast(this,"Destination is not selected");
            return false;
        } else if(et_no_of_seats.getText().toString().equals("")){
            Utile.DisplayLongToast(this,"No of Seats are not entered.");
            return false;
        } else if(et_cash_received.getText().toString().equals("")){
            Utile.DisplayLongToast(this,"Enter Collected Amount");
            return false;
        } else if(btn_fare.getText().toString().trim().equals("0.0")){
            Utile.DisplayLongToast(this,"Fare Cannot be Zero");
            return false;
        }else if(Double.parseDouble(et_cash_received.getText().toString()) > Double.parseDouble(btn_total_amount.getText().toString())){
            Utile.DisplayLongToast(this,"Error: Invalid Amount Received!");
            return false;
        }
        else
            return true;
    }

    public void PrintTicketActivityClicks(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_print:
                insert();
                break;
            case R.id.btn_reset:
                ResetFields();
                break;
            case R.id.ib_select_seat:
                SelectSeat();
                break;
        }
    }

    public void SelectSeat(){
        Intent i = new Intent(this, SelectSeats.class);
        if(Source != null && Destination != null){
            i.putExtra("fromCity",Source.getCity_ID());
            i.putExtra("toCity",Destination.getCity_ID());
            i.putExtra("fare", Float.parseFloat(btn_fare.getText().toString()));
            startActivityForResult(i, Constants.ACTIVITY_CODES.SELECT_SEATS );
        } else {
            Toast.makeText(this, "Select Source and Destination.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Constants.ACTIVITY_CODES.SELECT_SEATS){
            et_no_of_seats.setText("" + SelectSeats.SelectedSeats.size());
        }
    }

    public void insert(){
        if(ValidateInputFields()){
            Seat_Nos = "";
            Seat_IDs = "";
            long milis = System.currentTimeMillis();
            date = DateUtil.timeMilisToString(milis, "MMM dd, yyyy");
            time = DateUtil.timeMilisToString(milis, "hh:mm a");

            Double discount = 0.0;

            Global.NoOfSeats = Integer.parseInt(et_no_of_seats.getText().toString());

            if(!(btn_discount.getText().toString().equals(""))){
                discount = Double.parseDouble(btn_discount.getText().toString()) / Global.NoOfSeats;
            }



            if(EmptySeatsList.size() > 0){
                for(int i = 0; i < SelectSeats.SelectedSeats.size(); i++){
                    if(i==0){
                        Seat_Nos = Seat_Nos + SelectSeats.SelectedSeats.get(i).getSeat_No();
                        Seat_IDs = Seat_IDs + SelectSeats.SelectedSeats.get(i).getSeat_id();
                    } else {
                        Seat_Nos = Seat_Nos + " ," + SelectSeats.SelectedSeats.get(i).getSeat_No();
                        Seat_IDs = Seat_IDs + "," + SelectSeats.SelectedSeats.get(i).getSeat_id();
                    }

//                    TicketingSchedule prevRecord = ticketingScheduleViewModel.getPrevRecord(Global.VoucherScheduleId, Global.VoucherTime, Global.VoucherDate, Global.VoucherServiceTypeId, Global.VoucherNo);
                    TicketingSeat ticketingSeat = new TicketingSeat(Global.TicketingScheduleID,SelectSeats.SelectedSeats.get(i).getSeat_No(),0, et_date.getText().toString() ,
                            0,Global.User_ID, Source.getCity_ID(), Destination.getCity_ID(),"","",Double.parseDouble("" + SelectSeats.SelectedSeats.get(i).getFare()),
                            Double.isNaN(discount)? 0.0:discount,"",0,0,0,"",SelectSeats.SelectedSeats.get(i).getGender(),0,0,"",0,"",0,"","","",0,0,0,0,0,0,0,"","",0,Global.Lattitude,Global.Longitude,false);

                    /*if(Utile.isNetConnected(this)){
                        SeatsInfo seatinfo = SelectSeats.SelectedSeats.get(i);
                        //String URL = Constants.URLS.UPDATE_SEAT_STATUS + "pSeat_Id=" + (Seat_IDs + ",") +  "&pSeat_No=" + (Seat_Nos.replaceAll(" ,",",") + ",") + "&pGender=Male&pSource_Id=" + Source.getCity_ID() + "&pDestination_Id=" + Destination.getCity_ID() + "&pFare=" + ((int) Float.parseFloat(btn_fare.getText().toString()));
                        String URL = Constants.URLS.UPDATE_SEAT_STATUS + "pSeat_Id=" + seatinfo.getSeat_id() +  "&pSeat_No=" + seatinfo.getSeat_No() + "&pGender=Male&pSource_Id=" + Source.getCity_ID() + "&pDestination_Id=" + Destination.getCity_ID() + "&pFare=" + ( (int) Double.parseDouble(btn_fare.getText().toString()) );
                        //Toast.makeText(this,)
                        SaveTicketVolleyRequest(URL);
                    }*/

                    ticketingSeatViewModel.insert(ticketingSeat);
                    SeatsInfo seatsInfo = new SeatsInfo(SelectSeats.SelectedSeats.get(i).getSeat_id(),"","Reserved",SelectSeats.SelectedSeats.get(i).getSeat_No(),Float.parseFloat(btn_fare.getText().toString()), SelectSeats.SelectedSeats.get(i).getGender() ,Constants.SEAT_INS_TYPES.LOCAL, false);
                    seatsInfoViewModel.update(seatsInfo);
                    //}

                }

            }  else {
                for(int i = 0; i < Integer.parseInt(et_no_of_seats.getText().toString()); i++){
                    int SeatNo = AppSession.getInt(Constants.SHARED_PREF.EXTRA_SEATS);
                    if(SeatNo==0){
                        AppSession.put(Constants.SHARED_PREF.EXTRA_SEATS, ( AppSession.getInt(Constants.SHARED_PREF.EXTRA_SEATS) + 46 + i ) );
                    } else {
                        if(i==0){
                            AppSession.put(Constants.SHARED_PREF.EXTRA_SEATS, ( AppSession.getInt(Constants.SHARED_PREF.EXTRA_SEATS) + 1 + i ) );
                        } else {
                            AppSession.put(Constants.SHARED_PREF.EXTRA_SEATS, ( AppSession.getInt(Constants.SHARED_PREF.EXTRA_SEATS) + i ) );
                        }

                    }
                    SeatNo = AppSession.getInt(Constants.SHARED_PREF.EXTRA_SEATS);
                    if(i==0){
                        Seat_Nos = Seat_Nos + SeatNo;
                        //Seat_IDs = Seat_IDs + SelectSeats.SelectedSeats.get(i).getSeat_id();
                    } else {
                        Seat_Nos = Seat_Nos + " ," + SeatNo;
                        //Seat_IDs = Seat_IDs + "," + SelectSeats.SelectedSeats.get(i).getSeat_id();
                    }

                    TicketingSeat ticketingSeat = new TicketingSeat(Global.TicketingScheduleID, SeatNo,0, et_date.getText().toString() ,
                            0,Global.User_ID, Source.getCity_ID(), Destination.getCity_ID(),"","",Double.parseDouble(btn_fare.getText().toString()),
                            Double.isNaN(discount)? 0.0:discount,"",0,0,0,"","Male",0,0,"",0,"",0,"","","",0,0,0,0,0,0,0,"","",0,Global.Lattitude,Global.Longitude,false);

                    /*if(Utile.isNetConnected(this)){
                        //SeatsInfo seatinfo = SelectSeats.SelectedSeats.get(i);
                        String URL = Constants.URLS.UPDATE_SEAT_STATUS + "pSeat_Id=" + SeatNo +  "&pSeat_No=" + SeatNo + "&pGender=Male&pSource_Id=" + Source.getCity_ID() + "&pDestination_Id=" + Destination.getCity_ID() + "&pFare=" + ( (int) Double.parseDouble(btn_fare.getText().toString()) );


                        SaveTicketVolleyRequest(URL);
                    }*/
                    ticketingSeatViewModel.insert(ticketingSeat);
                    //SeatsInfo seatsInfo = new SeatsInfo(SelectSeats.SelectedSeats.get(i).getSeat_id(),"","Reserved",SelectSeats.SelectedSeats.get(i).getSeat_No(),Float.parseFloat(btn_fare.getText().toString()),"");
                    //seatsInfoViewModel.update(seatsInfo);
                }
            }

            if(Utile.isNetConnected(this)){
                //SeatsInfo seatinfo = SelectSeats.SelectedSeats.get(i);
                String URL = Constants.URLS.UPDATE_SEAT_STATUS + "pSeat_Id=" + (Seat_IDs + ",") +  "&pSeat_No=" + (Seat_Nos.replaceAll(" ,",",") + ",") + "&pGender=Female&pSource_Id=" + Source.getCity_ID() + "&pDestination_Id=" + Destination.getCity_ID() + "&pFare=" + ((int) Float.parseFloat(btn_fare.getText().toString()));

                //Toast.makeText(this,)
                //SaveTicketVolleyRequest(URL);

                //PushDataToServer();
                //PushSeatsInfoDataToServer();
                thread();

            } else {
                Toast.makeText(this, "Internet is not connected. ", Toast.LENGTH_LONG).show();
            }

            printReceipt();
            ResetFields();
        }
    }

    public void getSeatsByScheduleIdVolleyRequest() {
        Map<String, String> map = new HashMap<String, String>();

        Calendar c = Calendar.getInstance();

        String date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"  +  c.get(Calendar.DAY_OF_MONTH)  ;

        String URL = Constants.URLS.GET_SEAT_BY_SCHEDULE_ID + Utile.Fromatmdytoymd(Global.VoucherDate)  + "&pDep_Time=" + Global.VoucherTime + /*+ Global.VoucherTime*/
                "&pSchedule_Id=" + Global.VoucherScheduleId + "&fromCityId=" + Source.getCity_ID() + "&toCityId=" + Destination.getCity_ID() + "&pMaskDate="+ Utile.Fromatmdytoymd(Global.VoucherDate) +"&pMaskRoute=" + Global.VoucherServiceTypeId + "&pMaskTerminalId=1";

        VolleyRequest volleyRequest = new VolleyRequest();
        volleyRequest.delegate = this;
        volleyRequest.volleyRequestCall(map, this, Request.Method.GET, URL );
    }

    @Override
    public void processFinish(String output) {
        if(TAG.equals(Constants.TAGS.TAG_SCHEDULE) || TAG.equals(Constants.TAGS.TAG_SEATS) || TAG.equals(Constants.TAGS.TAG_UPDATE_SEAT_STATUS) ){
            if(output.trim().equals("\"SUCCESS\"")){
                if(TAG.equals(Constants.TAGS.TAG_SCHEDULE)){
                    InsertScheduleToServerSucceded(TicketingScheduleJSONOutupt);
                    PushTicketingSeatDataToServer();
                } else if(TAG.equals(Constants.TAGS.TAG_SEATS)) {
                    InsertTicketingSeatToServerSucceded(TicketingSeatJSONOutupt);
                    //ticketingScheduleViewModel
                } else if (TAG.equals(Constants.TAGS.TAG_UPDATE_SEAT_STATUS)){
                    UpdateTicketingSeatStatusToServerSucceded(SeatsInfoJSONOutupt);
                }
            }
        } else {
            if(output.contains("Empty")){
                ib_select_seat.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onError(String Error) {
        /*btn_save_and_print_challan.setEnabled(true);*/
        Utile.DisplayShortToast(this,Error);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onResume() {
        if(!TAG.equals("")){
            if(TAG.equals(Constants.TAGS.TAG_SOURCE)) {
                if(Source!=null)
                btn_source.setText(Source.getCity_Name());
            } else if(TAG.equals(Constants.TAGS.TAG_DESTINATION)){
                if(Destination!=null)
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
                        TotalAmount(btn_fare.getText().toString(),et_no_of_seats.getText().toString(),btn_discount.getText().toString());
                    } else {
                        btn_fare.setText("0.0");
                    }

                }
            });

            TotalAmount(btn_fare.getText().toString(),et_no_of_seats.getText().toString(),btn_discount.getText().toString());

            if(Utile.isNetConnected(this)){
                getSeatsByScheduleIdVolleyRequest();
            }
        }

        if(AppSession.getBoolean(Constants.SHARED_PREF.IS_INSERTED)){
            EmptySeatsList =  seatsInfoViewModel.SelectSeatsInfoByStatus("Empty");
            if(EmptySeatsList.size() == 0){
                et_no_of_seats.setEnabled(true);
                ib_select_seat.setVisibility(View.GONE);
            }
        }

        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public String TotalAmount(String rent, String no_of_tickets, String discount){
        if(rent.equals("")){
            rent = "0.0";
        }
        if(no_of_tickets.equals("")){
            no_of_tickets = "0.0";
        }
        if(discount.equals("")){
            discount = "0.0";
        }
        Double TotalRent = (Double.parseDouble(rent) * Double.parseDouble(no_of_tickets)) /*- Double.parseDouble(discount)*/;
        btn_total_amount.setText(TotalRent + "");
        et_cash_received.setText(TotalRent + "");
        return  TotalRent + "";
    }

    public void TotalDiscount(String rent, String no_of_tickets, String paid){
        if(rent.equals("")){
            rent = "0.0";
        }
        if(no_of_tickets.equals("")){
            no_of_tickets = "1.0";
        }
        if(paid.equals("")){
            paid = "0.0";
        }
        Double discount = (Double.parseDouble(btn_total_amount.getText().toString()) - Double.parseDouble(paid)) /*- Double.parseDouble(discount)*/;
        btn_discount.setText("" + discount);
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

    public void PostTicketingSeatUpdateData(String mRequestBody){
        if(Utile.isNetConnected(this)) {
            TAG = Constants.TAGS.TAG_UPDATE_SEAT_STATUS;
            VolleyRequest volleyRequest = new VolleyRequest();
            volleyRequest.delegate = this;
            volleyRequest.jsonVolleyRequest(this, mRequestBody, Request.Method.POST, Constants.URLS.POST_UPDATE_SEAT_STATUS);
        } else {
            Toast.makeText(this,getResources().getString(R.string.msg_no_internet),Toast.LENGTH_LONG).show();
        }
    }



    public void PushDataToServer(){
        List<TicketingSchedule> ticketingSchedules = ticketingScheduleViewModel.getUnPushedData();

        if(ticketingSchedules.size()>0){
            //ticketingSchedule = ticketingSchedules.get(0);
            TicketingScheduleJSONOutupt = BuildTicketScheduleJSONArray(ticketingSchedules);

            Log.e("Ticketing Schedule",TicketingScheduleJSONOutupt);
            PostTicketingScheduleData(TicketingScheduleJSONOutupt);
        } else {
            PushTicketingSeatDataToServer();
        }
    }

    public void PushSeatsInfoDataToServer(){
        SeatsInfoJSONOutupt = "";
        List<SeatsInfo> seatsInfos = seatsInfoViewModel.getUnPushedData();

        if(seatsInfos.size()>0){
            //ticketingSchedule = ticketingSchedules.get(0);
            SeatsInfoJSONOutupt = BuildSeatsInfoJSONArray(seatsInfos);

            Log.e("Seats Info",SeatsInfoJSONOutupt);
            PostTicketingSeatUpdateData(SeatsInfoJSONOutupt);
        } else {
            //PushSeatsInfoDataToServer();
        }
    }

    public String BuildTicketScheduleJSONArray(List<TicketingSchedule> list){
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i< list.size(); i++){
            jsonArray.put(Utile.GenerateTicketScheduleJSONObject(list.get(i)));
        }
        return jsonArray.toString();
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
                                PushSeatsInfoDataToServer();
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

    private void PushTicketingSeatDataToServer(){
        List<TicketingSeat> ticketingSeats = ticketingSeatViewModel.getUnPushedData();

        if(ticketingSeats.size()>0) {
            //ticketingSeats = ticketingSeats.get(0);
            TicketingSeatJSONOutupt = BuildTicketSeatJSONArray(ticketingSeats);
            Log.e("Ticketing Seat",TicketingSeatJSONOutupt);
            PostTicketingSeatData(TicketingSeatJSONOutupt);
        }
    }

    public String BuildSeatsInfoJSONArray(List<SeatsInfo> list){
        JSONArray jsonArray = new JSONArray();
        for(int i = 0; i< list.size(); i++){

            jsonArray.put(Utile.GenerateSeatsInfoJSONObject(list.get(i)));
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

    private void UpdateTicketingSeatStatusToServerSucceded(String output){
        try {
            JSONArray jsonArray = new JSONArray(output);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Gson gson = new Gson();
                SeatsInfo seatsInfo = gson.fromJson(jsonObject.toString(), SeatsInfo.class);
                //seatsInfo.setBookedBy(Constants.SEAT_INS_TYPES.LOCAL);
                seatsInfo.setIsPushed(true);
                //seatsInfo.setSeat_status("Reserved");
                seatsInfoViewModel.updateIsPushed(seatsInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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

}
