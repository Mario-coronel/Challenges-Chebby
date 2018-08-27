package com.example.admin.chebbychallenge.network;

import com.example.admin.chebbychallenge.data.model.localday.LocalDayResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetClima {

    @GET("api/location/{woeid}/{date}/")
    Call<List<LocalDayResponse>> getClimaByCity(@Path("woeid") int woeid, @Path("date") String date);

}
