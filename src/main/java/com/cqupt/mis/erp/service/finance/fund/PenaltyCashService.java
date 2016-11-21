package com.cqupt.mis.erp.service.finance.fund;

public interface PenaltyCashService {
	
	/**
	 * 用于计算违约金 = 商品数量 * 单价 * 违约金比率 * （实际交货日期 - 正常最晚交货日期）
	 * @author LX
	 * @param orderID
	 * @return
	 */
	double computePenaltyCash(String orderID);
	
	
}
