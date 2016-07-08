package com.example.zloykurd.findappterminals;

import android.*;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class SupportActivity extends AppCompatActivity {
    String LOG_TAG = "zloykurd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void callbtn(View view) {
        Log.d(LOG_TAG,"Intent.ACTION_CALL 996555505033");
        String myPhone = "996555505033";
        Intent phnum = new Intent(Intent.ACTION_CALL);
        phnum.setData(Uri.parse("tel:" + myPhone));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(phnum);
    }

    public void onSendemailtosupport(View view){


            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "radzhoyan@gmail.com", null));
            startActivity(Intent.createChooser(intent, "Выбирите почтовую программу :"));

    }



}
