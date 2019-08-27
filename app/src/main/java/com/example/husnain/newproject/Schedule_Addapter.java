package com.example.husnain.newproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Husnain on 5/2/2018.
 */

public class Schedule_Addapter extends RecyclerView.Adapter<Schedule_Addapter.ViewHolder>{


    ArrayList<Schedule_Info> List;
    Context context;
    String pDate;
    String ToCityId;
    String FromCityId;

    public Schedule_Addapter(ArrayList<Schedule_Info> list,Context con,String pDate,String ToCityId,String FromCityId)
    {
        List=list;
        context=con;
        this.pDate=pDate;
        this.ToCityId=ToCityId;
        this.FromCityId=FromCityId;
    }


    @Override
    public Schedule_Addapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(context).inflate(R.layout.list_schedule ,parent, false);
        //View v = inflater.inflate(R.layout.user_custom_layout ,parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final Schedule_Addapter.ViewHolder holder, final int position) {

        TextView tv = holder.busType;
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

    }

    @Override
    public int getItemCount() {

        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView busType;
        TextView Fare;
        TextView Departure;
        TextView Arrival;
        TextView Seats;
//        ImageView image;

        public View root;

        public ViewHolder(View view)
        {
            super(view);
            busType =(TextView) itemView.findViewById(R.id.busType);
            Fare =(TextView) itemView.findViewById(R.id.Fare);
            Departure =(TextView) itemView.findViewById(R.id.departure);
            Arrival =(TextView) itemView.findViewById(R.id.arrival);
            Seats =(TextView) itemView.findViewById(R.id.Seats);

//            image = (ImageView) itemView.findViewById(R.id.ImageView);

            root = view;
        }
    }

}
