package com.example.husnain.newproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.activities.SystemConfigActivity;

public class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Constants.APPLICATION_CONTEXT = this;
        imageView = (ImageView)findViewById(R.id.image);
        progressBar = (ProgressBar)findViewById(R.id.splah_Progress);
        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_version.setText("v" + BuildConfig.VERSION_NAME);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
        }

        Animation myAnim = AnimationUtils.loadAnimation(this,R.anim.splash_transition);
        imageView.startAnimation(myAnim);

        if(AppSession.get(Constants.SHARED_PREF.IP).equals("blank_string_key")){
            Intent i = new Intent(this, SystemConfigActivity.class);
            startActivity(i);
            this.finish();
        } else {
            final Intent intent = new Intent(this,LoginActivity.class);
            Thread timer = new Thread(){

                public void run(){
                    try
                    {
                        doWork();
                        sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                        finish();
                    }
                }
            };
            timer.start();
        }
    }

    private void doWork()
    {
        for (int progress =0; progress < 100; progress+=10)
        {
            try
            {
                Thread.sleep(100);
                progressBar.setProgress(progress);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
