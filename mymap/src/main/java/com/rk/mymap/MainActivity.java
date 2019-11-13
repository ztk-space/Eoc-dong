package com.rk.mymap;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;

    private MyLocationListener myListener = new MyLocationListener();
    LocationClientOption option;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //该方法注意要在setContentView之前实现
        //获取地图控件引用
        setContentView(R.layout.activity_main);
       intView();
    }
    //地图定位
    public void intView(){
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
        //默认显示普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationConfiguration(locationConfig());
        //注册监听函数
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedLocationDescribe(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
    }
    public class MyLocationListener extends BDAbstractLocationListener{
        @Override
        public void onReceiveLocation(BDLocation location){
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
//            Log.i("TAG",latitude+"======");
//            Log.i("TAG",longitude+"======");
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            //            // 设置定位数据
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
          // mLocationClient.stop();
            //地理坐标基本数据结构
            LatLng latLng=new LatLng(latitude,longitude);
            //描述地图状态将要发生的变化,通过当前经纬度来使地图显示到该位置
            MapStatusUpdate msu= MapStatusUpdateFactory.newLatLng(latLng);
            //改变地图状态
            mBaiduMap.setMapStatus(msu);
//            String buildingID = location.getBuildingID();// 百度内部建筑物ID
//            String buildingName = location.getBuildingName();// 百度内部建筑物缩写
//            String floor = location.getFloor();// 室内定位的楼层信息，如 f1,f2,b1,b2
            String locationDescribe = location.getLocationDescribe();
            Log.i("TAG",locationDescribe+"_____________________");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    private MyLocationConfiguration locationConfig(){

//        mCurrentMode = LocationMode.FOLLOWING;//定位跟随态
//        mCurrentMode = LocationMode.NORMAL;   //默认为 LocationMode.NORMAL 普通态
//        mCurrentMode = LocationMode.COMPASS;  //定位罗盘态
        MyLocationConfiguration.LocationMode locationMode = MyLocationConfiguration.LocationMode.NORMAL;

        //是否开启方向
        boolean enableDirection = true;

        //自定义定位图标
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);

        //精度圈填充颜色
        int accuracyCircleFillColor = Color.parseColor("8");

        //自定义精度圈边框颜色
        int accuracyCircleStrokeColor = Color.parseColor("#11ff0000");

        MyLocationConfiguration locationConfiguration = new MyLocationConfiguration(
                locationMode,
                enableDirection,
                bitmapDescriptor,
                accuracyCircleFillColor,
                accuracyCircleStrokeColor
        );

        return locationConfiguration;
    }
}