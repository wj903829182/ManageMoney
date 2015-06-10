package com.jack.util;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class AccountChart extends AbstractDemoChart{

	private String costMode [] =null;
	private double[] costMoney =null;
	private int n=0;
	public AccountChart(String costMode [],double costMoney []){
		this.costMode=costMode;
		this.costMoney=costMoney;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "账户";
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return "账户柱状图";
	}

	@Override
	public Intent execute(Context context) {
		// TODO Auto-generated method stub
		String titles[]  =new String[]{ "账户"};//图例  
		n=costMode.length;
		List<double[]> values = new ArrayList<double[]>();  
		/*values.add(new double[] { 14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,  
			12600, 14000 });//第一种柱子的数值 
			
*/		values.add(costMoney);
		int[] colors = new int[] { Color.BLUE };//两种柱子的颜色  
		XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);//调用AbstractDemoChart中的方法构建renderer.  
		setChartSettings(renderer, "个人支出报表", "支付方式", "金额", 0.5,  
				5, 0, 5000, Color.GRAY, Color.LTGRAY);//调用AbstractDemoChart中的方法设置renderer的一些属性.  
		renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//设置柱子上是否显示数量值  
		//renderer.setXLabels(5);//X轴的近似坐标数  
		renderer.setYLabels(10);//Y轴的近似坐标数  
		for(int i=0;i<n;i++)
	    {
	    	//renderer.addTextLabel(i+1, costSubject[i]);
	    	renderer.addXTextLabel(i+1, costMode[i]);
	    }
		renderer.setXLabelsAlign(Align.CENTER);//刻度线与X轴坐标文字中心对齐  
		renderer.setYLabelsAlign(Align.LEFT);//Y轴与Y轴坐标文字左对齐  
		renderer.setPanEnabled(true, true);//允许左右拖动,但不允许上下拖动.  
		renderer.setZoomButtonsVisible(true);  
		renderer.setZoomRate(1.1f);//放大的倍率  
		renderer.setBarSpacing(1.0f);//柱子间宽度  
		renderer.setXLabels(0);//取消x轴显示的刻度标签的个数
		renderer.setApplyBackgroundColor(true);//设置使用自定义颜色
		renderer.setBackgroundColor(Color.YELLOW);//设置中间背景颜色
		renderer.setMarginsColor(Color.YELLOW);//设置四周背景颜色
		return ChartFactory.getBarChartIntent(context, buildBarDataset(titles,values ), renderer,  
		Type.STACKED);//构建Intent, buildBarDataset是调用AbstractDemochart中的方法.
		
	}

}
