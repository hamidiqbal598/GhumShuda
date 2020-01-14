package com.example.hamidkhan.smd_project;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.MimeTypeFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class caseAddition extends AppCompatActivity {

    protected EditText name;
    protected EditText cnic;
    protected EditText fathername;
    protected EditText phonenumer;
    protected EditText lostlocation;
    protected EditText lostdate;
    protected EditText age;
    protected EditText height;
    protected EditText color;
    protected Button chooseimage;
    protected Button uploadimage;
    protected Button save;
    protected ImageView image;

    private Uri imageuri;
    private static final int PICK_IMAGE_REQUEST=1;

    private StorageReference storageRef;
    private DatabaseReference databaseRef;

    private StorageTask mUploadTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_addition);

        name=(EditText) findViewById(R.id.Name);
        cnic=(EditText) findViewById(R.id.CNIC);
        fathername=(EditText) findViewById(R.id.fatherName);
        phonenumer=(EditText) findViewById(R.id.phoneno);
        lostlocation=(EditText) findViewById(R.id.lostlocation);
        lostdate=(EditText) findViewById(R.id.lostdate);
        age=(EditText) findViewById(R.id.age);
        height=(EditText) findViewById(R.id.height);
        color=(EditText) findViewById(R.id.color);
        image=(ImageView) findViewById(R.id.image);
        chooseimage=(Button) findViewById(R.id.choose);
        chooseimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                chooseImage((Button) view);
            }

        });
        save=(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                saveContinue((Button) view);
            }

        });
        uploadimage=(Button) findViewById(R.id.upload);
        uploadimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(mUploadTask!=null &&  mUploadTask.isInProgress())
                {
                    Toast.makeText(caseAddition.this,"Upload is in Progress",Toast.LENGTH_LONG).show();
                }
                else
                {
                    uploadImage((Button) view);
                }
            }

        });

        storageRef= FirebaseStorage.getInstance().getReference("uploads");
        databaseRef= FirebaseDatabase.getInstance().getReference("uploads");
    }
    public void chooseImage(View view)
    {
        Intent i =new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestcode,int resultcode,Intent data)
    {
        super.onActivityResult(requestcode,resultcode,data);
        if(requestcode==PICK_IMAGE_REQUEST && resultcode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageuri=data.getData();
            Picasso.with(this).load(imageuri).into(image);
        }
    }

    private String getFileExtension(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    public void uploadImage(View view)
    {
        if(imageuri!=null)
        {
            StorageReference fileRef=storageRef.child(System.currentTimeMillis()+"."+getFileExtension(imageuri));
            mUploadTask = fileRef.putFile(imageuri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                }
                            }, 500);

                            Toast.makeText(caseAddition.this, "Upload successful", Toast.LENGTH_LONG).show();
                            uploadData upload = new uploadData(name.getText().toString().trim(),cnic.getText().toString().trim(),fathername.getText().toString().trim(),
                                    phonenumer.getText().toString().trim(),lostlocation.getText().toString().trim(),lostdate.getText().toString().trim(),
                                    age.getText().toString().trim(),height.getText().toString().trim(),color.getText().toString().trim(),
                                    taskSnapshot.getDownloadUrl().toString());
                            String uploadId = databaseRef.push().getKey();
                            databaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caseAddition.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            //mProgressBar.setProgress((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(this,"No file Selected",Toast.LENGTH_SHORT).show();
        }
    }

    public void saveContinue(View view)
    {
        Intent i=new Intent(this,Navigation.class);
        startActivity(i);
    }
}
