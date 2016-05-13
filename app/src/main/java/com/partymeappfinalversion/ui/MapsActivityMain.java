package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.partymeappfinalversion.R;

import android.content.Context;
import android.location.Geocoder;
import android.location.Address;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import java.util.List;


public class MapsActivityMain extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    Button WhatsOn;
    Button createEvent;
    Button searchByCat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
        WhatsOn = (Button) findViewById(R.id.WhatIsON);
        WhatsOn.setOnClickListener(this);

        createEvent = (Button) findViewById(R.id.CreatEvent);
        createEvent.setOnClickListener(this);

        searchByCat = (Button) findViewById(R.id.searchByCategory);
        searchByCat.setOnClickListener(this);

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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 31);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap = googleMap;
        LatLng latlng;
        float zoomLevel = 16; //This goes up to 21
        String address = "The Temple Bar, Temple Bar";

        latlng = getLocationFromAddress(address);

        try {
            mMap.setMyLocationEnabled(true);

        } catch (SecurityException se) {
            se.printStackTrace();
        }

        // Add a marker in Temple Bar and move the camera
//        locationLat[0] = 53.3455333;
//        locationLong[0] = -6.26428199999998;
//        locationTitle[0] = "The Temple Bar";
//        locationSnippet[0] = "The Most Famous Pub in Dublin";
//        locationLat[1] = 53.34475197446625;
//        locationLong[1] = -6.264762166964715;
//        locationTitle[1] = "The Mezz";
//        locationSnippet[1] = "The Rock'n Roll Pub";
//        locationLat[2] = 53.34565188171889;
//        locationLong[2] = -6.263099197375482;
//        locationTitle[2] = "The Quays";
//        locationSnippet[2] = "The Traditional Irish Pub";

//        for(int i = 0; i < 3; i++ ){
//            latlng = new LatLng(locationLat[i],locationLong[i]);
//            mMap.addMarker(new MarkerOptions()
//                    .title(locationTitle[i])
//                    .snippet(locationSnippet[i])
//                    .position(latlng));
//        }

        mMap.addMarker(new MarkerOptions()
            .title("EX-HOME")
            .snippet("Lots of Parties")
            .position(latlng));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoomLevel));




    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.WhatIsON:
                startActivity(new Intent(this, WhatsIsOn.class));
                break;
            case R.id.CreatEvent:
                startActivity(new Intent(this, CreateEvent.class));
                break;
            case R.id.searchByCategory:
                startActivity(new Intent(this, SearchByCategory.class));
                break;
        }
    }

    //for the map

    public LatLng getLocationFromAddress(String strAddress){
        Geocoder coder = new Geocoder(this);
        LatLng p1 = null;
        List<android.location.Address> address;
        try{
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;

            }
            android.location.Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }
        return p1;
    }


}


