package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.manager.tool.BaseDao;

import java.util.List;

public interface MarketDao extends BaseDao {
	
	public List<String> findMarketName();
	
}
