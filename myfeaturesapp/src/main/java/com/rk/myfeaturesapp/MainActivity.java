package com.rk.myfeaturesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.rk.myfeaturesapp.base.BaseActivity;
import com.rk.myfeaturesapp.util.CustomTitleBar;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button button;
    private Button Bottomnavigation;
    private Button SMS;
    private Button camear;
    private Button contact;
    private CustomTitleBar customTitleBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        button = findViewById(R.id.btn_layout);
        Bottomnavigation = findViewById(R.id.btn_Bottomnavigation);
        SMS = findViewById(R.id.btn_SMS);
        camear = findViewById(R.id.btn_camear);
        contact = findViewById(R.id.btn_contact);
        customTitleBar = findViewById(R.id.maintitlebar);
        customTitleBar
                .setTitle("功能中心");
        customTitleBar.hideBackImg();
        button.setOnClickListener(this);
        Bottomnavigation.setOnClickListener(this);
        SMS.setOnClickListener(this);
        camear.setOnClickListener(this);
        contact.setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_layout:
                startActivity(new Intent(MainActivity.this,TabLayoutActivity.class));
                break;
            case R.id.btn_Bottomnavigation:
                startActivity(new Intent(MainActivity.this,BottomNavigationActivity.class));
                break;
            case R.id.btn_SMS:
                startActivity(new Intent(MainActivity.this,SMSActivity.class));
                break;
            case R.id.btn_camear:
                startActivity(new Intent(MainActivity.this,CamearActivity.class));
                break;
            case R.id.btn_contact:
                startActivity(new Intent(MainActivity.this,ContactActivity.class));
                break;
        }
    }
}
