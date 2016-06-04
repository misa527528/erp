package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.product.UndevelopProduct;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("undevelopProductDao")
public interface UndevelopProductDao extends BaseDao {

    /**
     * 取出还未开始研发的产品
     *
     * @param userUnique
     * @return
     * @author LX
     */
    public List<UndevelopProduct> findUndevelopProductsByUserUnique(String userUnique);

    /**
     * 取出用户某个特定的还未开始研发的产品
     *
     * @param undevelopProduct
     * @return
     * @author LX
     */
    public UndevelopProduct findUndevelopProductByUserUnique(UndevelopProduct undevelopProduct);

    /**
     * 删除某个未研发的产品(该产品进入了研发期)
     */
    public boolean deleteUndevelopProduct(UndevelopProduct undevelopProduct);


}
