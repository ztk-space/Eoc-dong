package com.rk.myfeaturesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.rk.myfeaturesapp.base.BaseActivity;
import com.rk.myfeaturesapp.util.CustomTitleBar;
import com.rk.myfeaturesapp.util.ZXingUtils;

    public class QRcodeActivity extends BaseActivity {

    private Bitmap bitmap;
    private ImageView imageView;
    private CustomTitleBar customTitleBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

        @Override
        protected void init() {
            customTitleBar = findViewById(R.id.qr_custom);
            customTitleBar.setTitle("生成二维码");
            imageView = findViewById(R.id.qr_imageview);
            bitmap = ZXingUtils.createQRImage("https://cloud.baidu.com/doc/Developer/index.html#.E6.92.AD.E6.94.BE.E5.99.A8.20Android.20SDK", 400, 400);
            imageView.setImageBitmap(bitmap);
    }

        @Override
        protected int findView() {
            return R.layout.activity_qrcode;
        }
    }
