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


public class FilmActivity extends NavigationActivity {
    CheckBox maction,mmystery,mcomedy,mhorror,mromance;

    FirebaseAuth fAuth;
    DatabaseReference mDatabase;
    private DatabaseReference mUsersDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_film,null,false);
        drawerLayout.addView(contentView,0);

        fAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        drawerLayout.addView(contentView, 0);

        maction = findViewById(R.id.action);
        mmystery = findViewById(R.id.mystery);
        mcomedy = findViewById(R.id. comedy);
        mhorror = findViewById(R.id.horror);
        mromance = findViewById(R.id.romance);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(fAuth.getCurrentUser().getUid());


        /*boolean checked = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("action", false);
        maction.setChecked(checked);

        boolean checked1 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("mystery", false);
        mmystery.setChecked(checked1);

        boolean checked2 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("comedy", false);
        mcomedy.setChecked(checked2);

        boolean checked3 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("horror", false);
        mhorror.setChecked(checked3);

        boolean checked5 = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("romance", false);
        mromance.setChecked(checked5);*/



    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?

        boolean checked = ((CheckBox) view).isChecked();
        User user = new User();


        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.action:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("action", checked).apply();*/
                    Toast.makeText(FilmActivity.this, "You have selected Action.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Action");
                    user.setCategory("Action");

                    break;
                } else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.mystery:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("mystery", checked).apply();*/

                    Toast.makeText(FilmActivity.this, "You have selected Mystery.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Mystery");
                    user.setCategory("Mystery");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }


            case R.id.comedy:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("comecy", checked).apply();*/
                    Toast.makeText(FilmActivity.this, "You have selected Comedy.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Comedy");
                    user.setCategory("Comedy");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.horror:
                if (checked) {
                    /*PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("horror", checked).apply();*/
                    Toast.makeText(FilmActivity.this, "You have selected Horror.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Horror");
                    user.setCategory("Horror");

                    break;
                }
                else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }

            case R.id.romance:
                if (checked) {
                    PreferenceManager.getDefaultSharedPreferences(this).edit()
                            .putBoolean("romance", checked).apply();
                    Toast.makeText(FilmActivity.this, "You have selected Romance.", Toast.LENGTH_LONG).show();
                    mUsersDatabase.child("category").setValue("Romance");
                    user.setCategory("Romance");

                    break;
                }else{
                    mUsersDatabase .child("category").removeValue();
                    user.setCategory(null);
                }
        }
    }
}
