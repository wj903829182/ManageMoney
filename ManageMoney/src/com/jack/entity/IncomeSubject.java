package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.2.21
 * 收入科目类：用于收入科目管理
 */
 
public class IncomeSubject extends DataSupport {

	private int id;//id
	private String subject;//收入科目
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
