package com.example.demo.UI.Functions.Weather;

import com.example.demo.CommonData.Resopnse.WeatherResponse;

public interface WeatherContract {
    interface view{
        void getWeatherData(WeatherResponse data);
    }
    interface presenter{
        void setWeatherApi(String locationName, String elementName, String time);
    }
}
