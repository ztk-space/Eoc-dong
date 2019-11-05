package com.rk.myfeaturesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private Button Bottomnavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_layout);
        Bottomnavigation = findViewById(R.id.btn_Bottomnavigation);
        button.setOnClickListener(this);
        Bottomnavigation.setOnClickListener(this);
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
        }
    }
}
