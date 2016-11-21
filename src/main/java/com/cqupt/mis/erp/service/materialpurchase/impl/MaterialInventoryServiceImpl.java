package com.cqupt.mis.erp.service.materialpurchase.impl;

import com.cqupt.mis.erp.manager.materialpurchase.MaterialOfUserDao;
import com.cqupt.mis.erp.model.materialpurchase.MaterialInventory;
import com.cqupt.mis.erp.service.materialpurchase.MaterialInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("materialInventoryService")
public class MaterialInventoryServiceImpl implements MaterialInventoryService{
	@Resource
	private MaterialOfUserDao materialOfUserDao;
	
	public List<MaterialInventory> findMaterialInventories(String userUnique) {
		return materialOfUserDao.findMaterialInventories(userUnique);
	}



}
