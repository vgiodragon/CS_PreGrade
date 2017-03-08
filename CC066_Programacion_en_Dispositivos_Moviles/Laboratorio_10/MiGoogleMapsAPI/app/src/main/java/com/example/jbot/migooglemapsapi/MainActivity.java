package com.example.jbot.migooglemapsapi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    private static GoogleMap mMap;
    private CameraUpdate mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setUpMapIfNeeded();
        setMarker(new LatLng(-12.017128, -77.050748), "Cafetería",
                "El mejor café", 0.5F, 0.1F, 0.1F, R.drawable.cafeteria);
        setMarker(new LatLng(-12.017124, -77.050744), "Restaurante",
                "Ají de gallina buenaso", 0.5F, 0.5F, 0.5F, R.drawable.restaurante);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.setMyLocationEnabled(true);
                setUpMap();
            }
        }
    }
/*  en el paso 9 modificamos el setUpMap
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(-12.017124, -77.050744)).title("Facultad de Ciencias"));
    }*/
/*  en el paso 10 volvemos a modificar el setUpMap
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(-12.017124,-77.050744)).title("Facultad de Ciencias"));
        mCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(-12.017124, -77.050744), 0);
        mMap.animateCamera(mCamera);
    }
*/
    private void setUpMap() {
        /* ejercicio 13
        mMap.addMarker(new MarkerOptions().position(new LatLng(-12.017124,
                -77.050744)).title("Facultad de Ciencias")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cafeteria)).snippet("The beast School"));
        *//*
                .icon(BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_CYAN))
          */
        mCamera = CameraUpdateFactory.newLatLngZoom(new LatLng(
                -12.017124, -77.050744), 14);
        mMap.animateCamera(mCamera);
    }

    private void setMarker(LatLng position, String title, String info,
                           float opacity, float dimension1, float dimension2, int icon) {
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(info)
                .alpha(opacity)
                .anchor(dimension1, dimension2)
                .icon(BitmapDescriptorFactory.fromResource(icon)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuOpcion1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.MenuOpcion2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.MenuOpcion3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            case R.id.MenuOpcion4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
