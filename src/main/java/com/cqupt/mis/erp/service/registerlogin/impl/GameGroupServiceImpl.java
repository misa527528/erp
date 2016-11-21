package com.cqupt.mis.erp.service.registerlogin.impl;

import com.cqupt.mis.erp.manager.advertisement.AdStatusOfUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberStatus;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import com.cqupt.mis.erp.utils.dwr.DWRScriptSessionListener;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service("gameGroupService")
public class GameGroupServiceImpl implements GameGroupService {
	@Resource
	private GameGroupDao gameGroupDao;

	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	
	@Resource
	private AdStatusOfUserDao adStatusOfUserDao;
	@Resource
	private GameGroupMemberService gameGroupMemberService;
	@Resource
	private AdvertisementService advertisementService;
	
	@Override
	public void showGameGroupList() {
		HttpSession session = WebContextFactory.get().getSession();
		List<GameGroupInfo> gameGroupList = gameGroupDao.findAllGameGroupList();
		String gameGroupString = JSONUtils.toJSONString(gameGroupList);
		
		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "showGameGroupList"+session.getId());
		DWRPush.pushMessageWithFilter("displayGameGroupList",gameGroupString, "showGameGroupList"+session.getId());
	}
	
	@Override
	public List<GameGroupInfo> showGameGroupList(HttpSession session){
		List<GameGroupInfo> gameGroupList = gameGroupDao.findAllGameGroupList();
		System.out.println("SESSIONID: " + session.getId());
		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "showGameGroupList"+session.getId());
		return gameGroupList;
	}
	
	
	public void showOthersPage() {
		List<GameGroupInfo> gameGroupList = gameGroupDao.findAllGameGroupList();
		String gameGroupString = JSONUtils.toJSONString(gameGroupList);
		
		DWRPush.pushMessageWithFilter("displayGameGroupList",gameGroupString, "showGameGroupList");
	}
	
	
	@Override
    // todo DWRPush.refresh();导致测试无法通过
	public boolean addGameGroup(GameGroupInfo gameGoupInfo) {
		try {
			String groupName = gameGoupInfo.getGroupName();
			String groupCreaterId = gameGoupInfo.getGroupCreaterId();
			int userNumbers = gameGoupInfo.getUserNumbers();
			int years = gameGoupInfo.getYears();
			int periodsOfOneYear = gameGoupInfo.getPeriodsOfOneYear();
            int currentPeriod = 0;//初始值为0
            int autoPeriodFresh = gameGoupInfo.getAutoPeriodFresh();
            int atuoYearFresh = gameGoupInfo.getAtuoYearFresh();
            String enableAutoPeriodFresh = gameGoupInfo.getEnableAutoPeriodFresh();
            String enableAutoYearFresh = gameGoupInfo.getEnableAutoYearFresh();
            String userId = gameGoupInfo.getGroupCreaterId();
            String userUnique = null;

		 	gameGroupDao.addGameGroup(groupName, groupCreaterId, userNumbers, years, periodsOfOneYear,
                    currentPeriod, autoPeriodFresh, atuoYearFresh, enableAutoPeriodFresh, enableAutoYearFresh);

			gameGroupMemberDao.addGameGroupMember(groupName, userId, userUnique, currentPeriod);
			DWRPush.refresh();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("不能创建名字相同的小组");
			return false;
		}
		return true;
	}

	@Override
	public GameGroupInfo findGameGroupInfoByGroupName(String groupName) {
		return gameGroupDao.findGameGroupInfoByGroupName(groupName);
	}

	@Override
	public boolean deleteGameGroupByGroupName(String groupName) {
		/*try {
			gameGroupDao.deleteGameGroupByGroupName(groupName);
			//2015.1.15号修改
			//这里主要是将推送消息的机制修改了.
			//改成了 删除之后全部人 去加载 deleteGameGroup的方法 同时在js上面加多一个方法.另外要在另外一个页面上面多加一个方法.
			// 因为 dwr找不到方法会报错
			DWRPush.pushMessageWithFilter("deleteGameGroup", "","showGameGroupMemberList"+groupName);
            System.out.println("执行到这里2");
			return true;
		} catch (Exception e) {
            System.out.println("执行到这里3");
			e.printStackTrace();
			return false;
		}
*/      // 把try-catch语句去掉是因为SQL的delete语句即使是没有删除到记录也不会报错
        int result = gameGroupDao.deleteGameGroupByGroupName(groupName);
        if (result > 0){
            DWRPush.pushMessageWithFilter("deleteGameGroup", "","showGameGroupMemberList"+groupName);
            return true;
        }else {
            return false;
        }
	}

	@Override
	public int findYearsByGroupName(String groupName) {
		return gameGroupDao.findYearsByGroupName(groupName);		
	}

	@Override
	public boolean isYearBegin(String userUnique) {
		GameGroupInfo gameGroupInfo;
		gameGroupInfo =	gameGroupDao.findGameGroupYearAndCurrentPeriod(userUnique);
		int currentPeriod = gameGroupInfo.getCurrentPeriod();//当前期数
		int periodsOfOneYear = gameGroupInfo.getPeriodsOfOneYear();//每年有多少期

		// 加上这一个是因为当每一年只有一期时，第二年开始就可以不用投广告了
		if(periodsOfOneYear == 1){
			return true;
		}

		if(currentPeriod % periodsOfOneYear == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<GameGroupInfo> showGameGroups() {
		return gameGroupDao.findAllGameGroups();
	}

	/**
	 * 修改：需要先判断是是否已经开始游戏（gamegroupmember.groupName-->gamegroupmember.stutus）如果为空则还没开始游戏
	 * @since 2016-05-02
	 * @author 杨青
	 *  public List<GameGroupMemberStatus> findGameGroupMemberStatusByGroupName(String groupName) {
			return gameGroupDao.findGameGroupMemberStatusByGroupName(groupName);	
		}
	 */
	@Override
	public List<GameGroupMemberStatus> findGameGroupMemberStatusByGroupName(String groupName) {
		List<GameGroupMemberInfo> gameGroupMemberInfos = gameGroupMemberDao.findGameGroupMemberList(groupName);
		//先判断是否开始游戏（status=10表示还没开始）
		if(!gameGroupMemberInfos.isEmpty() && gameGroupMemberInfos.get(0).getStatus() == 10){
			return gameGroupDao.findGameGroupMemberStatusbygroupNameWithoutStartGame(groupName);
		} else {
			return gameGroupDao.findGameGroupMemberStatusByGroupName(groupName);
		}	
	}

	@Override
	public int endPlayGame(String userUnique, String groupName) {
		//这里开始做广告单的清算
		advertisementService.updateAdvertisementFinish(userUnique);
		//先判断是否已经破产, 如果已经破产了就不用做这个了. 
		int status = gameGroupMemberService.findStatusByUserUnique(userUnique);
		if(status != 0){
			GameGroupInfo gameGroupInfo = new GameGroupInfo();
			gameGroupInfo = gameGroupDao.findGameGroupYearAndCurrentPeriod(userUnique);
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
		int result1 = gameGroupMemberDao.deleteByUserUnique(groupName, userUnique);
		int result2 = gameGroupDao.updateUserNumbers(groupName);
		if(result1 == 1 && result2 == 1){
			return 1;
		}
		return 0;
	}

	// TODO: 2016/8/22 为什么返回类型是void？
	private void insertDataToAdststusofuser(String userUnique, int D) {
		int period = D;
		int finishadflag = 1;
		int finishorderflag = 1;
		int chooseorderflag = 1;

		adStatusOfUserDao.addAdStatusOfUser(userUnique, period, finishadflag, finishorderflag, chooseorderflag);
	}
	
	@Override
	public boolean delete_GameGroupByGroupName(String groupName) {
		int memberNum = gameGroupMemberDao.findUserNumByGroupName(groupName);
		int currentPeriod = gameGroupDao.findCurrentPeriodByGroupName(groupName);
		//2015.10.07 currentPeriod <= 0 game haven't start 
		if(memberNum > 0 && currentPeriod > 0){
			return false;
		}else{
			gameGroupDao.deleteGameGroupByGroupName(groupName);
			return true;
		}
	}

	@Override
	public boolean addChangeToHistory(String groupName) {
		GameGroupInfo g = findGameGroupInfoByGroupName(groupName);
		if(g.getCurrentPeriod()>0){
			int result = 0;
			if(gameGroupDao.addHistory(groupName, result) > 0){
				//gameGroupDao.delete(GameGroupInfo.class, groupName);
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

    @Override
    public boolean isGroupCreator(String userId) {
        GameGroupMemberInfo gameGroupCreator = gameGroupDao.findGameCreatorByUserId(userId);

        return gameGroupCreator != null;
    }

	@Override
	public Integer findPeriodOfYear(String userUnique) {
		int periodOfYear = gameGroupDao.findPeriodOfYear(userUnique);

		return periodOfYear;
	}

	@Override
    public boolean isGameRunning(String userId) {
        GameGroupMemberInfo groupMember = gameGroupMemberDao.findUserUniqueInGroupMemberByUserId(userId);
        if(groupMember!=null){
            if(groupMember.getUserUnique()!= null && groupMember.getCurrentPeriod() != 0){
                return true;
            }
        }

        return false;
    }
}
