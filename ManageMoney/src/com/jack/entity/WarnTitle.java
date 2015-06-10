package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 提醒详情类：用于管理所有提醒标题
 * */
public class WarnTitle extends DataSupport {

	private int id;//提醒记录编号
	private String title;//提醒标题
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
	
	
}
