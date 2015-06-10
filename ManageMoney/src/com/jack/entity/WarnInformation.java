package com.jack.entity;

import java.util.Date;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 提醒详情类：记录提醒事件的基本信息
 * */
public class WarnInformation extends DataSupport {

	private int id;//提醒事件编号
	private String title;//提醒标题
	private Date dtime;//提醒日期
	private String cycle;//提醒周期
	private String time;//提醒时间
	private String note;//备注
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDtime() {
		return dtime;
	}
	public void setDtime(Date dtime) {
		this.dtime = dtime;
	}
	public String getCycle() {
		return cycle;
	}
	public void setCycle(String cycle) {
		this.cycle = cycle;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
