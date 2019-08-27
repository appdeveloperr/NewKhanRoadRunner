package com.example.husnain.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import java.util.Calendar;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.Utils;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity{

    private String CityName;
    private TextView Cityfrom;
    private TextView Cityto;
    private String Check;
    private TextView Day;
    private TextView Today;
    private TextView Month;
    private TextView DayofWeek;
    private Calendar Date;
    private TextView HomeText;
    private ImageView Home;

    private RelativeLayout Homelayout;

    private static final String MY_PREF_NAME = "MyPrefsFile";

    private static final String Key_FromCity = "key_fromcity";
    private static final String Key_ToCity = "key_tocity";

    private ImageView ErrorFrom;
    private ImageView ErrorTo;

    private DatePicker datePicker;
    private FloatingActionButton fab;

    private int year, month, day;
    SimpleDateFormat formatter;

    private String FromCityID;
    private String ToCityID;

    SparseArray<MonthAdapter.CalendarDay> disabledDays = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Cityfrom = (TextView)findViewById(R.id.CityFrom);
        Cityto = (TextView) findViewById(R.id.CityTo);
        Day = (TextView)findViewById(R.id.day);
        Month = (TextView)findViewById(R.id.Month);
        DayofWeek = (TextView)findViewById(R.id.DayofWeek);
        Today = (TextView)findViewById(R.id.today);

        HomeText = (TextView)findViewById(R.id.HomeText);
        HomeText.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor());

        Homelayout = (RelativeLayout)findViewById(R.id.Homelayout);
        Homelayout.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        Home = (ImageView)findViewById(R.id.Home);
        Home.setImageResource(R.drawable.white_home);

        ErrorFrom = (ImageView)findViewById(R.id.Error);
        ErrorTo = (ImageView)findViewById(R.id.Error1);

        ErrorFrom.setVisibility(View.INVISIBLE);
        ErrorTo.setVisibility(View.INVISIBLE);

        CurrentDate();

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
        actionbar.setIcon(R.drawable.toolbar_icon);


        fab = (FloatingActionButton)findViewById(R.id.fab);

        final Bundle bundle = getIntent().getExtras();

        Check = bundle.getString("Check");

        //********************************************************************************Shared Preference
        final SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        Cityfrom.setText(prefs.getString("FromCity",""));
        Cityto.setText(prefs.getString("ToCity",""));



//        if(savedInstanceState != null)
//        {
//            String from = savedInstanceState.getString(Key_FromCity);
//            Cityfrom.setText(from);
//
//            String to = savedInstanceState.getString(Key_ToCity);
//            Cityfrom.setText(to);
//        }

        SharedPreferences shared = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();

//        editor.putString("ToCity","");
//        editor.commit();// commit is important here.


        if(Check.equals("f"))
        {
            CityName = bundle.getString("CityFrom");
            FromCityID = bundle.getString("CityId");
//***************************************************************** Uncomment this if cities not working **********************
//            ((City)this.getApplication()).setCityFrom(CityName);

            editor.putString("FromCity", CityName);
            editor.putString("FromCityID", FromCityID);
            editor.commit();

//            Toast.makeText(this,"CityFromID ..........."+FromCityID,Toast.LENGTH_SHORT).show();
            Cityfrom.setText(CityName);
        }
        else if(Check.equals("t"))
        {
            CityName = bundle.getString("CityFrom");
            ToCityID = bundle.getString("CityId");
//***************************************************************** Uncomment this if cities not working **********************
//            ((City)this.getApplication()).setCityTo(CityName);

            editor.putString("ToCity", CityName);
            editor.putString("ToCityID", ToCityID);
            editor.commit();

            Cityto.setText(CityName);
        }
//        Log.e("Before sending---------", "onClick: "+FromCityID);
//**********************************************************************************Go To Next Activity***************

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(prefs.getString("FromCityID","").equals("") || prefs.getString("ToCityID","").equals("")) {
                    if (prefs.getString("FromCityID","").equals("")) {
                        ErrorFrom.setVisibility(View.VISIBLE);
                    }
                    if (prefs.getString("ToCityID","").equals("")) {
                        ErrorTo.setVisibility(View.VISIBLE);
                    }
                }
                else
                {
//                    cal.add(Calendar.DATE, 1);
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                    int mon = Integer.parseInt(Date.get(Calendar.DAY_OF_MONTH))
//                    String dat = Date.get(Calendar.YEAR)+"-"+Date.get(Calendar.MONTH)+"-"+Date.get(Calendar.DAY_OF_MONTH)+1;

                    String dat = format1.format(Date.getTime());

                    Intent intent = new Intent(HomeActivity.this, Schedule.class);
                    intent.putExtra("fromCity",prefs.getString("FromCityID",""));
                    intent.putExtra("toCity",prefs.getString("ToCityID",""));
                    intent.putExtra("pdate",dat);

                    Global g = (Global) getApplication();
                    g.setDate(dat);

                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                    finish();
//                    Snackbar.make(view, "Select the Cities!", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                }

            }
        });

//**********************************************************************************************************************
    }

//******************************************************Save Activity Instance***************************************//
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        outState.putString(Key_FromCity,Cityfrom.getText().toString().trim());
        outState.putString(Key_ToCity,Cityto.getText().toString().trim());
        super.onSaveInstanceState(outState, outPersistentState);
    }

//***************************************************** Click Functions ***********************************************//
    public void SelectCityFrom(View view)
    {
        Intent intent = new Intent(this, SelectCity.class);
        intent.putExtra("Check","f");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    public void SelectCityTo(View view)
    {
        Intent intent = new Intent(this, SelectCity.class);
        intent.putExtra("Check","t");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    public void setDate(View view) {

        showDatePickerDialog();

    }

    public void Home(View view)
    {

    }

    public void My_Booking(View view)
    {
        Intent intent = new Intent(this, MyBooking.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    public void Help(View view)
    {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

    public void My_Account(View view)
    {
        Intent intent = new Intent(this, MyAccount.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }
    //-----------------------------------------------------Date--------------------------//

    public void CurrentDate()
    {
        Today.setVisibility(View.VISIBLE);

        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        day = c.get(Calendar.DAY_OF_MONTH);

        int dayofWeek= c.get(Calendar.DAY_OF_WEEK);

        Date = c;

        DayofWeek.setText(getDayOfWeek(dayofWeek));
        Day.setText(String.valueOf(day));
        Month.setText(getMonth(month+1));

    }


    public void showDatePickerDialog(){
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment();
        cdp.show(this.getSupportFragmentManager(), "Material Calendar Example");

        Calendar now = Calendar.getInstance();

        //^^^^^^^^^^^^^^^^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Date Check &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*********************//

        MonthAdapter.CalendarDay minDate = new MonthAdapter.CalendarDay(now.get(Calendar.YEAR), now.get(Calendar.MONTH) , now.get(Calendar.DAY_OF_MONTH));
        MonthAdapter.CalendarDay maxDate = new MonthAdapter.CalendarDay(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH) + 8);

        int key = Utils.formatDisabledDayForKey(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        disabledDays.put(key, new MonthAdapter.CalendarDay(now));

//        int key = Utils.formatDisabledDayForKey(now.get(Calendar.YEAR));
        disabledDays.put(key, new MonthAdapter.CalendarDay(now));

        cdp.setDateRange(minDate, maxDate)
                // Set Disabled Days
                .setDisabledDays(disabledDays);
//                .setOnDateSetListener(SampleCalendarDateRangeAndDisabledDays.this);

//-------&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&**************//


        cdp.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                try {

                    Today.setVisibility(View.INVISIBLE);

                    formatter = new SimpleDateFormat("dd/MM/yyyy");

                    String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    Date date = formatter.parse(dateInString);

                    //formatter = new SimpleDateFormat("dd/MMM/yyyy");

                    Log.e("Date Get: ",formatter.format(date).toString());

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.set(Calendar.MONTH,monthOfYear);
                    cal.set(Calendar.YEAR,year);
                    cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                    month = cal.get(Calendar.MONTH);
                    year = cal.get(Calendar.YEAR);
                    day = cal.get(Calendar.DAY_OF_MONTH);

                    int dayofWeek= cal.get(Calendar.DAY_OF_WEEK);

                    DayofWeek.setText(getDayOfWeek(dayofWeek));
                    Day.setText(String.valueOf(day));
                    Month.setText(getMonth(month+1));

                    Date = cal;
                    Log.e("DayValue: ",getDayOfWeek(dayofWeek));

                } catch (Exception ex) {
                    Toast.makeText(HomeActivity.this,ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//--------------------------------------------------------------------Date End-------------------//

    public String getDayOfWeek(int day_of_week){
        String dayValue = "non";
        switch (day_of_week){
            case 1:
                dayValue = "Sunday";
                break;
            case 2:
                dayValue = "Monday";
                break;
            case 3:
                dayValue = "Tuesday";
                break;
            case 4:
                dayValue = "Wednesday";
                break;
            case 5:
                dayValue = "Thursday";
                break;
            case 6:
                dayValue = "Friday";
                break;
            case 7:
                dayValue = "Saturday";
                break;

        }
        return dayValue;
    }

    public String getMonth(int month){

        String dayValue = "non";

        switch (month){
            case 1:
                dayValue = "Jan";
                break;
            case 2:
                dayValue = "Feb";
                break;
            case 3:
                dayValue = "Mar";
                break;
            case 4:
                dayValue = "April";
                break;
            case 5:
                dayValue = "May";
                break;
            case 6:
                dayValue = "June";
                break;
            case 7:
                dayValue = "Jul";
                break;
            case 8:
                dayValue = "Aug";
                break;
            case 9:
                dayValue = "Sep";
                break;
            case 10:
                dayValue = "Oct";
                break;
            case 11:
                dayValue = "Nov";
                break;
            case 12:
                dayValue = "Dec";
                break;

        }
        return dayValue;
    }
//---------------------------------------------------------------------------------

}


