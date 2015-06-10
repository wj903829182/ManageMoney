package com.jack.managemoney;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class FootMarkActivity extends Activity {

	// 百度地图控件
	private MapView mMapView = null;
	private BaiduMap mBaiduMap;
	// 进行正向方向编码
	private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
	//
	//private LocationClient mLocationClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 无标题
		// 全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 强制竖屏
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 在使用SDK各组件之前初始化context信息，传入ApplicationContext
		// 注意该方法要再setContentView方法之前实现
		// SDKInitializer.initialize(getApplicationContext());
		// 设置布局
		setContentView(R.layout.footmark);
		// 调用初始化函数
		init();

	}

	// 初始化函数
	private void init() {
		// 获取地图控件引用
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		// 普通地图
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		
		//定义Maker坐标点  
		//LatLng point = new LatLng(39.963175, 116.400244);
		LatLng point = new LatLng(27.8274330000, 113.1316950000);
		//构建Marker图标  
		BitmapDescriptor bitmap = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_marka);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option = new MarkerOptions()  
		    .position(point)  
		    .icon(bitmap);  
		//在地图上添加Marker，并显示  
		mBaiduMap.addOverlay(option);
		
		
		LatLng point2 = new LatLng(28.2134780000, 112.9793530000);
		//构建Marker图标  
		BitmapDescriptor bitmap2 = BitmapDescriptorFactory  
		    .fromResource(R.drawable.icon_marka);  
		//构建MarkerOption，用于在地图上添加Marker  
		OverlayOptions option2 = new MarkerOptions()  
		    .position(point2)  
		    .icon(bitmap);  
		//在地图上添加Marker，并显示  
		mBaiduMap.addOverlay(option2);
		
		
		// 第一步，创建地理编码检索实例；
		mSearch = GeoCoder.newInstance();

	}

	// 设置监听
	private void setListener() {
		//创建地理编码检索监听者
		OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
			public void onGetGeoCodeResult(GeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					// 没有检索到结果
				}
				// 获取地理编码结果
				
			}

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					// 没有找到检索结果
				}
				// 获取反向地理编码结果
			}
		};
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

}
