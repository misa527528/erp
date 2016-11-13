package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("factoryUsingDao")
public interface FactoryUsingDao {
    /**
     * 获取“管理建造好厂房的操作界面”要显示的数据信息
     * @param userUnique
     * @return
     */
    List<FactoryCommonInfo> findFactoryUsings(String userUnique);

    /**
     * 根据市场的开拓名称来找出相应的正在使用的工厂
     * @param userUnique
     * @param marketName
     * @return
     */
    List<FactoryCommonInfo> findFactoryUsingsByMarketName(@Param("userUnique") String userUnique,
                                                          @Param("marketName") String marketName);

    /**
     * 查看厂房中有生产线的厂房
     * @param userUnique
     * @param factoryId
     * @return
     */
    FactoryCommonInfo findUsingDetailWithProductLineNumber(@Param("userUnique") String userUnique,
                                                           @Param("factoryId") String factoryId);

    /**
     * 查看厂房明细信息
     * @param userUnique
     * @param factoryId
     * @return
     */
    FactoryCommonInfo findUsingDetail(@Param("userUnique") String userUnique,
                                      @Param("factoryId") String factoryId);

    int deleteFactory(@Param("userUnique") String userUnique,
                      @Param("factoryId") String factoryId);

    /**
     * 这里是原来 factoryUsingDao.findABCDE里面的方法find_account_a
     * @param userUnique
     * @param factoryId
     * @return
     */
    float findAccountA(@Param("userUnique") String userUnique,
                       @Param("factoryId") String factoryId);
}
