package com.sunnyweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.sunnyweatherapp.WeatherClass.Datum;
import com.sunnyweatherapp.WeatherClass.WeatherbitClass;

import org.json.JSONObject;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemAdapterActivity itemAdapterActivity;
    DrawerLayout drawerLayout;

    Intent setUserCityName;
    String userCityName;

    String url;

    AsyncHttpClient asyncHttpClient;

    Gson gson;

    WeatherbitClass weatherbitClass;

    String cityName;

    List<Datum> datumList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        drawerLayout = findViewById(R.id.drawerLayout);

        setUserCityName = getIntent();
        userCityName = setUserCityName.getStringExtra("userCityName");

        url = "https://api.weatherbit.io/v2.0/forecast/daily?city="+ userCityName +",IR&key=b1790b057c914f13a2ee6fb142412271";

        asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                gson = new Gson();

                weatherbitClass = gson.fromJson(response.toString(), WeatherbitClass.class);
                cityName = weatherbitClass.getCityName();
                datumList = weatherbitClass.getData();

                itemAdapterActivity = new ItemAdapterActivity(datumList, cityName);
                recyclerView.setAdapter(itemAdapterActivity);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
