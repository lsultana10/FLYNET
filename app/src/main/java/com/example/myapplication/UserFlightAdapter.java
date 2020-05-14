package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;

import com.google.firebase.storage.FirebaseStorage;


import java.util.ArrayList;


public class UserFlightAdapter extends RecyclerView.Adapter<UserFlightAdapter.MyFlightsHolder> {

    private Context context;
    private ArrayList<UserFlights> userflightList;
    private DatabaseReference myRef;
    public FirebaseStorage mStorage;
    public FirebaseAuth fAuth;
    public UserFlightAdapter userFlightAdapter;
    RecyclerView mRecyclerView;
    private static final String TAG = "TestActivity";


    public UserFlightAdapter(Context context, ArrayList<UserFlights> userflightList) {
        this.context = context;
        this.userflightList = userflightList;
    }

    @NonNull
    @Override
    public UserFlightAdapter.MyFlightsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_user_flight_adapter, viewGroup, false);
        return new UserFlightAdapter.MyFlightsHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final UserFlightAdapter.MyFlightsHolder holder, final int i) { //variable i is the position
        //get data
        final UserFlights currentItem = userflightList.get(i);
        fAuth = FirebaseAuth.getInstance();

        holder.mflightno.setText(currentItem.getFlightNo());
        holder.mdestination.setText(currentItem.getDestination());
        holder.mdeparture.setText(currentItem.getDeparture());
        holder.mdate.setText(currentItem.getDate());



        //button to remove flights
        holder.mRemoveFlights.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Flights").child(fAuth.getCurrentUser().getUid()).child("list");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            final String pushKey = child.getKey();
                            Log.d(TAG, "pushKey: " + pushKey);
                            reference.removeValue();


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });
    }


    @Override
    public int getItemCount() {

        return (userflightList == null) ? 0 : userflightList.size();


    }

    //View Holder class
    final class MyFlightsHolder extends RecyclerView.ViewHolder {

        final TextView mflightno,mdestination,mdate,mdeparture;
        final Button mRemoveFlights;


        public MyFlightsHolder(@NonNull View itemView) {
            super(itemView);

            mflightno = itemView.findViewById(R.id.flightNo);
            mdestination= itemView.findViewById(R.id.destination);
            mdeparture = itemView.findViewById(R.id.departure);
            mdate = itemView.findViewById(R.id.date);
            mRemoveFlights = itemView.findViewById(R.id.removeFlights);



        }

    }


}
