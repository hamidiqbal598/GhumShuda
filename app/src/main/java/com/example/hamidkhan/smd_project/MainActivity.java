package com.example.hamidkhan.smd_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    protected EditText et1;
    protected EditText et2;
    protected Button btn1;
    protected Button btn2;
    public FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.loginCNICNum);
        et2= (EditText) findViewById(R.id.loginPassword);
        btn1= (Button) findViewById(R.id.loginButton);
        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                SIGNIN((Button) view);
            }

        });
        btn2= (Button) findViewById(R.id.signUp);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                SIGNUP((Button) view);
            }

        });

        auth=FirebaseAuth.getInstance();
    }

    public void SIGNUP(View view)
    {
        Intent i=new Intent(this,registrationpage.class);
        startActivity(i);
    }

    public void SIGNIN(View view)
    {
        //Login
        if(et1.getText().toString() .equals("") && et1.getText().toString() .equals(""))
        {
            Toast.makeText(getApplicationContext(),"Blank not allowed",Toast.LENGTH_SHORT).show();
        }
        else
        {
            String email=et1.getText().toString();
            String password=et2.getText().toString();
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this,new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "User entered successfully", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(MainActivity.this,Navigation.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "User could not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });


        }
    }
}
