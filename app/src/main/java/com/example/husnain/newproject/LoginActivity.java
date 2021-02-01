package com.example.husnain.newproject;

import android.Manifest;
import android.app.ProgressDialog;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.Utils.Utile;
import com.example.husnain.newproject.activities.DataSyncingActivity;
import com.example.husnain.newproject.activities.MainDrawerActivity;
import com.example.husnain.newproject.entities.Users;
import com.example.husnain.newproject.viewmodels.UsersViewModel;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText cus_id, pass;
    CheckBox chkRememberPasw;
    private ProgressDialog progressDialog;
    private UsersViewModel usersViewModel;

    boolean RememberPasw = false;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                }).check();


        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.setCancelable(false);

        // intialization
        chkRememberPasw = (CheckBox) findViewById(R.id.chkRememberPasw);
        cus_id = (EditText) findViewById(R.id.customerid);
        pass = (EditText) findViewById(R.id.password);
        if (!AppSession.get(Constants.SHARED_PREF.USER_NAME).equals("blank_string_key")) {
            cus_id.setText(AppSession.get(Constants.SHARED_PREF.USER_NAME));
            pass.setText(AppSession.get(Constants.SHARED_PREF.PASW));
            chkRememberPasw.toggle();
        }
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.chkRememberPasw:
                if (checked)
                    RememberPasw = true;
                else
                    RememberPasw = false;
                break;
        }
    }

    public void Log_In(View view) {
        if (cus_id.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty()) {
            Toast.makeText(LoginActivity.this, "Please Enter all required fields!", Toast.LENGTH_SHORT).show();
        } else {
            if (!gpsturnon()) {
                Utile.dialogEnableLocation("Location Alert", this);
            } else {
                String UserName = cus_id.getText().toString();
                String Pasw = pass.getText().toString();
                usersViewModel.validateUser(UserName, Pasw).observe(this, new Observer<List<Users>>() {
                    @Override
                    public void onChanged(@Nullable List<Users> users) {
                        if (users.size() > 0) {

                            RememberPasword();

                            Global.User_ID = users.get(0).getUser_Id();
                            Global.User_Name = users.get(0).getUser_Name();
                            Global.IsSuperAdmin = users.get(0).getIsSuperAdmin();
                            Global.IsAdmin = users.get(0).getIsAdmin();
                            Intent intent = new Intent(LoginActivity.this, MainDrawerActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid UserName or Password.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                usersViewModel.validateUser(UserName, Pasw);
            }
        }
    }

    public void RememberPasword() {
        boolean checked = ((CheckBox) chkRememberPasw).isChecked();
        if (checked) {
            AppSession.put(Constants.SHARED_PREF.USER_NAME, cus_id.getText().toString());
            AppSession.put(Constants.SHARED_PREF.PASW, pass.getText().toString());
        } else {
            AppSession.remove(Constants.SHARED_PREF.USER_NAME);
            AppSession.remove(Constants.SHARED_PREF.PASW);
        }
    }

    public void ButtonSync(View view) {
        Intent i = new Intent(this, DataSyncingActivity.class);
        startActivity(i);
    }


    public boolean gpsturnon() {
        try {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ee) {
            return false;
        }
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
    //End

}
