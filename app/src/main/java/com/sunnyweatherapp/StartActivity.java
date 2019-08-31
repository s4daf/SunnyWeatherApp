package com.sunnyweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class StartActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    Button btnChangeCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        drawerLayout = findViewById(R.id.drawerLayout);
        btnChangeCity = findViewById(R.id.btnChangeCity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                drawerLayout.openDrawer(GravityCompat.START);
            }
        }, 1000);

        btnChangeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent searchActivityIntent = new Intent(StartActivity.this, SearchActivity.class);
                startActivity(searchActivityIntent);
            }
        });

    }
}
