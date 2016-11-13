package com.cqupt.mis.erp.manager.materialpurchase;

import com.cqupt.mis.erp.model.materialpurchase.MaterialPurchaseRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("buyingMaterialDao")
public interface BuyingMaterialDao {
    /**
     * 购买原材料订单查询(已到货),添加了分页
     * @param userUnique
     * @param start
     * @param end
     * @param currentTime
     * @return
     */
    List<MaterialPurchaseRecord> findMaterialOrdersReach(@Param("userUnique") String userUnique,
                                                         @Param("start") int start,
                                                         @Param("end") int end,
                                                         @Param("currentTime") int currentTime);

    /**
     * 查询已到货订单总数
     * @param userUnique
     * @param currentTime
     * @return
     */
    int findMaterialOrdersReachCount(@Param("userUnique") String userUnique,
                                     @Param("currentTime") int currentTime);

    /**
     * 购买原材料订单查询(未到货),添加了分页
     * @param userUnique
     * @param start
     * @param end
     * @param currentTime
     * @return
     */
    List<MaterialPurchaseRecord> findMaterialOrdersUnReach(@Param("userUnique") String userUnique,
                                                           @Param("start") int start,
                                                           @Param("end") int end,
                                                           @Param("currentTime") int currentTime);

    /**
     * 查看用户所有订单,添加了分页
     * @param userUnique
     * @param start
     * @param end
     * @return
     */
    List<MaterialPurchaseRecord> findAllMaterialOrders(@Param("userUnique") String userUnique,
                                                       @Param("start") int start,
                                                       @Param("end") int end);

    /**
     * 查询未到货订单总数
     * @param userUnique
     * @param currentTime
     * @return
     */
    int findMaterialOrdersReachUnCount(@Param("userUnique") String userUnique,
                                       @Param("currentTime") int currentTime);

    int addMaterialPurchaseRecord(@Param("userUnique") String userUnique,
                                  @Param("materialName") String materialName,
                                  @Param("materialNumber") int materialNumber,
                                  @Param("wareHouseName") String wareHouseName,
                                  @Param("happenTime") int happenTime,
                                  @Param("endTime") int endTime);
}
