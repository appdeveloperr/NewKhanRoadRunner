package com.example.husnain.newproject.activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.activities.cashdetail.CashDetail;
import com.example.husnain.newproject.activities.cashdetail.CashDetailFragment;
import com.example.husnain.newproject.entities.TicketingSeat;

import static com.example.husnain.newproject.Global.MY_PREFS_NAME;

public class CashDetailActivity extends AppCompatActivity implements CashDetailFragment.OnListFragmentInteractionListener{

    CashDetailFragment fragment;

    public CashDetailActivity() {
        this.fragment = CashDetailFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cash_detail_activity);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, this.fragment)
                    .commitNow();
        }
        this.setTitle(this.getString(R.string.action_cash_detail) + " [" + Global.User_Name + "]");
    }

//    @Override
//    protected void onResume() {
//        this.fragment.reload();
//        super.onResume();
//
//    }

    @Override
    public void onListFragmentInteraction(CashDetail item) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();


        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("ComingFromCashDetailsActivity", "yes");
        editor.apply();
    }
}
