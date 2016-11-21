package com.cqupt.mis.erp.service.product;

import com.cqupt.mis.erp.model.product.ProductDetailBasic;
import com.cqupt.mis.erp.model.product.ProductOfUser;

import java.util.List;


public interface ProductBasicService {
	/**
	 * 显示所有产品库存信息
	 * @author zy
	 * @return List<ProductInventory>
	 * 
	 */
	List<ProductOfUser> findProductInventories(String userUnique);
	
	/**
	 * 查看所有产品的名称
	 * @author zy
	 * @return
	 */
	List<String> findProductName();
	
	/**
	 * 查看所有产品的原材料组成
	 * @author zy
	 * @return
	 */
	List<ProductDetailBasic> findProductDetail();
	
	/**
	 * 获取产品成本价
	 * @author zy 
	 * @param productName
	 * @return
	 */
	float findProductPrice(String productName);
	
	/**
	 * 减少用户库存的产品，此参数对象与model描述可能不符，主要是其中的PNumber是要减少的数量，而不是该用户拥有的产品数量！
	 * @author lx
	 * @param productOfUser
	 * @return
	 */
	boolean updateDecreasePNumber(ProductOfUser productOfUser);
	
}
