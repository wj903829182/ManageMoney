package com.jack.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.jack.entity.BudgetAll;
import com.jack.entity.BudgetBalance;
import com.jack.entity.CostSubject;
import com.jack.entity.CostTable;
import com.jack.entity.InOutWay;
import com.jack.entity.IncomeSubject;
import com.jack.entity.ShouRu;
import com.jack.entity.WarnTitle;

public class DBUtil {

	// 打开数据库
	// 关闭数据库
	// 添加财务信息
	public static void insertZhangWu(String tableName, Double amount,// 收入金额
			String subject,// 收入科目
			Date date,// 日期
			String mode,// 方式
			String place,// 收入地点
			String note// 备注
	) {
		if ("ShouRu".equals(tableName)) {
			// 创建收入对象
			ShouRu sr = new ShouRu();
			// 调用set方法进行值的设置
			sr.setSubject(subject);
			sr.setDate(date);
			sr.setMode(mode);
			sr.setAmount(amount);
			sr.setPlace(place);
			sr.setNote(note);
			// 保存对象的数据到数据库里面
			if (sr.save()) {
				Toast.makeText(LitePalApplication.getContext(), "成功保存收入数据到数据库",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(LitePalApplication.getContext(), "失败保存收入数据到数据库",
						Toast.LENGTH_SHORT).show();
			}
		} else if ("CostTable".equals(tableName)) {
			// 创建日常支出对象
			CostTable ct = new CostTable();
			// 调用set方法进行值的设置
			ct.setSubject(subject);
			ct.setDate(date);
			ct.setMode(mode);
			ct.setAmount(amount);
			ct.setPlace(place);
			ct.setNote(note);
			// 保存对象的数据到数据库里面
			if (ct.save()) {
				Toast.makeText(LitePalApplication.getContext(), "成功保存支出数据到数据库",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(LitePalApplication.getContext(), "失败保存支出数据到数据库",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	// 插入支出科目
	public static void insertCostSubject(String scostSubject) {
		CostSubject costSubject = new CostSubject();
		costSubject.setSubject(scostSubject);
		if (costSubject.save()) {
			Toast.makeText(LitePalApplication.getContext(), "成功保存支出科目数据到数据库",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(LitePalApplication.getContext(), "失败保存支出科目数据到数据库",
					Toast.LENGTH_SHORT).show();
		}
	}

	// 获得支出科目列表
	public static List<CostSubject> getCostSubject() {
		return DataSupport.findAll(CostSubject.class);
	}

	// 插入收入科目
	public static void insertIncomeSubject(String subject) {
		IncomeSubject inSubject = new IncomeSubject();
		inSubject.setSubject(subject);
		if (inSubject.save()) {
			Toast.makeText(LitePalApplication.getContext(), "成功保存收入科目数据到数据库",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(LitePalApplication.getContext(), "失败保存收入科目数据到数据库",
					Toast.LENGTH_SHORT).show();
		}
	}

	// 获得收入科目列表
	public static List<IncomeSubject> getIncomeSubject() {
		return DataSupport.findAll(IncomeSubject.class);
	}

	// 插入收支模式方式
	public static void insertInOutWay(String mode) {
		InOutWay inoutway = new InOutWay();
		inoutway.setSubject(mode);
		if (inoutway.save()) {
			Toast.makeText(LitePalApplication.getContext(), "成功保存收支模式数据到数据库",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(LitePalApplication.getContext(), "失败保存收支模式数据到数据库",
					Toast.LENGTH_SHORT).show();
		}
	}

	// 得到收支模式
	public static List<InOutWay> getInOutWay() {
		return DataSupport.findAll(InOutWay.class);
	}

	// 获得今日收列表
	public static List<ShouRu> getTodayDetailsShouRu(long date) {
		List<ShouRu> shouRuList = null;
		shouRuList = DataSupport.select("subject", "amount", "mode", "date")
				.where("date=?", String.valueOf(date)).order("date desc")
				.find(ShouRu.class);

		return shouRuList;
	}

	// 获得今日支出列表
	public static List<CostTable> getTodayDetailsCostTable(long date) {
		List<CostTable> costList = null;
		costList = DataSupport.select("subject", "amount", "mode", "date")
				.where("date=?", String.valueOf(date)).order("date desc")
				.find(CostTable.class);

		return costList;
	}

	// 获取本月收入情况
	public static List<ShouRu> getMonthYearDetails(long date) {
		List<ShouRu> shouRuList = null;
		// 获取本月收入情况
		shouRuList = DataSupport.select("subject", "amount", "mode", "date")
				.where("date>?", String.valueOf(date)).order("date desc")
				.find(ShouRu.class);
		return shouRuList;
	}

	// 获取本月支出
	public static List<CostTable> getMonthYearDetailsCost(long date) {
		List<CostTable> costList = null;
		// 获取本月收入情况
		costList = DataSupport.select("subject", "amount", "mode", "date")
				.where("date>?", String.valueOf(date)).order("date desc")
				.find(CostTable.class);
		return costList;
	}

	// 获取上月收入列表
	public static List<ShouRu> getLastMonthYearDetailsIn(long lastdate,
			long nowdate) {
		List<ShouRu> shouRuList = null;
		// 获取上月收入情况
		shouRuList = DataSupport
				.select("subject", "amount", "mode", "date")
				.where("date>=? and date <=?", String.valueOf(lastdate),
						String.valueOf(nowdate)).order("date desc")
				.find(ShouRu.class);
		return shouRuList;
	}

	// 获取上月支出情况
	public static List<CostTable> getLastMonthYearDetailsCost(long lastdate,
			long nowdate) {
		List<CostTable> costList = null;
		// 获取上月支出情况
		costList = DataSupport
				.select("subject", "amount", "mode", "date")
				.where("date>=? and date <=?", String.valueOf(lastdate),
						String.valueOf(nowdate)).order("date desc")
				.find(CostTable.class);
		return costList;
	}

	// 获取前三个月收入
	public static List<ShouRu> getLastThreeMonthDetailsIn(long threedate,
			long nowdate) {
		List<ShouRu> shouRuList = null;
		// 获取上月收入情况
		shouRuList = DataSupport
				.select("subject", "amount", "mode", "date")
				.where("date>=? and date <=?", String.valueOf(threedate),
						String.valueOf(nowdate)).order("date desc")
				.find(ShouRu.class);
		return shouRuList;
	}

	// 获取前三个月支出情况
	public static List<CostTable> getLastThreeMonthDetailsCost(long threedate,
			long nowdate) {
		List<CostTable> costList = null;
		// 获取上月收入情况
		costList = DataSupport
				.select("subject", "amount", "mode", "date")
				.where("date>=? and date <=?", String.valueOf(threedate),
						String.valueOf(nowdate)).order("date desc")
				.find(CostTable.class);
		return costList;
	}

	// 获取今年收入情况
	public static List<ShouRu> getThisYearIn(long date) {
		List<ShouRu> shouRuList = null;
		shouRuList = DataSupport.select("subject", "amount", "mode", "date")
				.where("date>=?", String.valueOf(date)).order("date desc")
				.find(ShouRu.class);

		return shouRuList;
	}

	// 获取今年支出情况
	public static List<CostTable> getThisYearCost(long date) {
		List<CostTable> costList = null;
		costList = DataSupport.select("subject", "amount", "mode", "date")
				.where("date>=?", String.valueOf(date)).order("date desc")
				.find(CostTable.class);

		return costList;
	}

	// 获取去年收入情况
	public static List<ShouRu> getLastYearIn(long date1, long date2) {
		List<ShouRu> shouRuList = null;
		shouRuList = DataSupport
				.select("subject", "amount", "mode", "date")
				.where("date>=? and date <=?", String.valueOf(date1),
						String.valueOf(date2)).order("date desc")
				.find(ShouRu.class);

		return shouRuList;
	}

	// 去年支出总情况
	public static List<CostTable> getLastYearCost(long date1, long date2) {
		List<CostTable> costList = null;
		costList = DataSupport
				.select("subject", "amount", "mode", "date")
				.where("date>=? and date <=?", String.valueOf(date1),
						String.valueOf(date2)).order("date desc")
				.find(CostTable.class);

		return costList;
	}

	// 获得全部收支列表
	public static List<ShouRu> getAllDetailsIn() {
		List<ShouRu> shouRuList = null;
		shouRuList = DataSupport.select("subject", "amount", "mode", "date")
				.order("date desc").find(ShouRu.class);
		return shouRuList;
	}

	// 获得全部支出列表
	public static List<CostTable> getAllDetailsCost() {
		List<CostTable> costList = null;
		costList = DataSupport.select("subject", "amount", "mode", "date")
				.order("date desc").find(CostTable.class);
		return costList;
	}

	// 查询支出科目
	public static List<String[]> searchCostSubject(long start_date,
			long end_date) {
		List<String[]> list = new ArrayList<String[]>();
		String sql = "select subject,sum(amount) from costtable where date>=? and date <=?"
				+ " group by subject";
		Cursor cursor = DataSupport.findBySQL(sql, String.valueOf(start_date),
				String.valueOf(end_date));
		// String
		// sql="select isubject,sum(iamount) from zhichu where idate>=? and idate<=? group by isubject";
		Log.d("DBUtil240", String.valueOf(start_date));
		Log.d("DBUtil241", String.valueOf(end_date));
		while (cursor.moveToNext()) {
			String str[] = new String[2];
			str[0] = cursor.getString(0);// 科目
			str[1] = String.valueOf(cursor.getDouble(1));// 金额
			list.add(str);
		}
		if (cursor != null) {
			cursor.close();
		}

		Log.d("DBUtil253", "the size is :" + list.size());
		return list;
	}

	// 查询支出账号
	public static List<String[]> searchCostAccount(long start_date,
			long end_date) {
		List<String[]> list = new ArrayList<String[]>();
		String sql = "select mode,sum(amount) from costtable where date>=? and date <=?"
				+ " group by mode";
		Cursor cursor = DataSupport.findBySQL(sql, String.valueOf(start_date),
				String.valueOf(end_date));
		// String
		// sql="select isubject,sum(iamount) from zhichu where idate>=? and idate<=? group by isubject";
		while (cursor.moveToNext()) {
			String str[] = new String[2];
			str[0] = cursor.getString(0);// 支付方式
			str[1] = String.valueOf(cursor.getDouble(1));// 金额
			list.add(str);
		}
		if (cursor != null) {
			cursor.close();
		}

		return list;
	}

	// 查询支出地点
	public static List<String[]> searchCostPlace() {
		return null;
	}

	// 查询所有支出地点
	public static List<String[]> searchAllCostPlace() {
		return null;
	}

	// 添加预算信息
	public static boolean addBudget() {
		return false;
	}

	// 添加预算信息
	public static boolean updateBudget() {
		return false;
	}

	// 获取总预算额
	public static double allBudget() {
		return 0;
	}

	// 添加计算结果详情
	public static void insertDetail() {
	}

	// 获取详情列表
	public static List<String[]> getDetail() {
		return null;
	}

	// 更新计算结果详情
	public static void updateDetail() {
	}

	// 更新还款信息
	public static void updateTq() {
	}

	// 添加提醒事件
	public static void insertTiXing() {
	}

	// 获取提醒详情
	public static List<String[]> getTiXing() {
		return null;
	}

	// 添加提醒标题
	public static void insertTiXingTitle(String title) {
		WarnTitle wt = new WarnTitle();
		wt.setTitle(title);
		if (wt.save()) {
			Toast.makeText(LitePalApplication.getContext(), "成功保存提醒标题到到数据库",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(LitePalApplication.getContext(), "失败保存提醒标题到数据库",
					Toast.LENGTH_SHORT).show();
		}
	}

	// 获得提醒标题
	public static List<WarnTitle> getTiXingTitle() {
		return DataSupport.findAll(WarnTitle.class);
	}

	// 添加新科目
	public static void insertSubjects() {
	}

	// 获取支出金额
	public static double getYuAmountAllDetails() {
		return 0;
	}

	// 获取科目及相应的支出总额
	public static List<String[]> getAmountAllDetails() {
		return null;
	}

	// 删除表内容
	public static void deleteFromTable() {
	}

	// 清空表
	public static void deleteAllFromTable() {
	}

	// 插入总额表预算
	public static void insertBudget(int ssid, double amount) {
		BudgetAll bg = new BudgetAll();
		bg.setSsid(ssid);
		bg.setAmount(amount);

		if (bg.save()) {
			Toast.makeText(LitePalApplication.getContext(), "成功保存预算数据到数据库",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(LitePalApplication.getContext(), "失败保存预算数据到数据库",
					Toast.LENGTH_SHORT).show();
		}
	}

	// 获取到预算总额
	public static List<BudgetAll> getBudgetAll() {
		return DataSupport.findAll(BudgetAll.class);
	}

	// 插入预算余额
	public static void insertBudgetBalance(int ssid, double amount) {
		BudgetBalance bg = new BudgetBalance();
		bg.setSsid(ssid);
		bg.setAmount(amount);

		if (bg.save()) {
			Toast.makeText(LitePalApplication.getContext(), "成功保存预算数据到数据库",
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(LitePalApplication.getContext(), "失败保存预算数据到数据库",
					Toast.LENGTH_SHORT).show();
		}
	}

	// 获取到预算余额
		public static List<BudgetBalance> getBudgetBalance() {
			return DataSupport.findAll(BudgetBalance.class);
		}

}
