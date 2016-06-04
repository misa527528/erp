package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("marketDao")
public interface MarketDao extends BaseDao {
	
	public List<String> findMarketName();
	
}
