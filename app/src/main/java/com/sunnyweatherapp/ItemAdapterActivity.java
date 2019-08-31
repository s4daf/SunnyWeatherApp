package com.sunnyweatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.sunnyweatherapp.WeatherClass.Datum;

import java.util.List;

public class ItemAdapterActivity extends RecyclerView.Adapter<ItemAdapterActivity.ItemAdapterViewHolder> {

    List<Datum> datumList;
    String cityName;

    public ItemAdapterActivity(List<Datum> weatherList, String cityNameList) {

        datumList = weatherList;
        cityName = cityNameList;
    }

    @NonNull
    @Override
    public ItemAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.acitvity_item, parent, false);
        ItemAdapterViewHolder itemAdapterViewHolder = new ItemAdapterViewHolder(view);
        return itemAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapterViewHolder holder, int position) {

        String city;
        String date;
        String weatherIcon;
        String weatherDescription;
        Double minWeather;
        Double maxWeather;

        city = cityName;
        holder.txtCity.setText(city);
        date = datumList.get(position).getValidDate();
        holder.txtDate.setText(date);
        weatherIcon = datumList.get(position).getWeather().getIcon();
        weatherIcon = "https://www.weatherbit.io/static/img/icons/" + weatherIcon + ".png";
        Picasso.get().load(weatherIcon).into(holder.imgWeatherIcon);
        weatherDescription = datumList.get(position).getWeather().getDescription();
        holder.txtWeatherDescription.setText(weatherDescription);
        minWeather = datumList.get(position).getMinTemp();
        holder.txtMinWeather.setText(minWeather.toString() + " °C");
        maxWeather = datumList.get(position).getMaxTemp();
        holder.txtMaxWeather.setText(maxWeather.toString() + " °C");
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    class ItemAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView txtCity;
        TextView txtDate;
        ImageView imgWeatherIcon;
        TextView txtWeatherDescription;
        TextView txtMinWeather;
        TextView txtMaxWeather;

        public ItemAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCity = itemView.findViewById(R.id.txtCity);
            txtDate = itemView.findViewById(R.id.txtDate);
            imgWeatherIcon = itemView.findViewById(R.id.imgWeatherIcon);
            txtWeatherDescription = itemView.findViewById(R.id.txtWeatherDescription);
            txtMinWeather = itemView.findViewById(R.id.txtMinWeather);
            txtMaxWeather = itemView.findViewById(R.id.txtMaxWeather);
        }
    }
}
