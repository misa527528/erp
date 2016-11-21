package com.cqupt.mis.erp.service.finance.balancesheet;

import com.cqupt.mis.erp.model.finance.BalanceSheet;

import java.util.List;
import java.util.Map;

public interface BalancesheetService {
	
	/**
	 * 根据用户唯一码和当前的期数获得用户期初资产数
	 * 
	 * @author 毛家杰
	 * @param userUnique 用户唯一码
	 * @param period 当前的期数
	 * @return 用户期初资产数
	 */
	List<Double> findBeginValue(String userUnique, int period);

	/**
	 * 查询资产负债表
	 * @param userUnique 用户唯一码
	 * @param year 用户想查询的年
	 * @param season 用户想查询的一年中的周期
     * @return
     */
	List<BalanceSheet> findBalanceSheet(String userUnique, Integer year, Integer season);
	
	/**
	 * 返回一个map,装有前台传来的year,season
	 * 
	 * @author 毛家杰
	 * @param year 年
	 * @param season 期
	 * @return
	 */
	Map<String, Object> findPageMsg(String userUnique, Integer year, Integer season);
	
	/**
	 * 获得BALANCESHEETTEXTBASIC表中字段RowNumber的最大值
	 * 
	 * @author 毛家杰
	 * @return
	 */
	Integer findMaxRowNum();
	
	/**
	 * 获得BALANCESHEETTEXTBASIC表中字段ColNumber的最大值
	 * 
	 * @author 毛家杰
	 * @return
	 */
	Integer findMaxColNum();
}
