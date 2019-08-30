package com.example.husnain.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Husnain on 5/1/2018.
 */

public class MyAccount extends AppCompatActivity {

    private static final String MY_PREF_NAME = "MyPrefsFile";

    private TextView MyAccountText;
    private RelativeLayout MyAccountLayout;
    private ImageView AccountImage;

    private TextView F_Name,L_Name,Cnic,MobileNo;
    private EditText P_Password,N_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);

        F_Name = (TextView)findViewById(R.id.Name);
        L_Name = (TextView)findViewById(R.id.Name1);
        Cnic = (TextView)findViewById(R.id.Cnic);
        MobileNo = (TextView)findViewById(R.id.MobileNo);
        P_Password = (EditText)findViewById(R.id.P_Password);
        N_Password = (EditText)findViewById(R.id.N_Password);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("My Account");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        MyAccountText = (TextView)findViewById(R.id.MyAccountText);
        MyAccountText.setTextColor(new ColorDrawable(getResources().getColor(R.color.fotterText)).getColor());

        MyAccountLayout = (RelativeLayout)findViewById(R.id.Accountlayout);
        MyAccountLayout.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        AccountImage = (ImageView)findViewById(R.id.Account);
        AccountImage.setImageResource(R.drawable.white_myaccount);

        SharedPreferences prefs = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        String str = prefs.getString("Name","");
        String[] splitStr = str.split("\\s+");
        F_Name.setText(splitStr[0]);
        L_Name.setText(splitStr[1]);
        Cnic.setText(prefs.getString("CNIC",""));
        MobileNo.setText(prefs.getString("MobileNo",""));

    }





    //**************************************************Click Functions*************************************************//

    public void Change_Password(View view)
    {
        String pre_pass = P_Password.getText().toString().trim();
        String new_pass = N_Password.getText().toString().trim();
    }

    public void Home(View view)
    {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("CityFrom","");
        intent.putExtra("Check","");
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

    public void My_Booking(View view)
    {
        Intent intent = new Intent(this, MyBooking.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

    public void Help(View view)
    {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

    public void My_Account(View view)
    {

    }

}
