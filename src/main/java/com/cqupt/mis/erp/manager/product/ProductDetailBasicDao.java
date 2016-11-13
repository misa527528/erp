package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.model.product.ProductDetailBasic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("productDetailBasicDao")
public interface ProductDetailBasicDao {
    /**
     * 查看所有产品的原材料组成
     * @return
     */
   // @Cacheable(value="productDetailBasic")
    List<ProductDetailBasic> findProductDetail();

    /**
     * 查看特定产品所需原材料种类
     * @param productName
     * @return
     */
    List<String> findMaterialNamesBYProduct(String productName);

    /**
     * 查询出生产该产品所需要的原材料数量
     * @param productName
     * @param materialName
     * @return
     */
    int findMaterialNumber(@Param("productName") String productName,
                           @Param("materialName") String materialName);
}
