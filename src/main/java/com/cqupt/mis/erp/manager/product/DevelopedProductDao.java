package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.model.product.DevelopedProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("developedProductDao")
public interface DevelopedProductDao {
    /**
     * 重构的时候新增的方法，用于解决以前的isProductDeveloped，把它放在service层了
     * @param userUnique
     * @param productName
     * @return
     * @Author 杨青
     */
    DevelopedProduct findByUserUniqueAndProductName(String userUnique,
                                                    String productName);

    /**
     * 取出用户研发完成的产品
     * @param userUnique
     * @return
     */
    List<DevelopedProduct> findDevelopProductsByUserUnique(String userUnique);
}
