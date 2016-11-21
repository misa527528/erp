package com.cqupt.mis.erp.service.product;

import com.cqupt.mis.erp.model.product.UndevelopProduct;

import java.util.List;

public interface UndevelopProductService {
	
	/**
	 * 研发新产品
	 * @author lx
	 * @param userUnique
	 * @param productName
	 * @return
	 */
	int updateDevelopProduct(String userUnique, String productName);
	
	/**
	 * 取出还未开始研发的产品
	 * @param userUnique
	 * @author lx
	 * @return
	 */
	List<UndevelopProduct> findUndevelopProductsByUserUnique(String userUnique);
	
	/**
	 * 取出用户某个特定的还未开始研发的产品
	 * @param
	 * @author lx
	 * @return
	 */
	UndevelopProduct findUndevelopProductByUserUnique(UndevelopProduct undevelopProduct);
	
	/**
	 * 删除某个未研发的产品(该产品进入了研发期)
	 */
	boolean deleteUndevelopProduct(UndevelopProduct undevelopProduct);
	
}
