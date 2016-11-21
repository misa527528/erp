package com.cqupt.mis.erp.service.product;

import com.cqupt.mis.erp.model.product.DevelopingProduct;

import java.util.List;

public interface DevelopingProductService {
	
	/**
	 * 取出研发中的产品
	 * @param userUnique
	 * @author lx
	 * @return
	 */
	List<DevelopingProduct> findDevelopingProductsByUserUnique(String userUnique);
	
	/**
	 * 添加一条研发的产品到研发中的产品表中
	 * @author lx
	 */
	boolean addDevelopingProduct(DevelopingProduct developingProduct);
	
	/**
	 * 停止产品的研发
	 * @author lx
	 */
	boolean updateStopDevelopProduct(String userUnique, String productName);
	
	/**
	 * 恢复产品的研发
	 * @author lx
	 */
	boolean updateRecoveryDevelopProduct(DevelopingProduct developingProduct);
	
	/**
	 * 改变产品研发中的状态
	 * @param userUnique
	 * @param productName
	 * @author lx
	 */
	boolean updateDevelopingProductStatus(String userUnique, String productName);
	
	/**
	 * 更改产品研发的状态: status = status^1 
	 * @author lx	
	 * 
	 */
	/*
	public boolean updateModifyStatus();
	*/
	
	
}
