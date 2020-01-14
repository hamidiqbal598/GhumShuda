package com.example.hamidkhan.smd_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.mViewHolder> {
    private  ArrayList<listItemForCases> mExampleList;
    private Context context;

    public static class mViewHolder extends  RecyclerView.ViewHolder{
        public ImageView mImageView;

        public TextView mTextViewName;
        public TextView mTextBasicInfo;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imagePerson);
            mTextViewName=itemView.findViewById(R.id.namePerson);
            mTextViewName=itemView.findViewById(R.id.basicInfoPerson);


        }
    }

    public listAdapter(ArrayList<listItemForCases> exampleList){
        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View tempView=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cases_items,viewGroup,false);
        context = viewGroup.getContext();
        mViewHolder VH =new mViewHolder(tempView);
        return VH;

    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder mViewHolder, int position) {
        listItemForCases currentItem=mExampleList.get(position);

        Picasso.with(context).load(currentItem.getmImageResource()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(mViewHolder.mImageView);


        //mViewHolder.mImageView.setImageResource(currentItem.getmImageResource());
        mViewHolder.mTextViewName.setText(currentItem.getmTextName());
        mViewHolder.mTextBasicInfo.setText(currentItem.getmTextBasicInfo());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
