package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;

import java.util.List;

public interface FactoryMakingDao {
    /**
     * 获取“显示管理在建厂房”要显示的数据信息
     *
     * @param userUnique
     * @return List<FactoryCommonInfo>
     * @author zy
     */
    public List<FactoryCommonInfo> findFactoryMakings(String userUnique);

    /**
     * findFactoryMakings 根据市场 来加载正在修建的factory
     *
     * @param userUnique
     * @param marketName
     * @return List<FactoryCommonInfo>
     * @throws
     * @author hhy
     */
    public List<FactoryCommonInfo> findFactoryMakings(String userUnique, String marketName);

    /**
     * 查看在建厂房的明细信息
     *
     * @param userUnique ,factoryId
     * @return FactoryCommonInfo
     * @author zy
     */
    public FactoryCommonInfo findMakingDetail(String userUnique,
                                              String factoryId);


    /**
     * 新建厂房
     *
     * @param factoryCommonInfo
     * @return
     * @author zy
     */
    public boolean addMakingFactory(FactoryCommonInfo factoryCommonInfo);

    /**
     * 暂停修建厂房
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public boolean updateStatusToZero(String userUnique, String factoryId);

    /**
     * 恢复修建厂房
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public boolean updateStatusToOne(String userUnique, String factoryId);

    /**
     * findfindAllFactoryType 查找所有基本表中的factoryType
     *
     * @return List<String>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<String> findAllFactoryType();


}
