package com.cqupt.mis.erp.service.finance.account;

import com.cqupt.mis.erp.model.finance.AccountHead;

import java.util.List;
import java.util.Map;

public interface AccountHeadService {

	/**
	 * 填写分录头表(ACCOUNTHEAD)
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户在竞赛中的唯一标识
	 * @param happenTime
	 *            发生此事件的时间，即第几期
	 * @param description
	 *            产生此分录的说明
	 */
	void addAccountHead(String userUnique, int happenTime,
							   String description);

	/**
	 * 查询会计分录表
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param minYear
	 *            开始年
	 * @param minPeriod
	 *            开始期
	 * @param maxYear
	 *            结束年
	 * @param maxPeriod
	 *            结束期
	 * @param pageIndex
	 *            要查询的页码
	 * @return
	 */
	List<AccountHead> findAccount(String userUnique, Integer minYear,
										 Integer minPeriod, Integer maxYear, Integer maxPeriod,
										 Integer pageIndex);

	/**
	 * 查询总的记录数和总的页数,都装在一个map里面了 ,key分别为recordCount和pageCount,这样可以减少访问数据库的次数
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param minYear
	 *            开始年
	 * @param minPeriod
	 *            开始期
	 * @param maxYear
	 *            结束年
	 * @param maxPeriod
	 *            结束期
	 * @return
	 */
	Map<String, Object> findPageMsg(String userUnique,
										   Integer minYear, Integer minPeriod, Integer maxYear,
										   Integer maxPeriod, Integer pageIndex);

}
