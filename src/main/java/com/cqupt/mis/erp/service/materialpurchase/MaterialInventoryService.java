package com.cqupt.mis.erp.service.materialpurchase;

import com.cqupt.mis.erp.model.materialpurchase.MaterialInventory;

import java.util.List;

public interface MaterialInventoryService {
	/**
	 * 展示库存信息
	 * @author zy
	 * @param userUnique
	 * @return
	 */
	List<MaterialInventory> findMaterialInventories(String userUnique);
	
	
}
