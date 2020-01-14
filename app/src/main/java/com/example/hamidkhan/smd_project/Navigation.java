package com.example.hamidkhan.smd_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Navigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerVier;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DatabaseReference dataref;
    private List<uploadData> muploads;
    private ImageView image;
    Adapter.ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);


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
                    System.out.println("Name :"+ upload.getName() + ";");
                    muploads.add(upload);
                }
                System.out.println("Size: "+ muploads.size());
                //ImageView imageView = null;
                for(int i=0;i<muploads.size();i++)
                {
                    String url=muploads.get(i).getImageurl();

                    int imageint=0;

                    //imageint=Integer.parseInt(url);
                    Uri uri=Uri.parse(url);

                    System.out.println("pic url:" + uri);
                    Picasso.with(Navigation.this).load(uri).placeholder(R.mipmap.ic_launcher).fit().centerCrop();
                    //image=(ImageView) findViewById(R.id.imagePerson);

                    exampleList.add(new listItemForCases(uri,muploads.get(i).getName(),"Age: " + muploads.get(i).getAge() + ", Height: " + muploads.get(i).getHeight() + ", Color: " + muploads.get(i).getColor()));
                }
                mRecyclerVier=findViewById(R.id.casesList);

                mRecyclerVier.setHasFixedSize(true);
                mLayoutManager=new LinearLayoutManager(Navigation.this);
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
                Toast.makeText(Navigation.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                //mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_addcase) {
            // Handle the camera action
            Intent i=new Intent(this,caseAddition.class);
            startActivity(i);
        } else if (id == R.id.nav_searchcase) {
            Intent i=new Intent(this,searchCase.class);
            startActivity(i);

        } else if (id == R.id.nav_manage) {
            //Intent i=new Intent(this,MainActivity.class);
            //startActivity(i);

        } else if (id == R.id.nav_logOut) {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
