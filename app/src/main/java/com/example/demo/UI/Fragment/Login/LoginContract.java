package com.example.demo.UI.Fragment.Login;

public interface LoginContract {
    interface view{
        void accountInformationSuccess(boolean success);
        void successfulSetAccountInformation(boolean success);
    }
    interface presenter{
        void checkAccountInformation(String account, String password);
        void setAccountInformation(String account, String password);
    }
}
