package com.example.admin.chebbychallenge.data.model.services;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.chebbychallenge.data.model.ClimaCity;
import com.example.admin.chebbychallenge.data.model.localday.LocalDayResponse;
import com.example.admin.chebbychallenge.main.ClimaPresenter;
import com.example.admin.chebbychallenge.network.GetClima;
import com.example.admin.chebbychallenge.network.RetrofitInstance;
import com.example.admin.chebbychallenge.utils.Cities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchWeather {

    ClimaPresenter presenter;
    public String date;
    public HashMap<String,Integer> citiesToFech;



    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getData(){
        date = getDate();
        initCitiestoFetch();
        presenter = ClimaPresenter.getClimaPresenter();
        presenter.initCityList();
        GetClima service = RetrofitInstance.getRetrofitInstance().create(GetClima.class);
        citiesToFech.forEach((city,woeid) -> {

            Call<List<LocalDayResponse>> call = service.getClimaByCity(woeid, date);
            Log.wtf("URL Called", call.request().url() + "");
            call.enqueue(new Callback<List<LocalDayResponse>>() {
                @Override
                public void onResponse(Call<List<LocalDayResponse>> call, Response<List<LocalDayResponse>> response) {
                    System.out.println("got the data");
                    presenter.climaCityList.add(new ClimaCity(city, response.body().get(0).getMaxTemp(), response.body().get(0).getMinTemp(), response.body().get(0).getWeatherStateName(), "https://www.metaweather.com/static/img/weather/png/64/" + response.body().get(0).getWeatherStateAbbr() + ".png"));
                }

                @Override
                public void onFailure(Call<List<LocalDayResponse>> call, Throwable t) {
                    System.out.println("didnt get the data");
                    System.out.println(t.getCause());
                    Toast.makeText(presenter.getMainView(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        });

       presenter.generateWeatherList();

    }

    private void initCitiestoFetch() {

        citiesToFech = new HashMap<>();
        citiesToFech.put("Gothenburg", Cities.GOTHENBURG);
        citiesToFech.put("Stockholm", Cities.STOCKHOLM);
        citiesToFech.put("Mountain View", Cities.MOUNTAIN_VIEW);
        citiesToFech.put("London", Cities.LONDON);
        citiesToFech.put("New York", Cities.NEW_YORK);
        citiesToFech.put("Berlin", Cities.BERLIN);

    }

    private String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(new Date()).toString();

    }



}

