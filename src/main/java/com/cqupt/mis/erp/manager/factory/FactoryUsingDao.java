package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("factoryUsingDao")
public interface FactoryUsingDao {

    /**
     * 获取“管理建造好厂房的操作界面”要显示的数据信息
     *
     * @param userUnique
     * @return List<FactoryCommonInfo>
     * @author zy
     */
    public List<FactoryCommonInfo> findFactoryUsings(String userUnique);

    /**
     * findFactoryUsings 根据市场的开拓名称来找出相应的正在使用的工厂
     *
     * @param userUnique
     * @param marketName
     * @return List<FactoryCommonInfo>
     * @throws
     * @since 1.0.0
     */
    public List<FactoryCommonInfo> findFactoryUsings(String userUnique, String marketName);

    /**
     * 查看厂房中有生产线的厂房
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public FactoryCommonInfo findUsingDetailWithProductLineNumber(
            String userUnique, String factoryId);

    /**
     * 查看厂房明细信息
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public FactoryCommonInfo findUsingDetail(String userUnique, String factoryId);

    /**
     * 获取会计分录中的abcde的值A:sellprice 厂房的剩余价值，即厂房的卖价
     * C:makeperiod*makecost 厂房的建造成本
     * B:C-A
     * D:A-E
     * E:selldifferentprice 卖厂房时采取一次性收取现金时，卖价与厂房剩余价值之间的差价。
     *
     * @param userUnique
     * @param factoryId
     * @param factoryType
     * @return
     * @author zy
     */
    public Map findABCDE(String userUnique, String factoryId, String factoryType);

    /**
     * 得到某种厂房类型的出售厂房后延迟多少个账期买房资金到账。
     *
     * @param factoryType
     * @return
     * @author zy
     */
    public int findFactoryDelayTime(String factoryType);

    public void deleteFactory(String userUnique, String factoryId);
}
