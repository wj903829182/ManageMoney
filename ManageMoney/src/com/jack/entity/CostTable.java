package com.jack.entity;

import java.util.Date;

import org.litepal.crud.DataSupport;
/*
 * 2015.2.21
 * 日常支出类：用于记录支出账务信息
 * */
public class CostTable extends DataSupport {

	private int id;//记录编号
	private String subject;//支出科目
	private Date date;//支出日期
	private String mode;//支出方式
	private Double amount;//支出金额
	private String place;//支出地点
	private String note;//支出备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
