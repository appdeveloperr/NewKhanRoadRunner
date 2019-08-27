package com.example.husnain.newproject;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Husnain on 5/18/2018.
 */

public class Preference_For_Schedule extends AppCompatActivity {

    private RelativeLayout first;
    private RelativeLayout second;
    private RelativeLayout third;
    private RelativeLayout fourth;

    private TextView DST;
    private TextView FST;
    private TextView BTT;
    private TextView ETT;

    private ImageView time_one;
    private ImageView time_two;
    private ImageView time_three;
    private ImageView time_four;

    private TextView first_text;
    private TextView second_text;
    private TextView third_text;
    private TextView fourth_text;

    private Global g;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference_for_schedule);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setTitle("Filter");
        actionbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

        g = (Global) getApplication();

        first = (RelativeLayout)findViewById(R.id.first);
        second = (RelativeLayout)findViewById(R.id.second);
        third = (RelativeLayout)findViewById(R.id.third);
        fourth = (RelativeLayout)findViewById(R.id.fourth);

        DST = (TextView) findViewById(R.id.DST);
        FST = (TextView) findViewById(R.id.FST);
        BTT = (TextView)findViewById(R.id.BTT);
        ETT = (TextView)findViewById(R.id.ETT);

        time_one = (ImageView) findViewById(R.id.i1);
        time_two = (ImageView) findViewById(R.id.i2);
        time_three = (ImageView) findViewById(R.id.i3);
        time_four = (ImageView) findViewById(R.id.i4);

        first_text = (TextView) findViewById(R.id.first_text);
        second_text = (TextView) findViewById(R.id.second_text);
        third_text = (TextView)findViewById(R.id.third_text);
        fourth_text = (TextView)findViewById(R.id.fourth_text);

        check_precerence();
    }
//    ***********************************************************Menu for Filter Disabled ******************************************
@Override
public boolean onCreateOptionsMenu(Menu menu)
{

    getMenuInflater().inflate(R.menu.menu_pref_disable,menu);

    MenuItem item = menu.findItem(R.id.preference);

    return super.onCreateOptionsMenu(menu);
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.filter_disable)
        {
            g.setTime(0);
            g.setDeparture(false);
            g.setFare(false);
            g.setBusiness(false);
            g.setEconomy(false);

            first.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            second.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            third.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            DST.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            FST.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            BTT.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            ETT.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            time_one.setImageResource(R.drawable.time_one);
            time_two.setImageResource(R.drawable.time_two);
            time_three.setImageResource(R.drawable.time_three);
            time_four.setImageResource(R.drawable.time_four);

            first_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            second_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            third_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            fourth_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
        }

        return super.onOptionsItemSelected(item);
    }
//***************************************************************************************************************************
//****************************************************************Check Prefrence for color***************************************
    public void check_precerence()
    {
        if (g.isFare())
        {
            FST.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
        }
        if (g.isDeparture())
        {
            DST.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
        }

        if(g.isBusiness())
        {
            BTT.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
        }
        else if (g.isEconomy())
        {
            ETT.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
        }

        if (g.getTime() != 0)
        {
            if(g.getTime() == 1)
            {
                first.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            }
            else if(g.getTime() == 2)
            {
                second.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            }
            else if(g.getTime() == 3)
            {
                third.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            }
            else
            {
                fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            }
        }
    }
//*********************************************************************** On Click ************************************************
    public void Departure_Sorting(View view)
    {
        int color = DST.getCurrentTextColor();
        if(color == new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor())
        {
            DST.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
            g.setDeparture(true);
        }
        else if (color == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            DST.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            g.setDeparture(false);
        }
    }

    public void Fare_Sorting(View view)
    {
        int color = FST.getCurrentTextColor();
        if(color == new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor())
        {
            FST.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
            g.setFare(true);
        }
        else if (color == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            FST.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            g.setFare(false);
        }
    }

    public void Business_Type(View view)
    {
        int color = BTT.getCurrentTextColor();
        if(color == new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor() )
        {
            BTT.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
            ETT.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            g.setEconomy(false);
            g.setBusiness(true);
        }
        else if (color == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            BTT.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            g.setBusiness(false);
        }
    }

    public void Economy_Type(View view)
    {
        int color = ETT.getCurrentTextColor();
        if(color == new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor())
        {
            ETT.setTextColor(new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor());
            BTT.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            g.setEconomy(true);
            g.setBusiness(false);
        }
        else if (color == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            ETT.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            g.setEconomy(false);
        }
    }

    public void First_Time(View view)
    {
        ColorDrawable viewColor = (ColorDrawable) first.getBackground();
        int colorId = viewColor.getColor();
        if (colorId == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            first.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_one.setImageResource(R.drawable.time_one);
            first_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(0);
        }
        else if (colorId == new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor())
        {
            first.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            second.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            third.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_one.setImageResource(R.drawable.time_one_white);
            time_two.setImageResource(R.drawable.time_two);
            time_three.setImageResource(R.drawable.time_three);
            time_four.setImageResource(R.drawable.time_four);

            first_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor());
            second_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            third_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            fourth_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(1);
        }
    }

    public void Second_Time(View view)
    {
        ColorDrawable viewColor = (ColorDrawable) second.getBackground();
        int colorId = viewColor.getColor();
        if (colorId == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            second.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_two.setImageResource(R.drawable.time_two);
            second_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(0);
        }
        else if (colorId == new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor())
        {
            first.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            second.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            third.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_one.setImageResource(R.drawable.time_one);
            time_two.setImageResource(R.drawable.time_two_white);
            time_three.setImageResource(R.drawable.time_three);
            time_four.setImageResource(R.drawable.time_four);

            first_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            second_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor());
            third_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            fourth_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(2);
        }
    }

    public void Third_Time(View view)
    {
        ColorDrawable viewColor = (ColorDrawable) third.getBackground();
        int colorId = viewColor.getColor();
        if (colorId == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            third.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_three.setImageResource(R.drawable.time_three);
            third_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(0);
        }
        else if (colorId == new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor())
        {
            first.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            second.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            third.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));
            fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_one.setImageResource(R.drawable.time_one);
            time_two.setImageResource(R.drawable.time_two);
            time_three.setImageResource(R.drawable.time_three_white);
            time_four.setImageResource(R.drawable.time_four);

            first_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            second_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            third_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor());
            fourth_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(3);
        }
    }

    public void Fourth_Time(View view)
    {
        ColorDrawable viewColor = (ColorDrawable) fourth.getBackground();
        int colorId = viewColor.getColor();
        if (colorId == new ColorDrawable(getResources().getColor(R.color.darkcolor)).getColor())
        {
            fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));

            time_four.setImageResource(R.drawable.time_four);
            fourth_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());

            g.setTime(0);
        }
        else if (colorId == new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor())
        {
            first.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            second.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            third.setBackground(new ColorDrawable(getResources().getColor(R.color.bpWhite)));
            fourth.setBackground(new ColorDrawable(getResources().getColor(R.color.darkcolor)));

            time_one.setImageResource(R.drawable.time_one);
            time_two.setImageResource(R.drawable.time_two);
            time_three.setImageResource(R.drawable.time_three);
            time_four.setImageResource(R.drawable.time_four_white);

            first_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            second_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            third_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpblack)).getColor());
            fourth_text.setTextColor(new ColorDrawable(getResources().getColor(R.color.bpWhite)).getColor());

            g.setTime(4);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //***********************************************************Apply Filter
    public void Apply_Filter(View view)
    {
        super.onBackPressed();
    }
}
