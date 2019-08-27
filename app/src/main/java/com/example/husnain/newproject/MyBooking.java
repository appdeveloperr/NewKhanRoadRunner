package com.example.husnain.newproject;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Husnain on 5/1/2018.
 */

public class MyBooking extends AppCompatActivity {

    private TextView MyBookingText;
    private RelativeLayout MyBookingLayout;
    private ImageView MyBookingImage;
    private RecyclerView BRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_booking);


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("My Booking");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        MyBookingText = (TextView)findViewById(R.id.MyBookingText);
        MyBookingText.setTextColor(new ColorDrawable(getResources().getColor(R.color.fotterText)).getColor());

        MyBookingLayout = (RelativeLayout)findViewById(R.id.MyBookinglayout);
        MyBookingLayout.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        MyBookingImage = (ImageView)findViewById(R.id.MyBooking);
        MyBookingImage.setImageResource(R.drawable.white_mybooking);

        BRV = (RecyclerView)findViewById(R.id.BRV);
    }




    //**************************************************Click Functions*************************************************//

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

}
