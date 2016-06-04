package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.product.DevelopingProduct;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("developingProductDao")
public interface DevelopingProductDao extends BaseDao {

    /**
     * 取出研发中的产品
     *
     * @param userUnique
     * @return
     * @author LX
     */
    public List<DevelopingProduct> findDevelopingProductsByUserUnique(String userUnique);

    /**
     * 添加一条研发的产品到研发中的产品表中
     *
     * @author LX
     */
    public boolean addDevelopingProduct(DevelopingProduct developingProduct);

    /**
     * 停止产品的研发
     *
     * @author lx
     */
    public boolean updateStopDevelopProduct(DevelopingProduct developingProduct);

    /**
     * 恢复产品的研发
     *
     * @author LX
     */
    public boolean updateRecoveryDevelopProduct(DevelopingProduct developingProduct);

    /**
     * updateRecoveryDevelopProduct
     */
    public DevelopingProduct findDevelopingProductByUserAndPName(Map map);

}
