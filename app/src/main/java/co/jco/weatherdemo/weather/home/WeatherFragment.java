package co.jco.weatherdemo.weather.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.jco.weatherdemo.R;
import co.jco.weatherdemo.data.WeatherCity;

/**
 * Fragment displaying a list of cities, with their respective weather
 */
public class WeatherFragment extends Fragment implements WeatherContract.View {

    //static counter to differentiate mock cities added
    private static int sCityCounter = 0;

    private RecyclerView mCityList;
    private LinearLayoutManager mLayoutManager;
    private WeatherCityAdapter mCityAdapter;
    private WeatherContract.Presenter mPresenter;
    private FloatingActionButton mFab;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weather, container, false);

        mPresenter = new WeatherPresenterImpl(this);

        mFab = view.findViewById(R.id.fab);
        mCityList = view.findViewById(R.id.rv_weather_city_list);

        setupFab();
        setupCityList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void setupCityList() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCityList.setLayoutManager(mLayoutManager);
        mCityAdapter = new WeatherCityAdapter();
        mItemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT
                                | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        // remove swiped item from the list
                        mPresenter.removeCity(((WeatherCityViewHolder) viewHolder).mCityName.getText().toString());
                    }
                });

        mItemTouchHelper.attachToRecyclerView(mCityList);
        mCityList.setAdapter(mCityAdapter);
    }

    private void setupFab() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addCity("New city " + ++sCityCounter);
            }
        });
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showEmpty() {
        //TODO Display a sad cloud
    }

    @Override
    public void showError() {
        Snackbar.make(getActivity().findViewById(android.R.id.content),
                R.string.weather_error_occurred,
                Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void showCities(List<WeatherCity> cities) {
        mCityAdapter.setCities(cities);
    }

}
