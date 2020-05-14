package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NavigationActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    CircleImageView image;
    private FirebaseStorage mStorage;
    private StorageReference storageRef;
    DatabaseReference mUsersDatabase;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Navigation Code
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(NavigationActivity.this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        mStorage = FirebaseStorage.getInstance();
        /*mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());*/
        storageRef = mStorage.getReferenceFromUrl("gs://flynet-25b07.appspot.com");//to get profile pic
        navigationView = findViewById(R.id.navigation_view);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                UserMenuSelected(menuItem);
                View headerView = navigationView.getHeaderView(0);
                final CircleImageView imageViewUser = headerView.findViewById(R.id.image);



                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void UserMenuSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                Intent aIntent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(aIntent1);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_interests:
                Intent aIntent2 = new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(aIntent2);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_flights:
                Intent aIntent3 = new Intent(getApplicationContext(), UserFlightFragment.class);
                startActivity(aIntent3);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_matches:
                Intent aIntent4 = new Intent(getApplicationContext(), MatchesFragment.class);
                startActivity(aIntent4);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_messages:
                Intent aIntent5 = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(aIntent5);
                drawerLayout.closeDrawers();
                break;
        }
    }
}




