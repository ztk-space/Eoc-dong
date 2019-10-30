package com.rk.mynewdome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rk.mynewdome.bean.EventBusBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class EventBusActivity extends AppCompatActivity {
    private Button bj;
    private Button bc;
    private boolean p = true;
     public RecyclerView recyclerView;
    private List<String> li = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
       // EventBus.getDefault().register(this);
        recyclerView.findViewById(R.id.recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        li.add("赵腾开");
        li.add("李晓");
        bj = findViewById(R.id.bj);
        bc = findViewById(R.id.bc);
        bj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                p = true;
//                EventBusBean eventBusBean = new EventBusBean(p);
//                EventBus.getDefault().post(eventBusBean);
                setChuanOnListener.chuan("hehe");
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                p = false;
//                EventBusBean eventBusBean = new EventBusBean(p);
//                EventBus.getDefault().post(eventBusBean);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    SetChuanOnListener setChuanOnListener;
    public void setSetChuanOnListener(SetChuanOnListener setChuanOnListener){
        this.setChuanOnListener = setChuanOnListener;
    }
    interface SetChuanOnListener{
        void chuan(String s);
    }
}
