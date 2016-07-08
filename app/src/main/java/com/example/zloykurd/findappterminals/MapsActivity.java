package com.example.zloykurd.findappterminals;

import android.support.design.widget.*;
import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.*;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    String LOG_TAG = "zloykurd";
    private GoogleMap mMap;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().isShowing();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        Database db = new Database(MapsActivity.this);
        List<Point> nl = db.getAllMapsPoint();


        for (int i = 0; i < nl.size(); i++) {
            LatLng lat = new LatLng(nl.get(i).getLat(), nl.get(i).getLongt());
            mMap.addMarker(new MarkerOptions().position(lat).title(String.valueOf(nl.get(i).getPoint_id()) + " " + nl.get(i).getPoint_name()));

        }
/*
* .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_idlocations))*/

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(nl.get(0).getLat(), nl.get(0).getLongt()), 8));

        // Other supported types include: MAP_TYPE_NORMAL,
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

       /* mMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mip))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(41.889, -87.622)));*/
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.idlocations) {
            Log.d(LOG_TAG, "нажатие idlocations");
            Database db = new Database(MapsActivity.this);

            // List<Point> nlid = db.createUserPosition();
            double lat = 0.0;
            double longT = 0.0;
            GPSTracker gp = new GPSTracker(MapsActivity.this);


            if (gp.canGetLocation()) {
                lat = gp.getLatitude();
                longT = gp.getLongitude();
            } else {
                gp.showSettingsAlert();
            }
            if (lat == 0.0 && longT == 0.0) {
                Toast t = Toast.makeText(this, "Координаты не определены. Попробуйте еще раз.", Toast.LENGTH_LONG);
                t.show();
            }
            mMap.addMarker(new MarkerOptions().position(new LatLng(lat, longT)).title("").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_idlocations)));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(lat, longT), 16));
            //db.addMyPosition(lat, longT);
            return true;
        }
        if (id == R.id.support) {
            Log.d(LOG_TAG, "нажатие support");
            Intent support = new Intent(MapsActivity.this, SupportActivity.class);
            Log.d(LOG_TAG, "переход на SupportActivity");
            startActivity(support);
            return true;
        }


        if (id == R.id.listview) {
            Log.d(LOG_TAG, "нажатие getsupportemail");
            Intent listview = new Intent(MapsActivity.this, FindListActivity.class);
            Log.d(LOG_TAG, "переход на FindListActivity");
            startActivity(listview);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.zloykurd.findappterminals/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.zloykurd.findappterminals/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
