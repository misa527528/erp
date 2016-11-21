package com.cqupt.mis.erp.service.finance.fund.impl;

import com.cqupt.mis.erp.manager.finance.CashSheetDao;
import com.cqupt.mis.erp.service.finance.fund.CashSheetService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("cashSheetService")
public class CashSheetServiceImpl implements CashSheetService {
	@Resource
	private CashSheetDao cashSheetDao;
	
	@Override
	public Double findCash(String userUnique) {
		return cashSheetDao.findCash(userUnique);
	}

	@Override
	public void updateAddCash(String userUnique, Double cash) {
		cashSheetDao.addCash(userUnique, ERPUtils.round(cash));
	}

	@Override
	public void updateSubtractCash(String userUnique, Double cash) {
		cashSheetDao.subtractCash(userUnique, ERPUtils.round(cash));
	}
	
}
