package co.jco.weatherdemo.weather.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import co.jco.weatherdemo.R;
import co.jco.weatherdemo.data.WeatherCity;


public class WeatherCityAdapter extends RecyclerView.Adapter<WeatherCityViewHolder> {

    private final List<WeatherCity> mCities = new ArrayList<>();

    @Override
    public WeatherCityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_city, parent, false);
        return new WeatherCityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherCityViewHolder holder, int position) {
        holder.bind(mCities.get(position));
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public void setCities(Collection<WeatherCity> cities) {
        mCities.clear();
        mCities.addAll(cities);
        notifyDataSetChanged();
    }
}
