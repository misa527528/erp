package com.cqupt.mis.erp.service.finance.account.impl;

import com.cqupt.mis.erp.manager.finance.AccountDetailDao;
import com.cqupt.mis.erp.manager.finance.AccountHeadDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.finance.AccountDetail;
import com.cqupt.mis.erp.model.finance.AccountHead;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("accountHeadService")
public class AccountHeadServiceImpl implements AccountHeadService {

	@Resource
	private AccountHeadDao accountHeadDao;
	@Resource
	private AccountDetailDao accountDetailDao;
	@Resource
	private GameGroupDao gameGroupDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;

	// 设定每一页显示的记录数量
	private Integer pageSize = 7;

	@Override
	public void addAccountHead(String userUnique, int happenTime,
			String description) {
		accountHeadDao.addAccountHead(userUnique, happenTime, description);
	}

	@Override
	public List<AccountHead> findAccount(String userUnique, Integer minYear,
			Integer minPeriod, Integer maxYear, Integer maxPeriod,
			Integer pageIndex) {
		List<AccountHead> accountList;
		List<AccountDetail> detailList;

		// 设定开始时间,结束时间
		Integer minTime = minPeriod + gameGroupDao.findPeriodOfYear(userUnique)
				* (minYear - 1);
		Integer maxTime = maxPeriod + gameGroupDao.findPeriodOfYear(userUnique)
				* (maxYear - 1);

		// 计算rowNum
		Integer minNum = (pageIndex - 1) * pageSize;
		//limit 后面的数字是pagesize 意思
		Integer maxNum = pageSize;

		accountList = accountHeadDao.findWithPageNumAndTime(userUnique, minNum,
				maxNum, minTime, maxTime);

		//如果accountList.size为0,那么get(0)操作会出错
		if(accountList.size()==0){
			return accountList;
		}
		
		// 由于accoutList是按照accountID的升序排列的,所以可以得出最小最大accoutID
		Integer minID = accountList.get(0).getAccountID();
		Integer maxID = accountList.get(accountList.size() - 1).getAccountID();

		detailList = accountDetailDao.findAccountDetail(userUnique, minID,
				maxID);

		for (int i = 0; i < accountList.size(); i++) {
			accountList.get(i).setAccountDetailList(
					new ArrayList<AccountDetail>());
			for (int j = 0; j < detailList.size(); j++) {
				if (accountList.get(i).getAccountID().equals(detailList.get(j).getAccountId())){
					accountList.get(i).getAccountDetailList()
							.add(detailList.get(j));
				}
			}
		}

		return accountList;
	}

	@Override
	public Map<String, Object> findPageMsg(String userUnique, Integer minYear,
			Integer minPeriod, Integer maxYear, Integer maxPeriod,
			Integer pageIndex) {
		Integer periodOfYear = gameGroupDao.findPeriodOfYear(userUnique);
		Integer currentTime = gameGroupMemberDao.findCurrentTime(userUnique);
		// 设定开始时间,结束时间
		Integer minTime = minPeriod + gameGroupDao.findPeriodOfYear(userUnique)
				* (minYear - 1);
		Integer maxTime = maxPeriod + gameGroupDao.findPeriodOfYear(userUnique)
				* (maxYear - 1);

		Map<String, Object> map = new HashMap<String, Object>();
		Integer recordCount = accountHeadDao.findRecordCount(userUnique,
				minTime, maxTime);
		Integer pageCount;
		if(recordCount == 0){
			pageCount = 0;
			pageIndex = 0;
		}else{
			pageCount = (recordCount-1) / pageSize + 1;
		}
		map.put("recordCount", recordCount);
		map.put("pageCount", pageCount);
		map.put("pageIndex", pageIndex);
		map.put("minYear", minYear);
		map.put("minPeriod", minPeriod);
		map.put("maxYear", maxYear);
		map.put("maxPeriod", maxPeriod);
		map.put("periodOfYear", periodOfYear);
		map.put("yearInGame", currentTime / periodOfYear + 1);
		return map;
	}
}
