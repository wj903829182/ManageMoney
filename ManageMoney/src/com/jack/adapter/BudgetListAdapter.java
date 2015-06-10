package com.jack.adapter;

import java.util.List;

import com.jack.managemoney.R;
import com.jack.util.BudgetBean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class BudgetListAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private List<BudgetBean> list;
	public BudgetListAdapter(Context context,List<BudgetBean> list){
		layoutInflater=LayoutInflater.from(context);
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		BudgetBean bb=(BudgetBean) getItem(position);
		if(convertView==null){
			convertView=layoutInflater.inflate(R.layout.budget_list_item, null);
			viewHolder=new ViewHolder();
			viewHolder.tv_subject=(TextView) convertView.findViewById(R.id.tv_subject);
			viewHolder.pb=(ProgressBar) convertView.findViewById(R.id.budget_pb);
			viewHolder.tv_total=(TextView) convertView.findViewById(R.id.tv_total);
			viewHolder.tv_balance=(TextView) convertView.findViewById(R.id.tv_balance);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		viewHolder.tv_subject.setText(bb.getTv_subject());
		viewHolder.pb.setProgress(bb.getPb());
		if(bb.getTv_total()==0){
			viewHolder.tv_total.setText("");
		}else{
			viewHolder.tv_total.setText(String.valueOf(bb.getTv_total()));
		}
		//viewHolder.tv_total.setText(String.valueOf(bb.getTv_total()));
		if(bb.getTv_total()==0){
			viewHolder.tv_balance.setText("预算未设置");
		}else{
			viewHolder.tv_balance.setText(String.valueOf(bb.getTv_balance()));
		}
		//viewHolder.tv_balance.setText(String.valueOf(bb.getTv_balance()));
		return convertView;
	}
	
	class ViewHolder{
		TextView tv_subject;
		ProgressBar pb;
		TextView tv_total;
		TextView tv_balance;
	}
	

}
