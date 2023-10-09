package com.example.demo.UI.Fragment.Login;

import android.content.Context;
import android.util.Log;

import com.example.demo.CommonData.SharedPref.SharedPref;

public class LoginPresenter implements LoginContract.presenter{
    private LoginContract.view view;
    private SharedPref pref;
    public LoginPresenter(LoginContract.view view, Context context){
        this.view = view;
        pref = new SharedPref(context);
    }
    @Override
    public void checkAccountInformation(String account, String password) {
        String act = pref.getAct(); String pwd = pref.getPwd();
        Log.d("test", "checkAccountInformation: "+act+"\n"+pwd);
        if (act.equals(account) && pwd.equals(password)) view.accountInformationSuccess(true);
        else view.accountInformationSuccess(false);
    }

    @Override
    public void setAccountInformation(String account, String password) {
        if (account.isEmpty() || password.isEmpty()) view.successfulSetAccountInformation(false);
        else {
            pref.setAct(account);  pref.setPwd(password);
            view.successfulSetAccountInformation(true);
        }
    }
}
