package com.cqupt.mis.erp.service.materialpurchase;

import com.cqupt.mis.erp.model.materialpurchase.MaterialBasic;

import java.util.List;

public interface MaterialBasicService {
	/**
	 * 展示原材料基本信息
	 * @author zy
	 * @return List<MaterialBasic>
	 * 
	 */
	List<MaterialBasic> findAllMaterialBasic();
	
	/**
	 * 查询特定原材料的单价
	 * @author zy
	 * @param materialName
	 * @return
	 */
	float findMateriaPrice(String materialName);
}
