package com.jack.managemoney;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jack.entity.CostSubject;
import com.jack.entity.InOutWay;
import com.jack.entity.IncomeSubject;
import com.jack.util.DBUtil;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 2015.03.20
 * 记账的Activity
 * */
public class Bookkeep extends Activity implements OnClickListener{

	private Button backButton=null;//返回
	private EditText moneyEdit=null;//输入金额
	private Spinner inoroutSpinner=null;//收入或支出下拉组件
	private Spinner subject=null;//科目
	private TextView datepick=null;//日期
	private Spinner model=null;//方式
	private EditText placeEdit=null;//地点
	private EditText remarkEdit=null;//备注
	private Button   saveButton=null;//保存
	private Button   readdButton=null;//在添加一笔
	
	private String ssubject;     //科目
    private String mode;        //方式
    private String szStr;       //日常收入、日常支出
    ArrayAdapter<String> adapter=null;//科目适配器
    ArrayAdapter<String> modeladapter=null;//科目适配器
	private String instrarrysubject[]=null;
	private String outstrarrysubject[]=null;
	private String incostmodel[]=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//全屏显示
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//强制竖屏
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.bookkeeping);
		//初始化组件
		init();
		//设置事件监听事件
		setListen();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.back1://监听返回按钮
			finish();//关闭目前的activity，并返回
			break;
		case R.id.save://监听保存按钮id
			Toast.makeText(Bookkeep.this, "你点击了保存按钮", 
					Toast.LENGTH_SHORT).show();
			saveData();
			break;
		case R.id.readd://监听再添一笔id
			Toast.makeText(Bookkeep.this, "你点击了再添一笔按钮", 
					Toast.LENGTH_SHORT).show();
			againSaveDate();
			break;
		}
	}

	
	//初始化组件
	private void init(){
		//获得组件
		backButton=(Button) findViewById(R.id.back1);//返回按钮
		moneyEdit=(EditText) findViewById(R.id.money);//输入money的组件
		inoroutSpinner=(Spinner) findViewById(R.id.spinner1);//获得收入支出下拉组件
		subject=(Spinner) findViewById(R.id.spinner2);//科目
		datepick=(TextView) findViewById(R.id.datepicker1);
		model=(Spinner) findViewById(R.id.spinner3);//方式
		placeEdit=(EditText) findViewById(R.id.place);//地点
		remarkEdit=(EditText) findViewById(R.id.remark);//备注
		saveButton=(Button) findViewById(R.id.save);//保存
		readdButton=(Button) findViewById(R.id.readd);//再增加一笔记录
		//设置组件的初始化值
		//格式化日期
		SimpleDateFormat   sDateFormat=new SimpleDateFormat("yyyy-MM-dd"); //日期格式
		String strDate=sDateFormat.format(new Date());//得到今天日期的字符串
		datepick.setText(strDate);
		
		//创建方式的适配器
		
		/*ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				getResources().getStringArray(R.array.mode)
				);*/
		//得到收入支出方式
		List<InOutWay> incostlist=DBUtil.getInOutWay();
		incostmodel=new String[incostlist.size()];
		for(int i=0;i<incostmodel.length;i++){
			incostmodel[i]=incostlist.get(i).getSubject();
		}
		ArrayAdapter<String> modeladapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				incostmodel
				);
		//设置适配器
		model.setAdapter(modeladapter);
		
		//得到收入科目
		List<IncomeSubject> inlist=DBUtil.getIncomeSubject();
		instrarrysubject=new String[inlist.size()];
		for(int i=0;i<instrarrysubject.length;i++){
			instrarrysubject[i]=inlist.get(i).getSubject();
		}
		//获得支出科目
		List<CostSubject> outlist=DBUtil.getCostSubject();
		outstrarrysubject=new String[outlist.size()];
		for(int i=0;i<outstrarrysubject.length;i++){
			outstrarrysubject[i]=outlist.get(i).getSubject();
		}
		
		
	}
	
	//设置监听
	private void setListen(){
		backButton.setOnClickListener(this);
		saveButton.setOnClickListener(this);
		readdButton.setOnClickListener(this);
		//收支事件监听
		inoroutSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//获取到下拉列表中选择的值
				szStr=parent.getItemAtPosition(position).toString();
				//进行收入支出的判断
				if("日常收入".equals(szStr)){
					//String[] array=getResources().getStringArray(R.array.sujectspinner1);
					//创建适配器
					adapter=new ArrayAdapter<String>(Bookkeep.this,
							android.R.layout.simple_spinner_item, instrarrysubject);
				}else{
					//String[] array=getResources().getStringArray(R.array.sujectspinner2);
					//创建适配器
					adapter=new ArrayAdapter<String>(Bookkeep.this,
							android.R.layout.simple_spinner_item, outstrarrysubject);
				}
				//设置适配器
				subject.setAdapter(adapter);
				Toast.makeText(Bookkeep.this, "你选择的是："+szStr, 
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//科目事件监听
		subject.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ssubject=parent.getItemAtPosition(position).toString();
				Toast.makeText(Bookkeep.this, "你选择的是："+ssubject, 
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//方式事件监听
		model.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mode=parent.getItemAtPosition(position).toString();
				Toast.makeText(Bookkeep.this, "你选择的是："+mode, 
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//日期监听
		datepick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//showDialog();
				showDateDialog().show();
			}
		});
		
		
		
	}
	
	//显示日期选择对话框
	private Dialog showDateDialog(){
		Calendar c=Calendar.getInstance();//获取日期对象
		Dialog dialog = new DatePickerDialog(this,
				new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						datepick.setText(" "+year+"-"+((monthOfYear+1)<10?"0"+(monthOfYear+1):""+(monthOfYear+1))+"-"+(dayOfMonth<10?"0"+dayOfMonth:""+dayOfMonth));
					}
				},
				c.get(Calendar.YEAR),
				c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH)
				);
		return dialog;
	}
	
	
	//保存数据到后台
	private void save(){
		
		//时间格式化对象
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd"); 
		Date date=null;
		try {
			date=formatter.parse(datepick.getText().toString().trim());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String place=placeEdit.getText().toString().trim();//获得地点
		String remark=remarkEdit.getText().toString().trim();//获得备注
		if("".equals(moneyEdit.getText().toString().trim())){
			Toast.makeText(this, "金额不能为空", Toast.LENGTH_LONG).show();
			return;
		}else{
			Double money=Double.parseDouble(moneyEdit.getText().toString().trim());//输入金额
			if("日常收入".equals(szStr)){
				//保存到收入表
				DBUtil.insertZhangWu("ShouRu", 
						money, ssubject, date, mode, place, remark);
			}else{
				//保存到花费表
				DBUtil.insertZhangWu("CostTable", 
						money, ssubject, date, mode, place, remark);
			}
			Toast.makeText(this, "记账成功", Toast.LENGTH_LONG).show();
		}
		
		
	}
	
	
	private void saveData(){
		save();
		//finish();//关闭activity
	}
	
	
	
	private void againSaveDate(){
		//saveDate();
		save();//保存数据
		moneyEdit.setText("");
		//设置组件的初始化值
		//格式化日期
		SimpleDateFormat   sDateFormat=new SimpleDateFormat("yyyy-MM-dd"); //日期格式
		String strDate=sDateFormat.format(new Date());//得到今天日期的字符串
		datepick.setText(strDate);
			
		inoroutSpinner.setSelection(0);
		String[] array=getResources().getStringArray(R.array.sujectspinner1);
		ArrayAdapter<String> adapter1=new ArrayAdapter<String>(Bookkeep.this,
				android.R.layout.simple_spinner_item, array);
		subject.setAdapter(adapter1);
		
		//创建方式的适配器
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item,
					getResources().getStringArray(R.array.mode)
					);
		//设置适配器
		model.setAdapter(adapter);
		
		placeEdit.setText("");
		remarkEdit.setText("");
		
	}
	
	
}
