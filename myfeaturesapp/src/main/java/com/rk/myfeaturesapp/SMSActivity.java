package com.rk.myfeaturesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rk.myfeaturesapp.base.BaseActivity;
import com.rk.myfeaturesapp.util.CustomTitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SMSActivity extends BaseActivity {

    public static final int REQ_CODE_CONTACT = 1;
    private CustomTitleBar customTitleBar;
    private RecyclerView recyclerView;
    private SmsRecycler smsRecycler;
    private List<String> list = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void init() {
        customTitleBar  = findViewById(R.id.smstitlebar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customTitleBar.setTitle("SMS");
        if (ContextCompat.checkSelfPermission(SMSActivity.this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SMSActivity.this, new String[]{Manifest.permission.READ_SMS}, 1);
        }else {
            getSmsDate();
        }
    }

    private void getSmsDate() {
        Uri uri = Uri.parse("content://sms/");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{"_id", "address", "body", "date", "type"}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            int _id;
            String address;
            String body;
            String date;
            int type;
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                _id = cursor.getInt(0);
                address = cursor.getString(1);
                body = cursor.getString(2);
                date = cursor.getString(3);
                type = cursor.getInt(4);
                map.put("names", body);

                Log.i("test", "_id=" + _id + " address=" + address + " body=" + body + " date=" + date + " type=" + type);
//                data.add(map);
//                //通知适配器发生改变
//                sa.notifyDataSetChanged();
                list.add(body);
                smsRecycler = new SmsRecycler(list, SMSActivity.this);
                recyclerView.setAdapter(smsRecycler);
                smsRecycler.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected int findView() {
        return R.layout.activity_sms;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //判断用户是否，同意 获取短信授权
        if (requestCode == REQ_CODE_CONTACT && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //获取到读取短信权限
            getSmsDate();
        } else {
            Toast.makeText(this, "未获取到短信权限", Toast.LENGTH_SHORT).show();
        }
    }
    public class SmsRecycler extends RecyclerView.Adapter<SmsRecycler.SmsHolder>{

        private List<String> list;
        private Context context;

        public SmsRecycler(List<String> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @NonNull
        @Override
        public SmsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new SmsHolder(LinearLayout.inflate(context,R.layout.smslayout,null));
        }

        @Override
        public void onBindViewHolder(@NonNull SmsHolder holder, int position) {
           holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class SmsHolder extends RecyclerView.ViewHolder{
            private TextView textView;
            public SmsHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.smstextView);
            }
        }

    }
}
