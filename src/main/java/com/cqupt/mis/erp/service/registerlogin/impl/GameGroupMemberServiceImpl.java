package com.cqupt.mis.erp.service.registerlogin.impl;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.LoginService;
import com.cqupt.mis.erp.utils.ERPUtils;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import com.cqupt.mis.erp.utils.dwr.DWRScriptSessionListener;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service("gameGroupMemberService")
public class GameGroupMemberServiceImpl implements GameGroupMemberService {

	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private LoginService loginService;
	@Resource
	private ApprovedUserDao approvedUserDao;


	@Override
	public void showGameGroupMemberList() {
		HttpSession session = WebContextFactory.get().getSession();
		String groupName = (String)session.getAttribute("groupName");

		List<ApprovedUserInfo> groupMemberList = this.findGameGroupUserMessageByGroupName(groupName);
		String strMemberList = JSONUtils.toJSONString(groupMemberList);

		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "showGameGroupMemberList"+groupName);
		DWRPush.pushMessageWithFilter("displayGameGroupMemberList", strMemberList,"showGameGroupMemberList"+groupName);
	}

	@Override
	public void pageTagForGamegroupMember(){
		HttpSession session = WebContextFactory.get().getSession();
		String groupName = (String)session.getAttribute("groupName");
		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "showGameGroupMemberList"+groupName);
	}


	@Override
	public boolean addJoinInGroup(String gameGroupName, String userId) {
		try {
			String userUnique = null;
			int currentPeriod = 0;
			gameGroupMemberDao.addGameGroupMember(gameGroupName, userId, userUnique, currentPeriod);
			// DWRPush.refresh();
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("加入分组失败");
			return false;
		}
		return true;
	}


	@Override
	public boolean deleteExitGroup(String groupName, String userId) {
		/*Map<String,String> map = new HashMap<String,String>();
		map.put("groupName", groupName);
		map.put("userID", userId);*/
		try {
			gameGroupMemberDao.deleteByUserUnique(groupName, userId);
//			DWRPush.refresh();
		} catch (Exception e) {
			e.printStackTrace();
            System.out.print("这里是1");
			return false;
		}
        System.out.print("这里是2");
		return true;
	}

	@Override
	public void startGameFilter(){
		//设置相应的filter值 后面初始化完成后直接发送给对应的值
		HttpSession session = WebContextFactory.get().getSession();
		String groupName = (String)session.getAttribute("groupName");
		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "start"+groupName);
	}


	@Override
	public boolean updateUserUnqiqueAndCurrentPeriod(String groupName) {
		List<GameGroupMemberInfo> gameGroupMembers = gameGroupMemberDao.findGameGroupMemberList(groupName);
        
		//取出每条记录，用userID+时间联合创建userUnique，并且设置CurrentPeriod
		for(int i=0; i<gameGroupMembers.size(); i++) {
			GameGroupMemberInfo gameGroupMemberInfo = gameGroupMembers.get(i);
			String userID = gameGroupMemberInfo.getUserID();
			String userUnique = userID + ERPUtils.getStringDate();
			int currentPeriod = 1;
			int status = 1;
            // TODO: 2016/8/23 这里要想办法重构
            gameGroupMemberDao.updateGameGroupMember(userUnique, currentPeriod, status, userID, groupName);
		}
		return true;
	}



	@Override
	public String findGroupNameByUserId(String userId) {
		return  gameGroupMemberDao.findGroupNameByUserId(userId);
	}

	@Override
	public List<ApprovedUserInfo> findGameGroupUserMessageByGroupName(String groupName){
		ApprovedUserInfo creator = approvedUserDao.findGameGroupCreatorUserByGroupName(groupName);
		List<ApprovedUserInfo> list = approvedUserDao.findAllGameGroupUserByGroupName(groupName);

		List<ApprovedUserInfo> approvedUsers = new ArrayList<ApprovedUserInfo>(15);
		approvedUsers.add(creator);
		for(ApprovedUserInfo a : list){
			if(!a.getUserID().equals(creator.getUserID())){
				approvedUsers.add(a);
			}
		}

		return approvedUsers;

	}


	@Override
	public String findUserUniqueByUserId(String userId) {
		return gameGroupMemberDao.findUserUniqueByUserId(userId);
	}


	@Override
	public int findCurrentPeriod(String userUnique) {
		return gameGroupMemberDao.findCurrentPeriod(userUnique);
	}


	@Override
	public int findStatusByUserUnique(String userUnique) {
		return gameGroupMemberDao.findStatusByUserUnique(userUnique);
	}


	@Override
	public void startGameForward(String groupName) {
		DWRPush.pushMessageWithFilter("startgame", "","showGameGroupMemberList"+groupName);
	}


	@Override
	public void startGameForwardToMain(String groupName) {
		//调用loading中的startGameForward 方法
		DWRPush.pushMessageWithFilter("startGameForward", "","start"+groupName);
	}


	@Override
	public void updateGameGroupMemberNumber(String groupName) {
		gameGroupMemberDao.updateGameGroupNumber(groupName);
	}


	@Override
	public void exitGroupReload(String groupName) {
		DWRPush.pushMessageWithFilter("exitGroup","","showGameGroupMemberList"+groupName);
	}


	@Override
	public List<GameGroupMemberInfo> findUsersByGroupName(String groupName) {
		return gameGroupMemberDao.findUsersByGroupName(groupName);
	}

	@Override
	public boolean isGroupMember(String userId) {
		GameGroupMemberInfo groupMember = gameGroupMemberDao.finGroupMemberByUserId(userId);
		if(groupMember==null){
			return false;
		}else
			return true;
	}

	@Override
	public void updateBankruptcyUserStatus(String userUnique, int status) {
		try {
			gameGroupMemberDao.updateBankruptcyUserStatus(userUnique, status);
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("updateBankruptcyUserStatus出错了");
		}
	}
}
