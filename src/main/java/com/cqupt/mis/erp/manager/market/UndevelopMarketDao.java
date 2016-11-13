package com.cqupt.mis.erp.manager.market;

import com.cqupt.mis.erp.model.market.UndevelopMarket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("undevelopMarketDao")
public interface UndevelopMarketDao {
    List<UndevelopMarket> findUndevelopMarketsByUserUnique(String userUnique);

    /**
     * 根据userUnique和市场名称条件,查找还没开拓市场
     * @param userUnique
     * @param marketName
     * @return
     */
    UndevelopMarket findUndevelopMarketByUserUniqueAndMarketName(@Param("userUnique") String userUnique,
                                                                 @Param("marketName") String marketName);

    // TODO: 2016/8/24 添加测试
    int deleteUndevelopMarketByUserUniqueAndMarketName(@Param("userUnique") String userUnique,
                                                       @Param("marketName") String marketName);
}
