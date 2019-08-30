package com.example.husnain.newproject.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.husnain.newproject.LoginActivity;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;

public class SystemConfigActivity extends AppCompatActivity {

    EditText et_ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_config);
        et_ip = (EditText) findViewById(R.id.et_server_ip);
    }

    public void SetServer(View view){
        if(et_ip.getText().toString().equals("")){
            Toast.makeText(this,"Field is required",Toast.LENGTH_LONG).show();
        } else {
            AppSession.put(Constants.SHARED_PREF.IP, et_ip.getText().toString());
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}
