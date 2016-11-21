package com.cqupt.mis.erp.service.finance.balancesheet.impl;

import com.cqupt.mis.erp.manager.finance.BalanceSheetDao;
import com.cqupt.mis.erp.manager.finance.BalanceSheetTextBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.finance.BalanceSheet;
import com.cqupt.mis.erp.service.finance.balancesheet.BalancesheetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("balancesheetService")
public class BalancesheetServiceImpl implements BalancesheetService {
	@Resource
	BalanceSheetDao balanceSheetDao;
	@Resource
	private BalanceSheetTextBasicDao balanceSheetTextBasicDao;
	@Resource
	private GameGroupDao gameGroupDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;

	@Override
	public List<Double> findBeginValue(String userUnique, int period) {
		return balanceSheetDao.findBeginValue(userUnique, period);
	}

	@Override
	public Integer findMaxRowNum() {
		return balanceSheetTextBasicDao.findMaxRowNum();
	}

	@Override
	public Integer findMaxColNum() {
		return balanceSheetTextBasicDao.findMaxColNum();
	}

	@Override
	public List<BalanceSheet> findBalanceSheet(String userUnique, Integer year,
			Integer season) {

		 //计算游戏中当前的期数
		 int periodOfYear = gameGroupDao.findPeriodOfYear(userUnique);
		 int period = (year - 1) * periodOfYear + season;
		
		 int currentTime = gameGroupMemberDao.findCurrentTime(userUnique);
		 int targetTime = (year-1) * periodOfYear + season;
		
		 if(targetTime > currentTime){
		 return null;
		 }
	
		return balanceSheetDao.findBalanceSheet(userUnique, period);
	}

	@Override
	public Map<String, Object> findPageMsg(String userUnique, Integer year,
			Integer season) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("season", season);
		map.put("periodOfYear", gameGroupDao.findPeriodOfYear(userUnique));
		map.put("yearInGame",
				gameGroupMemberDao.findCurrentTime(userUnique) / gameGroupDao.findPeriodOfYear(userUnique) + 1);

		return map;
	}
}
