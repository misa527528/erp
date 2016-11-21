package com.cqupt.mis.erp.service.finance.fund.impl;

import com.cqupt.mis.erp.manager.finance.TaxSheetDao;
import com.cqupt.mis.erp.model.finance.TaxSheet;
import com.cqupt.mis.erp.service.finance.fund.TaxSheetService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("taxSheetService")
public class TaxSheetServiceImpl implements TaxSheetService {

	@Resource
	private TaxSheetDao taxSheetDao;
	
	@Override
	public boolean updateTaxSheet(String userUnique, float tax) {
		tax = ERPUtils.round(tax);//四舍五入
		
		int result = taxSheetDao.updateTaxSheet(userUnique, tax);

		return result > 0;
	}

	@Override
	public TaxSheet findTaxSheetByUserUnique(String userUnique) {
		return taxSheetDao.findTaxSheetByUserUnique(userUnique);
	}

}
