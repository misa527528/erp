package com.cqupt.mis.erp.service.advertisement.impl;

import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.service.advertisement.AdAndOrderRefreshService;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import com.cqupt.mis.erp.utils.dwr.DWRScriptSessionListener;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service("adAndOrderRefreshService")
public class AdAndOrderRefreshServiceImpl implements AdAndOrderRefreshService {
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	
	@Override
	public void getAdAndOrderFlag() {
		HttpSession session = WebContextFactory.get().getSession();
		String userUnique = (String)session.getAttribute("userUnique");
		String groupName = null;
		if(userUnique !=null){
			groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
		}else {
			String userId = (String)session.getAttribute("userId");
			groupName = gameGroupMemberDao.findCurrentGroupName(userId);
		}
		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "adAndOrderFlag"+groupName);
	}

	@Override
	public void changeOrder(String userUnique) {
		String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
		DWRPush.pushMessageWithFilter("changeOrder",null,"adAndOrderFlag"+groupName);
	}

}
