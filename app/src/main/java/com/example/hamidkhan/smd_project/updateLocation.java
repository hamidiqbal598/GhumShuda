package com.example.hamidkhan.smd_project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class updateLocation extends AppCompatActivity {

    private Button savebut;
    private EditText thread;
    private ImageView imageView;
    private TextView name;
    private TextView fathername;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);
        savebut=(Button) findViewById(R.id.savebutton);
        thread=(EditText)findViewById(R.id.thread);
        name=(TextView)findViewById(R.id.name);
        fathername=(TextView) findViewById(R.id.fatherName);
        imageView=(ImageView) findViewById(R.id.image);
        savebut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                saving((Button) view);
            }

        });

        final String names=getIncomingIntent().toString();

    }

    public void saving(View v)
    {
        Intent i=new Intent(this,Navigation.class);
        String str=thread.getText().toString();
        i.putExtra("thread",str);

        startActivity(i);
    }

    public String getIncomingIntent()
    {
        String imageName1="Hamid";
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("name_txt") && getIntent().hasExtra("name_father") )
        {
            String name1=getIntent().getStringExtra("image_url");
            String name2=getIntent().getStringExtra("name_txt");
            String name3=getIntent().getStringExtra("name_father");
            System.out.println("Name :" + name1 + "::;" + name2 +"?");
            Uri uri=Uri.parse(name1);
            setImage(uri,name2,name3);

        }
        else
        {
        }
        return imageName1;
    }

    private void setImage(Uri imageUrl, String Name, String Fname)
    {
        name.setText(Name);
        fathername.setText(Fname);

        //int foo = Integer.parseInt(imageUrl);
        //Picasso.with(this).load(String.valueOf(imageUrl)).placeholder(R.mipmap.ic_launcher).fit().centerCrop().into(image);
        Picasso.with(this)
                .load(imageUrl)  // full uri retrieved from database
                .placeholder(R.mipmap.ic_launcher) //optional
                .error(R.mipmap.ic_launcher) //optional
                .into(imageView);

    }
}
