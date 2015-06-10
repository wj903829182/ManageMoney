package com.jack.managemoney;

import java.util.ArrayList;
import java.util.List;

import com.jack.adapter.BudgetListAdapter;
import com.jack.entity.BudgetAll;
import com.jack.entity.BudgetBalance;
import com.jack.entity.CostSubject;
import com.jack.util.BudgetBean;
import com.jack.util.DBUtil;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BudgetActivity extends Activity {

	private Button back;// 返回按钮
	private TextView tv_total_money;// 总预算
	private TextView tv_budget_balance;// 预算余额
	private ListView listview;// 列表
	private BudgetListAdapter budgetlistAdapter;
	private List<BudgetBean> list=new ArrayList<BudgetBean>();
	// 支出的科目
	private List<CostSubject> costSubjectal = null;
	// 预算总额
	static List<BudgetAll> budgetAll = null;
	// 预算余额
	static List<BudgetBalance> budgetBalance = null;
	//ListView的适配器对象
    private BudgetListAdapter listAdapter=null;
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
		// 设置布局文件
		setContentView(R.layout.budget);
		// 调用初始化函数
		init();
		// 设置监听
		setListener();
	}

	// 初始化函数
	private void init() {
		// 初始化组件
		back = (Button) findViewById(R.id.budget_back);
		tv_total_money = (TextView) findViewById(R.id.text_budget_total_money);
		tv_budget_balance = (TextView) findViewById(R.id.text_budget_balance);
		listview = (ListView) findViewById(R.id.listview_budeget);

		// 获取到初始化数据
		costSubjectal = DBUtil.getCostSubject();
		budgetAll = DBUtil.getBudgetAll();
		budgetBalance = DBUtil.getBudgetBalance();
		//初始化适配器的list列表
		for(int i=0;i<costSubjectal.size();i++){
			//创建列表项对象
			BudgetBean bb=new BudgetBean();
			//设置科目
			bb.setTv_subject(costSubjectal.get(i).getSubject());
			//设置总预算
			bb.setTv_total(budgetAll.get(i).getAmount());
			//设置预算余额
			bb.setTv_balance(budgetBalance.get(i).getAmount());
			//设置进度条位置
			bb.setPb(0);
			list.add(bb);//添加到列表
		}
		
		//创建适配器
		listAdapter=new BudgetListAdapter(BudgetActivity.this,
				list);
		//给listView设置适配器
		listview.setAdapter(listAdapter);
		

	}

	// 设置监听
	private void setListener() {
		// 返回按钮事件监听处理
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BudgetActivity.this.finish();// 关闭当前的Activity
			}
		});
		
		//对ListView的子项的点击事件进行监听
		listview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Toast.makeText(BudgetActivity.this, 
						"预算金额输入功能还没实现", Toast.LENGTH_SHORT).show();
			}});
		
		

	}

}
