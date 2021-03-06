package co.jco.weatherdemo.weather.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import co.jco.weatherdemo.R;
import co.jco.weatherdemo.data.WeatherApi;
import co.jco.weatherdemo.data.WeatherCity;
import co.jco.weatherdemo.data.WeatherRepository;
import co.jco.weatherdemo.weather.detail.WeatherDetailFragment;
import dagger.Lazy;
import dagger.android.support.DaggerFragment;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

import static co.jco.weatherdemo.UtilsKt.replaceFragment;

/**
 * Fragment displaying a list of cities, with their respective weather
 */
public class WeatherFragment extends DaggerFragment implements WeatherContract.View {

    private RecyclerView mCityList;
    private LinearLayoutManager mLayoutManager;
    private WeatherCityAdapter mCityAdapter;

    /**
     * see https://google.github.io/dagger/api/2.0/dagger/Lazy.html
     */
    @Inject
    Lazy<WeatherContract.Presenter> mPresenter;

    private FloatingActionButton mFab;
    private ItemTouchHelper mItemTouchHelper;
    private MenuItem mMenuSearchItem;
    private SearchView mSearchView;

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.get().setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weather, container, false);

        setHasOptionsMenu(true);

        mFab = view.findViewById(R.id.fab);
        mCityList = view.findViewById(R.id.rv_weather_city_list);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        setupFab();
        setupCityList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.get().start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);
        mMenuSearchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) mMenuSearchItem.getActionView();

        setupSearchView();
    }

    private void setupSearchView() {
        if (getContext() != null) {
            mSearchView.setQueryHint(getString(R.string.weather_main_menu_search_city_hint));
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!TextUtils.isEmpty(query)) {
                        mPresenter.get().addCity(query);
                        mSearchView.clearFocus();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    private void setupCityList() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCityList.setLayoutManager(mLayoutManager);
        mCityAdapter = new WeatherCityAdapter(new Function1<WeatherCity, Unit>() {
            @Override
            public Unit invoke(WeatherCity weatherCity) {
                mPresenter.get().onCityClick(weatherCity);
                return null;
            }
        });
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
                        mPresenter.get().removeCity(((WeatherCityViewHolder) viewHolder).getMCityName().getText().toString());
                    }
                });

        mItemTouchHelper.attachToRecyclerView(mCityList);
        mCityList.setAdapter(mCityAdapter);
    }

    private void setupFab() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.get().addCity(null);
            }
        });
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        //TODO It's empty now since we inject it. We need to clean the BaseView.
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
        // if the previous query was a success, we can hide the search view
        if (mMenuSearchItem != null) {
            mMenuSearchItem.collapseActionView();
        }
    }

    @Override
    public void showCityDetail(WeatherCity weatherCity) {
        replaceFragment((AppCompatActivity) getActivity(), WeatherDetailFragment.Factory.newInstance(weatherCity.getCityName()), R.id.fl_fragment_container);
    }

}
