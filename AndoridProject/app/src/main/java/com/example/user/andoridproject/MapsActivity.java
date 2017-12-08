package com.example.user.andoridproject;

import android.content.Context;
import android.content.res.AssetManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng startlocation = new LatLng(1.290270, 103.851959); //Singapore LagLng
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startlocation,11.0f));
    }


    /**
     * gets LatLng value from a string input using Geocoder
     * @param location
     * @return LatLng of the string location
     */
    private LatLng getCoordinate(String location) {
        Geocoder myGeo = new Geocoder(this);
        List<Address> matchedList;
        Address address = null;
        LatLng loc = null;
        try {
            matchedList = myGeo.getFromLocationName(location, 1);
            address = matchedList.get(0);
        } catch (IOException e) {
            //geocoder cannot find the location
            Toast.makeText(this, "Invalid location" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        try {
            loc = new LatLng(address.getLatitude(), address.getLongitude());

        } catch (NullPointerException e) {
            Toast.makeText(this, "error in retrieving location", Toast.LENGTH_SHORT).show();

        }
        return loc;
    }

    public void search(View view) {
        //clear map first
        mMap.clear();
        //get location from search bar
        EditText location = (EditText) findViewById(R.id.editText1);
        String s = location.getText().toString().toLowerCase();
        String correctLocation = correct(s);
        if (!correctLocation.equals("")) {
            correctLocation = "Singapore" + correctLocation;
            LatLng loc = getCoordinate(correctLocation);
            mMap.addMarker(new MarkerOptions().position(loc).title("Marker in SG").anchor(0.5f, 0.5f));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 17f));
        } else {
            //location is not a location listed in locations
            Toast.makeText(this, "Unfound location", Toast.LENGTH_SHORT).show();
        }

        hideSoftKeyboard();
    }


    /**
     * Given a string input, check if the string matches any location stored in locations
     *
     * @param location user input
     * @return the correct location that matches the input, or empty string if there is none
     */
    private String correct(String location) {
        ArrayList<String> check = new ArrayList<String>();
        AssetManager am = getAssets();
        try {
            InputStream assetIn = am.open("locations");
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetIn));
            String line;
            while ((line = reader.readLine()) != null) {
                check.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : check) {
            if (editDistance(location, s) < 4) {
                return s;
            }
        }

        return "";
    }

    /**
     * Get the edit distance of the two strings.
     * @param s1
     * @param s2
     * @return edit distance of the two strings
     */
    private int editDistance(String s1, String s2) { //s1 is input, s2 is loc name in dictionary
        //initial conditions
        int ls1 = s1.length();
        int ls2 = s2.length();
        int[][] minEdits = new int[ls1 + 1][ls2 + 1];
        for (int i = 0; i <= ls1; i++) {
            minEdits[i][0] = i;
        }
        for (int j = 0; j <= ls2; j++) {
            minEdits[0][j] = j;
        }

        for (int i = 1; i <= ls1; i++) {
            for (int j = 1; j <= ls2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) { //when the last char x and y are the same
                    minEdits[i][j] = minEdits[i - 1][j - 1];
                } else {
                    int insert = minEdits[i][j - 1] + 1;
                    int delete = minEdits[i - 1][j] + 1;
                    int replace = minEdits[i - 1][j - 1] + 1;
                    minEdits[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        return minEdits[ls1][ls2];
    }

    private void hideSoftKeyboard() {
        Button button = findViewById(R.id.search);
        InputMethodManager inputManager = (InputMethodManager) getSystemService
                (Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                button.getWindowToken(), 0);
    }
}
