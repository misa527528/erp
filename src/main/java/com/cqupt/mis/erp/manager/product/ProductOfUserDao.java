package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.model.product.ProductOfUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("productOfUserDao")
public interface ProductOfUserDao {
    /**
     * 显示所有产品库存信息
     * @param userUnique
     * @return
     */
    List<ProductOfUser> findProductInventories(String userUnique);

    /**
     * 出用户某个产品的库存数量
     * @param userUnique
     * @param productName
     * @return
     */
    int findProductOfUserPNumber(@Param("userUnique") String userUnique,
                                 @Param("productName") String productName);

    /**
     * 更新用户库存的产品
     * @param userUnique
     * @param productName
     * @param pNumber
     * @return
     */
    int updateProductOfUserPNumber(@Param("userUnique") String userUnique,
                                   @Param("productName") String productName,
                                   @Param("pNumber") int pNumber);

    /**
     * 减少用户库存的产品，此参数对象与model描述可能不符，
     * 主要是其中的PNumber是要减少的数量，而不是该用户拥有的产品数量！
     * @param userUnique
     * @param productName
     * @param pNumber
     * @return
     */
    int updateDecreasePNumber(@Param("userUnique") String userUnique,
                              @Param("productName") String productName,
                              @Param("pNumber") int pNumber);
}
