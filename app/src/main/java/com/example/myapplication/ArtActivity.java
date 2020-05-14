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

public class ArtActivity extends NavigationActivity {

    CheckBox mpainting,msculpture,mpoetry,mgraphicdesign,mcalligraphy;
    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_art, null, false);
        drawerLayout.addView(contentView, 0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mpainting = findViewById(R.id.painting);
        msculpture = findViewById(R.id.sculpture);
        mpoetry = findViewById(R.id.poetry);
        mgraphicdesign = findViewById(R.id.graphicdesign);
        mcalligraphy = findViewById(R.id.calligraphy);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());


       /* boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("painting", false);
        mpainting.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("scuplture", false);
        msculpture.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("poetry", false);
        mpoetry.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("graphicdesign", false);
        mgraphicdesign.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("calligraphy", false);
        mcalligraphy.setChecked(checked5);*/


    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.painting:
                if (checked) {
                   /* PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("painting", checked).apply();*/

                    Toast.makeText(ArtActivity.this, "You have selected Painting.", Toast.LENGTH_LONG).show();

                    user.setCategory("Painting");
                    mUsersDatabase.child("category").setValue("Painting");


                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.sculpture:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("sculpture", checked).apply();*/

                    Toast.makeText(ArtActivity.this, "You have selected Sculpture.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Sculpture");
                    user.setCategory("Sculpture");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.poetry:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("poetry", checked).apply();*/
                    Toast.makeText(ArtActivity.this, "You have selected poetry and writing.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Poetry");
                    user.setCategory("Poetry");

                    break;
                } else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.graphicdesign:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("graphicdesign", checked).apply();*/
                    Toast.makeText(ArtActivity.this, "You have selected graphic design.", Toast.LENGTH_LONG).show();
                    mUsersDatabase .child("category").setValue("Graphic Design");
                    user.setCategory("Graphic Design");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.calligraphy:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("calligraphy", checked).apply();*/
                    Toast.makeText(ArtActivity.this, "You have selected calligraphy.", Toast.LENGTH_LONG).show();

                    user.setCategory("Calligraphy");
                    mUsersDatabase.child("category").setValue("Calligraphy");

                    break;
                }
                else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
        }
    }
}