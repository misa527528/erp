package com.cqupt.mis.erp.service.factory.impl;

import com.cqupt.mis.erp.manager.factory.FactoryBasicDao;
import com.cqupt.mis.erp.manager.factory.FactoryUsingDao;
import com.cqupt.mis.erp.manager.factory.ProductLineDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;
import com.cqupt.mis.erp.model.vo.FactoryVO;
import com.cqupt.mis.erp.service.factory.FactoryUsingService;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.service.finance.fund.CashSheetService;
import com.cqupt.mis.erp.service.finance.fund.WillReceiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("factoryUsingService")
public class FactoryUsingServiceImpl implements FactoryUsingService {
	@Resource
	private FactoryBasicDao factoryBasicDao;
	@Resource
	private FactoryUsingDao factoryUsingDao;
	@Resource
	private ProductLineDao productLineDao;
	@Resource
	private AccountHeadService accountHeadService;
	@Resource
	private AccountDetailService accountDetailService;
	@Resource
	private CashSheetService cashSheetService;
	@Resource
	private WillReceiveService willReceiveService;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	
	@Override
	public List<FactoryCommonInfo> findFactoryUsings(String userUnique) {
		return factoryUsingDao.findFactoryUsings(userUnique);
	}

	
	public FactoryCommonInfo findUsingDetail(String userUnique, String factoryId) {
		FactoryCommonInfo factoryCommonInfo = new FactoryCommonInfo();
		factoryCommonInfo = factoryUsingDao
				.findUsingDetailWithProductLineNumber(userUnique, factoryId);
		if (factoryCommonInfo == null) {
			factoryCommonInfo = factoryUsingDao.findUsingDetail(userUnique,
					factoryId);
			factoryCommonInfo.setProductLineNumber(0);
		}
		return factoryCommonInfo;
	}

	@Override
	public List<FactoryVO> findFactoryUsing(String userUnique) {
		List<FactoryVO> factoryVoList = new ArrayList<FactoryVO>(20);
		
		List<FactoryCommonInfo> factoryList = this.findFactoryUsings(userUnique);
		FactoryVO factoryVo = null;
		for(FactoryCommonInfo factoryCommon : factoryList){
			factoryVo = new FactoryVO();
			factoryCommon.setStatus("已建成");
			List<ProductLineInfo> productInfos = productLineDao.findProductLines(userUnique, factoryCommon.getFactoryId());
			factoryVo.setFactoryCommonInfo(factoryCommon);
			factoryVo.setProductLines(productInfos);
			factoryVoList.add(factoryVo);
	 	}
		
		return factoryVoList;
	}

	@Override
	public List<FactoryVO> findFactoryUsing(String userUnique, String marketName) {
		List<FactoryVO> factoryVoList = new ArrayList<FactoryVO>(20);
		
		List<FactoryCommonInfo> factoryList = factoryUsingDao.findFactoryUsingsByMarketName(userUnique,marketName);
		FactoryVO factoryVo = null;
		for(FactoryCommonInfo factoryCommon : factoryList){
			factoryVo = new FactoryVO();
			factoryCommon.setStatus("已建成");
			List<ProductLineInfo> productInfos = productLineDao.findProductLines(userUnique, factoryCommon.getFactoryId());
			factoryVo.setFactoryCommonInfo(factoryCommon);
			factoryVo.setProductLines(productInfos);
			factoryVoList.add(factoryVo);
	 	}
		
		return factoryVoList;
	}
	
	@Override
	public void deleteSaleUsingFactory(String userUnique, String factoryId) {
		FactoryCommonInfo factoryUsing = factoryUsingDao.findUsingDetail(userUnique, factoryId);
		String factoryType = factoryUsing.getFactoryType();
		String saleCatagory = factoryUsing.getSellDescription();
		Map<String,Object> accountMap = this.findABCDE(userUnique, factoryId, factoryType);
		double A = (Double) accountMap.get("A");
		double B = (Double) accountMap.get("B");
		double C = (Double) accountMap.get("C");
		double D = (Double) accountMap.get("D");
		double E = (Double) accountMap.get("E");
		int happenTime= gameGroupMemberDao.findCurrentTime(userUnique);
		// String description="";
		if(saleCatagory.startsWith("现金方式")){
			//插入第一条会计分录明细信息
			accountHeadService.addAccountHead(userUnique, happenTime, "采用现金交易方式出售厂房_1");
			accountDetailService.addAccountDetail(userUnique, "固定资产清理", "累计折旧", "厂房", "借", "借", "贷", A, B, (-C));
			//插入第二条会计分录明细信息
			accountHeadService.addAccountHead(userUnique, happenTime, "采用现金交易方式出售厂房_2");
			accountDetailService.addAccountDetail(userUnique, "现金", "固定资产清理", "借", "贷", D, D,(-D));
			//插入第三条会计分录信息（包括分录头和分录明细）
			accountHeadService.addAccountHead(userUnique, happenTime, "采用现金交易方式出售厂房_3");
			accountDetailService.addAccountDetail(userUnique, "其他业务支出", "固定资产清理", "借", "贷", E, E,(-E));
			//增加现金表中的数值
			cashSheetService.updateAddCash(userUnique, D);
			
		}else{
			//采用正常交易方式出售厂房
			int endTime=factoryBasicDao.findFactoryDelayTime(factoryType)+happenTime;
			//插入第一条会计分录信息
			accountHeadService.addAccountHead(userUnique, happenTime, "采用正常交易方式出售厂房_1");
			accountDetailService.addAccountDetail(userUnique, "固定资产清理", "累计折旧", "厂房", "借", "借", "贷",  A, B, (-C));
			//插入第二条会计分录明细信息
			accountHeadService.addAccountHead(userUnique, happenTime, "采用正常交易方式出售厂房_2");
			accountDetailService.addAccountDetail(userUnique, "应收账款", "固定资产清理", "借", "贷", A, A,(-A));
			//处理应收账款表
			willReceiveService.addWillReceive(userUnique, A, happenTime, endTime, "出售厂房的应收账款", "");
		}
		//删除factory_Using表中的信息。
		factoryUsingDao.deleteFactory(userUnique, factoryId);
	}

	@Override
	public boolean findisSale(String userUnique, String factoryId) {
		boolean result=false ;
		int number=productLineDao.findProductLineNumber(userUnique, factoryId);
		if(number>0){
			result=true;
		}
		return result;
	}

	// 这里是原来FactoryUsingDaoImpl里面的findABCDE方法
	Map findABCDE(String userUnique, String factoryId, String factoryType) {
		Map<String,Object> resultMap=new HashMap();

		float a = factoryUsingDao.findAccountA(userUnique, factoryId);
		float c = factoryBasicDao.findAccountC(factoryType);
		float b = c-a;
		float e = factoryBasicDao.findAccountE(factoryType);
		float d = a-e;
		double A = new Double(a);
		double B = new Double(b);
		double C = new Double(c);
		double D = new Double(d);
		double E = new Double(e);
		resultMap.put("A", A);
		resultMap.put("B", B);
		resultMap.put("C", C);
		resultMap.put("D", D);
		resultMap.put("E", E);

		return resultMap;
	}

}
