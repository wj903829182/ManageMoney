package com.jack.managemoney;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jack.adapter.DilyexpenseAdapter;
import com.jack.entity.CostTable;
import com.jack.entity.ShouRu;
import com.jack.fragment.DetailDialogFragment;
import com.jack.util.DBUtil;
import com.jack.util.Dilyexpense;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

public class DilyecpenseActivity extends Activity {

	private Button bBack = null;// 返回
	private RadioGroup radioGroup = null;// 单选
	private Spinner spninner = null;// 下拉列表
	private ArrayAdapter<String> sadapter = null;// 下拉列表的适配器
	private ListView listView = null;// 列表
	private DilyexpenseAdapter listAdapter = null;// 列表适配器
	private List<Dilyexpense> list = new ArrayList<Dilyexpense>();

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
		setContentView(R.layout.dily_expense);

		// 初始化,获得组件
		init();
		// 设置事件响应
		setListener();

	}

	// 初始函数
	private void init() {
		// 获得组件
		bBack = (Button) findViewById(R.id.back2);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup1);
		spninner = (Spinner) findViewById(R.id.spinnerChoice);
		listView = (ListView) findViewById(R.id.listView1);
		// 初始适配器
		sadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, getResources()
						.getStringArray(R.array.StatisticIn));
		spninner.setAdapter(sadapter);

		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		String sd = sDateFormat.format(new Date());
		long date = getDateTime(sd, 1);
		if (date != 0) {
			getDilyListIn(DBUtil.getTodayDetailsShouRu(date));
		}
		listAdapter = new DilyexpenseAdapter(this,
				R.layout.dily_expense_listview_item, list);
		listView.setAdapter(listAdapter);

	}

	// 监听的函数
	private void setListener() {
		// 返回监听
		bBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 设置事件响应
		// 单选监听
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (R.id.radio1 == checkedId) {
					Toast.makeText(DilyecpenseActivity.this, "你选择的是收入统计",
							Toast.LENGTH_SHORT).show();
					sadapter = new ArrayAdapter<String>(
							DilyecpenseActivity.this,
							android.R.layout.simple_spinner_item,
							getResources().getStringArray(R.array.StatisticIn));

				} else if (R.id.radio2 == checkedId) {
					Toast.makeText(DilyecpenseActivity.this, "你选择的是支出统计",
							Toast.LENGTH_SHORT).show();
					sadapter = new ArrayAdapter<String>(
							DilyecpenseActivity.this,
							android.R.layout.simple_spinner_item,
							getResources().getStringArray(R.array.StatisticOut));

				}
				spninner.setAdapter(sadapter);
			}
		});
		// 设置事件响应
		// 下拉列表的监听
		spninner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				String str = sadapter.getItem(position).toString().trim();
				String sd;
				long date;
				if (((RadioButton) findViewById(R.id.radio1)).isChecked()) {
					if ("今日收入情况".equals(str)) {
						sd = getTime(1);
						date = getDateTime(sd, 1);
						if (date != 0) {
							getDilyListIn(DBUtil.getTodayDetailsShouRu(date));
						}
					} else if ("本月收入情况".equals(str)) {
						sd = getTime(2);
						date = getDateTime(sd, 2);
						if (date != 0) {
							getDilyListIn(DBUtil.getMonthYearDetails(date));
						}
					} else if ("上月收入情况".equals(str)) {
						sd = getTime(3);
						String s2 = getTime(0);
						date = getDateTime(sd, 1);
						long date2 = getDateTime(s2, 1);
						if (date != 0 && date2 != 0) {
							getDilyListIn(DBUtil.getLastMonthYearDetailsIn(
									date, date2));
						}
					} else if ("前三个月收入情况".equals(str)) {
						sd = getTime(4);
						String s2 = getTime(0);
						date = getDateTime(sd, 1);
						long date2 = getDateTime(s2, 1);
						if (date != 0 && date2 != 0) {
							getDilyListIn(DBUtil.getLastThreeMonthDetailsIn(
									date, date2));
						}

					} else if ("今年收入总情况".equals(str)) {
						sd = getTime(5);
						date = getDateTime(sd, 1);
						if (date != 0) {
							getDilyListIn(DBUtil.getThisYearIn(date));
						}

					} else if ("去年收入总情况".equals(str)) {
						sd = getTime(6);
						String s2 = getTime(7);
						date = getDateTime(sd, 1);
						long date2 = getDateTime(s2, 1);
						if (date != 0 && date2 != 0) {
							getDilyListIn(DBUtil.getLastYearIn(date, date2));
						}
					} else if ("全部收入情况".equals(str)) {
						getDilyListIn(DBUtil.getAllDetailsIn());
					}

				} else {
					if ("今日支出情况".equals(str)) {
						sd = getTime(1);
						date = getDateTime(sd, 1);
						if (date != 0) {
							getDilyListCost(DBUtil
									.getTodayDetailsCostTable(date));
						}

					} else if ("本月支出情况".equals(str)) {
						sd = getTime(2);
						date = getDateTime(sd, 2);
						if (date != 0) {
							getDilyListCost(DBUtil
									.getMonthYearDetailsCost(date));
						}

					} else if ("上月支出情况".equals(str)) {
						sd = getTime(3);
						String s2 = getTime(0);
						date = getDateTime(sd, 1);
						long date2 = getDateTime(s2, 1);
						if (date != 0 && date2 != 0) {
							getDilyListCost(DBUtil.getLastMonthYearDetailsCost(
									date, date2));
						}

					} else if ("前三个月支出情况".equals(str)) {
						sd = getTime(4);
						String s2 = getTime(0);
						date = getDateTime(sd, 1);
						long date2 = getDateTime(s2, 1);
						if (date != 0 && date2 != 0) {
							getDilyListCost(DBUtil
									.getLastThreeMonthDetailsCost(date, date2));
						}

					} else if ("今年支出总情况".equals(str)) {
						sd = getTime(5);
						date = getDateTime(sd, 1);
						if (date != 0) {
							getDilyListCost(DBUtil.getThisYearCost(date));
						}

					} else if ("去年支出总情况".equals(str)) {
						sd = getTime(6);
						String s2 = getTime(7);
						date = getDateTime(sd, 1);
						long date2 = getDateTime(s2, 1);
						if (date != 0 && date2 != 0) {
							getDilyListCost(DBUtil.getLastYearCost(date, date2));
						}
					} else if ("全部支出情况".equals(str)) {
						getDilyListCost(DBUtil.getAllDetailsCost());

					}

				}
				listAdapter.notifyDataSetChanged();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		// 对ListView进行监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Dilyexpense dilyexpense = list.get(position);
				Toast.makeText(
						DilyecpenseActivity.this,
						"科目：" + dilyexpense.getSubject() + ";" + "金额:"
								+ dilyexpense.getMoney() + ";" + "方式:"
								+ dilyexpense.getModel() + ";" + "日期:"
								+ dilyexpense.getDate(), Toast.LENGTH_SHORT)
						.show();
				// 显示对话框

				DetailDialogFragment.setTextString(dilyexpense.getSubject(),
						dilyexpense.getMoney(), dilyexpense.getModel(),
						dilyexpense.getDate());
				DetailDialogFragment dialogFragment = new DetailDialogFragment();
				dialogFragment.show(getFragmentManager(), "dialogf");

			}

		});

	}

	// 显示对话框
	private void showDateDialog() {

		// Dialog dialog = new Date
		// Dialog dialog=new Dial
		/*
		 * DetailDialogFragment dialogFragment=new DetailDialogFragment();
		 * //dialogFragment dialogFragment.setTextString(suject, money, model,
		 * date); dialogFragment.show(getFragmentManager(), "dialogf");
		 */
	}

	private void getDilyListIn(List<ShouRu> listIn) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		// String strDate=sDateFormat.format(new Date());//得到今天日期的字符串
		if (listIn != null) {
			list.clear();
			for (int i = 0; i < listIn.size(); i++) {
				ShouRu sr = listIn.get(i);
				Dilyexpense de = new Dilyexpense();
				de.setSubject(sr.getSubject());
				de.setMoney(String.valueOf(sr.getAmount()));
				de.setModel(sr.getMode());
				de.setDate(sDateFormat.format(sr.getDate()));
				list.add(de);
			}
		} else {
			list.clear();
		}
		// listAdapter.notifyDataSetChanged();
	}

	private void getDilyListCost(List<CostTable> listout) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		// String strDate=sDateFormat.format(new Date());//得到今天日期的字符串
		if (listout != null) {// 如果查询出了数据
			list.clear();
			for (int i = 0; i < listout.size(); i++) {
				CostTable sr = listout.get(i);
				Dilyexpense de = new Dilyexpense();
				de.setSubject(sr.getSubject());
				de.setMoney(String.valueOf(sr.getAmount()));
				de.setModel(sr.getMode());
				de.setDate(sDateFormat.format(sr.getDate()));
				list.add(de);
			}
		} else {
			list.clear();// 如果没有查询出数据则进行清空
		}

	}

	// 根据传入的字符串，得到时间戳
	private long getDateTime(String str, int index) {
		SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
		SimpleDateFormat sDateFormat2 = new SimpleDateFormat("yyyy-MM"); // 日期格式
		// String sd =sDateFormat.format(new Date());//
		Date date = null;
		try {
			switch (index) {
			case 1:
				date = sDateFormat1.parse(str);
				break;

			case 2:
				date = sDateFormat2.parse(str);
				break;
			case 3:
				break;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (date == null) {
			return 0;
		}
		return date.getTime();
	}

	// 获取到时间
	private String getTime(int id) {
		String time = null;
		SimpleDateFormat sDateFormat = null;
		Date date = new Date();
		Calendar c = Calendar.getInstance();// 获取日期对象
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		/*
		 * 0:上月的最后一天 1:今日，2：本月，3：上月，4：前三月 5:今年，6：去年，7:去你的最后一天
		 */
		switch (id) {
		case 0:
			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			c.add(Calendar.MONTH, -1);
			c.set(Calendar.DAY_OF_MONTH,
					c.getActualMaximum(Calendar.DAY_OF_MONTH));
			time = sDateFormat.format(c.getTime());
			Log.d("DilyecpenseActivity", time);
			break;
		case 1:
			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			time = sDateFormat.format(date).trim();
			break;

		case 2:
			sDateFormat = new SimpleDateFormat("yyyy-MM");
			time = sDateFormat.format(date).trim();
			break;

		case 3:

			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// c.clear();
			// c.set(2012, 2, 31);
			// Log.d("DilyecpenseActivity", sDateFormat.format(c.getTime()));
			// c.add(Calendar.MONTH, -1);
			// Log.d("DilyecpenseActivity",sDateFormat.format(c.getTime()));
			c.add(Calendar.MONTH, -1);
			c.set(Calendar.DAY_OF_MONTH, 1);
			time = sDateFormat.format(c.getTime());
			Log.d("DilyecpenseActivity", time);
			break;

		case 4:
			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			c.add(Calendar.MONTH, -3);
			c.set(Calendar.DAY_OF_MONTH, 1);
			time = sDateFormat.format(c.getTime());
			Log.d("DilyecpenseActivity", time);
			break;

		case 5:
			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			time = sDateFormat.format(c.getTime());
			Log.d("DilyecpenseActivity", time);
			break;

		case 6:
			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			c.add(Calendar.YEAR, -1);
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DAY_OF_MONTH, 1);
			time = sDateFormat.format(c.getTime());
			Log.d("DilyecpenseActivity", time);
			break;

		case 7:

			sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			c.add(Calendar.YEAR, -1);
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DAY_OF_MONTH,
					c.getActualMaximum(Calendar.DAY_OF_MONTH));
			time = sDateFormat.format(c.getTime());
			Log.d("DilyecpenseActivity", time);
			break;
		}
		return time;
	}

}
