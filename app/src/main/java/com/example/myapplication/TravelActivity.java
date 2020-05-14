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

import com.example.myapplication.NavigationActivity;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TravelActivity  extends NavigationActivity {

    CheckBox mluxury,madventure,mbeach,mroadtrip,mcountryside,mcity;

    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    DatabaseReference myRef;
    private DatabaseReference mUsersDatabase;
    private FirebaseStorage mStorage;
    private StorageReference storageRef;
    private String mCurrent_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_travel,null,false);
        drawerLayout.addView(contentView,0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        SharedPreferences prefs = getSharedPreferences("private preference", Context.MODE_PRIVATE);

        boolean isChecked = prefs.getBoolean("CheckboxData",false);


        mluxury = findViewById(R.id.luxury);
        madventure = findViewById(R.id.adventure);
        mbeach = findViewById(R.id.beach);
        mroadtrip = findViewById(R.id.roadtrip);
        mcountryside = findViewById(R.id.countryside);
        mcity = findViewById(R.id.city);
        mCurrent_user_id = fAuth.getCurrentUser().getUid();
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrent_user_id);


        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("luxury", false);
        mluxury.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("adventure", false);
        madventure.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("beach", false);
        mbeach.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("roadtrip", false);
        mroadtrip.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("countryside", false);
        mcountryside.setChecked(checked5);

        boolean checked6 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("city", false);
        mcity.setChecked(checked6);*/

    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?


        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.luxury:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("luxury", checked).apply();*/

                    Toast.makeText(TravelActivity.this, "You have selected Luxury.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Luxury");
                    user.setCategory("Luxury");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.adventure:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("adventure", checked).apply();*/
                    Toast.makeText(TravelActivity.this, "You have selected Adventure.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Adventure");
                    user.setCategory("Adventure");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.beach:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("beach", checked).apply();*/
                    Toast.makeText(TravelActivity.this, "You have selected Beach.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Beach");
                    user.setCategory("Beach");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.roadtrip:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("roadtrip", checked).apply();*/

                    Toast.makeText(TravelActivity.this, "You have selected Road Trip.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Road Trip");
                    user.setCategory("Road Trip");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.countryside:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("countryside", checked).apply();*/

                    Toast.makeText(TravelActivity.this, "You have selected Countryside.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Countryside");
                    user.setCategory("Countryside.");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.city:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("city", checked).apply();(*/

                    Toast.makeText(TravelActivity.this, "You have selected City.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("City");
                    user.setCategory("City");

                    break;
                }else{
                    mUsersDatabase.child("category").removeValue();
                    user.setCategory(null);
                }

        }

    }

}
