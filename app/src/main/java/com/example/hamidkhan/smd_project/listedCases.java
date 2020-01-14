package com.example.hamidkhan.smd_project;

//import android.support.v7.app.AlertController;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

public class listedCases extends AppCompatActivity {

    private RecyclerView mRecyclerVier;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference dataref;
    private List<uploadData> muploads;
    Adapter.ViewHolder holder;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listed_cases);

        final ArrayList<listItemForCases> exampleList=new ArrayList<>();
        //exampleList.add(new listItemForCases(R.drawable.hamid1, "Hamid Iqbal", "Age: 22, Height: 5ft 5in ,Color: Caucasian"));
        //exampleList.add(new listItemForCases(R.drawable.abdullah1, "Chaudhary Abdullah", "Age: 25, Height: 5ft 3in, Color: Brown"));
        //exampleList.add(new listItemForCases(R.drawable.azeem1, "M. Azeem Khan", "Age: 23, Height: 5ft 6in, Color: Caucasian"));
        //exampleList.add(new listItemForCases(R.drawable.greeting, "Chaudhary Abdullah", "Age: 25, Height: 5ft 3in, Color: Brown"));
        //exampleList.add(new listItemForCases(R.drawable.abdullah1, "Chaudhary Abdullah", "Age: 25, Height: 5ft 3in, Color: Brown"));
        //exampleList.add(new listItemForCases(R.drawable.logosmall, "Hamid Iqbal", "Age: 22, Height: 5ft 5in ,Color: Caucasian"));
        //exampleList.add(new listItemForCases(R.drawable.hamid1, "Hamid Iqbal", "Age: 22, Height: 5ft 5in ,Color: Caucasian"));
        //exampleList.add(new listItemForCases(R.drawable.abdullah1, "Chaudhary Abdullah", "Age: 25, Height: 5ft 3in, Color: Brown"));
        //exampleList.add(new listItemForCases(R.drawable.azeem1, "M. Azeem Khan", "Age: 23, Height: 5ft 6in, Color: Caucasian"));

        muploads=new ArrayList<>();
        dataref= FirebaseDatabase.getInstance().getReference("uploads");
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    uploadData upload = postSnapshot.getValue(uploadData.class);
                    //exampleList.add(new listItemForCases(R.drawable.abdullah1, "Chaudhary Abdullah", "Age: 25, Height: 5ft 3in, Color: Brown"));
                    System.out.println("Nanme: "+ upload.getName());
                    muploads.add(upload);
                }
                System.out.println("Nanme: "+ muploads.size());
                //ImageView imageView = null;
                for(int i=0;i<muploads.size();i++)
                {
                    String url=muploads.get(i).getImageurl();
                    //ImageView imageView=(ImageView)findViewById(R.mipmap.ic_launcher);
                    //Picasso.with(listedCases.this).load(muploads.get(i).getImageurl()).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(imageView);

                    //exampleList.add(new listItemForCases(R.mipmap.ic_launcher,muploads.get(i).getName(),"Age: " + muploads.get(i).getAge() + ", Height: " + muploads.get(i).getHeight() + ", Color: " + muploads.get(i).getColor()));
                }
                mRecyclerVier=findViewById(R.id.casesList);
                mRecyclerVier.setHasFixedSize(true);
                mLayoutManager=new LinearLayoutManager(listedCases.this);
                //mAdapter=new listAdapter(exampleList);
                mAdapter = new Adapter(exampleList);

                mRecyclerVier.setLayoutManager(mLayoutManager);
                mRecyclerVier.setAdapter(mAdapter);

                //mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

                //mRecyclerView.setAdapter(mAdapter);
                //mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(listedCases.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


    }
}