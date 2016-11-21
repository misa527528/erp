package com.cqupt.mis.erp.service.factory.impl;

import com.cqupt.mis.erp.manager.factory.FactoryBasicDao;
import com.cqupt.mis.erp.manager.factory.FactoryRentingDao;
import com.cqupt.mis.erp.manager.factory.ProductLineDao;
import com.cqupt.mis.erp.model.factory.FactoryBasicInfo;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;
import com.cqupt.mis.erp.model.vo.FactoryVO;
import com.cqupt.mis.erp.service.factory.FactoryRentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("factoryRentService")
public class FactoryRentServiceImpl implements FactoryRentService {
	@Resource
	private FactoryBasicDao factoryBasicDao;
	@Resource
	private FactoryRentingDao factoryRentingDao;
	@Resource
	private ProductLineDao productLineDao;
	
	
	public List<FactoryCommonInfo> findFactoryRents(String userUnique) {
		return factoryRentingDao.findFactoryRents(userUnique);
	}

	
	
	
	public FactoryCommonInfo findRentDetail(String userUnique, String factoryId) {
		FactoryCommonInfo factoryCommonInfo=new FactoryCommonInfo();
		if (factoryRentingDao.findRentDetail(userUnique, factoryId) == null) {
			factoryCommonInfo.setProductLineNumber(0);
		}else{
			return factoryRentingDao.findRentDetail(userUnique, factoryId);
		}
		return factoryCommonInfo;
	}

	public List<FactoryBasicInfo> findFactoryBasics(String factoryType) {
		List<FactoryBasicInfo> factoryBasicInfos = new ArrayList<FactoryBasicInfo>();
		if (factoryType == null || "".equals(factoryType)) {
			factoryBasicInfos = factoryBasicDao.findAllFactoryBasics();
		} else {
			FactoryBasicInfo factoryBasicInfo = factoryBasicDao.findUniqueFactoryBasic(factoryType);
			factoryBasicInfos.add(factoryBasicInfo);
		}
		return factoryBasicInfos;
	}

	
	public boolean addRentFactory(FactoryCommonInfo factoryCommonInfo) {
        String userUnique = factoryCommonInfo.getUserUnique();
        String place = factoryCommonInfo.getPlace();
        String factoryType = factoryCommonInfo.getFactoryType();
        int beginTime = factoryCommonInfo.getBeginTime();
        float rentCost = factoryCommonInfo.getRentCost();
        int needPeriod = factoryCommonInfo.getNeedPeriod();

		int result;

 		try {
			result = factoryRentingDao.addRentFactory(userUnique, place, factoryType, beginTime, rentCost, needPeriod);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}

        return result > 0;
	}

	public int deleteRentedFactory(String userUnique, String factoryId) {
		int number = productLineDao.findProductLineNumber(userUnique, factoryId);

		if (number == 0) {
			int b = factoryRentingDao.deleteRentedFactory(userUnique,factoryId);

			if (b != 1) { // 删除失败
				return 3;
			}
		} else { // 厂房内有生产线，不能停止租用
			return 2;
		}
		return 1;
	}
	@Override
	public List<FactoryVO> findFactoryRentVO(String userUnique){
		List<FactoryVO> factoryVoList = new ArrayList<FactoryVO>(20);
		List<FactoryCommonInfo> factoryRentList = this.findFactoryRents(userUnique);
		FactoryVO factoryVo = null;
		for(FactoryCommonInfo factoryCommon : factoryRentList){
			factoryVo = new FactoryVO();
			factoryCommon.setStatus("租用中");
			List<ProductLineInfo> productInfos = productLineDao.findProductLines(userUnique,factoryCommon.getFactoryId());
			factoryVo.setFactoryCommonInfo(factoryCommon);
			factoryVo.setProductLines(productInfos);
			factoryVoList.add(factoryVo);
	 	}
		return factoryVoList;
	}

	
	@Override
	public List<FactoryVO> findFactoryRentVO(String userUnique, String marketName) {
		 List<FactoryVO> factoryVoList = new ArrayList<FactoryVO>(20);
			List<FactoryCommonInfo> factoryRentList = factoryRentingDao.findFactoryRentsByMarketName(userUnique,marketName);
			FactoryVO factoryVo = null;
			for(FactoryCommonInfo factoryCommon : factoryRentList){
				factoryVo = new FactoryVO();
				factoryCommon.setStatus("租用中");
				List<ProductLineInfo> productInfos = productLineDao.findProductLines(userUnique,factoryCommon.getFactoryId());
				factoryVo.setFactoryCommonInfo(factoryCommon);
				factoryVo.setProductLines(productInfos);
				factoryVoList.add(factoryVo);
		 	}
			return factoryVoList;
	}
	
}
