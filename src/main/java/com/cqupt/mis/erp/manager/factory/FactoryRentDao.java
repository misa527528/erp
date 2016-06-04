package com.cqupt.mis.erp.manager.factory;

import java.util.List;

import com.cqupt.mis.erp.model.factory.FactoryBasicInfo;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;

public interface FactoryRentDao {

    /**
     * 显示租用厂房信息
     *
     * @param userUnique
     * @return List<FactoryCommonInfo>
     * @author zy
     **/
    public List<FactoryCommonInfo> findFactoryRents(String userUnique);

    /**
     * findFactoryRents
     *
     * @param userUnique
     * @param marketName
     * @return List<FactoryCommonInfo>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<FactoryCommonInfo> findFactoryRents(String userUnique, String marketName);

    /**
     * 查询租用详细厂房信息当已安装生产线为0时
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public FactoryCommonInfo findProductLineNumberIsNull(String userUnique,
                                                         String factoryId);

    /**
     * 显示租用详细厂房信息
     *
     * @param userUnique
     * @param factoryId
     * @return List<FactoryCommonInfo>
     * @author zy
     */
    public FactoryCommonInfo findRentDetail(String userUnique, String factoryId);

    /**
     * 入“新租赁厂房”的时候，获取所有厂房基本信息表（FACTORYBASIC）里面所拥有的
     * 厂房类型;租用该厂房时，每期需要交纳的租金;还需要等待多少周期该租赁的厂房才可以使用
     *
     * @param factoryType
     * @return List<FactoryCommonInfo>
     * @author zy
     */
    public List<FactoryBasicInfo> findAllFactoryBasics();

    /**
     * 入“新租赁厂房”的时候，获取特定厂房基本信息表（FACTORYBASIC）里面所拥有的
     * 厂房类型;租用该厂房时，每期需要交纳的租金;还需要等待多少周期该租赁的厂房才可以使用
     *
     * @param factoryType
     * @return List<FactoryCommonInfo>
     * @author zy
     */
    public FactoryBasicInfo findUniqueFactoryBasic(String factoryType);

    /**
     * 增加“新租赁厂房”
     *
     * @param factoryCommonInfo
     * @return List<FactoryCommonInfo>
     * @author zy
     */
    public boolean addRentFactory(FactoryCommonInfo factoryCommonInfo);

    /**
     * 停止租用厂房
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public boolean deleteRentedFactory(String userUnique, String factoryId);


}
