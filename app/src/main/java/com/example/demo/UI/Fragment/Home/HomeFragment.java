package com.example.demo.UI.Fragment.Home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private Dialog dialog;
    private ArrayList<String> mArrayList;
    private ArrayList<Integer> picture;
    private RecyclerView recyclerView;
    private HomeListAdapter homeListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArrayList = new ArrayList<>();
        picture = new ArrayList<>();
        dialog = new Dialog(getContext());
        //初始化
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
        //綁定介面
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFindById(view);//綁定物件id
        setListener();//設定監聽器
    }

    private void setFindById(View view) {
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        recyclerView = view.findViewById(R.id.home_recyclerView);
    }//綁定物件id

    private void setListener() {
        floatingActionButton.setOnClickListener(view -> setDialog());
        //這裡的浮動式按鈕做的不是關閉介面，而是設定Dialog
    }//設定物件的監聽氣

    private void setDialog() {
        dialog.setContentView(R.layout.addfunction_dialog);
        CheckBox weather = dialog.findViewById(R.id.weather_function);
        CheckBox game = dialog.findViewById(R.id.game_function);
        CheckBox calc = dialog.findViewById(R.id.calculator_function);

        Button add = dialog.findViewById(R.id.add_btn);
        //對話框物件的id綁定

        dialog.setCanceledOnTouchOutside(false);
        //將對話框設定為按對話框的區域也不會關閉，因此一定要按下新增按鈕才可以關閉

        if (mArrayList.contains("天氣狀況")) weather.setChecked(true);
        if (mArrayList.contains("計算機")) calc.setChecked(true);
        if (mArrayList.contains("小遊戲")) game.setChecked(true);
        //先判定之前有沒有將function的資料放進陣列裡面，有的話就讓checkbox設定成以打勾

        dialog.show();
        //顯示對話框

        add.setOnClickListener(view -> {
            setRecyclerView(mArrayList, picture);
            //按下按鈕後就將跟新過的資料傳給RecyclerView設定
            homeListAdapter.notifyDataSetChanged();
            //按下按鈕後就提醒RecyclerView更新內容
            dialog.dismiss();
            //按下按鈕後就關閉對話框
            Toast.makeText(getContext(),"功能列表以更新",Toast.LENGTH_SHORT).show();
            //用Toast提醒功能列表已更新
        });//新增按鈕的點擊事件

        weather.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (weather.isChecked()) {//當天氣checkBox有被點擊後，就檢查現在的狀態，如果是打勾狀態就將資料丟給各自代表的陣列
                    int functionResourceId = getResources().getIdentifier("weather_function","string",getContext().getPackageName());
                    mArrayList.add(getResources().getString(functionResourceId));
                    //這次使用抓String的name的方式來指定加入的資料
                    picture.add(R.drawable.weather);
                    //將天氣相關的圖片加進picture
                }else {
                    mArrayList.remove("天氣狀況");
                    //當checkBox被點擊時，結果當下狀態不是打勾，就所以
                    int index = picture.indexOf(R.drawable.weather);
                    if (index != -1) {
                        picture.remove(index);
                    }
                }
            }
        });//checkBox的設定

        calc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (calc.isChecked()) {
                    int resourceId = getResources().getIdentifier("calculator_function","string",getContext().getPackageName());
                    mArrayList.add(getResources().getString(resourceId));
                    picture.add(R.drawable.calculator);
                }else {
                    mArrayList.remove("計算機");
                    int index = picture.indexOf(R.drawable.calculator);
                    if (index != -1) {
                        picture.remove(index);
                    }
                }
            }
        });

        game.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (game.isChecked()) {
                    int resourceId = getResources().getIdentifier("game_function","string",getContext().getPackageName());
                    if (mArrayList.contains("小遊戲")) return;
                    mArrayList.add(getResources().getString(resourceId));
                    picture.add(R.drawable.game);
                }else {
                    mArrayList.remove("小遊戲");
                    int index = picture.indexOf(R.drawable.game);
                    if (index != -1) {
                        picture.remove(index);
                    }
                }
            }
        });

    }

    private void setRecyclerView(ArrayList<String> mArrayList, ArrayList<Integer> picture) {
        homeListAdapter = new HomeListAdapter(mArrayList, picture, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(homeListAdapter);
    }
}