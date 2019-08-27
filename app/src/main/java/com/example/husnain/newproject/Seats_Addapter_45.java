package com.example.husnain.newproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.husnain.newproject.Sessions.AppSession;
import com.example.husnain.newproject.Utils.Constants;

import java.util.ArrayList;

/**
 * Created by Husnain on 5/3/2018.
 */

public class Seats_Addapter_45 extends RecyclerView.Adapter<Seats_Addapter_45.ViewHolder>{


    public static ArrayList<Integer> SeatId = new ArrayList<>();;
    int SeatCount;
    int PriceCount;
    ArrayList<SeatsInfo> List;
    ArrayList<SeatsInfo> NewList;
    ArrayList<SeatsInfo> SelectedSeats;
    Context context;
    TextView SeatCounter;
    TextView PriceCounter;
    boolean Morethan44;

    public Seats_Addapter_45(ArrayList<SeatsInfo> newList, Context con, TextView seatCounter, TextView priceCount, ArrayList<SeatsInfo> list, ArrayList SelecedSeats, boolean morethan44)
    {
        List = list;
        context = con;

        SeatCount = 0;
        PriceCount = 0;
        SeatCounter = seatCounter;
        PriceCounter = priceCount;
        NewList = newList;
        this.SelectedSeats = SelecedSeats;
        this.Morethan44 = morethan44;
        AppSession.remove(Constants.SHARED_PREF.SEAT_NO_CLICKED);
        AppSession.remove(Constants.SHARED_PREF.SEAT_GENDER);
    }

    @Override
    public int getItemViewType(int position) {

        return position;
//        return super.getItemViewType(position);
    }


    @Override
    public Seats_Addapter_45.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(context).inflate(R.layout.list_seats_45,parent, false);
        View v1 = LayoutInflater.from(context).inflate(R.layout.last_row_list_seats_45,parent, false);;

        if(viewType <= 9 ) {
            Seats_Addapter_45.ViewHolder viewHolder = new Seats_Addapter_45.ViewHolder(v);
            return viewHolder;
        }
        else if(Morethan44)
        {
            Log.e("Else part___","this is else part");
            Seats_Addapter_45.ViewHolder viewHolder1 = new Seats_Addapter_45.ViewHolder(v1);
            return viewHolder1;
        } else {
            Seats_Addapter_45.ViewHolder viewHolder = new Seats_Addapter_45.ViewHolder(v);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(final Seats_Addapter_45.ViewHolder holder, final int position) {

        ImageView img;
        TextView tv;

        if(position > 10 && Morethan44){
            tv = holder.textView1;
            tv.setText("" + List.get(position*4 + 1).getSeat_No());

            tv = holder.textView2;
            tv.setText("" + List.get(position*4 + 2).getSeat_No());

            tv = holder.textView3;
            tv.setText("" + List.get(position*4 + 3).getSeat_No());

            tv = holder.textView4;
            tv.setText("" + List.get(position*4 + 4).getSeat_No());

            tv = holder.textView5;
            tv.setText("" + List.get(position * 4 + 5).getSeat_No());
        } else {
            tv = holder.textView1;
            tv.setText("" + List.get(position*4).getSeat_No());

            tv = holder.textView2;
            tv.setText("" + List.get(position*4 + 1).getSeat_No());

            tv = holder.textView3;
            tv.setText("" + List.get(position*4 + 2).getSeat_No());

            tv = holder.textView4;
            tv.setText("" + List.get(position*4 + 3).getSeat_No());
        }


        if(position == 10 && Morethan44) {
            tv = holder.textView5;
            tv.setText("" + List.get(position * 4 + 4).getSeat_No());
        }
//        tv = holder.textView1;
//        tv.setText("Seat:" + List.get(position*4).getSeat_No());
//
//        tv = holder.textView2;
//        tv.setText("Seat:" + List.get(position*4 + 1).getSeat_No());
//
//        tv = holder.textView3;
//        tv.setText("Seat:" + List.get(position*4 + 2).getSeat_No());
//
//        tv = holder.textView4;
//        tv.setText("Seat:" + List.get(position*4 + 3).getSeat_No());


        /*if (List.get(position*4).getBookedBy().equals(Constants.SEAT_INS_TYPES.API) &&
                SeatId.contains(List.get(position*4).getSeat_id()) )
        {
            SelectedSeats.remove(SeatId.indexOf(List.get(position*4).getSeat_id()));
            SeatId.remove(SeatId.indexOf(List.get(position*4).getSeat_id()));

            SeatCounter.setText("Selected: "+ SelectedSeats.size());
            PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
        }*/

        if (List.get(position*4).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position *4).getSeat_id())) {
            img = holder.imageView1;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView1;
            if(List.get(position * 4 + 1).getGender().equals("Female")){
                img.setImageResource(R.drawable.ic_chair_female);
            } else {
                img.setImageResource(R.drawable.red_seat);
            }
        } else if (SeatId.contains(List.get(position *4).getSeat_id()))
        {
            img = holder.imageView1;
            img.setImageResource(R.drawable.green_seat);
        }

        if (List.get(position * 4 + 1).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position *4 +1).getSeat_id())) {
            img = holder.imageView2;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4 + 1).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView2;
            if(List.get(position * 4 + 1).getGender().equals("Female")){
                img.setImageResource(R.drawable.ic_chair_female);
            } else {
                img.setImageResource(R.drawable.red_seat);
            }
        } else if (SeatId.contains(List.get(position *4 +1).getSeat_id()))
        {
            img = holder.imageView2;
            img.setImageResource(R.drawable.green_seat);
        }

        if (List.get(position*4 + 2).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position *4 +2).getSeat_id())) {
            img = holder.imageView3;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4 + 2).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView3;
            if(List.get(position * 4 + 1).getGender().equals("Female")){
                img.setImageResource(R.drawable.ic_chair_female);
            } else {
                img.setImageResource(R.drawable.red_seat);
            }
        } else if (SeatId.contains(List.get(position *4 +2).getSeat_id()))
        {
            img = holder.imageView3;
            img.setImageResource(R.drawable.green_seat);
        }

        if (List.get(position*4 + 3).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position *4 +3).getSeat_id())) {
            img = holder.imageView4;
            img.setImageResource(R.drawable.black_seat);
        } else if (List.get(position*4 + 3).getSeat_status().toString().trim().equals("Reserved")) {
            img = holder.imageView4;
            //img.setImageResource(R.drawable.red_seat);
            if(List.get(position * 4 + 1).getGender().equals("Female")){
                img.setImageResource(R.drawable.ic_chair_female);
            } else {
                img.setImageResource(R.drawable.red_seat);
            }
        } else if (SeatId.contains(List.get(position *4 +3).getSeat_id()))
        {
            img = holder.imageView4;
            img.setImageResource(R.drawable.green_seat);
        }



        if(position > 9 && Morethan44) {
            if (List.get(position * 4 + 4).getSeat_status().equals("Empty") & !SeatId.contains(List.get(position * 4 + 4).getSeat_id())) {
                img = holder.imageView5;
                img.setImageResource(R.drawable.black_seat);
            } else if (List.get(position * 4 + 4).getSeat_status().toString().trim().equals("Reserved")) {
                img = holder.imageView5;
                //img.setImageResource(R.drawable.red_seat);
                if(List.get(position * 4 + 1).getGender().equals("Female")){
                    img.setImageResource(R.drawable.ic_chair_female);
                } else {
                    img.setImageResource(R.drawable.red_seat);
                }
            } else if (SeatId.contains(List.get(position * 4 + 4).getSeat_id()))
            {
                img = holder.imageView5;
                img.setImageResource(R.drawable.green_seat);
            }
        }
//    }

////****************************************************************************************************************************//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(context, SelectSeats.class);
//                intent.putExtra("Date",pDate);
//                intent.putExtra("fromCity",FromCityId);
//                intent.putExtra("toCity",ToCityId);
//                intent.putExtra("ScheduleData", List.get(position));
//                context.startActivity(intent);
////                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
////                finish();
//            }
//        });

        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender = getGender(position *4);

                if(List.get(position*4).getSeat_status().equals("Empty"))
                {

                    ImageView imageView = holder.imageView1;

                    if(SeatId.contains(List.get(position*4).getSeat_id()))
                    {
                        if(gender.equals("female")){
                            imageView.setImageResource(R.drawable.ic_chair_yellow);
                            SelectedSeats.get(SeatId.indexOf(List.get(position*4).getSeat_id())).setGender("Female");
                        } else if (gender.equals("none")){
                            imageView.setImageResource(R.drawable.black_seat);

                            //SeatCount--;
                            //PriceCount-=List.get(position*4).getFare();
                            //PriceCounter.setText("Price: "+PriceCount);
                            Log.e("Counter............","! " + SeatId.size());

                            SelectedSeats.remove(SeatId.indexOf(List.get(position*4).getSeat_id()));
                            SeatId.remove(SeatId.indexOf(List.get(position*4).getSeat_id()));
                            //SeatCounter.setText("Selected: "+ SelectSeats.SelectedSeats.size());
                            SeatCounter.setText("Selected: "+ SelectedSeats.size());
                            PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                        }
                    }
                    else
                    {

                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4).getSeat_id());
                        /*SeatCount++;
                        PriceCount+=List.get(position*4).getFare();
                        PriceCounter.setText("Price: "+PriceCount);*/
                        Log.e("Counter............","! " + SeatId.size());

                        SelectedSeats.add(List.get(position*4));
                        //SeatCounter.setText("Selected: "+ SelectSeats.SelectedSeats.size());
                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                    }
                }
            }
        });

        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender = getGender(position *4 +1);

                if(List.get(position*4 +1).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView2;

                    if(SeatId.contains(List.get(position*4 +1).getSeat_id()))
                    {
                        /*imageView.setImageResource(R.drawable.black_seat);
                        //SeatCount--;
                        //PriceCount-=List.get(position*4 +1).getFare();
                        //PriceCounter.setText("Price: "+PriceCount);

                        Log.e("Counter............","! " + SeatId.size());

                        SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +1).getSeat_id()));
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +1).getSeat_id()));

                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());*/

                        if(gender.equals("female")){
                            imageView.setImageResource(R.drawable.ic_chair_yellow);
                            SelectedSeats.get(SeatId.indexOf(List.get(position*4 +1).getSeat_id())).setGender("Female");
                        } else if (gender.equals("none")){
                            imageView.setImageResource(R.drawable.black_seat);

                            //SeatCount--;
                            //PriceCount-=List.get(position*4).getFare();
                            //PriceCounter.setText("Price: "+PriceCount);
                            Log.e("Counter............","! " + SeatId.size());

                            SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +1).getSeat_id()));
                            SeatId.remove(SeatId.indexOf(List.get(position*4 +1).getSeat_id()));
                            //SeatCounter.setText("Selected: "+ SelectSeats.SelectedSeats.size());
                            SeatCounter.setText("Selected: "+ SelectedSeats.size());
                            PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                        }

                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +1).getSeat_id());
                        /*SeatCount++;
                        PriceCount+=List.get(position*4 +1).getFare();
                        PriceCounter.setText("Price: "+PriceCount);*/
                        Log.e("Counter............","! " + SeatId.size());
                        //SeatCounter.setText("Selected: "+ (SelectSeats.SelectedSeats.size() + 1));


                        SelectedSeats.add(List.get(position*4 +1));
                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                    }

                }
            }
        });
        holder.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender = getGender(position *4 +2);

                if(List.get(position*4 +2).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView3;

                    if(SeatId.contains(List.get(position*4 +2).getSeat_id()))
                    {
                        /*imageView.setImageResource(R.drawable.black_seat);

                        *//*SeatCount--;
                        PriceCount-=List.get(position*4 +2).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());*//*
                        //SeatCounter.setText("Selected: "+(SelectSeats.SelectedSeats.size() + 1));

                        SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +2).getSeat_id()));
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +2).getSeat_id()));
                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());*/


                        if(gender.equals("female")){
                            imageView.setImageResource(R.drawable.ic_chair_yellow);
                            SelectedSeats.get(SeatId.indexOf(List.get(position*4 +2).getSeat_id())).setGender("Female");
                        } else if (gender.equals("none")){
                            imageView.setImageResource(R.drawable.black_seat);

                            //SeatCount--;
                            //PriceCount-=List.get(position*4).getFare();
                            //PriceCounter.setText("Price: "+PriceCount);
                            Log.e("Counter............","! " + SeatId.size());

                            SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +2).getSeat_id()));
                            SeatId.remove(SeatId.indexOf(List.get(position*4 +2).getSeat_id()));
                            //SeatCounter.setText("Selected: "+ SelectSeats.SelectedSeats.size());
                            SeatCounter.setText("Selected: "+ SelectedSeats.size());
                            PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                        }


                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +2).getSeat_id());
                        /*SeatCount++;
                        PriceCount+=List.get(position*4 +2).getFare();
                        PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());*/
                        //SeatCounter.setText("Selected: "+(SelectSeats.SelectedSeats.size() + 1));

                        SelectedSeats.add(List.get(position*4 +2));
                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                    }
                }
            }
        });
        holder.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender = getGender(position *4 +3);

                if(List.get(position*4 +3).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView4;

                    if(SeatId.contains(List.get(position*4 +3).getSeat_id()))
                    {
                        /*imageView.setImageResource(R.drawable.black_seat);
                        *//*SeatCount--;
                        PriceCount-=List.get(position*4 +3).getFare();
                        PriceCounter.setText("Price: "+PriceCount);*//*
                        Log.e("Counter............","! " + SeatId.size());
                        //SeatCounter.setText("Selected: "+(SelectSeats.SelectedSeats.size() + 1));

                        SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +3).getSeat_id()));
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +3).getSeat_id()));

                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());*/

                        if(gender.equals("female")){
                            imageView.setImageResource(R.drawable.ic_chair_yellow);
                            SelectedSeats.get(SeatId.indexOf(List.get(position*4 +3).getSeat_id())).setGender("Female");
                        } else if (gender.equals("none")){
                            imageView.setImageResource(R.drawable.black_seat);

                            //SeatCount--;
                            //PriceCount-=List.get(position*4).getFare();
                            //PriceCounter.setText("Price: "+PriceCount);
                            Log.e("Counter............","! " + SeatId.size());

                            SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +3).getSeat_id()));
                            SeatId.remove(SeatId.indexOf(List.get(position*4 +3).getSeat_id()));
                            //SeatCounter.setText("Selected: "+ SelectSeats.SelectedSeats.size());
                            SeatCounter.setText("Selected: "+ SelectedSeats.size());
                            PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                        }

                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +3).getSeat_id());
                        //SeatCount++;
                        //PriceCount+=List.get(position*4 +3).getFare();
                        //PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        //SeatCounter.setText("Selected: "+(SelectSeats.SelectedSeats.size() + 1));

                        SelectedSeats.add(List.get(position*4 +3));
                        SeatCounter.setText("Selected: " + SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                    }
                }
            }
        });

        if(position == 10 && Morethan44){
            holder.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String gender = getGender(position * 4 + 4 );

                if(List.get( position * 4 + 4 ).getSeat_status().equals("Empty"))
                {
                    ImageView imageView = holder.imageView5;

                    if(SeatId.contains(List.get(position*4 +4).getSeat_id()))
                    {
                        /*imageView.setImageResource(R.drawable.black_seat);
                        //SeatCount--;
                        //PriceCount-=List.get(position*4 +3).getFare();
                        //PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        //SeatCounter.setText("Selected: "+(SelectSeats.SelectedSeats.size() + 1));

                        SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +4).getSeat_id()));
                        SeatId.remove(SeatId.indexOf(List.get(position*4 +4).getSeat_id()));

                        SeatCounter.setText("Selected: "+ SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());*/
                        if(gender.equals("female")){
                            imageView.setImageResource(R.drawable.ic_chair_yellow);
                            SelectedSeats.get(SeatId.indexOf(List.get(position*4 +4).getSeat_id())).setGender("Female");
                        } else if (gender.equals("none")){
                            imageView.setImageResource(R.drawable.black_seat);

                            //SeatCount--;
                            //PriceCount-=List.get(position*4).getFare();
                            //PriceCounter.setText("Price: "+PriceCount);
                            Log.e("Counter............","! " + SeatId.size());

                            SelectedSeats.remove(SeatId.indexOf(List.get(position*4 +4).getSeat_id()));
                            SeatId.remove(SeatId.indexOf(List.get(position*4 +4).getSeat_id()));
                            //SeatCounter.setText("Selected: "+ SelectSeats.SelectedSeats.size());
                            SeatCounter.setText("Selected: "+ SelectedSeats.size());
                            PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                        }


                    }
                    else
                    {
                        imageView.setImageResource(R.drawable.green_seat);
                        SeatId.add(List.get(position*4 +4).getSeat_id());
                        //SeatCount++;
                        //PriceCount+=List.get(position*4 +3).getFare();
                        //PriceCounter.setText("Price: "+PriceCount);
                        Log.e("Counter............","! " + SeatId.size());
                        //SeatCounter.setText("Selected: "+(SelectSeats.SelectedSeats.size() + 1));

                        SelectedSeats.add(List.get(position*4 +4));
                        SeatCounter.setText("Selected: " + SelectedSeats.size());
                        PriceCounter.setText("Price: "+ SelectedSeats.size() * List.get(0).getFare());
                    }
                }
            }
        });
        }

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

        TextView textView5;

        public View root;

        public ViewHolder(View view)
        {
            super(view);
            imageView1 =(ImageView) itemView.findViewById(R.id.imageView1);
            imageView2 =(ImageView) itemView.findViewById(R.id.imageView2);
            imageView3 =(ImageView) itemView.findViewById(R.id.imageView3);
            imageView4 =(ImageView) itemView.findViewById(R.id.imageView4);

            imageView5 =(ImageView) itemView.findViewById(R.id.imageView5);

            textView1 =(TextView) itemView.findViewById(R.id.textView1);
            textView2 =(TextView) itemView.findViewById(R.id.textView2);
            textView3 =(TextView) itemView.findViewById(R.id.textView3);
            textView4 =(TextView) itemView.findViewById(R.id.textView4);

            textView5 =(TextView) itemView.findViewById(R.id.textView5);

            root = view;
        }
    }

    public String getGender(int position){
        if(AppSession.get(Constants.SHARED_PREF.SEAT_NO_CLICKED).equals("blank_string_key")){
            AppSession.put(Constants.SHARED_PREF.SEAT_NO_CLICKED, "" + List.get(position).getSeat_No() );
            AppSession.put(Constants.SHARED_PREF.CLICK_COUNTER, 1);
        } else if(AppSession.get(Constants.SHARED_PREF.SEAT_NO_CLICKED).equals("" + List.get(position).getSeat_No())){
            AppSession.put(Constants.SHARED_PREF.SEAT_NO_CLICKED, "" + List.get(position).getSeat_No());
            AppSession.put(Constants.SHARED_PREF.CLICK_COUNTER, AppSession.getInt(Constants.SHARED_PREF.CLICK_COUNTER) + 1);
        } else if( !(AppSession.get(Constants.SHARED_PREF.SEAT_NO_CLICKED).equals("" + List.get(position).getSeat_No())) ){
            if(SeatId.contains(List.get(position).getSeat_id())){
                AppSession.put(Constants.SHARED_PREF.CLICK_COUNTER, 3);
            } else {
                AppSession.put(Constants.SHARED_PREF.SEAT_NO_CLICKED, "" + List.get(position).getSeat_No());
                AppSession.remove(Constants.SHARED_PREF.CLICK_COUNTER);
                AppSession.put(Constants.SHARED_PREF.CLICK_COUNTER, 1);
            }
        }

        int click_counter = AppSession.getInt(Constants.SHARED_PREF.CLICK_COUNTER);

        if(click_counter == 1 ) {
            return  "male";
        } else if (click_counter == 2 )
            return  "female";
        else if (click_counter == 3 ){
            AppSession.remove(Constants.SHARED_PREF.CLICK_COUNTER);
            return  "none";
        } else
            return "";
    }

}
