package com.example.demo.UI.Functions.Weather;

import android.util.Log;

import com.example.demo.CommonData.API.ApiClient;
import com.example.demo.CommonData.API.GetApi;
import com.example.demo.CommonData.Resopnse.WeatherResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.presenter{
    private WeatherContract.view view;
    private ApiClient apiClient;
    private GetApi getApi;
    public WeatherPresenter(WeatherContract.view view){
        this.view = view;
        apiClient =new ApiClient();
        getApi = apiClient.myWeatherApi().create(GetApi.class);
    }
    @Override
    public void setWeatherApi(String locationName, String elementName, String time) {
        String authorization = "CWB-2F70211E-8C2F-4A7F-8841-292FDCE00BEB";
        if (elementName.equals("All")) elementName = "";
        String finalSelectedElement = elementName;
        getApi.getWeatherApi(authorization,locationName,finalSelectedElement)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Response<WeatherResponse>>() {
                    @Override
                    public void onNext(@NonNull Response<WeatherResponse> weatherResponse) {
                        view.getWeatherData(weatherResponse.body());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("test", "onError: " + e +"\n");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("test", "onComplete: ");
                    }
                });
    }
}
