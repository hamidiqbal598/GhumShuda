package com.example.hamidkhan.smd_project;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    private ArrayList<listItemForCases> mexamplelist;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mimage;
        public TextView mname;
        public TextView mdata;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mimage=itemView.findViewById(R.id.imagePerson);
            mname=itemView.findViewById(R.id.namePerson);
            mdata=itemView.findViewById(R.id.basicInfoPerson);
        }
    }

    public Adapter(ArrayList<listItemForCases> examplelist)
    {
        mexamplelist=examplelist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cases_items,viewGroup,false);
         ViewHolder evh=new ViewHolder(v);
        context = viewGroup.getContext();
         return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i)
    {
        final listItemForCases list=mexamplelist.get(i);

        Picasso.with(context).load(list.getmImageResource()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(holder.mimage);

        holder.mname.setText(list.getmTextName());
        holder.mdata.setText(list.getmTextBasicInfo());

        holder.mimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,caseDetail.class);

                String str=String.valueOf("abc");

                //System.out.println("1234567890: " + list.getmImageResource());

                i.putExtra("name_txt",list.getmTextName());
                i.putExtra("basicinfo_txt",list.getmTextBasicInfo());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return mexamplelist.size();
    }



}
