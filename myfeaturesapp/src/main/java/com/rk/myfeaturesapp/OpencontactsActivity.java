package com.rk.myfeaturesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rk.myfeaturesapp.base.BaseActivity;
import com.rk.myfeaturesapp.util.CustomTitleBar;

public class OpencontactsActivity extends BaseActivity implements View.OnClickListener {


    private static final int READ_REQUEST_CONTACTS = 100;
    private Button open;
    private CustomTitleBar customTitleBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init() {
        customTitleBar = findViewById(R.id.opencustom);
        customTitleBar.setTitle("打开手机通讯录");
        customTitleBar.setLeftIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        open = findViewById(R.id.opencontacts);
        open.setOnClickListener(this);
    }

    @Override
    protected int findView() {
        return R.layout.activity_opencontacts;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.opencontacts:
                //**版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取**
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //ContextCompat.checkSelfPermission() 方法 指定context和某个权限 返回PackageManager.PERMISSION_DENIED或者PackageManager.PERMISSION_GRANTED
                    if (ContextCompat.checkSelfPermission(OpencontactsActivity.this, android.Manifest.permission.READ_CONTACTS)
                            != PackageManager.PERMISSION_GRANTED) {
                        // 若不为GRANTED(即为DENIED)则要申请权限了
                        // 申请权限 第一个为context 第二个可以指定多个请求的权限 第三个参数为请求码
                        ActivityCompat.requestPermissions(OpencontactsActivity.this,
                                new String[]{android.Manifest.permission.READ_CONTACTS},
                                READ_REQUEST_CONTACTS);
                    } else {
                        //权限已经被授予，在这里直接写要执行的相应方法即可
                        intentToContact();
                    }
                }else {
                    // 低于6.0的手机直接访问
                    intentToContact();
                }
                break;
        }
    }
    // 用户权限 申请 的回调方法
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if(requestCode== READ_REQUEST_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                intentToContact();
            } else {
                Toast.makeText(OpencontactsActivity.this,"授权被禁止",Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);
    }
    private void intentToContact() {
        // 跳转到联系人界面
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("vnd.android.cursor.dir/phone_v2");
        startActivityForResult(intent, 0x30);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0x30) {
            if (data != null) {
                Uri uri = data.getData();
                String phoneNum = null;
                String contactName = null;
                // 创建内容解析者
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = null;
                if (uri != null) {
                    cursor = contentResolver.query(uri,
                            new String[]{"display_name","data1"}, null, null, null);
                }
                while (cursor.moveToNext()) {
                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }
                cursor.close();
                //  把电话号码中的  -  符号 替换成空格
                if (phoneNum != null) {
                    phoneNum = phoneNum.replaceAll("-", " ");
                    // 空格去掉  为什么不直接-替换成"" 因为测试的时候发现还是会有空格 只能这么处理
                    phoneNum= phoneNum.replaceAll(" ", "");
                }
                Log.i("TAG","contactName"+contactName+"phoneNum"+phoneNum);
            }
        }
    }

}
