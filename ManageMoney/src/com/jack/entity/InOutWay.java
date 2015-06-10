package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 收支方式类：用于管理收支方式
 * */
public class InOutWay extends DataSupport {

	private int id;//id
	private String subject;//收支方式
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
	
}
