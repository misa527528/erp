package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.model.product.UndevelopProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("undevelopProductDao")
public interface UndevelopProductDao {
    /**
     * 取出还未开始研发的产品
     * @param userUnique
     * @return
     */
    List<UndevelopProduct> findUndevelopProductsByUserUnique(String userUnique);

    /**
     * 取出用户某个特定的还未开始研发的产品
     * @param userUnique
     * @param productName
     * @return
     */
    UndevelopProduct findUndevelopProductByUserUnique(@Param("userUnique") String userUnique,
                                                      @Param("productName") String productName);

    /**
     *  删除某个未研发的产品(该产品进入了研发期)
     * @param userUnique
     * @param productName
     * @return
     */
    int deleteUndevelopProduct(@Param("userUnique") String userUnique,
                               @Param("productName") String productName);
}
