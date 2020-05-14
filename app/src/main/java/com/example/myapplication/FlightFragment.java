package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FlightFragment extends NavigationActivity {

    private List<Flight> flightList;
    private RecyclerView mRecyclerView;
    private FlightAdapter adapterflight;
    private RecyclerView.LayoutManager mLayoutManager;
    // This string will hold the results

    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //The code that directs the menu to this page
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame); //This is the FrameLayout area within activity_navigation.xml
        getLayoutInflater().inflate(R.layout.activity_navigation, contentFrameLayout);
        super.onCreate(savedInstanceState);

        //Code to make menu visible on each activity
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_flight_recycler, null, false);
        drawerLayout.addView(contentView, 0);

        flightList = new ArrayList<Flight>();


        buildRecyclerView();
        requestQueue = Volley.newRequestQueue(this);
        parseJSON();

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.searchflights, menu);

            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) searchItem.getActionView();

            searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapterflight.getFilter().filter(newText);
                    return false;
                }
            });
            return true;
        }


        //method to parse JSON flight API
    private void parseJSON() {
        String url = "https://my-json-server.typicode.com/lsultana10/flynet-api/db";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray JsonArray = response.getJSONArray("flights");
                            flightList = new ArrayList<>();

                            for (int i = 0; i < JsonArray.length(); i++) {
                                JSONObject flights = JsonArray.getJSONObject(i);

                                Flight flight = new Flight();
                                flight.setFlightNo(flights.getString("flight-number"));
                                flight.setDestination(flights.getString("flight-destination"));
                                flight.setDate(flights.getString("flight-datetime"));
                                flight.setDeparture(flights.getString("flight-departure"));


                                flightList.add(flight);


                                adapterflight = new FlightAdapter( flightList);
                                mRecyclerView.setAdapter(adapterflight);
                                adapterflight.notifyDataSetChanged();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);

    }



    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        adapterflight = new FlightAdapter(flightList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapterflight);
    }




}



