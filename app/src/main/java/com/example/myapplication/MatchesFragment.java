
package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends NavigationActivity {

    RecyclerView recyclerView;
    MatchesAdapter matchesAdapter;
    private List<Matches> matchList;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String userId;
    DatabaseReference myRef;
    TextView AddBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The code that directs the menu to this page
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);
        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_matches_fragment, null, false);
        drawerLayout.addView(contentView, 0);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        matchList = new ArrayList<Matches>();
        AddBtn = findViewById(R.id.addBtn);


        matchesAdapter = new MatchesAdapter(MatchesFragment.this, matchList);
        matchList = new ArrayList<Matches>();

        recyclerView.setAdapter(matchesAdapter);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UsersFragment.class));
            }
        });


        getMatches();



    }


   //method to retrieve matches added by user
    private void getMatches() {

        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Matches").child(fAuth.getCurrentUser().getUid());

        matchList = new ArrayList<Matches>();

        myRef.child("list").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                matchList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Matches match = snapshot.getValue(Matches.class);



                        matchList.add(match);

                }
                matchesAdapter = new MatchesAdapter(MatchesFragment.this, matchList);
                recyclerView.setAdapter(matchesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}







