package com.sunnyweatherapp;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SplashSpeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isConnected(SplashSpeedActivity.this))
            buildDialog(SplashSpeedActivity.this).show();
        else {

            setContentView(R.layout.activity_splash_speed);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(SplashSpeedActivity.this, "Connecting..Please wait!", Toast.LENGTH_LONG).show();
                }
            }, 1000);

//          Toast.makeText(SplashSpeedActivity.this, "Connecting..Please wait!", Toast.LENGTH_LONG).show();
//          Toast.makeText(SplashSpeedActivity.this, "Connecting..", Toast.LENGTH_SHORT).show();
//          Toast.makeText(SplashSpeedActivity.this, "Please wait!", Toast.LENGTH_SHORT).show();
//          Toast.makeText(SplashSpeedActivity.this, "You're Welcome", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent splashSpeedToMainActivity = new Intent(SplashSpeedActivity.this, StartActivity.class);
                    startActivity(splashSpeedToMainActivity);
                    finish();
                }
            }, 5000);

        }

    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("Please make sure you are connected to the internet." + "\n" + "Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
