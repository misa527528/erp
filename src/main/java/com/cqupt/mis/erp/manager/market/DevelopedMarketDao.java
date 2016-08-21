package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.model.market.DevelopedMarket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("developedMarketDao")
public interface DevelopedMarketDao {
    /**
     * 根据userUnique 来查找已经开发成功的市场
     * @param userUnique
     * @return
     */
    List<DevelopedMarket> findDevelopedMarketsByUserUnique(String userUnique);

    /**
     * 停止维护已经开发的市场
     * @param userUnique
     * @param marketName
     * @param status
     * @return
     */
    int updateStopMaintainDevelopedMarket(@Param("userUnique") String userUnique,
                                          @Param("marketName") String marketName,
                                          @Param("status") int status);

    /**
     * 通过userUnique、marketName来查找DevelopedMarket
     * @param userUnique
     * @param marketName
     * @return
     * @Since
     */
    DevelopedMarket findDevelopedMarket(@Param("userUnique") String userUnique,
                                        @Param("marketName") String marketName);

}
