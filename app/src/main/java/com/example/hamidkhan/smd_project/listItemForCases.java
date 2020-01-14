package com.example.hamidkhan.smd_project;

import android.net.Uri;

public class listItemForCases {
    private Uri mImageResource;
    private String mTextName, mTextBasicInfo;

    public  listItemForCases(Uri imageResource, String textName, String textBasicInfo){
        mImageResource=imageResource;
        mTextName=textName;
        mTextBasicInfo=textBasicInfo;
    }

    public listItemForCases()
    {

    }

    public Uri getmImageResource() {
        return mImageResource;
    }

    public String getmTextName() {
        return mTextName;
    }

    public String getmTextBasicInfo() {
        return mTextBasicInfo;
    }
}

