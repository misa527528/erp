package com.cqupt.mis.erp.service.finance.account.impl;

import com.cqupt.mis.erp.manager.bank.LoanOfUserDao;
import com.cqupt.mis.erp.manager.finance.AccountDetailDao;
import com.cqupt.mis.erp.manager.finance.AccountHeadDao;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("accountDetailService")
public class AccountDetailServiceImpl implements AccountDetailService {

	@Resource
	AccountDetailDao accountDetailDao;

	@Resource
	private AccountHeadDao accountHeadDao;

	@Resource
	LoanOfUserDao loanOfUserDao;

	@Override
	public void addAccountDetail(String userUnique, String item1, String item2,
			String itemType1, String itemType2, Double money, Double calValue1,
			Double calValue2) {
		// 对的money和calValue做四舍五入处理
		accountDetailDao.addAccountDetail(userUnique, accountHeadDao.findAccountID(userUnique), item1, itemType1,
                ERPUtils.round(money), ERPUtils.round(calValue1));
		accountDetailDao.addAccountDetail(userUnique, accountHeadDao.findAccountID(userUnique), item2, itemType2,
                ERPUtils.round(money), ERPUtils.round(calValue2));
	}

	@Override
	public void addAccountDetail(String userUnique, String item1, String item2,
			String item3, String itemType1, String itemType2, String itemType3,
			Double calValue1, Double calValue2, Double calValue3) {
		Double money1 = ERPUtils.round(Math.abs(calValue1));
		Double money2 = ERPUtils.round(Math.abs(calValue2));
		Double money3 = ERPUtils.round(Math.abs(calValue3));
		//lx:四舍五入处理
		accountDetailDao.addAccountDetail(userUnique, accountHeadDao.findAccountID(userUnique), item1, itemType1,
				money1, calValue1);
		accountDetailDao.addAccountDetail(userUnique, accountHeadDao.findAccountID(userUnique), item2, itemType2,
				money2, calValue2);
		accountDetailDao.addAccountDetail(userUnique, accountHeadDao.findAccountID(userUnique), item3, itemType3,
				money3, calValue3);
	}

}
