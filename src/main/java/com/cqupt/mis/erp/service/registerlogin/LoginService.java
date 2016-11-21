package com.cqupt.mis.erp.service.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoginService {

	/**
	 * 
	 * 没有注册的用户:-1 					 <br/>
	 * 已经注册并且密码正确 但是没有通过审批:1 		 <br/>
	 * 已经注册或者已经审批通过的用户,但是密码不正确:2  <br/>
	 * 通过审批,没有加入分组或比赛已经结束:3		 <br/>
	 * 通过审批,比赛已经破产:4					 <br/>
	 * 通过审批,比赛已经结束:5					 <br/>
	 * 通过审批,比赛正在进行:6					 <br/>
	 * 通过审批,创建者分组:7				 	 <br/>
	 * 通过审批,已经加入了分组:8			     <br/>
	 * @param username 用户的账号
	 * @param password 用户的密码
	 * @return 
	 *int 
	 * @exception 
	 * @since  1.0.0
	 */
	Map loginForword(String username, String password, HttpServletRequest request);

	int isExistsRegister(String userId, String password);

	int isExistsApprover(String userId, String password);

	RegisterInfo findRegisterByUserId(String userId);
	
	ApprovedUserInfo findApprovedUserByUserId(String userId);

	void showMenuList();

	/**
	 * 
	 * loginStatus
	 * 1.注册用户,就是没有通过审批的用户
	 * 2.只是通过审批的用户
	 * 3.创建房间的用户.
	 * 4.加入房间的用户.
	 * 5.已经开始游戏的用户.
	 * 
	 * @param userId
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 */
	int loginStatus(String userId);
}
