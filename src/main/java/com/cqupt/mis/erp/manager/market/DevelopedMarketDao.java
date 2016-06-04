package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.market.DevelopedMarket;

import java.util.List;

public interface DevelopedMarketDao extends BaseDao {
    /**
     * findDevelopedMarketsByUserUnique
     * 根据userUnique 来查找已经开发成功的市场
     *
     * @param userUnique
     * @return List<DevelopedMarket>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<DevelopedMarket> findDevelopedMarketsByUserUnique(String userUnique);

    /**
     * updateStopMaintainDevelopedMarket 停止维护已经开发的市场
     *
     * @param userUnique
     * @param marketName void
     * @param status
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public void updateStopMaintainDevelopedMarket(String userUnique, String marketName, int status);

    /**
     * isDevelopedMarket 判断市场是否已经开拓完毕.
     *
     * @param userUnique
     * @param marketName
     * @return false 没有开发完 true 已经开发完
     * Boolean
     * @throws
     * @since 1.0.0
     */
    public Boolean isDevelopedMarket(String userUnique, String marketName);
}
