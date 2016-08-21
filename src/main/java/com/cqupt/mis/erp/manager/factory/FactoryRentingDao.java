package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("factoryRentingDao")
public interface FactoryRentingDao {
    /**
     * 显示租用厂房信息
     * @param userUnique
     * @return
     */
    List<FactoryCommonInfo> findFactoryRents(String userUnique);

    /**
     *
     * @param userUnique
     * @param marketName
     * @return
     */
    List<FactoryCommonInfo> findFactoryRentsByMarketName(@Param("userUnique") String userUnique,
                                                         @Param("marketName") String marketName);

    /**
     * 查询租用详细厂房信息当已安装生产线为0时
     * @param userUnique
     * @param factoryId
     * @return
     */
    // TODO: 2016/8/16 这注释看起来要重命名接口
    FactoryCommonInfo findProductLineNumberIsNull(@Param("userUnique") String userUnique,
                                                  @Param("factoryId") String factoryId);

    /**
     * 显示租用详细厂房信息
     * @param userUnique
     * @param factoryId
     * @return
     */
    FactoryCommonInfo findRentDetail(@Param("userUnique") String userUnique,
                                     @Param("factoryId") String factoryId);

    /**
     * 增加“新租赁厂房”
     * @param userUnique
     * @param place
     * @param beginTime
     * @param rentCost
     * @param needPeriod
     * @return
     */
    int addRentFactory(@Param("userUnique") String userUnique,
                       @Param("place") String place,
                       @Param("factoryType") String factoryType,
                       @Param("beginTime") int beginTime,
                       @Param("rentCost") float rentCost,
                       @Param("needPeriod") int needPeriod);

    /**
     * 停止租用厂房
     * @param userUnique
     * @param factoryId
     * @return
     */
    int deleteRentedFactory(@Param("userUnique") String userUnique,
                            @Param("factoryId") String factoryId);
}
