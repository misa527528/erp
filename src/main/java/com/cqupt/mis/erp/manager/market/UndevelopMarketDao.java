package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.market.UndevelopMarket;

import java.util.List;

public interface UndevelopMarketDao extends BaseDao {
    public List<UndevelopMarket> findUndevelopMarketsByUserUnique(String userUnique);

    /**
     * findUndevelopMarketByUserUniqueAndMarketName 根据userUnique和市场名称条件,查找还没开拓市场
     *
     * @param userUnique
     * @param marketName
     * @return UndevelopMarket
     * @throws
     * @since 1.0.0
     */
    public UndevelopMarket findUndevelopMarketByUserUniqueAndMarketName(String userUnique, String marketName);
}
