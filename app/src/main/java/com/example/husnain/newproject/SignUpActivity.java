package com.example.husnain.newproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.example.husnain.newproject.Interfaces.AsyncResponse;
import com.example.husnain.newproject.VolleyRequest.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements AsyncResponse{

    private EditText FirstName,LastName,Email,Nic,Mobile,DateofBith,CustomerId,Password,Address;
    private String FName,LName,email,cnic,mobile,dateofbirth,customerid,password,address;

    private static final String MY_PREF_NAME = "MyPrefsFile";
    String starting_url;
    private boolean Check_Id;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_signup);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

//        ********************************* Progress Dialog **********************
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        //Declaration
        FirstName = (EditText) findViewById(R.id.First_Name);
        LastName = (EditText) findViewById(R.id.Last_Name);
        Email = (EditText) findViewById(R.id.Mail);
        Nic = (EditText) findViewById(R.id.idcard);
        Mobile = (EditText) findViewById(R.id.mobi);
        DateofBith = (EditText) findViewById(R.id.dobT);
        CustomerId = (EditText) findViewById(R.id.Customer_Id);
        Password = (EditText) findViewById(R.id.Password);
        Address = (EditText) findViewById(R.id.Address);

        Check_Id = false;

        final SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        starting_url = prefs.getString("Starting_IP","");

        CustomerId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                {
                    //When Focuse Losses from Edit Text
                    if (!CustomerId.getText().toString().trim().equals(""))
                    {
                        Check_Customer_Id();
                        progressDialog.show();
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this,"Customer Id Required!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(hasFocus)
                {
                    Check_Id = false;
                }
            }
        });

    }

    public void DatePicker(View view)
    {
        showDatePickerDialog();
    }

    public void Register (View view)
    {
        FName = FirstName.getText().toString().trim();
        LName = LastName.getText().toString().trim();
        email = Email.getText().toString().trim();
        cnic = Nic.getText().toString().trim();
        mobile = Mobile.getText().toString().trim();
        customerid = CustomerId.getText().toString();
        password = Password.getText().toString();
        dateofbirth = DateofBith.getText().toString().trim();
        address = Address.getText().toString().trim();

        if (FName.isEmpty() || LName.isEmpty() || email.isEmpty() ||
            cnic.isEmpty() || mobile.isEmpty() || customerid.isEmpty() ||
            password.isEmpty() || dateofbirth.isEmpty() || address.isEmpty())
        {
            Toast.makeText(this,"Please Enter all required fields!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(EmailValidation() || Mobile_Cnic_Validation())
            {
                getVollyData();
                progressDialog.show();
            }
        }
    }

    public void showDatePickerDialog(){
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment();
        cdp.show(this.getSupportFragmentManager(), "Material Calendar Example");

        cdp.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {
                try {

                    String date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                    DateofBith.setText(date);

                } catch (Exception ex) {
                    Toast.makeText(SignUpActivity.this,ex.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //*********************************************************** Check Customer ID ******************************************//

    public void Check_Customer_Id() {
        Map<String, String> map = new HashMap<String, String>();

        if (!CustomerId.getText().toString().trim().isEmpty()) {

            String url = "http://" + starting_url + ":999/APIMain.aspx?type=ValidateCustCode&cust_code="+CustomerId.getText().toString().trim()+"";

            //http://192.168.100.7:999/APIMain.aspx?type=ValidateCustCode&cust_code=

            VolleyRequest volleyRequest = new VolleyRequest();
            volleyRequest.delegate = this;
            volleyRequest.volleyRequestCall(map, this, Request.Method.GET, url);
        }
        else
        {
            Toast.makeText(SignUpActivity.this,"Customer Id Required!",Toast.LENGTH_SHORT).show();
        }
    }

    public void getData_for_Check_Customer_Id(String output)
    {
        try
        {
            JSONArray jsonArray;

            jsonArray = new JSONArray(output);

            for(int count=0;count<jsonArray.length();count++)
            {
                JSONObject JO = jsonArray.getJSONObject(count);

                int Result = JO.getInt("Column1");

                if(Result == 0)
                {
                    Check_Id = true;
                }
                else
                {
                    CustomerId.setError("Customer ID Already Exist!");
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    //*********************************************************** Get Data *************************************************//

    public void getVollyData() {
        Map<String, String> map = new HashMap<String, String>();

            //http://192.168.100.7:999/APIMain.aspx?type=CreateNewCustomer&pFirst_Name=Muhammad&pLast_Name=husnains&pEmailAddress=husnain1444&pCNIC=325486455565856&pMobileNo=032449345570&pDateofBirth=1990-6-5&pAddress=lahore&pCust_Code=123&pUserName=kamal&password=123
            String url = "http://"+starting_url+":999/APIMain.aspx?type=CreateNewCustomer&pFirst_Name="+FName+"&pLast_Name="+LName+"" +
                "&pEmailAddress="+email+"&pCNIC="+cnic+"&pMobileNo="+mobile+"&pDateofBirth="+dateofbirth+"&pAddress="+address+"" +
                "&pCust_Code="+customerid+"&pUserName="+""+"&password="+password+" ";

            VolleyRequest volleyRequest = new VolleyRequest();
            volleyRequest.delegate = this;
            volleyRequest.volleyRequestCall(map, this, Request.Method.GET, url);
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

                String Result = JO.getString("Column1");

                if(Result.trim().equals("Sucess"))
                {
                    Toast.makeText(this,"User Registered Successfully!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(this,Result,Toast.LENGTH_LONG).show();
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {

        Log.e("Process Finish: ",output);
        if (!Check_Id)
        {
            getData_for_Check_Customer_Id(output);
            progressDialog.dismiss();
        }
        else if(Check_Id)
        {
            getData(output);
//            Toast.makeText(this,output,Toast.LENGTH_LONG).show();
            progressDialog.dismiss();
        }
    }

    @Override
    public void onError(String Error) {
        progressDialog.dismiss();
//        Toast.makeText(this,Error,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Please Check Your Connection!",Toast.LENGTH_SHORT).show();
    }

    //********************************************************************************************************************//

    private boolean EmailValidation() {
        boolean ch = false;
        if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+") || email.isEmpty())
        {
            Email.setError("Invalid Email Address");
        }
        else
        {
            ch = true;
        }

        return ch;
    }

    private boolean Mobile_Cnic_Validation()
    {
        boolean ch = false;
        int CL = Nic.getText().toString().length();
        int PL = Mobile.getText().toString().length();
        if (CL != 13)
        {
            Nic.setError("Please Enter Correct CNIC Number!");
            ch = false;
        }
        else
        {
            ch = true;
        }
        if (PL != 11)
        {
            Mobile.setError("Please Enter Correct CNIC Number!");
            ch = false;
        }
        else
        {
            ch = true;
        }

        return ch;
    }
}
