package com.cqupt.mis.erp.service.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cqupt.mis.erp.manager.product.DevelopedProductDao;
import com.cqupt.mis.erp.model.product.DevelopedProduct;
import com.cqupt.mis.erp.service.product.DevelopedProductService;

@Component("developedProductService")
public class DevelopedProductServiceImpl implements DevelopedProductService{

	@Resource
	private DevelopedProductDao developedProductDao;
	
	@Override
	public List<DevelopedProduct> findDevelopedProductsByUserUnique(String userUnique) {
		return developedProductDao.findDevelopProductsByUserUnique(userUnique);
	}

}
