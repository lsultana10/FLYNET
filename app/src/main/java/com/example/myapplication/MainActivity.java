package com.example.myapplication;

import androidx.annotation.NonNull;
import de.hdodenhof.circleimageview.CircleImageView;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;



public class MainActivity extends  NavigationActivity {
    TextView memail, mfullname,mflightno,mjob,mdob;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    CircleImageView Image;
    FirebaseAuth fAuth;
    Button updateBtn;
    ProgressBar progressBar;
    DatabaseReference UsersRef;
    public static final String TAG = "TAG";
    private DatabaseReference mUsersDatabase;
    private DatabaseReference mFlightDb;
    private FirebaseStorage mStorage;
    private StorageReference storageRef;
    private String mCurrent_user_id;
    private FirebaseDatabase firebaseDatabase;
    private StorageReference storageReference;
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //The code that directs the menu to this page
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);
        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_main, null, false);
        drawerLayout.addView(contentView, 0);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();


        mfullname = findViewById(R.id.profileFullname);
        memail = findViewById(R.id.profileEmail);
        mjob = findViewById(R.id.profileJob);
        mdob = (TextView) findViewById(R.id.profileDate);
        mflightno = findViewById(R.id.profileFlightno);
        Image = findViewById(R.id.profileImage);
        updateBtn = findViewById(R.id.btnUpdate);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();
        storageReference = mStorage.getReference();//to upload prof pic
        mCurrent_user_id = fAuth.getCurrentUser().getUid();
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrent_user_id);
        mFlightDb = FirebaseDatabase.getInstance().getReference().child("Flights").child(mCurrent_user_id);
        storageRef = mStorage.getReferenceFromUrl("gs://flynet-25b07.appspot.com");//to get profile pic
        UsersRef = FirebaseDatabase.getInstance().getReference();

        //method to display user data
        mUsersDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = new User();
                String email = dataSnapshot.child("email").getValue().toString();
                String fullname = dataSnapshot.child("fullname").getValue().toString();
                String job = dataSnapshot.child("job").getValue().toString();
                String dob = dataSnapshot.child("dob").getValue().toString();


                storageRef.child("ProfilePictures").child(mCurrent_user_id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(Image);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                    }
                });


                memail.setText(email);
                mfullname.setText(fullname);
                mdob.setText(dob);
                mjob.setText(job);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });



        //button that leads to updating user profile
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UpdateProfile.class));
            }


        });
    }

    //method do delete user
    public void deleteUser(View view) {
        // [START delete_user]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mUsersDatabase.removeValue();
        mFlightDb.removeValue();

        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                        } else {
                            Log.d(TAG, "Error");
                        }
                    }
                });
        // [END delete_user]
    }

    //method to logout
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();//logout
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }
    }



