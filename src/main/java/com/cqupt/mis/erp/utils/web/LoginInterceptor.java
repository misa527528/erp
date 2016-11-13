/**
 * @author lx
 *
 */
package com.cqupt.mis.erp.utils.web;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import javax.annotation.Resource;

public class LoginInterceptor extends AbstractInterceptor {
	@Resource
	private ApprovedUserDao approvedUserDao;
    @Resource
    private GameGroupMemberDao gameGroupMemberDao;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String userUnique = (String) ActionContext.getContext().getSession().get("userUnique");
		String userId = (String)ActionContext.getContext().getSession().get("userId");
		if(userId != null && userUnique == null && !"".equals(userId) && !"".equals(userUnique)){
			String userName = approvedUserDao.findUsernameByUserUnique(userUnique);
			String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
			userUnique = gameGroupMemberDao.findUserUniqueByUserId(userId);
			ActionContext.getContext().getSession().put("userName", userName);
			ActionContext.getContext().getSession().put("groupName", groupName);
			ActionContext.getContext().getSession().put("userUnique",userUnique);
		}
	//	System.out.println("userUnique= " + userUnique);
		if (null == userUnique || "".equals(userUnique)) {
			//System.out.println("userUnique= " + userUnique);
			return "loginPage"; // 这里返回用户登录页面视图
		}
		return invocation.invoke();
	}

}

/*
 * public class LoginInterceptor extends MethodFilterInterceptor{
 * 
 * private static final long serialVersionUID = -4409507846064552966L;
 * 
 * @Override protected String doIntercept(ActionInvocation invoker) throws
 * Exception { String userUnique =
 * (String)ActionContext.getContext().getSession().get("userUnique");
 * System.out.println("userUnique= " + userUnique); if(null == userUnique ||
 * "".equals(userUnique)){ System.out.println("userUnique= " + userUnique);
 * return "loginPage"; // 这里返回用户登录页面视图 }
 * 
 * return invoker.invoke();
 * 
 * 
 * }
 * 
 * }
 */