package com.jack.adapter;

import java.util.List;

import com.jack.managemoney.R;
import com.jack.util.ReportList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ReportListAdapter extends ArrayAdapter<ReportList>{

	private int resourceId;
	public ReportListAdapter(Context context, int textViewResourceId,
			List<ReportList> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		resourceId=textViewResourceId;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=null;
		ViewHolder viewHolder=null;
		ReportList report=getItem(position);
		if(convertView==null){
			view =LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder=new ViewHolder();
			viewHolder.imgOut=(ImageView) view.findViewById(R.id.image_output);
			viewHolder.tSA=(TextView) view.findViewById(R.id.tv_showsa);
			viewHolder.money=(TextView) view.findViewById(R.id.tv_showmoney);
			viewHolder.imgMoney=(ImageView) view.findViewById(R.id.image_money);
			view.setTag(viewHolder);
		}else{
			view=convertView;
			viewHolder=(ViewHolder) view.getTag();
		}
		
		//viewHolder.imgOut.setImageResource(R.drawable.bb_ydg_item_icon_otherexpenses);
		viewHolder.imgOut.setImageDrawable(getContext().
				getResources().getDrawable(R.drawable.bb_ydg_item_icon_otherexpenses));
		viewHolder.tSA.setText(report.getShowSA());
		viewHolder.money.setText(report.getMoney());
		viewHolder.imgMoney.setImageDrawable(getContext().
				getResources().getDrawable(R.drawable.bb_ydg_money_cny));
		return view;
	}
	
	class ViewHolder{
		ImageView imgOut;  
		TextView tSA; 
		TextView money;  
		ImageView imgMoney;  
	}

}
