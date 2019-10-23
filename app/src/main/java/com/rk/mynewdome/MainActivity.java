package com.rk.mynewdome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);


//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        ImageSelectorUtils.openPhoto(MainActivity.this, REQUEST_CODE, false, 9);
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && data != null) {
//            //获取选择器返回的数据
//            ArrayList<String> images = data.getStringArrayListExtra(
//                    ImageSelectorUtils.SELECT_RESULT);
//            recyclerView.setAdapter(new RecyAdper(images,this));
//            for (int i = 0;i<images.size();i++){
//                String s = images.get(i);
//                s.replace("jpg","PNG");
//                Log.i("TAG",s);
//            }
//
//        }
//    }


}
