package com.example.myapplication;

import androidx.annotation.NonNull;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateProfile extends NavigationActivity  {

    private EditText editTextName,editTextEmail,editTextDob,editTextJob;
    CircleImageView Image;
    ProgressBar progressBar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private FirebaseAuth fAuth;
    private Button btnUpdate;
    private FirebaseDatabase firebaseDatabase;
    private String mCurrent_user_id;
    DatabaseReference myRef;
    private DatabaseReference mUsersDatabase;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;
    private FirebaseStorage firebaseStorage;
    private FirebaseStorage mStorage;
    private StorageReference storageRef;
    public static final String TAG = "TAG";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null)
        {
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                Image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The code that directs the menu to this page
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);
        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_update_profile, null, false);
        drawerLayout.addView(contentView, 0);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        editTextName = findViewById(R.id.editTextFullname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextJob = findViewById(R.id.editTextJob);
        editTextDob = findViewById(R.id.editTextDob);
        Image = findViewById(R.id.profileImage);
        btnUpdate = findViewById(R.id.saveBtn);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mCurrent_user_id = fAuth.getCurrentUser().getUid();
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef =  FirebaseDatabase.getInstance().getReference();
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrent_user_id);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mStorage = FirebaseStorage.getInstance();
        storageReference = mStorage.getReference().child("ProfilePictures");;//to upload prof pic
        storageRef = mStorage.getReferenceFromUrl("gs://flynet-25b07.appspot.com");//to get profile pic

        editTextDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateProfile.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                editTextDob.setText(date);
            }
        };



        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"),PICK_IMAGE);
            }
        });


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                editTextEmail.setText(user.getEmail());
                editTextName.setText(user.getFullname());
                editTextJob.setText(user.getJob());
                editTextDob.setText(user.getDob());


                storageRef.child("ProfilePictures").child(mCurrent_user_id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).fit().centerCrop().into(Image);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(UpdateProfile.this, "", Toast.LENGTH_SHORT).show();
                    }
                });



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UpdateProfile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }
        });

        //button that saves updated user data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String uid = fAuth.getCurrentUser().getUid();
                String email = editTextEmail.getText().toString().trim();
                String fullname = editTextName.getText().toString().trim();
                String job = editTextJob.getText().toString().trim();
                String dob  =  editTextDob.getText().toString().trim();
                String category = user.getCategory();
                String image = "";
                String flightNo = user.getFlightNo();

                myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getUid());
                user.setJob(job);
                myRef.child("job").setValue(job);

                user.setDob(dob);
                myRef.child("dob").setValue(dob);

                final User userProfile = new User(uid,email, fullname, job, dob,image, category,flightNo);

                databaseReference.setValue(userProfile);
                final StorageReference imageReference = storageReference.child(fAuth.getUid());
                UploadTask uploadTask = imageReference.putFile(imagePath);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfile.this, "Failed to Upload!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UpdateProfile.this, "Updated succesfully!", Toast.LENGTH_SHORT).show();

                        imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                //You will get donwload URL in uri
                                Log.d(TAG, "Download URL = " + uri.toString());
                                //Adding that URL to Realtime database
                                myRef.child("image").setValue(uri.toString());

                            }
                        });


                        finish();


                    }
                });
            }
        });
    }
}





