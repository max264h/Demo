package com.example.demo.UI.Fragment.Login;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.UI.Fragment.Home.HomeFragment;

public class LoginFragment extends Fragment implements LoginContract.view{
    private EditText et_act,et_pwd;
    private Button btn_register,btn_login;
    private Dialog dialog;
    private LoginPresenter presenter;
    //宣告
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new Dialog(getContext());
        presenter = new LoginPresenter(this,getContext());
    }//初始化

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }//連結布局檔
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFindById(view);
        //綁定物件id
        setListener();
        //設定監聽器
    }

    private void setFindById(View view) {
        et_act = view.findViewById(R.id.act_login);
        et_pwd = view.findViewById(R.id.pwd_login);
        btn_login = view.findViewById(R.id.btn_login);
        btn_register = view.findViewById(R.id.btn_register);
    }//綁定物件id

    private void setListener() {
        btn_login.setOnClickListener(view -> presenter.checkAccountInformation(
                et_act.getText().toString(),et_pwd.getText().toString()));
        //按下 登入 按鈕後，就將輸入的帳號密碼丟到Presenter幫忙檢查，往下看到accountInformationSuccess
        btn_register.setOnClickListener(view -> setRegisterDialog());
        //按下 註冊 按鈕後，就開啟註冊的對話框，往下看到setRegisterDialog
    }//設定監聽器

    @Override
    public void accountInformationSuccess(boolean success) {
        if (success){//如果帳戶資料比對成功，就創建一個Home的Fragment並且將LoginFragment替換掉
            Toast.makeText(getContext(),"成功登入",Toast.LENGTH_SHORT).show();
            // 創建 HomeFragment 實例
            Fragment homeFragment = new HomeFragment();

            // 使用 FragmentManager 替換當前的 Fragment
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, homeFragment);
            transaction.addToBackStack(null); // 如果需要加入回退堆疊，可以使用 addToBackStack
            transaction.commit();
        }else Toast.makeText(getContext(),"帳號或密碼錯誤",Toast.LENGTH_SHORT).show();
        //比對結果為失敗，就用Toast告知帳號密碼輸入錯誤
    }//繼承Contract的view後得到的方法，這個方法在點擊 登入 按鈕後，會將比對帳密的成果回傳到view，也就是這裡

    private void setRegisterDialog() {
        dialog.setContentView(R.layout.register_dialog);
        EditText act_register = dialog.findViewById(R.id.act_register);
        EditText pwd_register = dialog.findViewById(R.id.pwd_register);
        CheckBox checkBox = dialog.findViewById(R.id.checkbox_register);
        Button btn_create = dialog.findViewById(R.id.btn_create);
        //綁定dialog的頁面跟綁定dialog的物件id

        btn_create.setEnabled(false);
        checkBox.setChecked(false);
        //將建立按鈕跟checkbox初始化
        dialog.show();
        //設定完成將對話框顯示出來

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) btn_create.setEnabled(true);//如果checkbox有打勾，就解鎖 註冊 按鈕
                else btn_create.setEnabled(false);//反之就鎖定 註冊 按鈕
            }
        });//checkbox的監聽器

        btn_create.setOnClickListener(view -> presenter.setAccountInformation(//將資料丟到Presenter幫忙判斷是否有不合個的帳密出現(空白)
                act_register.getText().toString(),pwd_register.getText().toString()));//註冊按鈕的點擊事件
    }//設定註冊的對話框

    @Override
    public void successfulSetAccountInformation(boolean success) {
        if (success) dialog.dismiss();//比對註冊的帳密格式是否正確後，會回傳是否成功的訊息，成功就將對話框關閉
        else Toast.makeText(getContext(),"帳號或密碼不能為空白",Toast.LENGTH_SHORT).show();//反之就用Toast說明原因
    }//判斷註冊的帳密是否符合規定
}