package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.product.DevelopedProduct;

import java.util.List;

public interface DevelopedProductDao extends BaseDao {
    /**
     * isProductDeveloped 查询是否已经研发了产品
     *
     * @param userUnique
     * @param productName
     * @return false 没有开发完 true 已经开发完
     * Boolean
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public Boolean isProductDeveloped(String userUnique, String productName);

    /**
     * 取出用户研发完成的产品
     *
     * @param userUnique
     * @return
     * @author LX
     */
    public List<DevelopedProduct> findDevelopProductsByUserUnique(String userUnique);

}
