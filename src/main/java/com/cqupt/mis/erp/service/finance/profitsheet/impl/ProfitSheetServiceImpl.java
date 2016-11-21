package com.cqupt.mis.erp.service.finance.profitsheet.impl;

import com.cqupt.mis.erp.manager.finance.ProfitSheetDao;
import com.cqupt.mis.erp.manager.finance.ProfitSheetTextBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.finance.ProfitSheet;
import com.cqupt.mis.erp.service.finance.profitsheet.ProfitSheetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("profitSheetService")
public class ProfitSheetServiceImpl implements ProfitSheetService {
	@Resource
	ProfitSheetDao profitSheetDao;
	@Resource
	private ProfitSheetTextBasicDao profitSheetTextBasicDao;
	@Resource
	private GameGroupDao gameGroupDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	
	@Override
	public List<ProfitSheet> findProfitSheet(String userUnique, Integer year,
			Integer season) {
		
		//计算游戏中当前的期数
		 int periodOfYear = gameGroupDao.findPeriodOfYear(userUnique);
		 int period = (year - 1) * periodOfYear + season;
		
		 int currentTime = gameGroupMemberDao.findCurrentTime(userUnique);
		 int targetTime = (year-1) * periodOfYear + season;
		
		 if(targetTime > currentTime){
		 return null;
		 }
		
		return profitSheetDao.findProfitSheet(userUnique, period);
	}

	@Override
	public Integer findMaxRowNum() {
		return profitSheetTextBasicDao.findMaxRowNum();
	}

	@Override
	public Integer findMaxColNum() {
		return profitSheetTextBasicDao.findMaxColNum();
	}

	@Override
	public Map<String, Object> findPageMsg(String userUnique,Integer year, Integer season) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("season", season);
		map.put("periodOfYear", gameGroupDao.findPeriodOfYear(userUnique));
		map.put("yearInGame",
				gameGroupMemberDao.findCurrentTime(userUnique) / gameGroupDao.findPeriodOfYear(userUnique) + 1);
		return map;
	}

}
