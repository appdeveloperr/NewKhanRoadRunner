package com.example.husnain.newproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.husnain.newproject.PrintPakage.PrintTicketActivity;
import com.example.husnain.newproject.Utils.Constants;
import com.example.husnain.newproject.activities.TicketingActivity;
import com.example.husnain.newproject.entities.City;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.entities.InnerJoinRoute;

import java.util.ArrayList;

/**
 * Created by Husnain on 5/2/2018.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{



    ArrayList<InnerJoinRoute> List;
    Context context;

    public CityAdapter(ArrayList<InnerJoinRoute> list, Context con)
    {
        List=list;
        context=con;
    }


    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(context).inflate(R.layout.row_city ,parent, false);
        //View v = inflater.inflate(R.layout.user_custom_layout ,parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CityAdapter.ViewHolder holder, final int position) {

        TextView tvCity = holder.cityName;
        tvCity.setText(List.get(position).getCity_Name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PrintTicketActivity.TAG.equals(Constants.TAGS.TAG_SOURCE)){
                    PrintTicketActivity.Source = List.get(position);
                    ((Activity) context).finish();
                } else {
                    PrintTicketActivity.Destination = List.get(position);
                    ((Activity) context).finish();
                }


            }
        });

        /*TextView tv = holder.busType;
        tv.setText(List.get(position).getBusType());

        tv = holder.Fare;
        tv.setText("Rs: "+List.get(position).getFare());

        tv = holder.Departure;
        tv.setText("Departure Time: "+List.get(position).getDepartureTime());

        tv = holder.Arrival;
        tv.setText("Arrival Time: "+List.get(position).getArrivalTime());

        tv = holder.Seats;
        tv.setText("Seats: "+List.get(position).getSeats());
//        ImageView img = holder.image;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SelectSeats.class);
                intent.putExtra("Date",pDate);
                intent.putExtra("fromCity",FromCityId);
                intent.putExtra("toCity",ToCityId);
                intent.putExtra("ScheduleData", List.get(position));
                context.startActivity(intent);
//                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                finish();
            }
        });
*/
    }

    @Override
    public int getItemCount() {

        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityName;

        public View root;

        public ViewHolder(View view)
        {
            super(view);
            cityName =(TextView) itemView.findViewById(R.id.tx_city_name);
            root = view;
        }
    }

}
