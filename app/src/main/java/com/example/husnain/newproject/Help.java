package com.example.husnain.newproject;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Husnain on 5/1/2018.
 */

public class Help extends AppCompatActivity {

    private TextView HelpText;
    private RelativeLayout HelpLayout;
    private ImageView HelpImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Help");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        HelpText = (TextView)findViewById(R.id.HelpText);;
        HelpText.setTextColor(new ColorDrawable(getResources().getColor(R.color.fotterText)).getColor());

        HelpLayout = (RelativeLayout)findViewById(R.id.Helplayout);
        HelpLayout.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        HelpImage = (ImageView)findViewById(R.id.Help);
        HelpImage.setImageResource(R.drawable.white_help);
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
        Intent intent = new Intent(this, MyBooking.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();
    }

    public void Help(View view)
    {

    }

    public void My_Account(View view)
    {
        Intent intent = new Intent(this, MyAccount.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
        finish();
    }

}
