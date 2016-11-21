package com.cqupt.mis.erp.service.product;

import com.cqupt.mis.erp.model.product.DevelopedProduct;

import java.util.List;

public interface DevelopedProductService {
	
	/**
	 * 取出用户研发完成的产品
	 * @author lx
	 * @param userUnique
	 * @return
	 */
	List<DevelopedProduct> findDevelopedProductsByUserUnique(String userUnique);
}
