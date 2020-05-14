package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFlightFragment extends NavigationActivity {

    ArrayList<UserFlights> userFlightList;
    ArrayList<String> userflights;
    TextView mAddBtn;
    RecyclerView mRecyclerView;
    UserFlightAdapter adapterFlightUser;
    FirebaseAuth fAuth;
    DatabaseReference myRef;
    RecyclerView.LayoutManager mLayoutManager;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);
        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_user_flight_fragment, null, false);
        drawerLayout.addView(contentView, 0);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAddBtn = findViewById(R.id.addBtn);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fAuth = FirebaseAuth.getInstance();



        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FlightFragment.class));
            }
        });


        buildRecyclerView();
        getUserFlights();



    }

    private void getUserFlights(){

        myRef = FirebaseDatabase.getInstance().getReference().child("Flights").child(fAuth.getCurrentUser().getUid());

        myRef.child("list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userFlightList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserFlights user = ds.getValue(UserFlights.class);

                    userFlightList.add(user);


                    adapterFlightUser= new UserFlightAdapter(UserFlightFragment.this, userFlightList);
                    mRecyclerView.setAdapter(adapterFlightUser);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }





    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        adapterFlightUser = new UserFlightAdapter(this, userFlightList);
        mRecyclerView.setAdapter(adapterFlightUser);
    }
}
