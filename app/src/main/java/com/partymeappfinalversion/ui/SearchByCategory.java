package com.partymeappfinalversion.ui;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.partymeappfinalversion.R;

public class SearchByCategory extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private GoogleMap mMap;
    private Spinner searchBy;
    Button back;
    private static final String[]category = {"default", "Pub", "Theater", "Church", "Forro"};

    ViewFlipper slide;
    Button prev;
    Button next;
    TextView eventTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_category);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchBy = (Spinner) findViewById(R.id.dropDown);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchByCategory.this,
                android.R.layout.simple_spinner_item, category);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchBy.setAdapter(adapter);
        searchBy.setOnItemSelectedListener(this);


        back = (Button)findViewById(R.id.backToMain);
        back.setOnClickListener(this);

        slide =(ViewFlipper)findViewById(R.id.mySlide);
        prev = (Button)findViewById(R.id.prev);
        prev.setOnClickListener(this);

        next = (Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        eventTitle = (TextView)findViewById(R.id.eventTitle);


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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                eventTitle.setText(category[0]);

                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                eventTitle.setText(category[1]);
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                eventTitle.setText(category[2]);
                break;
            case 3:
                // Whatever you want to happen when the thrid item gets selected
                eventTitle.setText(category[3]);
                break;

            case 4:
                // Whatever you want to happen when the thrid item gets selected
                eventTitle.setText(category[4]);
                break;

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToMain:
                startActivity(new Intent(this, MapsActivityMain.class));
                break;
            case R.id.CreatEvent:
                startActivity(new Intent(this, CreateEvent.class));
                break;
            case R.id.searchByCategory:
                startActivity(new Intent(this, SearchByCategory.class));
                break;
            case R.id.next:
                slide.showNext();
                break;
            case R.id.prev:
                slide.showPrevious();
                break;

        }
    }
}
