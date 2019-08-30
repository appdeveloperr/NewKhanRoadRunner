package com.example.husnain.newproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.husnain.newproject.BuildConfig;
import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;

public class InfoActivity extends AppCompatActivity {

    EditText etServerIP;
    ImageButton ibSave;
    TextView tvInfoAppVersion, tvInfoUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("App Info");



        etServerIP = (EditText) findViewById(R.id.etServerIP);
        tvInfoAppVersion = (TextView) findViewById(R.id.tvInfoAppVersion);
        tvInfoUserName = (TextView) findViewById(R.id.tvInfoUserName);
        ibSave = (ImageButton) findViewById(R.id.ibSave);

        etServerIP.setText(AppSession.get(Constants.SHARED_PREF.IP));
        tvInfoUserName.setText("User Name: " + Global.User_Name);
        tvInfoAppVersion.setText("App Version: " + BuildConfig.VERSION_NAME);
        if(Global.IsAdmin || Global.IsSuperAdmin ){
            ibSave.setVisibility(View.VISIBLE);
            etServerIP.setEnabled(true);
        }

    }

    public void UpdateIP(View view){
        AppSession.put(Constants.SHARED_PREF.IP,etServerIP.getText().toString());

        Toast.makeText(this,"Updated Successfully",Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        super.onBackPressed();
    }

}
