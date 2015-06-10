package com.jack.managemoney;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jack.util.AccountChart;
import com.jack.util.DBUtil;
import com.jack.util.SubjectChart;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 2015.04.03
 * 报表的Activity
 * */
public class ReportFormsActivity extends Activity {

	private int select = 1;// 0表示账户，1表示科目
	private int current_id = 2;// 0表示每天报表，1表示每周报表，2表示每月报表

	private Button backButton = null;// 返回按钮
	private Button buttonLeft = null;// 减少按钮
	private Button buttonRight = null;// 增加少按钮
	private TextView textViewDay = null;// 显示日期
	private Calendar calendar = null;// 增加按钮
	private RadioGroup radioGroup = null;// 单选按钮

	private Button dayButton = null;// 日报表
	private Button weekButton = null;// 周报表
	private Button monthButton = null;// 月报表

	private Button showButton = null;// 以图表显示的按钮
	private Button showhButtonList = null;// 以列表显示的按钮
	SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

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
		setContentView(R.layout.report_forms);// 报表界面

		// 初始组件
		init();
		// 设置监听
		setListener();

	}

	// 初始
	private void init() {
		// 初始化组件
		backButton = (Button) findViewById(R.id.report_back);
		buttonLeft = (Button) findViewById(R.id.button_left);
		buttonRight = (Button) findViewById(R.id.button_right);
		textViewDay = (TextView) findViewById(R.id.textview_date);
		radioGroup = (RadioGroup) findViewById(R.id.radio_group1);
		dayButton = (Button) findViewById(R.id.day_report_forms);
		weekButton = (Button) findViewById(R.id.week_report_forms);
		monthButton = (Button) findViewById(R.id.month_report_forms);
		showButton = (Button) findViewById(R.id.show_report_forms);
		showhButtonList = (Button) findViewById(R.id.show_report_forms_list);
		// 初始时间
		calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String str = sf.format(calendar.getTime()) + "-";
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		str = str + sf.format(calendar.getTime());
		textViewDay.setText(str);
	}

	// 设置监听
	private void setListener() {
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ReportFormsActivity.this.finish();
			}
		});

		buttonLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 减少
				sub();
			}
		});

		buttonRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 增加
				add();
			}

		});

		// 单选按钮监听
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (R.id.radion_account == checkedId) {
					select = 0;
				} else {
					select = 1;
				}
				Toast.makeText(ReportFormsActivity.this,
						"the checkid is select : " + select, Toast.LENGTH_SHORT)
						.show();
			}

		});

		dayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				current_id = 0;
				dayButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg_active);
				weekButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg);
				monthButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg);
				calendar = Calendar.getInstance();
				String str = sf.format(calendar.getTime());
				textViewDay.setText(str);
				Toast.makeText(ReportFormsActivity.this,
						"你单击的是每天报表  current_id：" + current_id,
						Toast.LENGTH_SHORT).show();
			}

		});

		weekButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				current_id = 1;
				dayButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg);
				weekButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg_active);
				monthButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg);
				calendar = Calendar.getInstance();
				//calendar.setFirstDayOfWeek(Calendar.MONDAY);
				//int indexof = calendar.getFirstDayOfWeek();
				int indexof=calendar.get(Calendar.DAY_OF_WEEK);
				
				boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
				//获取周几
				int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
				//若一周第一天为星期天，则-1
				if(isFirstSunday){
				  weekDay = weekDay - 1;
				  if(weekDay == 0){
				    weekDay = 7;
				  }
				}	
				
				Log.d("ReportFormsActivity181", "the weekDay is "+weekDay);
				calendar.add(Calendar.DAY_OF_MONTH, -(weekDay - 1));
				String str = sf.format(calendar.getTime()) + "-";
				calendar.add(Calendar.DAY_OF_MONTH, 6);
				str = str + sf.format(calendar.getTime());
				textViewDay.setText(str);
				Toast.makeText(ReportFormsActivity.this,
						"你单击的是每周报表 current_id： " + current_id,
						Toast.LENGTH_SHORT).show();
			}

		});

		monthButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				current_id = 2;
				dayButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg);
				weekButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg);
				monthButton
						.setBackgroundResource(R.drawable.tx_top_normal_btn_bg_active);

				// 初始时间
				calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				String str = sf.format(calendar.getTime()) + "-";
				calendar.set(Calendar.DAY_OF_MONTH,
						calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				str = str + sf.format(calendar.getTime());
				textViewDay.setText(str);
				Toast.makeText(ReportFormsActivity.this,
						"你单击的是每月报表  current_id:" + current_id,
						Toast.LENGTH_SHORT).show();
			}

		});

		showButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showChart();
			}

		});

		showhButtonList.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showListView();
				
			}
		});

	}

	private void add() {
		if (current_id == 0) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String str = sf.format(calendar.getTime());
			textViewDay.setText(str);

		} else if (current_id == 1) {
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			String str = sf.format(calendar.getTime()) + "-";
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
		} else if (current_id == 2) {
			calendar.add(Calendar.MONTH, 1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String str = sf.format(calendar.getTime()) + "-";
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
		}
	}

	private void sub() {
		if (current_id == 0) {
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			String str = sf.format(calendar.getTime());
			textViewDay.setText(str);
		} else if (current_id == 1) {
			calendar.add(Calendar.DAY_OF_MONTH, -13);
			String str = sf.format(calendar.getTime()) + "-";
			calendar.add(Calendar.DAY_OF_MONTH, 6);
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
		} else if (current_id == 2) {
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			String str = sf.format(calendar.getTime()) + "-";
			calendar.set(Calendar.DAY_OF_MONTH,
					calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			str = str + sf.format(calendar.getTime());
			textViewDay.setText(str);
		}
	}

	private void showChart() {
		Intent intent = null;
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

		if (select == 0) {

			String costMode[] = null;
			double costMoney[] = null;
			if (current_id == 0) {
				String str = s.format(calendar.getTime());
				long startDate = 0;
				long endDate = 0;
				List<String[]> list = null;
				try {
					endDate = s.parse(str).getTime();
					startDate = endDate;
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (startDate != 0 && endDate != 0) {
					list = DBUtil.searchCostAccount(startDate, endDate);
				}

				if (list != null) {
					costMode = new String[list.size()];
					costMoney = new double[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Log.i("ReportFormsActivity",
								"subject:" + list.get(i)[0] + " "
										+ "sum(amount)" + list.get(i)[1]);
						costMode[i] = list.get(i)[0];
						costMoney[i] = Double.parseDouble(list.get(i)[1]);
					}
				}
				if (list.size() == 0) {
					Toast.makeText(ReportFormsActivity.this, "没有相应支出",
							Toast.LENGTH_SHORT).show();
					return;
				}
			} else if (current_id == 1) {

				String str = s.format(calendar.getTime());
				long startDate = 0;
				long endDate = 0;
				List<String[]> list = null;
				try {
					endDate = s.parse(str).getTime();
					calendar.add(Calendar.DAY_OF_MONTH, -6);
					str = s.format(calendar.getTime());
					startDate = s.parse(str).getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (startDate != 0 && endDate != 0) {
					list = DBUtil.searchCostAccount(startDate, endDate);
				}

				if (list != null) {
					costMode = new String[list.size()];
					costMoney = new double[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Log.i("ReportFormsActivity",
								"subject:" + list.get(i)[0] + " "
										+ "sum(amount)" + list.get(i)[1] + "-"
										+ calendar.get(Calendar.DAY_OF_MONTH));
						costMode[i] = list.get(i)[0];
						costMoney[i] = Double.parseDouble(list.get(i)[1]);

					}
				}
				if (list.size() == 0) {
					Toast.makeText(ReportFormsActivity.this, "没有相应支出",
							Toast.LENGTH_SHORT).show();
					return;
				}

			} else if (current_id == 2) {
				String str = s.format(calendar.getTime());
				long startDate = 0;
				long endDate = 0;
				List<String[]> list = null;
				try {
					endDate = s.parse(str).getTime();
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					str = s.format(calendar.getTime());
					startDate = s.parse(str).getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (startDate != 0 && endDate != 0) {
					list = DBUtil.searchCostAccount(startDate, endDate);
				}

				if (list != null) {
					costMode = new String[list.size()];
					costMoney = new double[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Log.i("ReportFormsActivity",
								"subject:" + list.get(i)[0] + " "
										+ "sum(amount)" + list.get(i)[1]);
						costMode[i] = list.get(i)[0];
						costMoney[i] = Double.parseDouble(list.get(i)[1]);

					}
				}
				if (list.size() == 0) {
					Toast.makeText(ReportFormsActivity.this, "没有相应支出",
							Toast.LENGTH_SHORT).show();
					return;
				}

			}
			Toast.makeText(ReportFormsActivity.this, "你选择的是账户柱状图:",
					Toast.LENGTH_SHORT).show();
			intent = new AccountChart(costMode, costMoney)
					.execute(ReportFormsActivity.this);
			startActivity(intent);

		} else if (select == 1) {
			String costSubject[] = null;
			double costMoney[] = null;
			if (current_id == 0) {
				String str = s.format(calendar.getTime());
				long startDate = 0;
				long endDate = 0;
				List<String[]> list = null;
				try {
					endDate = s.parse(str).getTime();
					startDate = endDate;
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (startDate != 0 && endDate != 0) {
					list = DBUtil.searchCostSubject(startDate, endDate);
				}

				if (list != null) {
					costSubject = new String[list.size()];
					costMoney = new double[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Log.i("ReportFormsActivity",
								"subject:" + list.get(i)[0] + " "
										+ "sum(amount)" + list.get(i)[1]);
						costSubject[i] = list.get(i)[0];
						costMoney[i] = Double.parseDouble(list.get(i)[1]);
					}
				}
				if (list.size() == 0) {
					Toast.makeText(ReportFormsActivity.this, "没有相应支出",
							Toast.LENGTH_SHORT).show();
					return;
				}
			} else if (current_id == 1) {

				String str = s.format(calendar.getTime());
				long startDate = 0;
				long endDate = 0;
				List<String[]> list = null;
				try {
					endDate = s.parse(str).getTime();
					calendar.add(Calendar.DAY_OF_MONTH, -6);
					str = s.format(calendar.getTime());
					startDate = s.parse(str).getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (startDate != 0 && endDate != 0) {
					list = DBUtil.searchCostSubject(startDate, endDate);
				}

				if (list != null) {
					costSubject = new String[list.size()];
					costMoney = new double[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Log.i("ReportFormsActivity",
								"subject:" + list.get(i)[0] + " "
										+ "sum(amount)" + list.get(i)[1] + "-"
										+ calendar.get(Calendar.DAY_OF_MONTH));
						costSubject[i] = list.get(i)[0];
						costMoney[i] = Double.parseDouble(list.get(i)[1]);

					}
				}
				if (list.size() == 0) {
					Toast.makeText(ReportFormsActivity.this, "没有相应支出",
							Toast.LENGTH_SHORT).show();
					return;
				}

			} else if (current_id == 2) {
				String str = s.format(calendar.getTime());
				long startDate = 0;
				long endDate = 0;
				List<String[]> list = null;
				try {
					endDate = s.parse(str).getTime();
					calendar.set(Calendar.DAY_OF_MONTH, 1);
					str = s.format(calendar.getTime());
					startDate = s.parse(str).getTime();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (startDate != 0 && endDate != 0) {
					list = DBUtil.searchCostSubject(startDate, endDate);
				}

				if (list != null) {
					costSubject = new String[list.size()];
					costMoney = new double[list.size()];
					for (int i = 0; i < list.size(); i++) {
						Log.i("ReportFormsActivity",
								"subject:" + list.get(i)[0] + " "
										+ "sum(amount)" + list.get(i)[1]);
						costSubject[i] = list.get(i)[0];
						costMoney[i] = Double.parseDouble(list.get(i)[1]);

					}
				}
				if (list.size() == 0) {
					Toast.makeText(ReportFormsActivity.this, "没有相应支出",
							Toast.LENGTH_SHORT).show();
					return;
				}

			}
			Toast.makeText(ReportFormsActivity.this, "你选择的是科目柱状图:",
					Toast.LENGTH_SHORT).show();
			intent = new SubjectChart(costSubject, costMoney)
					.execute(ReportFormsActivity.this);
			startActivity(intent);
		}

	}

	//使用列表的形式进行显示
	private void showListView(){
		Intent intent=null;
		intent=new Intent(ReportFormsActivity.this,ReportListActivity.class);
		intent.putExtra("select", select);
		intent.putExtra("current_id", current_id);
		String str=sf.format(calendar.getTime());
		intent.putExtra("date", str);
		//bundle.put
		startActivity(intent);
		Toast.makeText(ReportFormsActivity.this, "你选择的是List报表:",
				Toast.LENGTH_SHORT).show();
	}
	
	
}
