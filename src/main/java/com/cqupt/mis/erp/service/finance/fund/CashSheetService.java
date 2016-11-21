package com.cqupt.mis.erp.service.finance.fund;

public interface CashSheetService {
	/**
	 * 根据userUnique找出现金金额
	 * 
	 * @author 毛家杰
	 * @param userUnique 用户唯一码
	 * @return 现金金额
	 */
	Double findCash(String userUnique);
	
	/**
	 * 增加用户的现金
	 * 
	 * @author 毛家杰
	 * @param userUnique 用户唯一码
	 * @param cash 现金金额
	 */
	void updateAddCash(String userUnique, Double cash);

	/**
	 * 减少用户的现金
	 * 
	 * @author 毛家杰
	 * @param userUnique 用户唯一码
	 * @param cash 现金金额
	 */
	void updateSubtractCash(String userUnique, Double cash);
}
