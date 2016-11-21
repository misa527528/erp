package com.cqupt.mis.erp.service.materialpurchase.impl;

import com.cqupt.mis.erp.manager.materialpurchase.MaterialBasicDao;
import com.cqupt.mis.erp.model.materialpurchase.MaterialBasic;
import com.cqupt.mis.erp.service.materialpurchase.MaterialBasicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("materialBasicService")
public class MaterialBasicServiceImpl implements MaterialBasicService{
	@Resource
	private MaterialBasicDao  materialBasicDao;

	@Override
	public List<MaterialBasic> findAllMaterialBasic() {
		return materialBasicDao.findAllMaterialBasic();
	}
	
	@Override
	public float findMateriaPrice(String materialName) {
		
		return materialBasicDao.findMateriaPrice(materialName);
	}
	

}
