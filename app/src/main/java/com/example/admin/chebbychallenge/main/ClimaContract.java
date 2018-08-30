package com.example.admin.chebbychallenge.main;

import com.example.admin.chebbychallenge.data.model.ClimaCity;

import java.util.ArrayList;

public interface ClimaContract {


    interface View {

        void showResults();



    }

    interface Presenter {

        void generateWeatherList();

        void getResults();
    }


}
