package com.cqupt.mis.erp.service.finance.profitsheet;

import com.cqupt.mis.erp.model.finance.ProfitSheet;

import java.util.List;
import java.util.Map;


public interface ProfitSheetService {
	/**
	 * 查询利润表
	 * @param userUnique 用户唯一码
	 * @param year 用户想查询的年
	 * @param season 用户想查询的一年中的周期
     * @return
     */
	List<ProfitSheet> findProfitSheet(String userUnique, Integer year, Integer season);
	
	/**
	 * 返回一个map,记录前台传来的year和season
	 * 
	 * @param year 年数
	 * @param season 期数
	 * @return
	 */
	 Map<String,Object> findPageMsg(String userUnique, Integer year, Integer season);
	
	/**
	 * 获得PROFITSHEETTEXTBASIC表中字段RowNumber的最大值
	 * 
	 * @author 毛家杰
	 * @return
	 */
	Integer findMaxRowNum();
	
	/**
	 * 获得PROFITSHEETTEXTBASIC表中字段ColNumber的最大值
	 * 
	 * @author 毛家杰
	 * @return
	 */
	Integer findMaxColNum();
}
