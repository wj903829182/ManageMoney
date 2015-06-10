package com.jack.util;

public class Dilyexpense {

	private String subject;//科目
	private String money;//金额
	private String model;//方式
	private String date;//时间
	
	public Dilyexpense(){}
	
	public Dilyexpense(String subject, String money, String model, String date) {
		super();
		this.subject = subject;
		this.money = money;
		this.model = model;
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
