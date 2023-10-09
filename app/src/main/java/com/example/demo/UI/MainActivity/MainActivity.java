package com.example.demo.UI.MainActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.demo.R;
import com.example.demo.UI.Fragment.Home.HomeFragment;
import com.example.demo.UI.Fragment.Login.LoginFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFindById();
        setNavigationDrawer(savedInstanceState);
    }

    private void setFindById() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolBar);

    }


    private void setNavigationDrawer(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.Nav_Open
                ,R.string.Nav_Close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
            navigationView.setCheckedItem(R.id.login);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.login){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
            //當選到login選項時，就跳到login的分頁
        } else if (item.getItemId() == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
            //當選到home選項時，就跳到home的分頁
        } else if (item.getItemId() == R.id.logout) {
            Toast.makeText(this,"以登出",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new LoginFragment()).commit();
            //當選到logout時就跳回login的頁面，並且顯是以登出
        }
        drawerLayout.closeDrawer(GravityCompat.START);//設定像開始的地方關閉Navigation Drawer
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}