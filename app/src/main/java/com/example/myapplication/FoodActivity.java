package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FoodActivity extends NavigationActivity{
    CheckBox mbreakfast,mdessert,mbaking,mgourmet,mfastfood,mvegeterian;
    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_food,null,false);
        drawerLayout.addView(contentView,0);


        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();



        mbreakfast = findViewById(R.id. breakfast);
        mdessert = findViewById(R.id.dessert);
        mbaking = findViewById(R.id.baking);
        mgourmet = findViewById(R.id.gourmet);
        mfastfood= findViewById(R.id.fastfood);
        mvegeterian = findViewById(R.id.vegeterian);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());

        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("breakfast", false);
        mbreakfast.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("baking", false);
        mbaking.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("dessert", false);
        mdessert.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("gourmet", false);
        mgourmet.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("fastfood", false);
        mfastfood.setChecked(checked5);

        boolean checked6 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("vegeterian", false);
        mvegeterian.setChecked(checked6);*/



    }

    //method to add user's choices
    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.breakfast:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("breakfast", checked).commit();*/

                    Toast.makeText(FoodActivity.this, "You have selected breakfast.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Breakfast");
                    user.setCategory("Breakfast");

                    break;

                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.dessert:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("dessert", checked).apply();*/

                    Toast.makeText(FoodActivity.this, "You have selected dessert.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Dessert");
                    user.setCategory("Dessert");



                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
                case R.id.baking:
                if (checked) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("baking", checked).apply();

                    Toast.makeText(FoodActivity.this, "You have selected baking.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("Category").setValue("Baking");
                    user.setCategory("Baking");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.gourmet:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("gourmet", checked).apply();*/

                    Toast.makeText(FoodActivity.this, "You have selected gourmet.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Gourmet");
                    user.setCategory("Gourmet");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.fastfood:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("fastfood", checked).apply();*/
                    Toast.makeText(FoodActivity.this, "You have selected fastfood.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Fastfood");
                    user.setCategory("FastFood");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.vegeterian:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("vegeterian", checked).apply();*/

                    Toast.makeText(FoodActivity.this, "You have selected vegeterian.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Vegeterian");
                    user.setCategory("Vegeterian");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
        }
    }
}
