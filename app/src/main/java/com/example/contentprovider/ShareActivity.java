package com.example.contentprovider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ShareActivity extends AppCompatActivity {
    String number="100000000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent=new Intent();
                        intent.setAction(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT,"hey check out my new app ");
                        intent.setType("text/plain");
                        startActivity(intent);
            }
        });
          findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(Intent.ACTION_DIAL);
                  intent.setData(Uri.parse("tel:10000000"));
                  startActivity(intent);
              }
          });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               permission();

            }
        });


    }

    public void call(){


        Intent intent=new Intent(Intent.ACTION_CALL, Uri.fromParts("tel",number,null));
        startActivity(intent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 5: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    call();
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
    public void permission(){

        if(ContextCompat.checkSelfPermission(ShareActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ShareActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(ShareActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        5);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {


           call();
            // Permission has already been granted
        }
    }
}
