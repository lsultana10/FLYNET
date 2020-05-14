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

public class HistoryActivity extends NavigationActivity {

    CheckBox mpolitical,msocial,meconomic,mlegal,mdiplomatic,mmilitary,mintellectual,muniversal;

    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_history,null,false);
        drawerLayout.addView(contentView,0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

      ;

        mpolitical = findViewById(R.id.political);
        msocial = findViewById(R.id.social);
        meconomic = findViewById(R.id.economic);
        mlegal = findViewById(R.id.legal);
        mdiplomatic = findViewById(R.id.diplomatic);
        mmilitary = findViewById(R.id.military);
        mintellectual = findViewById(R.id.intellectual);
        muniversal = findViewById(R.id.universal);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());

        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("political", false);
        mpolitical.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("social", false);
        msocial.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("economic", false);
        meconomic.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("legal", false);
        mlegal.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("diplomatic", false);
        mdiplomatic.setChecked(checked5);

        boolean checked6 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("military", false);
        mmilitary.setChecked(checked6);

        boolean checked7 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("intellectual", false);
        mintellectual.setChecked(checked7);

        boolean checked8 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("universal", false);
        muniversal.setChecked(checked8);*/

    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.political:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("political", checked).apply();*/

                    Toast.makeText(HistoryActivity.this, "You have selected political.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Political");
                    user.setCategory("Political");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.social:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("social", checked).apply();*/
                    Toast.makeText(HistoryActivity.this, "You have selected social.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Social");
                    user.setCategory("Social");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

        case R.id.economic:
        if (checked) {
            /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putBoolean("economic", checked).apply();*/

            Toast.makeText(HistoryActivity.this,"You have selected Economic History.",Toast.LENGTH_LONG).show();
            mUsersDatabase .child("category").setValue("Economic History");
            user.setCategory("Economic History");

            break;
        }else{
            mUsersDatabase .child("category").removeValue();
            user.setCategory(null);
        }
            case R.id.legal:
                if (checked) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("legal", checked).apply();

                    Toast.makeText(HistoryActivity.this,"You have selected legal.",Toast.LENGTH_LONG).show();
                    mUsersDatabase .child("category").setValue("Legal");
                    user.setCategory("Legal");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.diplomatic:
                if (checked) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("diplomatic", checked).apply();

                    Toast.makeText(HistoryActivity.this,"You have selected Diplomatic.",Toast.LENGTH_LONG).show();
                    mUsersDatabase .child("category").setValue("Diplomatic");
                    user.setCategory("Diplomatic");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.military:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("military", checked).apply();*/

                    Toast.makeText(HistoryActivity.this,"You have selected Military.",Toast.LENGTH_LONG).show();
                    mUsersDatabase .child("category").setValue("Military");
                    user.setCategory("Military");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.intellectual:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("intellectual", checked).commit();*/

                    Toast.makeText(HistoryActivity.this,"You have selected intellectual.",Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Intellectual History");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
            case R.id.universal:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("universal", checked).apply();*/
                    Toast.makeText(HistoryActivity.this,"You have selected universal.",Toast.LENGTH_LONG).show();
                    mUsersDatabase .child("history").child("Universal History");
                    user.setCategory("Universal History");

                    break;
                }
                else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

        }
    }
}
