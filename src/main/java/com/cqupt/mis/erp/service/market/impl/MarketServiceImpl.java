package com.cqupt.mis.erp.service.market.impl;

import com.cqupt.mis.erp.manager.market.DevelopedMarketDao;
import com.cqupt.mis.erp.manager.market.DevelopingMarketDao;
import com.cqupt.mis.erp.manager.market.MarketBasicDao;
import com.cqupt.mis.erp.manager.market.UndevelopMarketDao;
import com.cqupt.mis.erp.model.market.DevelopedMarket;
import com.cqupt.mis.erp.model.market.DevelopingMarket;
import com.cqupt.mis.erp.model.market.UndevelopMarket;
import com.cqupt.mis.erp.service.market.MarketService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("marketService")
public class MarketServiceImpl implements MarketService {
	
	@Resource
	private MarketBasicDao marketBasicDao;
	@Resource
	private UndevelopMarketDao undevelopMarketDao;
	@Resource
	private DevelopingMarketDao developingMarketDao;
	@Resource
	private GameGroupMemberService gameGroupMemberService;
	@Resource
	private DevelopedMarketDao developedMarketDao;
	
	
	@Override
	public List<String> findMarketName() {
		return marketBasicDao.findMarketName();
	}

	@Override
	public List<UndevelopMarket> findUndevelopMarketsByUserUnique(String userUnique) {
		return undevelopMarketDao.findUndevelopMarketsByUserUnique(userUnique);
	}

	@Override
	public boolean updateUndevelopMarketToDeveloping(String userUnique, String marketName) {
        try {
            UndevelopMarket unDevelopMarket = undevelopMarketDao.findUndevelopMarketByUserUniqueAndMarketName
                    (userUnique, marketName);
            int currentPeriod = gameGroupMemberService.findCurrentPeriod(userUnique);
            int researchPeriod = unDevelopMarket.getResearchPeriod();
            double d = unDevelopMarket.getResearchCost();
            float researchCost = (float) d;
            int finishedPeriod = 0;
            int beginTime = currentPeriod;
            int status = 1;

			developingMarketDao.addDevelopingMarket(userUnique, marketName, researchPeriod,
                    researchCost, finishedPeriod, beginTime, status);
			undevelopMarketDao.deleteUndevelopMarketByUserUniqueAndMarketName(userUnique, marketName);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateStopDevelopingMarket(String userUnique,String marketName) {
		int result = developingMarketDao.updateChangeDevelopingMarketStatus(userUnique,marketName,0);

        return result > 0;
	}
	
	@Override
	public boolean updateStartDevelopingMarket(String userUnique,String marketName) {
		int result = developingMarketDao.updateChangeDevelopingMarketStatus(userUnique,marketName,1);

        return  result > 0;
	}
	
	@Override
	public List<DevelopingMarket> findDevelopingMarketsByUserUnique(String userUnique) {
		return developingMarketDao.findDevelopMarketsByUserUnique(userUnique);
	}

	@Override
	public List<DevelopedMarket> findDevelopedMarketsByUserUnique(String userUnique) {
		return developedMarketDao.findDevelopedMarketsByUserUnique(userUnique);
	}

	@Override
	public boolean updateStopMaintainDevelopedMarket(String userUnique,String marketName) {
        int result = developedMarketDao.updateStopMaintainDevelopedMarket(userUnique,marketName,0);

        return result > 0;
	}
	
	@Override
	public boolean updateStartMaintainDevelopedMarket(String userUnique,String marketName) {
		int result = developedMarketDao.updateStopMaintainDevelopedMarket(userUnique,marketName,1);

		return result > 0;
	}


}
