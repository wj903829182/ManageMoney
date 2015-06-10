package com.jack.managemoney;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jack.adapter.DilyexpenseAdapter;
import com.jack.adapter.ReportListAdapter;
import com.jack.util.DBUtil;
import com.jack.util.ReportList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReportListActivity extends Activity implements OnClickListener {

	private int select = 1;// 0表示账户，1表示科目
	private int current_id = 2;// 0表示每天报表，1表示每周报表，2表示每月报表

	private Button backButton = null;// 返回按钮
	private Button buttonLeft = null;// 减少按钮
	private Button buttonRight = null;// 增加少按钮
	private TextView textViewDay = null;// 显示日期
	private Calendar calendar = null;// 时间操作对象

	private Button dayButton = null;// 日报表
	private Button weekButton = null;// 周报表
	private Button monthButton = null;// 月报表

	private ListView listview = null;
	private Button sbSubject = null;// 按科目显示的按钮
	private Button sbAccount = null;// 按账号显示的按钮
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
	private SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
	private ReportListAdapter adapter = null;
	private long startTime;
	private long endTime;
	private List<ReportList> relist = new ArrayList<ReportList>();

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
		setContentView(R.layout.report_forms_list);
		init();// 进行初始化
		// 设置事件监听
		setListener();
	}

	// 初始化函数
	private void init() {
		// 初始化组件
		backButton = (Button) findViewById(R.id.list_report_back);
		dayButton = (Button) findViewById(R.id.day);
		weekButton = (Button) findViewById(R.id.week);
		monthButton = (Button) findViewById(R.id.month);
		buttonLeft = (Button) findViewById(R.id.bleft);
		buttonRight = (Button) findViewById(R.id.bright);
		textViewDay = (TextView) findViewById(R.id.dateTextView1);
		listview = (ListView) findViewById(R.id.report_listview);
		sbSubject = (Button) findViewById(R.id.list_subject);
		sbAccount = (Button) findViewById(R.id.list_accout);

		// 得到Intent
		Intent intent = getIntent();//
		select = intent.getIntExtra("select", 1);// 得到选择
		current_id = intent.getIntExtra("current_id", 2);// 得到目前的选择的id
		String str = intent.getStringExtra("date");// 得到时间字符串
		Date date = null;
		calendar = Calendar.getInstance();
		try {
			date = sf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (date != null) {
			calendar.setTime(date);
		}

		if (current_id == 0) {
			String strdate = sf.format(calendar.getTime());
			startTime = calendar.getTime().getTime();
			endTime = startTime;
			textViewDay.setText(strdate);
			dayButton.setBackgroundResource(R.drawable.bb_ydg_switch_day_on);
			weekButton.setBackgroundResource(R.drawable.bb_ydg_switch_week_off);
			monthButton
					.setBackgroundResource(R.drawable.bb_ydg_switch_month_off);
		} else if (current_id == 1) {
			calendar.add(Calendar.DAY_OF_MONTH, -6);
			startTime = calendar.getTime().getTime();
			String strdate = sf.format(calendar.getTime()) + "-";
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			endTime = calendar.getTime().getTime();
			strdate = strdate + sf.format(calendar.getTime());
			textViewDay.setText(strdate);

			dayButton.setBackgroundResource(R.drawable.bb_ydg_switch_day_off);
			weekButton.setBackgroundResource(R.drawable.bb_ydg_switch_week_on);
			monthButton
					.setBackgroundResource(R.drawable.bb_ydg_switch_month_off);

		} else if (current_id == 2) {

			calendar.set(Calendar.DAY_OF_MONTH, 1);
			startTime = calendar.getTime().getTime();
			String strdate = sf.format(calendar.getTime()) + "-";
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = calendar.getTime().getTime();
			strdate = strdate + sf.format(calendar.getTime());
			textViewDay.setText(strdate);

			dayButton.setBackgroundResource(R.drawable.bb_ydg_switch_day_off);
			weekButton.setBackgroundResource(R.drawable.bb_ydg_switch_week_off);
			monthButton
					.setBackgroundResource(R.drawable.bb_ydg_switch_month_on);
		}

		// 设置账户和科目的背景颜色以及初始数据
		if (select == 0) {
			sbSubject.setBackgroundResource(R.drawable.bb_ydg_kemu_off);
			sbAccount.setBackgroundResource(R.drawable.bb_ydg_zhanghu_on);
			getAccountInfomation(startTime, endTime);
		} else if (select == 1) {
			sbSubject.setBackgroundResource(R.drawable.bb_ydg_kemu_on);
			sbAccount.setBackgroundResource(R.drawable.bb_ydg_zhanghu_off);
			getSubjectInfomation(startTime, endTime);
		}

		adapter = new ReportListAdapter(this, R.layout.report_forms_list_itme,
				relist);
		listview.setAdapter(adapter);
        //Log.d("ReportListActivity156", ""+calendar.getTime().getTime());
		/*Toast.makeText(
				ReportListActivity.this,
				"select=" + select + " ,current_id=" + current_id + " ,date="
						+ str, Toast.LENGTH_SHORT).show();*/
	}

	// 设置监听
	private void setListener() {
		backButton.setOnClickListener(this);
		dayButton.setOnClickListener(this);
		weekButton.setOnClickListener(this);
		monthButton.setOnClickListener(this);
		buttonLeft.setOnClickListener(this);
		buttonRight.setOnClickListener(this);
		sbSubject.setOnClickListener(this);
		sbAccount.setOnClickListener(this);

	}

	// 事件监听函数
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 返回按钮的事件监听
		case R.id.list_report_back:
			finish();
			break;
		case R.id.day:
			handleDay();
			
			break;
		case R.id.week:
			handleWeek();
			
			break;
		case R.id.month:
			handleMonth();
			
			break;
		case R.id.bleft:
			sub();
			
			break;
		case R.id.bright:
			add();
			
			break;
		case R.id.list_accout:
			select=0;
			sbSubject.setBackgroundResource(R.drawable.bb_ydg_kemu_off);
			sbAccount.setBackgroundResource(R.drawable.bb_ydg_zhanghu_on);
			getAccountInfomation(startTime, endTime);
			adapter.notifyDataSetChanged();
			
			break;
		case R.id.list_subject:
			select=1;
			sbSubject.setBackgroundResource(R.drawable.bb_ydg_kemu_on);
			sbAccount.setBackgroundResource(R.drawable.bb_ydg_zhanghu_off);
			
			getSubjectInfomation(startTime, endTime);
			adapter.notifyDataSetChanged();
			
			break;
		}
	}

	// 处理日的点击事件
	private void handleDay() {
		// 设置按钮的背景色
		dayButton.setBackgroundResource(R.drawable.bb_ydg_switch_day_on);
		weekButton.setBackgroundResource(R.drawable.bb_ydg_switch_week_off);
		monthButton.setBackgroundResource(R.drawable.bb_ydg_switch_month_off);
		current_id = 0;
		calendar = Calendar.getInstance();
		String str = sf.format(calendar.getTime());
		try {
			calendar.setTime(sf.parse(str));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		formatDate(0);//格式时间
		endTime = startTime;// 记录结束时间
		textViewDay.setText(str);
		if (select == 0) {
			getAccountInfomation(startTime, endTime);
		} else if (select == 1) {
			getSubjectInfomation(startTime, endTime);
		}
		adapter.notifyDataSetChanged();

	}

	// 处理周的点击事件
	private void handleWeek() {
		// 设置按钮的背景色
		dayButton.setBackgroundResource(R.drawable.bb_ydg_switch_day_off);
		weekButton.setBackgroundResource(R.drawable.bb_ydg_switch_week_on);
		monthButton.setBackgroundResource(R.drawable.bb_ydg_switch_month_off);
		current_id = 1;

		calendar = Calendar.getInstance();
		String strdate = sf.format(calendar.getTime());
		try {
			calendar.setTime(sf.parse(strdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int indexof = calendar.get(Calendar.DAY_OF_WEEK);

		boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
		// 获取周几
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		// 若一周第一天为星期天，则-1
		if (isFirstSunday) {
			weekDay = weekDay - 1;
			if (weekDay == 0) {
				weekDay = 7;
			}
		}

		// Log.d("ReportFormsActivity181", "the weekDay is "+weekDay);
		calendar.add(Calendar.DAY_OF_MONTH, -(weekDay - 1));

		startTime = calendar.getTime().getTime();
		//formatDate(0);//格式时间

		String str = sf.format(calendar.getTime()) + "-";
		calendar.add(Calendar.DAY_OF_MONTH, 6);

		endTime = calendar.getTime().getTime();
		//formatDate(1);//格式时间
		str = str + sf.format(calendar.getTime());
		textViewDay.setText(str);

		if (select == 0) {
			getAccountInfomation(startTime, endTime);
		} else if (select == 1) {
			getSubjectInfomation(startTime, endTime);
		}
		adapter.notifyDataSetChanged();

	}

	// 处理月的点击事件
	private void handleMonth() {
		// 设置按钮的背景色
		dayButton.setBackgroundResource(R.drawable.bb_ydg_switch_day_off);
		weekButton.setBackgroundResource(R.drawable.bb_ydg_switch_week_off);
		monthButton.setBackgroundResource(R.drawable.bb_ydg_switch_month_on);
		current_id = 2;

		calendar = Calendar.getInstance();
		String strdate = sf.format(calendar.getTime());
		try {
			calendar.setTime(sf.parse(strdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		startTime = calendar.getTime().getTime();// 记录开始时间
		//formatDate(0);//格式时间
		String str = sf.format(calendar.getTime()) + "-";
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		endTime = calendar.getTime().getTime();// 记录结束时间
		//formatDate(1);//格式时间
		str = str + sf.format(calendar.getTime());
		textViewDay.setText(str);
		if (select == 0) {
			getAccountInfomation(startTime, endTime);
		} else if (select == 1) {
			getSubjectInfomation(startTime, endTime);
		}
		adapter.notifyDataSetChanged();
	}

	// 日期增加
	private void add() {
		if (current_id == 0) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			startTime = calendar.getTime().getTime();// 记录开始时间
			endTime = startTime;// 记录结束时间
			String str = sf.format(calendar.getTime());
			textViewDay.setText(str);
			if (select == 0) {
				getAccountInfomation(startTime, endTime);
			} else if (select == 1) {
				getSubjectInfomation(startTime, endTime);
			}
			adapter.notifyDataSetChanged();

		} else if (current_id == 1) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			startTime = calendar.getTime().getTime();// 记录开始时间
			String str = sf.format(calendar.getTime()) + "-";
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			endTime = calendar.getTime().getTime();// 记录结束时间
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
			
			if (select == 0) {
				getAccountInfomation(startTime, endTime);
			} else if (select == 1) {
				getSubjectInfomation(startTime, endTime);
			}
			adapter.notifyDataSetChanged();
			
		} else if (current_id == 2) {
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			startTime = calendar.getTime().getTime();// 记录开始时间
			String str = sf.format(calendar.getTime()) + "-";
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = calendar.getTime().getTime();// 记录结束时间
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
			
			if (select == 0) {
				getAccountInfomation(startTime, endTime);
			} else if (select == 1) {
				getSubjectInfomation(startTime, endTime);
			}
			adapter.notifyDataSetChanged();
			
		}
	}

	// 日期减少
	private void sub() {
		if (current_id == 0) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			startTime = calendar.getTime().getTime();// 记录开始时间
			endTime = startTime;// 记录结束时间
			String str = sf.format(calendar.getTime());
			textViewDay.setText(str);
			if (select == 0) {
				getAccountInfomation(startTime, endTime);
			} else if (select == 1) {
				getSubjectInfomation(startTime, endTime);
			}
			adapter.notifyDataSetChanged();
		} else if (current_id == 1) {
			calendar.add(Calendar.DAY_OF_MONTH, -13);
			startTime = calendar.getTime().getTime();// 记录开始时间
			String str = sf.format(calendar.getTime()) + "-";
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			endTime = calendar.getTime().getTime();// 记录结束时间
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
			
			if (select == 0) {
				getAccountInfomation(startTime, endTime);
			} else if (select == 1) {
				getSubjectInfomation(startTime, endTime);
			}
			adapter.notifyDataSetChanged();
			
		} else if (current_id == 2) {
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			startTime = calendar.getTime().getTime();// 记录开始时间
			String str = sf.format(calendar.getTime()) + "-";
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			endTime = calendar.getTime().getTime();// 记录结束时间
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
			
			if (select == 0) {
				getAccountInfomation(startTime, endTime);
			} else if (select == 1) {
				getSubjectInfomation(startTime, endTime);
			}
			adapter.notifyDataSetChanged();
			
		}
	}

	// 获得科目信息
	private void getSubjectInfomation(long startTime, long endTime) {
		List<String[]> list = null;
		list = DBUtil.searchCostSubject(startTime, endTime);
		Date date = new Date(startTime);
		Log.d("ReportListActivity412", sf.format(date) + ";" + startTime);
		date = new Date(endTime);
		Log.d("ReportListActivity414", sf.format(date) + ";" + endTime);
		relist.clear();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				Log.i("ReportListActivity", "subject:" + list.get(i)[0] + " "
						+ "sum(amount)" + list.get(i)[1]);
				ReportList re = new ReportList();
				re.setShowSA(list.get(i)[0]);
				re.setImgOutput(R.drawable.bb_ydg_item_icon_otherexpenses);
				re.setMoney(list.get(i)[1]);
				re.setImgMoney(R.drawable.bb_ydg_money_cny);
				relist.add(re);
			}
		}

		if (list.size() == 0) {
			Toast.makeText(ReportListActivity.this, "没有相应支出",
					Toast.LENGTH_SHORT).show();
			return;
		}

	}

	// 获得账户信息
	private void getAccountInfomation(long startTime, long endTime) {
		List<String[]> list = null;
		list = DBUtil.searchCostAccount(startTime, endTime);
		relist.clear();

		if (list != null) {
			if (list.size() == 0) {
				Toast.makeText(ReportListActivity.this, "没有相应支出",
						Toast.LENGTH_SHORT).show();
				return;
			}
			for (int i = 0; i < list.size(); i++) {
				Log.i("ReportListActivity", "account:" + list.get(i)[0] + " "
						+ "sum(amount)" + list.get(i)[1]);
				ReportList re = new ReportList();
				re.setShowSA(list.get(i)[0]);
				re.setImgOutput(R.drawable.bb_ydg_item_icon_otherexpenses);
				re.setMoney(list.get(i)[1]);
				re.setImgMoney(R.drawable.bb_ydg_money_cny);
				relist.add(re);
			}
		}
	}

	//对时间进行格式化
	private void formatDate(int indexof){
		try {
			if(indexof==0){
			startTime = s.parse(s.format(calendar.getTime())).getTime();
			}else if(indexof==1){
			endTime = s.parse(s.format(calendar.getTime())).getTime();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 记录开始时间
	}
	
	
}
