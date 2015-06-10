package com.jack.util;

import android.graphics.Color;

import com.jack.managemoney.R;

public class Constant {
		public static final int DATE_DIALOG=0;
		public static final int DEL_DIALOG=1;
		public static final int DETAIL_DIALOG=2;
		public static final int CALCULATOR_DIALOG=3;
		public static final int MENU_DIALOG=4;
		public static final int TIXING_DIALOG=5;
		public static final int TITLE_DIALOG=6;
		public static final int TIME_DIALOG=7;
		public static final int CYCLE_DIALOG=8;
		public static final int MUSIC_DIALOG=9;
		//final static int[] SONGID={R.raw.loveromance,R.raw.sky,R.raw.rain};
		final static String[] BELLNAME={"爱的罗曼史","天空之城","雨的印记"};//显示的铃声名称
		
		//理财宝典
		final static int P_MAIN=0;//主界面标志
		
		//计算器
		final static int P_JISUAN=1;//计算分支的主界面
		final static int P_FD_GJJ=2;//房贷的公基金计算界面
		final static int P_FD_SYDK=3;//房贷的商业贷款计算界面
		final static int P_FD_ZHX=4;//房贷的组合型计算界面
		final static int P_TQHK_SYDK=5;//商业贷款的提前还款计算界面
		final static int P_TQHU_GJJ=6;//商业贷款的公基金计算界面
		final static int P_HQCX=7;//活期储蓄计算界面
		final static int P_LCZQ=8;//零存整取计算界面
		final static int P_ZCZQ=9;//活期储蓄计算界面
		final static int P_ZCLQ=10;//零存整取计算界面
		static String[] shourutj={"今日收入情况","本月收入情况",
	                              "上月收入情况","前三个月收入情况",
	                              "今年收入总情况","去年收入总情况","全部收入情况"};
	    static String[] zhichutj={"今日支出情况","本月支出情况",
	                              "上月支出情况","前三个月支出情况",
	                              "今年支出总情况","去年支出总情况","全部支出情况"};
	    public static String[] shourukm={"职业收入","资产卖出","借贷收入","其他收入"};
	    public static String[] zhichukm={"饮食","交通","日常用品","通讯","衣服","教育"};
	    public static String[] fangshi={"我的现金","我的活期","我的信用卡"};
	    static String[] szIds={"日常收入","日常支出"}; 
	    /*static int numberIds[]={
	     		R.id.Button00,R.id.Button01,R.id.Button02,
	     		R.id.Button03,R.id.Button04,R.id.Button05,
	     		R.id.Button06,R.id.Button07,R.id.Button08,
	     		R.id.Button09,R.id.Button010
	     };*/
	    public static int []color={Color.RED,Color.BLUE };
	    public static int []color2={Color.RED,Color.BLACK };
	    public static String[] delayTime={"无延迟","2分钟","5分钟","10分钟","15分钟"};
	    public static String[] titleContent={"新建标题","交房租","还房贷","还车贷","水电费"};
	

}
