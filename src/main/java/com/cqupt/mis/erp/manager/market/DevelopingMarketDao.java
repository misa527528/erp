package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.model.market.DevelopingMarket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("developingMarketDao")
public interface DevelopingMarketDao {
    /**
     *
     * @param userUnique
     * @param marketName
     * @return
     */
    DevelopingMarket findDevelopingMarketByUserUniqueAndMarketName(@Param("userUnique") String userUnique,
                                                                   @Param("marketName") String marketName);

    int updateChangeDevelopingMarketStatus(@Param("userUnique") String userUnique,
                                           @Param("marketName") String marketName,
                                           @Param("status") int status);

    List<DevelopingMarket> findDevelopMarketsByUserUnique(String userUnique);

    // TODO: 2016/8/24 添加测试
    int addDevelopingMarket(@Param("userUnique") String userUnique,
                            @Param("marketName") String marketName,
                            @Param("researchPeriod") int researchPeriod,
                            @Param("researchCost") float researchCost,
                            @Param("finishedPeriod") int finishedPeriod,
                            @Param("beginTime") int beginTime,
                            @Param("status") int status);
}
