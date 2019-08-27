package com.example.husnain.newproject;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Husnain on 4/27/2018.
 */

public class City_Addapter  extends RecyclerView.Adapter<City_Addapter.ViewHolder>{


    ArrayList<CityInfo> List;
    Context context;
    String check;

    public City_Addapter(ArrayList<CityInfo> list,Context con,String ch)
    {
        List=list;
        context=con;
        check = ch;
    }



    @Override
    public City_Addapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(context).inflate(R.layout.list_item ,parent, false);
        //View v = inflater.inflate(R.layout.user_custom_layout ,parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final City_Addapter.ViewHolder holder, final int position) {

        TextView tv = holder.Name;
        tv.setText(List.get(position).getCity_Name());

//        ImageView img = holder.image;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, HomeActivity.class);
                String name = holder.Name.getText().toString();
                intent.putExtra("CityFrom",name);
                intent.putExtra("CityId",String.valueOf(List.get(position).getCity_Id()));
                intent.putExtra("Check",check);
                context.startActivity(intent);
                ((Activity)context).finish();
//                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

    }

//    private void overridePendingTransition(int slide_in_left, int slide_out_right) {
//        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
//    }

    @Override
    public int getItemCount() {

        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
//        ImageView image;

        public View root;

        public ViewHolder(View view)
        {
            super(view);
            Name =(TextView) itemView.findViewById(R.id.list_text);
//            image = (ImageView) itemView.findViewById(R.id.ImageView);

            root = view;
        }
    }
}
