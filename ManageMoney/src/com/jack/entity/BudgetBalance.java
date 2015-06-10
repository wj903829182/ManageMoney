package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 预算余额 类：用于记录各科目相应的预算余额
 * */
public class BudgetBalance extends DataSupport {

	private int id;//记录编号
	private int ssid;//花费科目的id
	private Double amount;//预算余额
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public int getSsid() {
		return ssid;
	}
	public void setSsid(int ssid) {
		this.ssid = ssid;
	}
	
	
}
