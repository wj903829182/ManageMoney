package com.jack.entity;

import org.litepal.crud.DataSupport;
/*
 * 2015.3.10
 * 等额本息详情表:用于记录每月以等额本息方式的还款情况
 * */
public class EquityInterest extends DataSupport {

	private int id;//还款月编号
	private int benxi;//应还本息
	private int lixi;//应还利息
	private int benjin;//应还本金
	private int yubenjin;//剩余本金
}
