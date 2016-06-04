package com.cqupt.mis.erp.manager.product;

import com.cqupt.mis.erp.manager.tool.BaseDao;
import com.cqupt.mis.erp.model.product.ProductDetailBasic;
import com.cqupt.mis.erp.model.product.ProductOfUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productBasicDao")
public interface ProductBasicDao extends BaseDao {
    /**
     * 显示所有产品库存信息
     *
     * @return List<ProductInventory>
     * @author zy
     */
    public List<ProductOfUser> findProductInventories(String userUnique);

    /**
     * 查看所有产品的原材料组成
     *
     * @return
     * @author zy
     */
    public List<ProductDetailBasic> findProductDetail();

    /**
     * 查看特定产品所需原材料种类
     *
     * @param productName
     * @return
     * @author zy
     */
    public List<String> findMaterialNamesBYProduct(String productName);

    /**
     * 查询出生产该产品所需要的原材料数量
     *
     * @return
     * @author zy
     */
    public int findMaterialNumber(String productName, String materialName);

    /**
     * findAllProductName 查找所有的产品的名称
     *
     * @return List<String>
     * @throws
     * @author hhy
     * @since 1.0.0
     */
    public List<String> findAllProductName();

    /**
     * 取出用户某个产品的库存数量
     *
     * @author LX
     */
    public int findProductOfUserPNumber(ProductOfUser productOfUser);

    /**
     * 更新用户库存的产品
     *
     * @param productOfUser
     * @return
     */
    public boolean updateProductOfUserPNumber(ProductOfUser productOfUser);

    /**
     * 减少用户库存的产品，此参数对象与model描述可能不符，主要是其中的PNumber是要减少的数量，而不是该用户拥有的产品数量！
     *
     * @param productOfUser
     * @return
     * @author LX
     */
    public boolean updateDecreasePNumber(ProductOfUser productOfUser);


}
