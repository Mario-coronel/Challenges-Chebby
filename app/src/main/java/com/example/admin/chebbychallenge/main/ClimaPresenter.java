package com.example.admin.chebbychallenge.main;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.admin.chebbychallenge.R;
import com.example.admin.chebbychallenge.adapters.ClimaAdapter;
import com.example.admin.chebbychallenge.data.model.ClimaCity;
import com.example.admin.chebbychallenge.data.model.services.FetchWeather;

import java.util.ArrayList;


public class ClimaPresenter implements ClimaContract.Presenter {

    public static ClimaPresenter climaPresenter;
    public ClimaList mainView;
    public RecyclerView recyclerView;
    public ClimaAdapter adapter;
    FetchWeather fetchWeather;
    public ArrayList<ClimaCity> climaCityList;
    boolean dataMissing;


    private ClimaPresenter() {

    }


    public void setMainView(ClimaList mainView) {
        this.mainView = mainView;
    }

    public static ClimaPresenter getClimaPresenter() {
        if (climaPresenter == null) {
            climaPresenter = new ClimaPresenter();
        }
        return climaPresenter;
    }


    @Override
    public void generateWeatherList() {
        dataMissing = true;
        while (dataMissing) {
            if (climaCityList.size() == 0) {
                Toast.makeText(mainView, "Getting Weather Data", Toast.LENGTH_SHORT).show();
            }else {
                dataMissing = false;
            }
        }
        recyclerView = mainView.findViewById(R.id.clima_list);
        adapter = new ClimaAdapter(climaCityList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mainView.getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getResults() {
        fetchWeather = new FetchWeather();
        fetchWeather.getData();

    }

    public Context getMainView() {
        return mainView.getApplicationContext();
    }

    public ArrayList<ClimaCity> initCityList() {
        climaCityList = new ArrayList<>();
        return climaCityList;
    }
}
