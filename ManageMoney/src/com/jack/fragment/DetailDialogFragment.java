package com.jack.fragment;

import com.jack.managemoney.R;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;

public class DetailDialogFragment extends DialogFragment{

	private static String s_suject;
	private static String s_money;
	private static String s_model;
	private static String s_date;
	
	public static void setTextString(String suject,
			String money,String model,String date){
		s_suject=suject;
		s_money=money;
		s_model=model;
		s_date=date;
		
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view =inflater.inflate(R.layout.detail_dialog, container, 
				false);
		TextView t_subject=(TextView) view.findViewById(R.id.td_suject);
		TextView t_money=(TextView) view.findViewById(R.id.td_money);
		TextView t_model=(TextView) view.findViewById(R.id.td_model);
		TextView t_date=(TextView) view.findViewById(R.id.td_date);
		Button b_closeButton=(Button) view.findViewById(R.id.closeButton);
		t_subject.setText(s_suject);
		t_money.setText(s_money);
		t_model.setText(s_model);
		t_date.setText(s_date);
		b_closeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DetailDialogFragment.this.dismiss();
			}
		});
		return view;
	}

}
