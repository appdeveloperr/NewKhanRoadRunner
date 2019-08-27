package com.example.husnain.newproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Husnain on 5/17/2018.
 */

public class Seats_Addapter_44 extends RecyclerView.Adapter<Seats_Addapter_44.ViewHolder>{


    ArrayList<Integer> SeatId;
    int SeatCount;
    int PriceCount;
    ArrayList<SeatsInfo> List;
    ArrayList<SeatsInfo> NewList;
    ArrayList<SeatsInfo> SelectedSeats;
    Context context;
    TextView SeatCounter;
    TextView PriceCounter;

    public Seats_Addapter_44(ArrayList<SeatsInfo> newList, Context con, TextView seatCounter, TextView priceCount, ArrayList<SeatsInfo> list, ArrayList SelecedSeats)
    {
        List=list;
        context=con;
        SeatId = new ArrayList<>();
        SeatCount = 0;
        PriceCount = 0;
        SeatCounter=seatCounter;
        PriceCounter = priceCount;
        NewList = newList;
        this.SelectedSeats=SelecedSeats;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
//        return super.getItemViewType(position);
    }


    @Override
    public Seats_Addapter_44.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v  = LayoutInflater.from(context).inflate(R.layout.list_seats_45,parent, false);

            Seats_Addapter_44.ViewHolder viewHolder = new Seats_Addapter_44.ViewHolder(v);
            return viewHolder;

    }

    @Override
    public void onBindViewHolder(final Seats_Addapter_44.ViewHolder holder, final int position) {

        ImageView img;
        TextView tv;

        tv = holder.textView1;
        tv.setText("" + List.get(position*4).getSeat_No());

        tv = holder.textView2;
        tv.setText("" + List.get(position*4 + 1).getSeat_No());

        tv = holder.textView3;
        tv.setText("" + List.get(position*4 + 2).getSeat_No());

        tv = holder.textView4;
        tv.setText("" + List.get(position*4 + 3).getSeat_No());


        if (List.get(position*4).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position *4).getSeat_id())) {
            img = holder.imageView1;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView1;
            img.setImageResource(R.drawable.red_seat);
        } else if (SeatId.contains(List.get(position *4).getSeat_id()))
        {
            img = holder.imageView1;
            img.setImageResource(R.drawable.green_seat);
        }

        if (List.get(position*4 + 1).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position*4 + 1).getSeat_id())) {
            img = holder.imageView2;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4 + 1).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView2;
            img.setImageResource(R.drawable.red_seat);
        } else if (SeatId.contains(List.get(position *4 + 1).getSeat_id()))
        {
            img = holder.imageView2;
            img.setImageResource(R.drawable.green_seat);
        }

        if (List.get(position*4 + 2).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position*4 + 2).getSeat_id())) {
            img = holder.imageView3;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4 + 2).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView3;
            img.setImageResource(R.drawable.red_seat);
        } else if (SeatId.contains(List.get(position *4 + 2).getSeat_id()))
        {
            img = holder.imageView3;
            img.setImageResource(R.drawable.green_seat);
        }

        if (List.get(position*4 + 3).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position *4).getSeat_id())) {
            img = holder.imageView4;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4 + 3).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView4;
            img.setImageResource(R.drawable.red_seat);
        } else if (SeatId.contains(List.get(position *4 + 3).getSeat_id()))
        {
            img = holder.imageView1;
            img.setImageResource(R.drawable.green_seat);
        }

////****************************************************************************************************************************//
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(List.get(position*4).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView1;

                    if(SeatId.contains(List.get(position*4).getSeat_id()))
                    {
                        imageView.setImageResource(R.drawable.black_seat);
                        SeatId.remove(SeatId.indexOf(List.get(position*4).getSeat_id()));
                        SeatCount--;
                        PriceCount-=List.get(position*4).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);

                        SelectedSeats.remove(SelectedSeats.indexOf(List.get(position*4)));
                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4).getSeat_id());
                        SeatCount++;
                        PriceCount+=List.get(position*4).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);

                        SelectedSeats.add(List.get(position*4));
                    }

                }
            }
        });

        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(List.get(position*4 +1).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView2;

                    if(SeatId.contains(List.get(position*4 +1).getSeat_id()))
                    {
                        imageView.setImageResource(R.drawable.black_seat);
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +1).getSeat_id()));
                        SeatCount--;
                        PriceCount-=List.get(position*4 +1).getFare();
                        PriceCounter.setText("Price: "+PriceCount);

                        SeatCounter.setText("Selected: "+SeatCount);
                        Log.e("Counter............","! " + SeatId.size());

                        SelectedSeats.remove(SelectedSeats.indexOf(List.get(position*4 +1)));
                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +1).getSeat_id());
                        SeatCount++;
                        PriceCount+=List.get(position*4 +1).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);


                        SelectedSeats.add(List.get(position*4 +1));
                    }

                }
            }
        });
        holder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(List.get(position*4 +2).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView3;

                    if(SeatId.contains(List.get(position*4 +2).getSeat_id()))
                    {
                        imageView.setImageResource(R.drawable.black_seat);
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +2).getSeat_id()));
                        SeatCount--;
                        PriceCount-=List.get(position*4 +2).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);

                        SelectedSeats.remove(SelectedSeats.indexOf(List.get(position*4 +2)));
                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +2).getSeat_id());
                        SeatCount++;
                        PriceCount+=List.get(position*4 +2).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);

                        SelectedSeats.add(List.get(position*4 +2));
                    }

                }
            }
        });
        holder.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(List.get(position*4 +3).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView4;

                    if(SeatId.contains(List.get(position*4 +3).getSeat_id()))
                    {
                        imageView.setImageResource(R.drawable.black_seat);
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +3).getSeat_id()));
                        SeatCount--;
                        PriceCount-=List.get(position*4 +3).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);

                        SelectedSeats.remove(SelectedSeats.indexOf(List.get(position*4 +3)));
                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +3).getSeat_id());
                        SeatCount++;
                        PriceCount+=List.get(position*4 +3).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        SeatCounter.setText("Selected: "+SeatCount);

                        SelectedSeats.add(List.get(position*4 +3));
                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return NewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;

        ImageView imageView5;

        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;

        public View root;

        public ViewHolder(View view)
        {
            super(view);
            imageView1 =(ImageView) itemView.findViewById(R.id.imageView1);
            imageView2 =(ImageView) itemView.findViewById(R.id.imageView2);
            imageView3 =(ImageView) itemView.findViewById(R.id.imageView3);
            imageView4 =(ImageView) itemView.findViewById(R.id.imageView4);

            textView1 =(TextView) itemView.findViewById(R.id.textView1);
            textView2 =(TextView) itemView.findViewById(R.id.textView2);
            textView3 =(TextView) itemView.findViewById(R.id.textView3);
            textView4 =(TextView) itemView.findViewById(R.id.textView4);

            root = view;
        }
    }
}
