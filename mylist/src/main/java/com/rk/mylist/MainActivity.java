package com.rk.mylist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button buttonbj;
    private Button buttonbc;
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    private boolean aBoolean;
    private RecyAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonbc = findViewById(R.id.bc);
        buttonbj = findViewById(R.id.bj);
        recyclerView = findViewById(R.id.recy);
        list.add("定西以南");
        list.add(".");

        list.add("定北以东");
        list.add("定西以北");
        list.add("定东以中");
        adpter = new RecyAdpter(list,this,aBoolean);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpter);
        buttonbj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 aBoolean = false;
                adpter = new RecyAdpter(list,MainActivity.this,aBoolean);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adpter);
            }
        });
        buttonbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aBoolean = true;
                adpter = new RecyAdpter(list,MainActivity.this,aBoolean);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(adpter);
            }
        });

    }
    public class RecyAdpter extends RecyclerView.Adapter<RecyAdpter.Holder>{
        private List<String> list;
        private Context context;
        private boolean aBoolean;

        public RecyAdpter(List<String> list, Context context, boolean aBoolean) {
            this.list = list;
            this.context = context;
            this.aBoolean = aBoolean;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(LinearLayout.inflate(context,R.layout.layout,null));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.textView.setText(list.get(position));
            Log.i("TAG",aBoolean+"====");
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class Holder extends RecyclerView.ViewHolder{
           private TextView textView;
           public Holder(@NonNull View itemView) {
               super(itemView);
               textView = itemView.findViewById(R.id.textView);
           }
       }

    }
}
