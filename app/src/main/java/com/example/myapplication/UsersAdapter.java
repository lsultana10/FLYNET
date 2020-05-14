package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyHolder> implements Filterable {

    ArrayList<User> userList;
    ArrayList<User> filterList;
    DatabaseReference matchesDatabase,userRef;
    FirebaseAuth fAuth;




    public static class MyHolder extends RecyclerView.ViewHolder {

        TextView memail, mfullname,mjob,mdob,mflightno,mcategory;
        CircleImageView Image;
        Button mAddUsers;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mfullname = itemView.findViewById(R.id.profileFullname);
            memail = itemView.findViewById(R.id.profileEmail);
            mjob = itemView.findViewById(R.id.profileJob);
            mdob = itemView.findViewById(R.id.profileDob);
            mflightno = itemView.findViewById(R.id.profileFlightno);
            mcategory= itemView.findViewById(R.id.profileCategory);
            Image = itemView.findViewById(R.id.profileImage);
            mAddUsers = itemView.findViewById(R.id.addUser);

        }

    }

    UsersAdapter(ArrayList<User> userList) {
        this.userList = userList;
        this.filterList = new ArrayList<>(userList);

    }


    @NonNull
    @Override
    public UsersAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_adapter,
                parent, false);
        UsersAdapter.MyHolder uvh = new UsersAdapter.MyHolder(v);
        return uvh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int position) { //variable i is the position
        //get data
        final User currentItem = userList.get(position);
        fAuth = FirebaseAuth.getInstance();

        myHolder.mfullname.setText(currentItem.getFullname());
        myHolder.memail.setText(currentItem.getEmail());
        myHolder.mjob.setText(currentItem.getJob());
        myHolder.mdob.setText(currentItem.getDob());
        myHolder.mflightno.setText(currentItem.getFlightNo());
        myHolder.mcategory.setText(currentItem.getCategory());




        String image = userList.get(position).getImage();


        try {
            Picasso.get().load(image).fit().placeholder(R.drawable.ic_launcher_background).into(myHolder.Image);
        } catch (Exception e) {
        }

        myHolder.mAddUsers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String uid = fAuth.getCurrentUser().getUid();
                String fullname = myHolder.mfullname.getText().toString().trim();
                String email = myHolder.memail.getText().toString().trim();
                String job = myHolder.mjob.getText().toString().trim();
                String dob = myHolder.mdob.getText().toString().trim();
                String image = userList.get(position).getImage();
                String category = myHolder.mcategory.getText().toString().trim();
                String flightNo = myHolder.mflightno.getText().toString().trim();

                matchesDatabase = FirebaseDatabase.getInstance().getReference().child("Matches").child(fAuth.getCurrentUser().getUid());
                Matches match = new Matches(uid, email, fullname,job, dob, image, category, flightNo);

                String key = matchesDatabase.child("list").push().getKey();

                matchesDatabase.child("list").child(key).setValue(match);
                userRef= FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());
                userRef.child("flightNo").setValue(match.getFlightNo());


            }
        });


    }


    @Override
    public int getItemCount() {

        return (userList == null) ? 0 : userList.size();
    }


    @Override
    public Filter getFilter() {

        return filter;

    }


    //filtering users by name, email, dob, job, flightno and category
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<User> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(filterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (User item : filterList) {
                    if (item.getFullname().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getEmail().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getDob().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getJob().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getFlightNo().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                    if (item.getCategory().toLowerCase().contains(filterPattern)) {
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
            userList.clear();
            userList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}

















