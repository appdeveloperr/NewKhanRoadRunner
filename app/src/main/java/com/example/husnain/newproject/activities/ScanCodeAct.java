package com.example.husnain.newproject.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.husnain.newproject.Global.MY_PREFS_NAME;


public class ScanCodeAct extends AppCompatActivity implements ZXingScannerView.ResultHandler{


    ZXingScannerView ScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting QR Code FrontEnd
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

        //setContentView(R.layout.activity_scan_code);
    }


    @Override
    public void handleResult(Result rawResult) {

        Log.d("Data Scanned: ","Successfully "+rawResult.getText());

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("ScannedData", rawResult.getText());
        editor.putString("ComingFrom", "ScanActivity");
        editor.putString("ComingFromCashDetailsActivity", "no");
        editor.apply();
//        String url = "https://www.ingrumid.com/"+rawResult.getText();
        String url = rawResult.getText();

//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        startActivity(browserIntent);


        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}
