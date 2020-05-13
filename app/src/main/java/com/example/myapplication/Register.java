package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import de.hdodenhof.circleimageview.CircleImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText mFullName, mEmail, mPassword, mPassword2;
    CircleImageView Image;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    DatabaseReference mDatabase;
    DatabaseReference myRef;
    private FirebaseStorage firebaseStorage;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;
    String email,fullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mPassword2 = findViewById(R.id.password2);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        progressBar = findViewById(R.id.progressBar);


        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();

                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String password2 = mPassword2.getText().toString().trim();
                final String fullname = mFullName.getText().toString();
                final String job = "";
                final String dob = "";
                final String image = "";
                final String category = "";
                final String flightNo = "";



                if (TextUtils.isEmpty(fullname)) {
                    mFullName.setError("Please Enter your name.");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password2)) {
                    mPassword2.setError("Please re-type your password.");
                    return;
                }

                if ((password.length() < 6)) {
                    mPassword.setError("Password must be at least 6 characters long.");
                    return;
                }

                if (!password.equals(password2)) {
                    mPassword.setError("Passwords must match.");
                    return;

                }

               /* progressBar.setVisibility(View.VISIBLE);*/

                // register the user in firebase

                fAuth.createUserWithEmailAndPassword(email, password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getUid());
                            final String uid = fAuth.getCurrentUser().getUid();
                            User userProfile = new User(uid,email, fullname,job,dob,image,category,flightNo);
                            myRef.setValue(userProfile);
                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }


}
