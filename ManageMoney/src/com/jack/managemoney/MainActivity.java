package com.jack.managemoney;

import java.util.List;

import org.litepal.tablemanager.Connector;

import com.baidu.mapapi.SDKInitializer;
import com.jack.entity.CostSubject;
import com.jack.entity.InOutWay;
import com.jack.entity.IncomeSubject;
import com.jack.entity.WarnTitle;
import com.jack.util.Constant;
import com.jack.util.DBUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 无标题
		// 全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 强制竖屏
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());
        
		// 设置布局
		setContentView(R.layout.activity_main);
		SQLiteDatabase db = Connector.getDatabase();// 如果数据库没有，则创建数据库
		ImageButton ibjizhan = (ImageButton) findViewById(R.id.imageJizhang);// 记账
		ImageButton ibshouzhi = (ImageButton) findViewById(R.id.imageShouzhi);// 日常收支
		ImageButton ibbaobiao = (ImageButton) findViewById(R.id.imageBaobiao);// 报表
		ImageButton ibyuyin = (ImageButton) findViewById(R.id.imageYuyin);// 语音
		ImageButton ibyusuan = (ImageButton) findViewById(R.id.imageYusuan);// 预算
		ImageButton ibzuji = (ImageButton) findViewById(R.id.imageZuji);// 足迹
		ImageButton ibjisuan = (ImageButton) findViewById(R.id.imageJisuan);// 计算器
		ImageButton ibtixing = (ImageButton) findViewById(R.id.imageTixing);// 提醒
		ImageButton ibshezhi = (ImageButton) findViewById(R.id.imageSet);// 设置
		ImageButton ibjianyi = (ImageButton) findViewById(R.id.imageJianyi);// 建议
		ImageButton ibtuichu = (ImageButton) findViewById(R.id.imageTuichu);// 退出
		// 设置监听事件
		ibjizhan.setOnClickListener(this);
		ibshouzhi.setOnClickListener(this);
		ibbaobiao.setOnClickListener(this);
		ibyuyin.setOnClickListener(this);
		ibyusuan.setOnClickListener(this);
		ibzuji.setOnClickListener(this);
		ibjisuan.setOnClickListener(this);
		ibtixing.setOnClickListener(this);
		ibshezhi.setOnClickListener(this);
		ibjianyi.setOnClickListener(this);
		ibtuichu.setOnClickListener(this);
		// 对数据库进行初始化
		initialize();

	}

	// 初始化数据库里面的数据
	private void initialize() {
		// 插入收入科目
		List<IncomeSubject> ilist = DBUtil.getIncomeSubject();
		if (ilist == null) {
			Log.d("MainActivity75", "the value is null");
		}
		if (ilist.size() == 0) {
			for (int i = 0; i < Constant.shourukm.length; i++)
				DBUtil.insertIncomeSubject(Constant.shourukm[i]);
		}
		// 插入支出科目
		List<CostSubject> clist = DBUtil.getCostSubject();
		if (clist.size() == 0) {

			for (int i = 0; i < Constant.zhichukm.length; i++) {
				DBUtil.insertCostSubject(Constant.zhichukm[i]);
			}

			// 插入预算总额
			for (int i = 0; i < Constant.zhichukm.length; i++) {
				// 初始化预算总额
				DBUtil.insertBudget(i + 1, 0);
				// 初始预算余额
				DBUtil.insertBudgetBalance(i + 1, 0);
			}

			

		}

		// 插入收支的模式List<InOutWay> getInOutWay()
		List<InOutWay> flist = DBUtil.getInOutWay();
		if (flist.size() == 0) {
			for (int i = 0; i < Constant.fangshi.length; i++)
				DBUtil.insertInOutWay(Constant.fangshi[i]);
		}

		// 插入提醒标题
		List<WarnTitle> wlist = DBUtil.getTiXingTitle();
		if (wlist.size() == 0) {
			for (int i = 0; i < Constant.titleContent.length; i++)
				DBUtil.insertTiXingTitle(Constant.titleContent[i]);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = null;
		// 进行点击事件的判断
		switch (v.getId()) {
		case R.id.imageJizhang:
			/* Toast.makeText(this, "你点击的是记账", Toast.LENGTH_SHORT).show(); */
			intent = new Intent(MainActivity.this, Bookkeep.class);
			startActivity(intent);
			break;

		case R.id.imageShouzhi:
			/* Toast.makeText(this, "你点击的是日常收支", Toast.LENGTH_SHORT).show(); */
			intent = new Intent(MainActivity.this, DilyecpenseActivity.class);
			startActivity(intent);
			break;

		case R.id.imageBaobiao:
			/* Toast.makeText(this, "你点击的是报表", Toast.LENGTH_SHORT).show(); */
			intent = new Intent(MainActivity.this, ReportFormsActivity.class);
			startActivity(intent);
			break;

		case R.id.imageYuyin:
			/*Toast.makeText(this, "你点击的是语音", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, VoiceActivity.class);
			startActivity(intent);
			break;

		case R.id.imageYusuan:
			/*Toast.makeText(this, "你点击的是预算", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, BudgetActivity.class);
			startActivity(intent);
			break;

		case R.id.imageZuji:
			/*Toast.makeText(this, "你点击的是足迹", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, FootMarkActivity.class);
			startActivity(intent);
			break;

		case R.id.imageJisuan:
			/*Toast.makeText(this, "你点击的是计算", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, CalculationActivity.class);
			startActivity(intent);
			break;
		case R.id.imageTixing:
			/*Toast.makeText(this, "你点击的是提醒", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, AddRemindActivity.class);
			startActivity(intent);
			break;

		case R.id.imageSet:
			/*Toast.makeText(this, "你点击的是设置", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, SetUpActivity.class);
			startActivity(intent);
			break;

		case R.id.imageJianyi:
			/*Toast.makeText(this, "你点击的是建议反馈", Toast.LENGTH_SHORT).show();*/
			intent = new Intent(MainActivity.this, AdviceFeedback.class);
			startActivity(intent);
			break;

		case R.id.imageTuichu:
			/*Toast.makeText(this, "你点击的是退出", Toast.LENGTH_SHORT).show();*/
			finish();
			break;
		}
	}

}
