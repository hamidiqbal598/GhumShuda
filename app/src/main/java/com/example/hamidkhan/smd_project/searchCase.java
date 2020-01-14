package com.example.hamidkhan.smd_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class searchCase extends AppCompatActivity {


    private Button search;
    private EditText nameSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_case);
        search=(Button) findViewById(R.id.search);
        nameSearch=(EditText) findViewById(R.id.nameSearch);

        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Searching((Button) view);
            }

        });
    }
    public void Searching(View view)
    {
        Intent i=new Intent(this,caseDetail.class);
        String str=nameSearch.getText().toString();
        i.putExtra("name_txt",str);
        i.putExtra("basicinfo_txt",str);
        startActivity(i);
    }
}
