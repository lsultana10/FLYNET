package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AnimalActivity extends NavigationActivity {

    CheckBox mdogs, mcats, mbirds, mreptiles, mfish, msafari, marctic, mfarm;

    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;
    private String mCurrent_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_animal, null, false);
        drawerLayout.addView(contentView, 0);


        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mdogs = findViewById(R.id.dogs);
        mcats = findViewById(R.id.cats);
        mbirds = findViewById(R.id.birds);
        mreptiles = findViewById(R.id.reptiles);
        mfish = findViewById(R.id.fish);
        msafari = findViewById(R.id.safari);
        marctic = findViewById(R.id.arctic);
        mfarm = findViewById(R.id.farm);

        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("dogs", false);
        mdogs.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("cats", false);
        mcats.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("birds", false);
        mbirds.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("reptiles", false);
        mreptiles.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("fish", false);
        mfish.setChecked(checked5);

        boolean checked6 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("safari", false);
        msafari.setChecked(checked6);

        boolean checked7 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("arctic", false);
        marctic.setChecked(checked7);

        boolean checked8 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("farm", false);
        mfarm.setChecked(checked8);*/


        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());


    }

    //Method for all checkboxes
    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        switch (view.getId()) {
            case R.id.dogs:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("dogs", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Dogs.", Toast.LENGTH_LONG).show();

                    user.setCategory("Dogs");
                    mUsersDatabase.child("category").setValue("Dogs");


                    break;
                }else{
                    mUsersDatabase.child("category").removeValue();
                    user.setCategory(null);


                }
            case R.id.cats:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("cats", checked).apply();*/

                    Toast.makeText(AnimalActivity.this, "You have selected Cats.", Toast.LENGTH_LONG).show();

                    mUsersDatabase.child("category").setValue("Cats");
                    user.setCategory("Cats");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.birds:
                if (checked) {
                   /* PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("birds", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Birds.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Birds");
                    user.setCategory("Birds");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);

                }

            case R.id.reptiles:
                if (checked) {
                   /* PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("reptiles", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Reptiles.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Reptiles");
                    user.setCategory("Reptiles");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.fish:
                if (checked) {
                   /* PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("fish", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Fish.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Fish");
                    user.setCategory("Fish");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);

                }


            case R.id.safari:
                if (checked) {
                   /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("safari", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Safari.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Safari");
                    user.setCategory("Safari");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);

                }

            case R.id.arctic:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("arctic", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Arctic.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("categories").setValue("Arctic");
                    user.setCategory("Arctic");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);

                }

            case R.id.farm:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("farm", checked).apply();*/
                    Toast.makeText(AnimalActivity.this, "You have selected Farm.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Farm");
                    user.setCategory("Farm");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);

                }
        }
    }
}
