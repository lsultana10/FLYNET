package com.example.myapplication;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SportActivity extends NavigationActivity {
    CheckBox mfootball, mbasketball, mvolleyball, mrugby, mtennis, mswimming, mgymnastics, mhockey, marchery;
    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    DatabaseReference catRef;
    private String mCurrent_user_id;
    User user;

    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_sport, null, false);
        drawerLayout.addView(contentView, 0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        user = new User();

        mfootball = findViewById(R.id.football);
        mbasketball = findViewById(R.id.basketball);
        mvolleyball = findViewById(R.id.volleyball);
        mrugby = findViewById(R.id.rugby);
        mtennis = findViewById(R.id.tennis);
        mswimming = findViewById(R.id.swimming);
        mgymnastics = findViewById(R.id.gymnastics);
        mhockey = findViewById(R.id.hockey);
        marchery = findViewById(R.id.archery);


        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("football", false);
        mfootball.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("basketball", false);
        mbasketball.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("volleyball", false);
        mvolleyball.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("rugby", false);
        mrugby.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("tennis", false);
        mtennis.setChecked(checked5);

        boolean checked6 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("swimming", false);
        mswimming.setChecked(checked6);

        boolean checked7 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("gymnastics", false);
        mgymnastics.setChecked(checked7);

        boolean checked8 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("hockey", false);
        mhockey.setChecked(checked8);

        boolean checked9 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("archery", false);
        marchery.setChecked(checked9);*/


        mCurrent_user_id = fAuth.getCurrentUser().getUid();
        catRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrent_user_id);




    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.mfootball:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("breakfast", checked).commit();*/

                    Toast.makeText(SportActivity.this, "You have selected Football.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Football");
                    user.setCategory("Football");

                    break;

                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mbasketball:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("dessert", checked).apply();*/

                    Toast.makeText(SportActivity.this, "You have selected Basketball.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Basketball");
                    user.setCategory("Basketball");



                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mvolleyball:
                if (checked) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("baking", checked).apply();

                    Toast.makeText(SportActivity.this, "You have selected Volleyball.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Volleyball");
                    user.setCategory("Volleyball");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.mrugby:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("gourmet", checked).apply();*/

                    Toast.makeText(SportActivity.this, "You have selected Rugby.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Rugby");
                    user.setCategory("Rugby");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mtennis:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("fastfood", checked).apply();*/
                    Toast.makeText(SportActivity.this, "You have selected Tennis.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Tennis");
                    user.setCategory("Tennis");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mswimming:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("vegeterian", checked).apply();*/

                    Toast.makeText(SportActivity.this, "You have selected Swimming.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Swimming");
                    user.setCategory("Swimming");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mgymnastics:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("vegeterian", checked).apply();*/

                    Toast.makeText(SportActivity.this, "You have selected Gymnastics.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Gymnastics");
                    user.setCategory("Gymnastics");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mhockey:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("vegeterian", checked).apply();*/

                    Toast.makeText(SportActivity.this, "You have selected Hockey", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Hockey");
                    user.setCategory("Hockey");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.marchery:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("vegeterian", checked).apply();*/

                    Toast.makeText(SportActivity.this, "You have selected Archery.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Archery");
                    user.setCategory("Archery");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
        }
    }
}






