package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.FactoryBasicInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("factoryBasicDao")
public interface FactoryBasicDao {
    /**
     * 查找所有基本表中的factoryType
     * @return
     */
    List<String> findAllFactoryType();

    /**
     * 入“新租赁厂房”的时候，获取所有厂房基本信息表（FACTORYBASIC）里面所拥有的厂房类型;
     * 租用该厂房时，每期需要交纳的租金;还需要等待多少周期该租赁的厂房才可以使用
     * @return
     */
    List<FactoryBasicInfo> findAllFactoryBasics();

    /**
     * 入“新租赁厂房”的时候，获取所有厂房基本信息表（FACTORYBASIC）里面所拥有的厂房类型;
     * 租用该厂房时，每期需要交纳的租金;还需要等待多少周期该租赁的厂房才可以使用
     * @param factoryType
     * @return
     */
    FactoryBasicInfo findUniqueFactoryBasic(String factoryType);

    /**
     * 得到某种厂房类型的出售厂房后延迟多少个账期买房资金到账。
     * @param factoryType
     * @return
     */
    int findFactoryDelayTime(String factoryType);

    /**
     * 查看厂房容量
     * @param userUnique
     * @param factoryId
     * @return
     */
   // @Cacheable(value={"FactoryCapacity"},key="#userUnique+#factoryId")
    int findFactoryCapacity(@Param("userUnique") String userUnique,
                            @Param("factoryId") String factoryId);

    /**
     * 这里是原来factoryUsingDao.findABCDE里面的方法
     * @param factoryType
     * @return
     */
    float findAccountC(String factoryType);

    /**
     * 这里是原来factoryUsingDao.findABCDE里面的方法
     * @param factoryType
     * @return
     */
    float findAccountE(String factoryType);
}
