package com.cqupt.mis.erp.service.finance.fund;

import com.cqupt.mis.erp.model.finance.TaxSheet;

public interface TaxSheetService {

	/**
	 * 更新税金
	 * @param userUnique
	 * @param tax
     * @return
     */
	boolean updateTaxSheet(String userUnique, float tax);
	
	/**
	 * 展现用户的税金
	 * @author LX
	 * @param userUnique
	 * @return
	 */
	TaxSheet findTaxSheetByUserUnique(String userUnique);
}
