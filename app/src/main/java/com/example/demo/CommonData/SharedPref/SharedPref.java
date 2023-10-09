package com.example.demo.CommonData.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.demo.R;

public class SharedPref {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    public SharedPref(Context context){
        sharedPreferences = context.getSharedPreferences(
                context.getResources().getString(R.string.app_name),Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setAct(String account){
        editor.putString("act",account).commit();
    }
    public void setPwd(String password){
        editor.putString("pwd",password).commit();
    }

    public String getAct(){return sharedPreferences.getString("act","error");}
    public String getPwd(){return  sharedPreferences.getString("pwd","error");}
}
