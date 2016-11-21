package com.cqupt.mis.erp.service.product.impl;

import com.cqupt.mis.erp.manager.product.DevelopingProductDao;
import com.cqupt.mis.erp.manager.product.UndevelopProductDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.product.DevelopingProduct;
import com.cqupt.mis.erp.model.product.UndevelopProduct;
import com.cqupt.mis.erp.service.product.UndevelopProductService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("undevelopProductService")
public class UndevelopProductServiceImpl implements UndevelopProductService {
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private UndevelopProductDao undevelopProductDao;
	@Resource
	private DevelopingProductDao developingProductDao;
	
	@Override
    // TODO: 2016/8/22 return要重构
    public int updateDevelopProduct(String userUnique, String productName) {
		
		//首先从还未研发的产品表中去取出要研发的产品
		UndevelopProduct undevelopProduct = undevelopProductDao.
                findUndevelopProductByUserUnique(userUnique, productName);
        
		//取出当前周期
		int beginTime = gameGroupMemberDao.findCurrentTime(userUnique);
		DevelopingProduct developingProduct = new DevelopingProduct();
		developingProduct.iniDevelopingProduct(userUnique, 
											   productName, 
											   undevelopProduct.getResearchPeriod(), 
											   undevelopProduct.getResearchCost(), 
											   0,
											   beginTime, 
											   1);

		//将要研发的产品添加到developinpProduct表中
        int researchPeriod = developingProduct.getResearchPeriod();
        float researchCost = developingProduct.getResearchCost();
        int finishedPeriod = developingProduct.getFinishedPeriod();
        int status = developingProduct.getStatus();
		developingProductDao.addDevelopingProduct(userUnique, productName,
                researchPeriod, researchCost, finishedPeriod, beginTime, status);
        
		//从未研发产品的表(UndevelopProduct)中删除刚研发的产品
        undevelopProductDao.deleteUndevelopProduct(userUnique, productName);
		
        return 1;
	}

	@Override
	public List<UndevelopProduct> findUndevelopProductsByUserUnique(
			String userUnique) {
		return undevelopProductDao.findUndevelopProductsByUserUnique(userUnique);
	}

	@Override
	public UndevelopProduct findUndevelopProductByUserUnique(UndevelopProduct undevelopProduct) {
		String userUnique = undevelopProduct.getUserUnique();
		String productName = undevelopProduct.getProductName();

		return undevelopProductDao.findUndevelopProductByUserUnique(userUnique, productName);
	}

	@Override
	public boolean deleteUndevelopProduct(UndevelopProduct undevelopProduct) {
		String userUnique = undevelopProduct.getUserUnique();
		String productName = undevelopProduct.getProductName();

		int result = undevelopProductDao.deleteUndevelopProduct(userUnique, productName);

		return result > 0;
	}
}
