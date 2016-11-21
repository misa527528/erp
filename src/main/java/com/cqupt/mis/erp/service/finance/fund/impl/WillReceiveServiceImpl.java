package com.cqupt.mis.erp.service.finance.fund.impl;

import com.cqupt.mis.erp.manager.finance.WillReceiveDao;
import com.cqupt.mis.erp.manager.finance.WillReceiveToCashBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.finance.WillReceive;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.service.finance.fund.CashSheetService;
import com.cqupt.mis.erp.service.finance.fund.WillReceiveService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("willReceiveService")
public class WillReceiveServiceImpl implements WillReceiveService {

	@Resource
	WillReceiveDao willReceiveDao;
	@Resource
	WillReceiveToCashBasicDao willReceiveToCashBasicDao;
	@Resource
	AccountHeadService accountHeadService;
	@Resource
	AccountDetailService accountDetailService;
	@Resource
	CashSheetService cashSheetService;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;

	@Override
	public void addWillReceive(String userUnique, Double money,
			Integer beginTime, Integer endTime, String willReceiveDescription,
			String note) {
		willReceiveDao.insert(userUnique, ERPUtils.round(money), beginTime, endTime,
				willReceiveDescription, note);
	}

	@Override
	public void delete(String userUnique, Integer willReceiveID) {
		willReceiveDao.delete(userUnique, willReceiveID);
	}

	@Override
	public List<WillReceive> findWillReceive(String userUnique) {
		return willReceiveDao.getWillReceive(userUnique);
	}

	private Double getRate() {
		return willReceiveToCashBasicDao.getRate();
	}

	@Override
	public synchronized void discount(String userUnique, Integer willReceiveID) {
		// 根据userUnique willReceiveID 获取money
		Double money = ERPUtils.round(willReceiveDao.getMoney(userUnique, willReceiveID));
		Double rate = this.getRate();
		Double calValue1 = ERPUtils.round(money * rate);//lx:四舍五入处理
		Double calValue2 = ERPUtils.round(money - calValue1);
		
		// 进行相关会计操作
		if (money > 0) {
			accountHeadService.addAccountHead(userUnique, gameGroupMemberDao.findCurrentTime(userUnique), "应收账款");
			accountDetailService.addAccountDetail(userUnique, "现金", "财务费用",
					"应收账款", "借", "借", "贷", calValue1, calValue2, -money);
			cashSheetService.updateAddCash(userUnique, calValue1);
		}
		
		// 删除WILLRECEIVE中的记录
		willReceiveDao.delete(userUnique, willReceiveID);
	}
}
