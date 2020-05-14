package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MyHolder> {

    private Context context;
    private List<Matches> matchList;

    public FirebaseAuth fAuth;
    private static final String TAG = "TestActivity";


    public MatchesAdapter(Context context, List<Matches> matchList) {
        this.context = context;
        this.matchList = matchList;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_matches_adapter, viewGroup, false);
        return new MyHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) { //variable i is the position
        //get data
        Matches match = matchList.get(i);
        String email = matchList.get(i).getEmail();
        String fullname = matchList.get(i).getFullname();
        String job = matchList.get(i).getJob();
        String dob = matchList.get(i).getDob();
        String flightno = matchList.get(i).getFlightNo();
        String category = matchList.get(i).getCategory();
        String image = matchList.get(i).getImage();


        myHolder.mfullname.setText(match.getFullname());
        myHolder.memail.setText(match.getEmail());
        myHolder.mcategory.setText(match.getCategory());
        myHolder.mflightno.setText(match.getFlightNo());
        myHolder.mjob.setText(match.getJob());
        myHolder.mdob.setText(match.getDob());

        try {
            Picasso.get().load(image).fit().placeholder(R.drawable.ic_launcher_background).into(myHolder.Image);
        } catch (Exception e) {
        }

        myHolder.mMessageUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ieventreport = new Intent(context,Main2Activity.class);
                context.startActivity(ieventreport);
            }
        });



        //button to remove flights
        myHolder.mRemoveUsers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Matches").child(fAuth.getCurrentUser().getUid()).child("list");

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

        return (matchList == null) ? 0 : matchList.size();
    }

    //View Holder class
    final class MyHolder extends RecyclerView.ViewHolder {

        TextView memail, mfullname, mflightno,mjob, mdob, mcategory;
        Button mRemoveUsers, mMessageUsers;
        final CircleImageView Image;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mfullname = itemView.findViewById(R.id.profileFullname);
            memail = itemView.findViewById(R.id.profileEmail);
            mjob = itemView.findViewById(R.id.profileJob);
            mdob = itemView.findViewById(R.id.profileDob);
            mflightno = itemView.findViewById(R.id.profileFlightno);
            mcategory = itemView.findViewById(R.id.profileCategory);
            Image = itemView.findViewById(R.id.profileImage);
            mRemoveUsers= itemView.findViewById(R.id.removeUser);
            mMessageUsers= itemView.findViewById(R.id.messageUser);


        }


    }
}











