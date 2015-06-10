package com.jack.adapter;

import java.util.List;

import com.jack.managemoney.R;
import com.jack.util.Dilyexpense;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DilyexpenseAdapter extends ArrayAdapter<Dilyexpense>{
	private int resourceId;
	public DilyexpenseAdapter(Context context, int textViewResourceId,
			List<Dilyexpense> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId=textViewResourceId;
	}

	
	
	
	//重写了父类的构造函数，用于将上下文，ListView子项布局的id和数据都传进来。 
    //重写getView方法
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		/* 
		* 重写getView方法，这个方法在每个子项被滚动到屏幕内的时候会被调用，在getView方法中 
		，首先通过getItem方法得到当前项的Fruit实例，然后使用LayoutInflater来为这个子项加载 
		* 我们传入的布局，接着调用View的findViewById方法分别获取到TextView的实例， 
		* 并分别调用他们的setText方法来设置显示的文字，最后返回布局 
		* */  
		
		Dilyexpense dilyexpense=getItem(position);//获取当前项的Dilyexpense实例  
		//初始话ListView的子项布局 
        View view;
        ViewHolder viewHolder;
        
        if(convertView==null){
        	//初始话ListView的子项布局
        	view=LayoutInflater.from(getContext()).inflate(resourceId, null);
        	
        	viewHolder=new ViewHolder();
        	viewHolder.tsubject=(TextView) view.findViewById(R.id.t_subject);  
        	viewHolder.tmoney=(TextView) view.findViewById(R.id.t_money); 
        	viewHolder.tmodel=(TextView) view.findViewById(R.id.t_model);  
        	viewHolder.tdate=(TextView) view.findViewById(R.id.t_date); 
        	view.setTag(viewHolder);//将ViewHolder存储在View中 
        	
        }else{
        	view=convertView;
        	viewHolder=(ViewHolder) view.getTag();//重新获取ViewHolder 
        	
        }
        viewHolder.tsubject.setText(dilyexpense.getSubject()); 
        viewHolder.tmoney.setText(dilyexpense.getMoney());
        viewHolder.tmodel.setText(dilyexpense.getModel());
        viewHolder.tdate.setText(dilyexpense.getDate());
       
		return view;  
	}

	class ViewHolder{
		TextView tsubject;  
		TextView tmoney; 
		TextView tmodel;  
		TextView tdate;  
	}

}
