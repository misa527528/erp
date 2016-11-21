package com.cqupt.mis.erp.service.factory.impl;

import com.cqupt.mis.erp.manager.factory.FactoryBasicDao;
import com.cqupt.mis.erp.manager.factory.FactoryMakingDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.service.factory.FactoryMakingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("factoryMakingService")
public class FactoryMakingServiceImpl implements FactoryMakingService {
    @Resource
    private FactoryBasicDao factoryBasicDao;
	@Resource
	private FactoryMakingDao factoryMakingDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;

	@Override
	public List<FactoryCommonInfo> findFactoryMakings(String userUnique) {
		List<FactoryCommonInfo> factoryCommonInfos = new ArrayList<FactoryCommonInfo>();
		List<FactoryCommonInfo> temp = factoryMakingDao.findFactoryMakings(userUnique);
		for (FactoryCommonInfo f : temp) {
			if ("0".equals(f.getStatus())) {
				f.setStatus("暂停修建");
			} else {
				f.setStatus("修建中");
			}
			if ("0".equals(f.getPayMode())) {
				f.setPayMode("按期支付建造资金");
			} else {
				f.setPayMode("一次性付全款");
			}
			factoryCommonInfos.add(f);
		}
		return factoryCommonInfos;
	}

	@Override
	public List<FactoryCommonInfo> findFactoryMakings(String userUnique,String marketName) {
		List<FactoryCommonInfo> factoryCommonInfos = new ArrayList<FactoryCommonInfo>();
		List<FactoryCommonInfo> temp = factoryMakingDao.findFactoryMakingsByMarketName(userUnique,marketName);
		for (FactoryCommonInfo f : temp) {
			if ("0".equals(f.getStatus())) {
				f.setStatus("暂停修建");
			} else {
				f.setStatus("修建中");
			}
			if ("0".equals(f.getPayMode())) {
				f.setPayMode("按期支付建造资金");
			} else {
				f.setPayMode("一次性付全款");
			}
			factoryCommonInfos.add(f);
		}
		return factoryCommonInfos;
	}
	
	public FactoryCommonInfo findMakingDetail(String userUnique, String factoryId) {
		FactoryCommonInfo factoryCommonInfo = factoryMakingDao.findMakingDetail(userUnique, factoryId);
		if ("0".equals(factoryCommonInfo.getStatus())) {
			factoryCommonInfo.setStatus("暂停修建");
		} else {
			factoryCommonInfo.setStatus("修建中");
		}
		if ("0".equals(factoryCommonInfo.getPayMode())) {
			factoryCommonInfo.setPayMode("按期支付建造资金");
		} else {
			factoryCommonInfo.setPayMode("一次性付全款");
		}
		return factoryCommonInfo;

	}

	public boolean addMakingFactory(FactoryCommonInfo factoryCommonInfo,String userUnique) {
		int beginTime = gameGroupMemberDao.findCurrentTime(userUnique);
		String factoryType = factoryCommonInfo.getFactoryType();
		String place = factoryCommonInfo.getPlace();
		int result;

		try {
			result = factoryMakingDao.addMakingFactory( userUnique,factoryType, place, beginTime);
		} catch (Exception e){
			e.printStackTrace();
            return false;
		}

		return result == 1;
	}

	public boolean updateStatusToZero(String userUnique, int factoryId) {
        int status = 0;

        int result = factoryMakingDao.updateFactoryStatus(userUnique, factoryId, status);

        return result > 0;
	}

	public boolean updateStatusToOne(String userUnique, int factoryId) {
        int status = 1;

        int result = factoryMakingDao.updateFactoryStatus(userUnique, factoryId, status);

        return result > 0;
	}

	@Override
	public List<String> findAllFactoryType() {
		return factoryBasicDao .findAllFactoryType();
	}


}
