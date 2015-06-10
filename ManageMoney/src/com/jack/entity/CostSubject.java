package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 支出科目表：用于支出科目管理
 * */
public class CostSubject extends DataSupport {

	private int id;//id
	private String subject;//支出科目
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
