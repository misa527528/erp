package com.cqupt.mis.erp.service.forwardquarter.impl;

import com.cqupt.mis.erp.manager.advertisement.AdStatusOfUserDao;
import com.cqupt.mis.erp.manager.finance.BalanceSheetDao;
import com.cqupt.mis.erp.manager.finance.CashSheetDao;
import com.cqupt.mis.erp.manager.inigame.IniGameDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.vo.BalancesheetVO;
import com.cqupt.mis.erp.model.vo.GameGroupVO;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.advertisement.AdvertisementStatusOfUserService;
import com.cqupt.mis.erp.service.forwardquarter.ForwardQuarterService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("forwardQuarterService")
public class ForwardQuarterServiceImpl implements ForwardQuarterService {

	@Resource
	private GameGroupMemberService gameGroupMemberService;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private	GameGroupService gameGroupService;
	@Resource
	private GameGroupDao gameGroupDao;
	@Resource
	private AdvertisementStatusOfUserService advertisementStatusOfUserService;
	@Resource
	private CashSheetDao cashSheetDao;
	@Resource
	private AdStatusOfUserDao adStatusOfUserDao;
	@Resource
	private BalanceSheetDao balanceSheetDao;
	@Resource
	private AdvertisementService advertisementService;
	@Resource
	private IniGameDao iniGameDao;
	
	@Override
	public int ForwardStatus(String userUnique) {
		int forwardStatus = -1; //0:用户已破产 , 1:进入下一年 , 2:年初并且用户未完成广告投放和选订单
		int status = 0;
		status = gameGroupMemberService.findStatusByUserUnique(userUnique);
		if(status == 0) {
			forwardStatus = status;
		} else {
			//检查是否是年初
			boolean isYearBegin = gameGroupService.isYearBegin(userUnique);
			//检查用户是否完成了“广告投放”和“选订单”
			boolean isFinish = advertisementStatusOfUserService.findFinishOrderFlag(userUnique);
			
			if(isYearBegin && !isFinish) {
				//表示是年初并且用户“未”完成广告投放和选订单
				forwardStatus = 2;
				return forwardStatus;
			} else {
                int result = 0;
				//orcale下一周期的存储函数调用
				iniGameDao.runFunctionInOracle(userUnique, result);
				
				//检查用户是否破产
				boolean isGameOver = isGameOver(userUnique);
				if(isGameOver) {
					//破产清算
					bankruptcy(userUnique);
					forwardStatus = 0;//表示用户破产
					return forwardStatus;
				}
				
				//更新用户的时间
				gameGroupMemberDao.updateIncreaseUserCurrentPeriod(userUnique);
				
				//第15步 是否更新GAMEGROUP表中的CurrentPeriod字段值。
				String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
				updateGroupCurrentPeriod(groupName);
			
				//第16步 检查用户是否已经完成比赛
                //注意：我们直接处理需要更新的操作，如果不需要更新，则不会对数据库进行操作的
				boolean isEndGame = updateFinishedGame(userUnique);
				if(isEndGame){
					forwardStatus = 3;
					return forwardStatus;
				}
				if(gameGroupService.isYearBegin(userUnique)) {
					advertisementService.initAdOfUser(userUnique);
					advertisementStatusOfUserService.initAdStatusOfUser(userUnique);
				}
				forwardStatus = 1;
			}
		}
		return forwardStatus;
	}

	@Override
	public boolean isGameOver(String userUnique) {
		if(isBalancesheetOut(userUnique) | !isHaveCash(userUnique)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isBalancesheetOut(String userUnique) {
		List<BalancesheetVO> balancesheetVOs= balanceSheetDao.findBalancesheetVO(userUnique);
		float assets = 0,liability = 0;
		for(int i=0; i<balancesheetVOs.size(); i++) {
			BalancesheetVO balancesheetVO = balancesheetVOs.get(i);
			
			if("资产".equals(balancesheetVO.getType1())) {
				assets += balancesheetVO.getCvalue();
				//System.out.println("assets=" + assets);
			}
			
			if("负债".equals(balancesheetVO.getType1())) {
				liability += balancesheetVO.getCvalue();
				//System.out.println("liability=" + liability);
			}
		}
		
		if(assets<liability) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isHaveCash(String userUnique) {
		double cash = cashSheetDao.findCash(userUnique);
		if(cash < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public void bankruptcy(String userUnique) {
		DWRPush.refresh();
		//先更新status-设置用户状态为0(破产)
		gameGroupMemberDao.updateBankruptcyUserStatus(userUnique,0);
		
		GameGroupInfo gameGroupInfo = new GameGroupInfo();
		gameGroupInfo = gameGroupDao.findGameGroupYearAndCurrentPeriod(userUnique);
		//理论上是不需要这样的. 但是还是要将这个人这期给结束掉. 如果第一期就退出比赛的话. 
		adStatusOfUserDao.updateFinishAdFlag(userUnique, 1, 1);
		adStatusOfUserDao.updateFinishOrderFlag(userUnique, 1, 1);

		int A = gameGroupInfo.getCurrentPeriod();		//当前期数
		int B = gameGroupInfo.getPeriodsOfOneYear();	//每年的期数
		int C = gameGroupInfo.getYears();				//年数
		int D = 1;										//计算出的每年开始的第一期
		//System.out.println("A=" + A +"     B:"+ B +"    C:"+ C + "    D:" + D);
		while(true) {
			if( D>A && D<=(C-1)*B + 1){
				insertDataToAdststusofuser(userUnique,D);
			}
			if(D>(C-1)*B+1){
			    break ;
			}else{
				D = D+B;
				//继续执行if语句
		  }
		}
		
	}

	@Override
	public void insertDataToAdststusofuser(String userUnique, int D) {
        int period = D;
        int finishadflag = 1;
        int finishorderflag = 1;
        int chooseorderflag = 1;

		adStatusOfUserDao.addAdStatusOfUser(userUnique, period, finishadflag, finishorderflag, chooseorderflag);
	}

	@Override
	public boolean updateFinishedGame(String userUnique) {
		String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userUnique", userUnique);
		map.put("groupName", groupName);
		GameGroupVO gameGroupVO = new GameGroupVO();
		gameGroupVO = gameGroupDao.findGameGroupVO(map);
		int CurrentPeriod = gameGroupVO.getCurrentPeriod();
		int getTotalPeriodOfGroup = gameGroupVO.getTotalPeriodOfGroup();
		if(CurrentPeriod > getTotalPeriodOfGroup) {
			gameGroupMemberDao.updateUserStatusToFinishGame(userUnique);
			return true;
		}
		
		return false;
	}

	@Override
	public void updateGroupCurrentPeriod(String groupName) {
		int minPeriod = gameGroupMemberDao.findLeastCurrentPeriodByGroupName(groupName);
		int currentPeriod  = gameGroupDao.findCurrentPeriodByGroupName(groupName);
		if(currentPeriod < minPeriod) {
			gameGroupDao.updateGroupCurrentPeriod(groupName, minPeriod);
		}
	}
}
