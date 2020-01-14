package com.example.hamidkhan.smd_project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class caseDetail extends AppCompatActivity {


    private DatabaseReference dataref;
    private List<uploadData> muploads;
    private RecyclerView mRecyclerVier;

    private TextView name;
    private TextView cnic;
    private TextView fathername;
    private TextView lostloc;
    private TextView lostdate;
    private TextView age;
    private TextView height;
    private TextView color;
    private TextView thread;

    private Button updateLoc;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_detail);

        name=findViewById(R.id.Name);
        image=(ImageView) findViewById(R.id.image);
        cnic=(TextView) findViewById(R.id.cnic);
        fathername=(TextView) findViewById(R.id.fatherName);
        lostloc=(TextView) findViewById(R.id.lostlocation);
        lostdate=(TextView) findViewById(R.id.lostdate);
        age=(TextView) findViewById(R.id.age);
        height=(TextView) findViewById(R.id.height);
        color=(TextView) findViewById(R.id.color);
        thread=(TextView) findViewById(R.id.thread);
        updateLoc=(Button) findViewById(R.id.updateloc);
        updateLoc.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                updateLocation((Button) view);
            }

        });
        //updateLoc.setOnClickListener;

        final String names=getIncomingIntent().toString();
        //final boolean n=names.toString().equalsIgnoreCase("1234");

        dataref= FirebaseDatabase.getInstance().getReference("uploads");
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    uploadData upload = postSnapshot.getValue(uploadData.class);
                    System.out.println("Name :"+ upload.getName() + ";");
                    if(upload.getName().equals(names))
                    {
                        name.setText(upload.getName());
                        age.setText(upload.getAge());
                        cnic.setText(upload.getCnic());
                        fathername.setText(upload.getFathername());
                        color.setText(upload.getColor());
                        height.setText(upload.getHeight());
                        lostdate.setText(upload.getLostdata());
                        lostloc.setText(upload.getLostlocation());
                        //image.setImageURI(Uri.parse(upload.getImageurl()));

                        Uri url=Uri.parse(upload.getImageurl());

                        setImage(url,upload.getName());

                        thread.setText(upload.getPhoneno());
                        System.out.println("Name11 :"+ upload.getName() + ";");
                        setFields(upload);
                    }
                    //muploads.add(upload);
                }

                //mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

                //mRecyclerView.setAdapter(mAdapter);
                //mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(caseDetail.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });


    }
    public void updateLocation(View v)
    {
        Intent i=new Intent(this,updateLocation.class);
        String path="abbc";
        //path = image.getTag().toString();
        //System.out.println("path:::" + path);


        i.putExtra("image_url",path);
        String str=name.getText().toString();
        i.putExtra("name_txt",str);
        String str2=fathername.getText().toString();
        i.putExtra("name_father",str2);
        startActivity(i);
    }
    public void setFields(uploadData upload)
    {


        System.out.println("Name123 :"+ upload.getName() + ";");

    }

    public String getIncomingIntent()
    {
        String imageName1="Hamid";
        if(getIntent().hasExtra("name_txt") && getIntent().hasExtra("basicinfo_txt") )
        {
            imageName1=getIntent().getStringExtra("name_txt");
            String imageBio=getIntent().getStringExtra("basicinfo_txt");
            System.out.println("NAme click :" + imageName1);
            //setImage(imageint,imageName1);

        }
        else
        {
            //System.out.println("122222222");
            //System.out.println("122222233");System.out.println("122222244");
        }
        return imageName1;
    }
    private void setImage(Uri imageUrl,String imageName)
    {
        //name.setText(imageName);
        //Picasso.with(this).load(String.valueOf(imageUrl)).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(image);
        Picasso.with(this)
                .load(imageUrl)  // full uri retrieved from database
                .placeholder(R.mipmap.ic_launcher) //optional
                .error(R.mipmap.ic_launcher) //optional
                .into(image);

    }
}
