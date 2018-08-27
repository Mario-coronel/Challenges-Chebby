package com.example.admin.chebbychallenge;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.chebbychallenge.adapters.ClimaAdapter;
import com.example.admin.chebbychallenge.data.model.ClimaCity;
import com.example.admin.chebbychallenge.data.model.localday.LocalDayResponse;
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

public class ClimaList extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ClimaAdapter adapter;
    public String date;
    public SimpleDateFormat formatter;
    public HashMap<String,Integer> citiesToFech;
    public ArrayList<ClimaCity> climaCityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_list);
        formatter = new SimpleDateFormat("yyyy/MM/dd");
        date = formatter.format(new Date()).toString();
        climaCityList = new ArrayList<>();
        initCities();


    }

    private void initCities() {
        citiesToFech = new HashMap<>();
        citiesToFech.put("Gothenburg", Cities.GOTHENBURG);
        citiesToFech.put("Stockholm", Cities.STOCKHOLM);
        citiesToFech.put("Mountain View", Cities.MOUNTAIN_VIEW);
        citiesToFech.put("London", Cities.LONDON);
        citiesToFech.put("New York", Cities.NEW_YORK);
        citiesToFech.put("Berlin", Cities.BERLIN);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void fetchData(View view) {
        GetClima service = RetrofitInstance.getRetrofitInstance().create(GetClima.class);
        citiesToFech.forEach((city,woeid) -> {

                    Call<List<LocalDayResponse>> call = service.getClimaByCity(woeid, date);
                    Log.wtf("URL Called", call.request().url() + "");
                    call.enqueue(new Callback<List<LocalDayResponse>>() {
                        @Override
                        public void onResponse(Call<List<LocalDayResponse>> call, Response<List<LocalDayResponse>> response) {
                            System.out.println("got the data");
                            climaCityList.add(new ClimaCity(city, response.body().get(0).getMaxTemp(), response.body().get(0).getMinTemp(), response.body().get(0).getWeatherStateName(), "https://www.metaweather.com/static/img/weather/png/64/" + response.body().get(0).getWeatherStateAbbr() + ".png"));
                        }

                        @Override
                        public void onFailure(Call<List<LocalDayResponse>> call, Throwable t) {
                            System.out.println("didnt get the data");
                            System.out.println(t.getCause());
                            Toast.makeText(ClimaList.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                        }
                    });
                });
        System.out.println(climaCityList.size());
        generateClimaList(climaCityList);


    }


    public void generateClimaList(List<ClimaCity> climaCityList) {
        recyclerView = findViewById(R.id.clima_list);
        adapter = new ClimaAdapter(climaCityList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(ClimaList.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }


}
