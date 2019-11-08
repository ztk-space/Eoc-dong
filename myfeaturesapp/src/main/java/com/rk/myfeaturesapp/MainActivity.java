package com.rk.myfeaturesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.rk.myfeaturesapp.base.BaseActivity;
import com.rk.myfeaturesapp.util.AndroidDeviceInfo;
import com.rk.myfeaturesapp.util.CustomTitleBar;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button button;
    private Button Bottomnavigation;
    private Button SMS;
    private Button camear;
    private Button contact;
    private CustomTitleBar customTitleBar;
    private Button opencontacts;
    private Button switchappicon;
    private Button easeui;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        String phoneModel = AndroidDeviceInfo.getPhoneModel();
        String phoneProducer = AndroidDeviceInfo.getPhoneProducer();
        Log.i("TAG","phoneModel"+phoneModel+"phoneProducer"+phoneProducer);
// 下面的点击切换了之后 一进程序再默认切换回来
//        PackageManager packageManager = getPackageManager();
//        packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
//                        ".RoundActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
//                PackageManager.DONT_KILL_APP);
//        packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
//                ".MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager
//                .DONT_KILL_APP);

        button = findViewById(R.id.btn_layout);
        Bottomnavigation = findViewById(R.id.btn_Bottomnavigation);
        SMS = findViewById(R.id.btn_SMS);
        camear = findViewById(R.id.btn_camear);
        contact = findViewById(R.id.btn_contact);
        opencontacts = findViewById(R.id.btn_opencontacts);
        customTitleBar = findViewById(R.id.maintitlebar);
        switchappicon = findViewById(R.id.btn_switchappicon);
        easeui = findViewById(R.id.btn_easeui);
        customTitleBar
                .setTitle("功能中心");
        customTitleBar.hideBackImg();
        button.setOnClickListener(this);
        Bottomnavigation.setOnClickListener(this);
        SMS.setOnClickListener(this);
        camear.setOnClickListener(this);
        contact.setOnClickListener(this);
        opencontacts.setOnClickListener(this);
        switchappicon.setOnClickListener(this);
        easeui.setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //tablayout布局
            case R.id.btn_layout:
                startActivity(new Intent(MainActivity.this,TabLayoutActivity.class));
                break;
                //底部导航栏
            case R.id.btn_Bottomnavigation:
                startActivity(new Intent(MainActivity.this,BottomNavigationActivity.class));
                break;
                //获取短信
            case R.id.btn_SMS:
                startActivity(new Intent(MainActivity.this,SMSActivity.class));
                break;
                //打开相机
            case R.id.btn_camear:
                startActivity(new Intent(MainActivity.this,CamearActivity.class));
                break;
                //短信通话记录
            case R.id.btn_contact:
                startActivity(new Intent(MainActivity.this,ContactActivity.class));
                break;
                //打开手机联系人点击联系人并返回联系人数据
            case R.id.btn_opencontacts:
                startActivity(new Intent(MainActivity.this,OpencontactsActivity.class));
                break;
                //切换App图标
            case R.id.btn_switchappicon:
              //  注意setComponentEnabledSetting()方法的第3个参数有两个值供选择：1（也就是PackageManager.DONT_KILL_APP）和0。这两种参数对应两种效果：当设为1时，当切换APP图标时，会有几秒钟的延迟，并且在延迟期间不能点击图标进入APP；当设为0时，当切换APP图标时，会立刻更换，但是应用会被强制退出并被清理掉。
              //https://blog.csdn.net/u013541140/article/details/79129916
                PackageManager packageManager = getPackageManager();
                packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
                        ".MainActivity"), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager
                        .DONT_KILL_APP);
                packageManager.setComponentEnabledSetting(new ComponentName(this, getPackageName() +
                        ".RoundActivity"), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager
                        .DONT_KILL_APP);
                break;
            case R.id.btn_easeui:
                startActivity(new Intent(MainActivity.this,EaseUiActivity.class));
                break;
        }
    }
}
