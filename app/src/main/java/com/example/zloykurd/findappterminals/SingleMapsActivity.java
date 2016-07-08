package com.example.zloykurd.findappterminals;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class SingleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    String point_name, point_lat, point_long;
    String LOG_TAG = "SingAc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_maps);

        point_name = getIntent().getStringExtra("point_nameid");
        Log.d(LOG_TAG, "point_nameid'");
        point_lat = getIntent().getStringExtra("point_latid");
        Log.d(LOG_TAG, "point_latid'");
        point_long = getIntent().getStringExtra("point_longid");
        Log.d(LOG_TAG, "point_longid'");
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
        Database db = new Database(SingleMapsActivity.this);
        List<Point> nl = db.getAllMapsPoint();
        // Add a marker in Sydney and move the camera
        LatLng bishkek = new LatLng(Double.valueOf(point_lat), Double.valueOf(point_long));
        mMap.addMarker(new MarkerOptions().position(bishkek).title("Место расположения"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bishkek));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(nl.get(0).getLat(), nl.get(0).getLongt()), 12));
    }

}
