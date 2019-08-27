package com.example.husnain.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Husnain on 5/18/2018.
 */

public class Add_IP extends AppCompatActivity {

    private static final String MY_PREF_NAME = "MyPrefsFile";
    String starting_url;

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_ip);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Schedule");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        editText = (EditText)findViewById(R.id.ip_text);
        starting_url = editText.toString().trim();

    }

    public void On_Click(View view)
    {
        if (starting_url.trim().equals(""))
        {
            SharedPreferences shared = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("Starting_IP", "110.36.208.66");
//            editor.putString("Starting_IP", "192.168.100.7");
            editor.commit();
            Intent intent = new Intent(this,SplashActivity.class);
            startActivity(intent);
        }
        else
        {
            SharedPreferences shared = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            editor.putString("Starting_IP", starting_url);
            Intent intent = new Intent(this,SplashActivity.class);
            startActivity(intent);
        }

    }

}
