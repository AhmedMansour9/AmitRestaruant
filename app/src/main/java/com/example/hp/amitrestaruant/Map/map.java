package com.example.hp.amitrestaruant.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hp.amitrestaruant.Branches.adapterpizza;
import com.example.hp.amitrestaruant.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by HP on 18/12/2016.
 */
public class map extends AppCompatActivity {
    private GoogleMap googleMap;
    latandlong a;
    double longgy;
    double laaatiy;
    Location location;
    Geocoder gecoder;
    List<Address> addresses;
    ArrayList<String> addressFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dokkimap);

        SharedPreferences sharedPreferences=getSharedPreferences("DDATA", MODE_PRIVATE);
        String  LOong = sharedPreferences.getString("LONG", null);
        String  LATY= sharedPreferences.getString("LATY", null);
//        Intent intn=getIntent();
//        String LOong=intn.getExtras().getString("LONG");
//        String LAT=intn.getExtras().getString("LATY");

        laaatiy=Double.parseDouble(LATY);
        longgy=Double.parseDouble(LOong);

        a = new latandlong(map.this);

        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.frag)).getMap();

            MarkerOptions marker = new MarkerOptions().position(new LatLng(laaatiy, longgy)).title("Hello Maps ");
            marker.snippet("welcome to your country ");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

            googleMap.addMarker(marker);

            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(laaatiy, longgy)).zoom(12).build();

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            gecoder = new Geocoder(map.this, Locale.getDefault());
        }
    }
}
