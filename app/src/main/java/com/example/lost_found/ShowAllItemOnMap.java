package com.example.lost_found;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ShowAllItemOnMap extends AppCompatActivity implements OnMapReadyCallback {

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_item_on_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        Geocoder geocoder = new Geocoder(this);

        myDatabaseHelper=new MyDatabaseHelper(this);


        List<String> columnValues = myDatabaseHelper.getColumnValues("MyTable", "Location");
        Log.d("ColumnValue", columnValues.toString());

        for(int i=0;i<columnValues.size();i++)
        {
            try {
                List<Address> addresses = geocoder.getFromLocationName(columnValues.get(i).toString(), columnValues.size());
                Address address=addresses.get(0);
                Log.d("ColumnValue", String.valueOf(addresses));

                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                LatLng loc = new LatLng(latitude, longitude);
                Log.d("ColumnValue", String.valueOf(loc));

                googleMap.addMarker(new MarkerOptions()
                        .position(loc)
                        .title("Marker in " +columnValues.get(i)));

                googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }
}