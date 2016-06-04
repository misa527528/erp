package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.market.DevelopingMarket;

import java.util.List;

public interface DevelopingMarketDao extends BaseDao {
    public DevelopingMarket findDevelopingMarketByUserUniqueAndMarketName(String userUnique, String marketName);

    void updateChangeDevelopingMarketStatus(String userUnique, String marketName, int status);

    public List<DevelopingMarket> findDevelopMarketsByUserUnique(String userUnique);
}
