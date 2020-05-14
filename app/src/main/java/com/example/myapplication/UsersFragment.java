package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;


public class UsersFragment extends NavigationActivity {


    UsersAdapter userAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<User> userList;
    FirebaseAuth fAuth;
    String userId;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The code that directs the menu to this page
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);
        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_fragment__users, null, false);
        drawerLayout.addView(contentView, 0);


        fAuth = FirebaseAuth.getInstance();
        userList = new ArrayList<User>();
        buildRecyclerView();
        getUsers();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchflights, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        userAdapter = new UsersAdapter(userList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(userAdapter);
    }

    //retrieve users
    private void getUsers() {

        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = FirebaseDatabase.getInstance().getReference().child("Users");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                userList = new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    User user = ds.getValue(User.class);

                        if (!fUser.getEmail().equals(user.getEmail())) {

                            userList.add(user);


                            userAdapter = new UsersAdapter(userList);
                            mRecyclerView.setAdapter(userAdapter);
                            userAdapter.notifyDataSetChanged();
                        }
                    }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



}











