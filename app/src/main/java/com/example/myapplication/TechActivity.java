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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TechActivity extends NavigationActivity {
    CheckBox martificialintelligence,mgaming,mbackend,mmobileapps,mdatabases,mbigdata;

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
        View contentView = inflater.inflate(R.layout.activity_tech,null,false);
        drawerLayout.addView(contentView,0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        martificialintelligence = findViewById(R.id.artificialintelligence);
        mgaming = findViewById(R.id.gaming);
        mbackend = findViewById(R.id.backend);
        mmobileapps = findViewById(R.id.mobileapps);
        mdatabases = findViewById(R.id.databases);
        mbigdata = findViewById(R.id.bigdata);
        mCurrent_user_id = fAuth.getCurrentUser().getUid();
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrent_user_id);

        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("artificalintelligence", false);
        martificialintelligence.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("gaming", false);
        mgaming.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("backend", false);
        mbackend.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("mobileapps", false);
        mmobileapps.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("databases", false);
        mdatabases.setChecked(checked5);

        boolean checked6 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("bigdata", false);
        mbigdata.setChecked(checked6);*/


    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.artificialintelligence:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("artificalintelligence", checked).apply();*/

                    Toast.makeText(TechActivity.this, "You have selected AI.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Artificial Intelligence");
                    user.setCategory("Artificial Intelligence");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.gaming:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("gaming", checked).apply();*/

                    Toast.makeText(TechActivity.this, "You have selected Gaming.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Gaming");
                    user.setCategory("Gaming");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.backend:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("backend", checked).apply();*/
                    Toast.makeText(TechActivity.this, "You have selected Backend.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Backend");
                    user.setCategory("Backend");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.mobileapps:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("mobileapps", checked).apply();*/

                    Toast.makeText(TechActivity.this, "You have selected mobile apps.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("tech").child("4").setValue("Mobile Applications");
                    user.setCategory("Mobile Applications");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.databases:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("databases", checked).apply();*/

                    Toast.makeText(TechActivity.this, "You have selected Databases.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Databases");
                    user.setCategory("Databases");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.bigdata:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("bigdata", checked).apply();*/
                    Toast.makeText(TechActivity.this, "You have selected Big Data.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Big Data");
                    user.setCategory("Big Data");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

        }

        }
}
