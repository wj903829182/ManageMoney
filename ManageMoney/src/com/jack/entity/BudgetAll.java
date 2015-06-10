package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 预算总额类：用于记录各科目相应的预算总额
 * */
public class BudgetAll extends DataSupport {

	private int id;//id为记录编号
	private int ssid;//花费科目的id
	private Double amount;//预算总额
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
