package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("factoryMakingDao")
public interface FactoryMakingDao {
    /**
     * 获取“显示管理在建厂房”要显示的数据信息
     * @param userUnique
     * @return
     */
    List<FactoryCommonInfo> findFactoryMakings(String userUnique);

    /**
     * 根据市场 来加载正在修建的factory
     * @param userUnique
     * @param marketName
     * @return
     */
    List<FactoryCommonInfo> findFactoryMakingsByMarketName(@Param("userUnique") String userUnique,
                                                           @Param("marketName") String marketName);

    /**
     * 查看在建厂房的明细信息
     * @param userUnique
     * @param factoryId
     * @return
     */
    FactoryCommonInfo findMakingDetail(@Param("userUnique") String userUnique,
                                       @Param("factoryId") String factoryId);

    /**
     * 新建厂房
     * @param userUnique
     * @param factoryType
     * @param place
     * @param beginTime
     * @return
     */
    int addMakingFactory(@Param("userUnique") String userUnique,
                         @Param("factoryType") String factoryType,
                         @Param("place") String place,
                         @Param("beginTime") int beginTime);

    /**
     * 暂停修建厂房、恢复修建厂房
     * @param userUnique
     * @param factoryId
     * @param status 0：暂停； 1：恢复
     * @return
     */
    int updateFactoryStatus(@Param("userUnique") String userUnique,
                           @Param("factoryId") int factoryId,
                           @Param("status") int status);



}
