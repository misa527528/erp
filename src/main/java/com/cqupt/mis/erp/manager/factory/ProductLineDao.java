package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.ProductLineBasic;
import com.cqupt.mis.erp.model.factory.ProductLineCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("productLineDao")
public interface ProductLineDao {
    /**
     * 获取进入厂房内部所拥有的生产线信息。
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public List<ProductLineInfo> findProductLines(String userUnique,
                                                  String factoryId);

    /**
     * 查看生产线详细信息
     *
     * @param userUnique
     * @param productLineId
     * @return ProductLineInfo
     * @author zy
     */
    public ProductLineCommonInfo findProductLineDetail(String userUnique,
                                                       String productLineId);

    /**
     * 查询所有生产线基本情况
     *
     * @return
     * @author zy
     */
    public List<ProductLineBasic> findAllProductLineBasics();

    /**
     * 查询特定生产线基本情况
     *
     * @param productLineType
     * @return
     * @author zy
     */
    public ProductLineInfo findUniqueProductLineBasic(String productLineType);

    /**
     * 查看厂房容量
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public int findFactoryCapacity(String userUnique, String factoryId);

    /**
     * 查看已有生产线条数
     *
     * @param userUnique
     * @param factoryId
     * @return
     * @author zy
     */
    public int findProductLineNumber(String userUnique, String factoryId);

    /**
     * 添加生产线
     *
     * @param productlineinfo
     * @return
     * @author zy
     */
    public boolean addProductLine(ProductLineCommonInfo productlineinfo);

    /**
     * 查看生产线状态 0 安装中
     *
     * @param userUnique
     * @param productLineId
     * @return 状态值 0 安装中，1 暂停安装，2 生产中，3 暂停生产，4待生产，5 转产，6 暂停转产
     * @author zy
     */
    public int findProductLineStatus(String userUnique, String productLineId);

    /**
     * 查看生产线上的产品类型
     *
     * @param userUnique
     * @param productLineId
     * @return ProductName
     * @author zy
     */
    public String findProductNameByProductLine(String userUnique,
                                               String productLineId);

    /**
     * 查看生产线类型
     *
     * @param userUnique
     * @param productLineId
     * @return ProductName
     * @author zy
     */
    public String findProductLineType(String userUnique, String productLineId);

    /**
     * 查看生产线已完成安装的周期数或者转产完成的周期数
     *
     * @param userUnique
     * @param productLineId
     * @return ProductName
     * @author zy
     */
    public int findProductLineFinishPeriod(String userUnique,
                                           String productLineId);

    /**
     * 查看生产线转产需要的周期数
     *
     * @param productLineType
     * @return ProductName
     * @author zy
     */
    public int findProductLineChangePeriod(String productLineType);

    /**
     * 更改生产线状态值为5，转产中
     *
     * @param userUnique
     * @param productLineId
     * @param productName
     * @return
     * @author zy
     */
    public boolean updateProductLineStatusTofive(String userUnique,
                                                 String productLineId, String productName);

    /**
     * 更改生产线状态值
     *
     * @param userUnique
     * @param productLineId
     * @return
     * @author zy
     */
    public boolean updateProductLineStatus(String userUnique, String productLineId, int status);

    /**
     * 获取ABC的值：A的值从PRODUCTLINE表的SellPrice字段获取，C的值为PRODUCTLINEBASIC表中SetupPeriodPrice字段与SetupPeriod字段的乘积，B的值由C-A得到
     *
     * @param userUnique
     * @param productLineId
     * @return
     * @author zy
     */
    public Map findABCList(String userUnique, String productLineId, String productLineType);

    /**
     * 获得收款延迟时间
     *
     * @param productLineType
     * @return
     * @author zy
     */
    public int findDelayTime(String productLineType);

    /**
     * 删除生产线
     *
     * @param userUnique
     * @param productLineId
     * @return
     * @author zy
     */
    public boolean deleteProductLine(String userUnique, String productLineId);


    /**
     * findProductLineBasic 查询出某类生产线的基本属性
     *
     * @param productLineId
     * @return ProductLineBasic
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public ProductLineBasic findProductLineBasic(String productLineId);

    /**
     * findProductLineInfo  更新某一条生产线的信息
     *
     * @param productLineId
     * @return ProductLineInfo
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public ProductLineInfo findProductLineInfo(String productLineId);

}
