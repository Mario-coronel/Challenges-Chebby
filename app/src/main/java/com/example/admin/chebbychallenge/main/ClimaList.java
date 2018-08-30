package com.example.admin.chebbychallenge.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.admin.chebbychallenge.R;
import com.example.admin.chebbychallenge.data.model.services.FetchWeather;

public class ClimaList extends AppCompatActivity implements ClimaContract.View {

    ClimaPresenter presenter;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_list);
        presenter = ClimaPresenter.getClimaPresenter();
        presenter.setMainView(this);
        showResults();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void showResults() {
        presenter.getResults();
    }


}
