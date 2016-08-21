package com.cqupt.mis.erp.manager.factory;

import com.cqupt.mis.erp.model.factory.ProductLineCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("productLineDao")
public interface ProductLineDao {
    /**
     * 获取进入厂房内部所拥有的生产线信息。
     * @param userUnique
     * @param factoryId
     * @return
     */
    List<ProductLineInfo> findProductLines(@Param("userUnique") String userUnique,
                                           @Param("factoryId") String factoryId);

    /**
     * 查看生产线详细信息
     * @param userUnique
     * @param productLineId
     * @return
     */
    ProductLineCommonInfo findProductLineDetail(@Param("userUnique") String userUnique,
                                                @Param("productLineId") String productLineId);

    /**
     * 查询特定生产线基本情况
     * @param productLineType
     * @return
     */
    @Cacheable(value={"ProductLineBasicByType"},key="#productLineType")
    ProductLineInfo findUniqueProductLineBasic(String productLineType);

    /**
     * 查看已有生产线条数
     * @param userUnique
     * @param factoryId
     * @return
     */
    int findProductLineNumber(@Param("userUnique") String userUnique,
                              @Param("factoryId") String factoryId);

    /**
     * 添加生产线
     * @param userUnique
     * @param factoryId
     * @param productLineType
     * @param productName
     * @return
     */
    int addProductLine(@Param("userUnique") String userUnique,
                       @Param("factoryId") String factoryId,
                       @Param("productLineType") String productLineType,
                       @Param("productName") String productName);

    /**
     * 查看生产线状态 0 安装中
     * @param userUnique
     * @param productLineId
     * @return 状态值 0 安装中，1 暂停安装，2 生产中，3 暂停生产，4待生产，5 转产，6 暂停转产
     */
    int findProductLineStatus(@Param("userUnique") String userUnique,
                              @Param("productLineId") String productLineId);

    /**
     * 查看生产线上的产品类型
     * @param userUnique
     * @param productLineId
     * @return
     */
    String findProductNameByProductLine(@Param("userUnique") String userUnique,
                                        @Param("productLineId") String productLineId);

    /**
     * 查看生产线类型
     * @param userUnique
     * @param productLineId
     * @return
     */
    @Cacheable(value={"ProductLineType"},key="#userUnique+#productLineId")
    String findProductLineType(@Param("userUnique") String userUnique,
                               @Param("productLineId") String productLineId);

    /**
     * 查看生产线已完成安装的周期数或者转产完成的周期数
     * @param userUnique
     * @param productLineId
     * @return
     */
    int findProductLineFinishPeriod(@Param("userUnique") String userUnique,
                                    @Param("productLineId") String productLineId);

    /**
     * 更改生产线状态值为5，转产中
     * @param userUnique
     * @param productLineId
     * @param productName
     * @return
     */
    int updateProductLineStatusTofive(@Param("userUnique") String userUnique,
                                      @Param("productLineId") String productLineId,
                                      @Param("productName") String productName);

    /**
     * 更改生产线状态值
     * @param userUnique
     * @param productLineId
     * @param status
     * @return
     */
    int updateProductLineStatus(@Param("userUnique") String userUnique,
                                @Param("productLineId") String productLineId,
                                @Param("status") int status);

    /**
     * 删除生产线
     * @param userUnique
     * @param productLineId
     * @return
     */
    int deleteProductLine(@Param("userUnique") String userUnique,
                          @Param("productLineId") String productLineId);

    /**
     * 更新某一条生产线的信息
     * @param productLineId
     * @return
     */
    ProductLineInfo findProductLineInfoByProductlineid(String productLineId);

}
