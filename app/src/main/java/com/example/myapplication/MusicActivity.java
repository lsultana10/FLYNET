package com.example.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MusicActivity extends NavigationActivity {
    CheckBox mcountry,mhiphop,mjazz,mrock,mclassical,mpop;

    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_music,null,false);
        drawerLayout.addView(contentView,0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mcountry = findViewById(R.id.country);
        mhiphop = findViewById(R.id.hiphop);
        mjazz = findViewById(R.id.jazz);
        mrock= findViewById(R.id.rock);
        mclassical = findViewById(R.id.classical);
        mpop = findViewById(R.id.pop);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        /*SharedPreferences prefs = getSharedPreferences("private preference", Context.MODE_PRIVATE);*/

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.country:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("country", checked).apply();*/

                    Toast.makeText(MusicActivity.this, "You have selected Country Music.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Country Music");
                    user.setCategory("Country Music");

                    break;

                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.hiphop:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("hiphop", checked).apply();*/
                    Toast.makeText(MusicActivity.this, "You have selected HipHop Music.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("HipHop Music");
                    user.setCategory("HipHop Music");

                    break;
                }else{
                    mUsersDatabase.child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.jazz:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("jazz", checked).apply();*/
                    Toast.makeText(MusicActivity.this, "You have selected Jazz Music.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Jazz Music");
                    user.setCategory("Jazz Music");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.rock:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("rock", checked).apply();*/
                    Toast.makeText(MusicActivity.this, "You have selected Rock Music.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Rock Music");
                    user.setCategory("Rock Music");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.classical:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("classical", checked).apply();*/

                    Toast.makeText(MusicActivity.this, "You have selected Classical Music.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Classical Music");
                    user.setCategory("Classical Music");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.pop:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("pop", checked).apply();*/
                    Toast.makeText(MusicActivity.this, "You have selected Pop Music.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Pop Music");
                    user.setCategory("Pop Music");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

        }
    }


}
