
package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> implements Filterable {
    List<Flight> flightList;
    List<Flight> filterList;
    DatabaseReference myRef,userRef;
    FirebaseAuth fAuth;



    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        public TextView mFlightNo;
        public TextView mDestination;
        public TextView mDeparture;
        public TextView mDate;
        public Button mAddFlights;


        public FlightViewHolder(View itemView) {
            super(itemView);
            mFlightNo = itemView.findViewById(R.id.flightNo);
            mAddFlights = itemView.findViewById(R.id.addFlights);
            mDestination = itemView.findViewById(R.id.destination);
            mDeparture = itemView.findViewById(R.id.departure);
            mDate = itemView.findViewById(R.id.date);


        }
    }

    FlightAdapter(List<Flight> flightList) {

        this.flightList = flightList;
        this.filterList = new ArrayList<>(flightList);


    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_flight,
                parent, false);
        FlightViewHolder fvh = new FlightViewHolder(v);
        return fvh;
    }

    @Override
    public void onBindViewHolder(final FlightViewHolder holder, int position) {
        final Flight currentItem = flightList.get(position);
        fAuth = FirebaseAuth.getInstance();

        holder.mFlightNo.setText(currentItem.getFlightNo());
        holder.mDestination.setText(currentItem.getDestination());
        holder.mDeparture.setText(currentItem.getDeparture());
        holder.mDate.setText(currentItem.getDate());


        //method for adding flights
        holder.mAddFlights.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String userID = fAuth.getCurrentUser().getUid();
                String flightNo = holder.mFlightNo.getText().toString().trim();
                String destination = holder.mDestination.getText().toString().trim();
                String departure = holder.mDeparture.getText().toString().trim();
                String date=  holder.mDate.getText().toString().trim();

                userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());
                myRef = FirebaseDatabase.getInstance().getReference().child("Flights").child(fAuth.getCurrentUser().getUid());


                String key = myRef.child("list").push().getKey();
                String position = key;
                UserFlights flt = new UserFlights(userID,flightNo,destination, departure, date,position);
                myRef.child("list").child(key).setValue(flt);
                User user = new User();
                user.setFlightNo(flt.getFlightNo());
                userRef.child("flightNo").setValue(flt.getFlightNo());


            }
        });


    }

    @Override
    public int getItemCount() {

        return (flightList == null) ? 0 : flightList.size();
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    //filtering flights by flightno, destination, departure and date
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Flight> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Flight item : filterList) {
                    if (item.getFlightNo().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getDestination().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getDate().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getDeparture().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            flightList.clear();
            flightList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}



