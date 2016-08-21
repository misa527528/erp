package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.model.product.DevelopingProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("developingProductDao")
public interface DevelopingProductDao {
    /**
     * 添加一条研发的产品到研发中的产品表中
     * @param userUnique
     * @param productName
     * @param researchPeriod
     * @param researchCost
     * @param finishedPeriod
     * @param beginTime
     * @param status
     * @return
     */
    int addDevelopingProduct(@Param("userUnique") String userUnique,
                             @Param("productName") String productName,
                             @Param("researchPeriod") int researchPeriod,
                             @Param("researchCost") float researchCost,
                             @Param("finishedPeriod") int finishedPeriod,
                             @Param("beginTime") int beginTime,
                             @Param("status") int status);

    /**
     * 取出研发中的产品
     * @param userUnique
     * @return
     */
    List<DevelopingProduct> findDevelopingProductsByUserUnique(String userUnique);

    /**
     * 用户修改产品的状态值：暂停研放、恢复研发
     * @param status
     * @param userUnique
     * @param productName
     * @return
     */
    int updateStopDevelopProductStatus(@Param("status") int status,
                                 @Param("userUnique") String userUnique,
                                 @Param("productName") String productName);


    // TODO: 2016/8/18 打死我也要重构
    DevelopingProduct findDevelopingProductByUserAndPName(Map map);


}
