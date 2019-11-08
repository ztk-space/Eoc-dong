package com.rk.myfeaturesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

import com.hyphenate.easeui.ui.EaseChatFragment;

public class ECChatActivity extends AppCompatActivity {

    private EaseChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ecchat);

        EaseChatFragment easeChatFragment = new EaseChatFragment();  //环信聊天界面
        easeChatFragment.setArguments(getIntent().getExtras()); //需要的参数
        getSupportFragmentManager().beginTransaction().add(R.id.ec_layout_container,easeChatFragment).commit();  //Fragment切换
    }
}
