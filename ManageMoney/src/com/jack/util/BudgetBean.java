package com.jack.util;

public class BudgetBean {

	private String tv_subject;//显示消费科目
	private int pb;//进度条的值
	private double tv_total;//总额
	private double tv_balance;//预算余额
	public String getTv_subject() {
		return tv_subject;
	}
	public void setTv_subject(String tv_subject) {
		this.tv_subject = tv_subject;
	}
	
	
	public int getPb() {
		return pb;
	}
	public double getTv_total() {
		return tv_total;
	}
	public void setPb(int pb) {
		this.pb = pb;
	}
	public void setTv_total(double tv_total) {
		this.tv_total = tv_total;
	}
	public double getTv_balance() {
		return tv_balance;
	}
	public void setTv_balance(double tv_balance) {
		this.tv_balance = tv_balance;
	}
	
}
