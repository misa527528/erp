package com.cqupt.mis.erp.service.product.impl;

import com.cqupt.mis.erp.manager.product.DevelopingProductDao;
import com.cqupt.mis.erp.model.product.DevelopingProduct;
import com.cqupt.mis.erp.service.product.DevelopingProductService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component("developingProductService")
public class DevelopingProductServiceImple implements DevelopingProductService {

	@Resource
	private DevelopingProductDao developingProductDao;
	
	@Override
	public List<DevelopingProduct> findDevelopingProductsByUserUnique(
			String userUnique) {
	
		return developingProductDao.findDevelopingProductsByUserUnique(userUnique);
	}

	@Override
	public boolean addDevelopingProduct(DevelopingProduct developingProduct) {
		String userUnique = developingProduct.getUserUnique();
		String productName = developingProduct.getProductName();
		int researchPeriod = developingProduct.getResearchPeriod();
		float researchCost = developingProduct.getResearchCost();
		int finishedPeriod = developingProduct.getFinishedPeriod();
		int beginTime = developingProduct.getBeginTime();
		int status = developingProduct.getStatus();

		int result = developingProductDao.addDevelopingProduct(userUnique, productName,
				researchPeriod, researchCost, finishedPeriod, beginTime, status);

		return result > 0;
	}

	@Override
	public boolean updateStopDevelopProduct(String userUnique, String productName) {
		int status = 0;
        
		int result = developingProductDao.updateStopDevelopProductStatus(userUnique, productName, status);
        
        return result > 0;
	}

	@Override
	public boolean updateRecoveryDevelopProduct(DevelopingProduct developingProduct) {
        String userUnique = developingProduct.getUserUnique();
        String productName = developingProduct.getProductName();
        int status = 1;
        
        int result = developingProductDao.updateStopDevelopProductStatus(userUnique, productName, status);

        return result > 0;
    }

	@Override
    // TODO: 2016/8/22 里面有一个map的地方要重构
    public boolean updateDevelopingProductStatus(String userUnique, String productName) {
		Map<String, Object> map = new HashMap();
		map.put("userUnique", userUnique);
		map.put("productName", productName);
		DevelopingProduct d = developingProductDao.findDevelopingProductByUserAndPName(map);
		//System.out.println(d.getStatus());
		if(d.getStatus() == 1) {
			this.updateStopDevelopProduct(userUnique, productName);
		} else if(d.getStatus() == 0) {
			this.updateRecoveryDevelopProduct(d);
		} 
		
		return true;
	}

}
