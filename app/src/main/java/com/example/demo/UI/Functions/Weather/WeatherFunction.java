package com.example.demo.UI.Functions.Weather;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.CommonData.Resopnse.WeatherResponse;
import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class WeatherFunction extends AppCompatActivity implements WeatherContract.view {
    private FloatingActionButton floatingActionButton;
    private TextView result;
    private Button search;
    private Spinner location_spinner,element_spinner,time_spinner;
    private String selected_location,selected_element,selected_time;
    private String[] location_data,element_data,time_data,tw_element;
    private WeatherPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_function);

        presenter= new WeatherPresenter(this);

        setFindById();//綁定物件id
        setListener();//設定監聽器
        getSpinnerData();//獲取Spinner選單的資料
        setSpinner();//設定Spinner
    }

    private void setSpinner() {
        ArrayAdapter location_adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                location_data
        );
        ArrayAdapter element_adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                element_data
        );
        ArrayAdapter time_adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                time_data
        );

        location_spinner.setAdapter(location_adapter);
        element_spinner.setAdapter(element_adapter);
        time_spinner.setAdapter(time_adapter);//分別設定spinner的資料

        location_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_location = location_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });//location的spinner點擊事件

        element_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_element = element_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });//element的spinner點擊事件

        time_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_time = time_spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });//time的spinner點擊事件
    }//跟Spinner相關的設定

    private void getSpinnerData() {
        location_data = getResources().getStringArray(R.array.location_data);
        element_data = getResources().getStringArray(R.array.element_data);
        time_data = getResources().getStringArray(R.array.time_data);
        tw_element = getResources().getStringArray(R.array.tw_element);
    }//從string獲取資料

    private void setListener() {
        floatingActionButton.setOnClickListener(view -> finish());

        search.setOnClickListener(view -> presenter.setWeatherApi(selected_location,selected_element,selected_time));
    }//設定監聽事件

    private void setFindById() {
        floatingActionButton = findViewById(R.id.weather_floatActionButton);
        result = findViewById(R.id.weather_result);
        search = findViewById(R.id.weather_search);
        location_spinner = findViewById(R.id.weather_locationName);
        element_spinner = findViewById(R.id.weather_elementName);
        time_spinner = findViewById(R.id.weather_time);
    }//綁定物件id

    @Override
    public void getWeatherData(WeatherResponse data) {
        if (selected_element.equals("All")) selected_element = "";
        String finalSelectedElement = selected_element;
        result.setText("");
        List time_list = Arrays.asList(time_data);
        List element_list = Arrays.asList(element_data);
        if(data.getElementSize() != 1){
            for (int i = 0; i < data.getElementSize(); i++) {
                result.append(tw_element[i] + data.getDataByTime(i,time_list.indexOf(selected_time))+"\n");
            }
        }
        else {
            result.setText(tw_element[element_list.indexOf(finalSelectedElement)] + data.getDataByTime(0,time_list.indexOf(selected_time))+"\n");
        }
    }
}