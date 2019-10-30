package com.rk.mynewdome;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleApder extends RecyclerView.Adapter<RecycleApder.Holder> {
    private EventBusActivity eventBusActivity;
    private List<String> list;

    public RecycleApder(EventBusActivity eventBusActivity, List<String> list) {
        this.eventBusActivity = eventBusActivity;
        this.list = list;
    }

    @NonNull
    @Override
    public RecycleApder.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LinearLayout.inflate(eventBusActivity,R.layout.eventlayout,null));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleApder.Holder holder, int position) {
    holder.textView.setText(list.get(position));
    onTextViewListener.setTextView(list.get(position));
    eventBusActivity.setSetChuanOnListener(new EventBusActivity.SetChuanOnListener() {
        @Override
        public void chuan(String s) {
            Log.i("TAG",s+"====");
        }
    });
    }

    class Holder extends RecyclerView.ViewHolder{
        private TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
    OnTextViewListener onTextViewListener;

    public void setOnTextViewListener(OnTextViewListener onTextViewListener) {
        this.onTextViewListener = onTextViewListener;
    }

    interface  OnTextViewListener{
        void setTextView(String name);
  }
}
