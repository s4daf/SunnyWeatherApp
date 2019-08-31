package com.sunnyweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    EditText edtCityName;
    Button btnForecast;

    String userCity;

    Intent getUserCityNameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        edtCityName = findViewById(R.id.edtCityName);
        btnForecast = findViewById(R.id.btnForecast);

        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userCity = edtCityName.getText().toString();

                getUserCityNameIntent = new Intent(SearchActivity.this, MainActivity.class);
                getUserCityNameIntent.putExtra("userCityName", userCity);
                startActivity(getUserCityNameIntent);
            }
        });
    }
}
