package com.example.demo.CommonData.API;

import com.example.demo.CommonData.Resopnse.WeatherResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetApi {
    @GET("F-C0032-001")
    Observable<Response<WeatherResponse>> getWeatherApi(
            @Query("Authorization") String Authorization,
            @Query("locationName") String locationName,
            @Query("elementName") String elementName
    );
}