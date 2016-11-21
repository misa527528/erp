package com.cqupt.mis.erp.service.registerlogin.impl;

import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.manager.registerlogin.RegisterUserDao;
import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.RegisterInfo;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.service.registerlogin.LoginService;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import com.cqupt.mis.erp.utils.dwr.DWRScriptSessionListener;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private ApprovedUserDao approvedUserDao;
	@Resource
	private RegisterUserDao registerUserDao;
	@Resource
	private GameGroupMemberService gameGroupMemberService;
	@Resource
	private GameGroupService gameGroupService;

	@Override
	public Map loginForword(String userId, String password, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		int registerResult = isExistsRegister(userId,password);
		int approvedResult = isExistsApprover(userId,password);

		if(registerResult == 1){ //已经注册的用户并且密码正确.
			RegisterInfo register = registerUserDao.findRegisterByUserId(userId);
            request.getSession().setAttribute("userId", userId);

			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.REGISTERPAGE, "登录成功", register);
		}else if(registerResult == 0){ // 已经注册的用户,但是密码不正确
			map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "用户名或密码错误", null);

		}else { // 不是注册用户则判断是否为审批通过用户
			if(approvedResult == 0){ // 已经通过审批但是密码不正确
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "用户名或密码错误", null);

			}else if(approvedResult == -1){ // 没有注册的用户
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "用户不存在", null);

			}else { // 已经通过审批的用户,或者已经开始比赛
				if(!gameGroupMemberService.isGroupMember(userId)){ // 还没有开始加入分组
                    request.getSession().setAttribute("userId", userId);
                    ApprovedUserInfo approvedUser = approvedUserDao.findApprovedUserByUserId(userId);

                    map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.NOTINAGROUPAGE, "登录成功", approvedUser);

				}else{// 已经开始比赛了
					if(gameGroupService.isGameRunning(userId)){
						String userUnique = gameGroupMemberDao.findUserUniqueByUserId(userId);
						int status = gameGroupMemberDao.getStatusByUserUnique(userUnique);
                        ApprovedUserInfo approvedUser = approvedUserDao.findApprovedUserByUserId(userId);

						if(status == 0){ // 已经破产
                            request.getSession().setAttribute("userId", userId);
                            request.getSession().setAttribute("userUnique", userUnique);

                            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.BROKENUPPAGE, "登录成功", approvedUser);

						}else if(status ==2){ // 已经完成了比赛
                            request.getSession().setAttribute("userId", userId);
                            request.getSession().setAttribute("userUnique", userUnique);

                            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.GAMEFINISHEDPAGE, "登录成功", approvedUser);
						}else if(status == 1){ // 正在比赛中
                            String userName = approvedUserDao.findUsernameByUserUnique(userUnique);
                            String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
                            request.getSession().setAttribute("userId", userId);
                            request.getSession().setAttribute("userUnique", userUnique);
                            request.getSession().setAttribute("userName", userName);
                            request.getSession().setAttribute("groupName", groupName);

                            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.GAMERUNNINGPAGE, "登录成功", approvedUser);
						}else{ // 不存在这个status状态.报错显示.
                            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "用户状态异常", null);
						}
					}else{ // 用户加入了某个分组，并且游戏尚未开始, 状态码暂时没有区分
                        String userUnique = gameGroupMemberDao.findUserUniqueByUserId(userId);
                        String userName = approvedUserDao.findUsernameByUserUnique(userUnique);
                        String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
                        ApprovedUserInfo approvedUser = approvedUserDao.findApprovedUserByUserId(userId);
                        request.getSession().setAttribute("userId", userId);
                        request.getSession().setAttribute("userUnique", userUnique);
                        request.getSession().setAttribute("userName", userName);
                        request.getSession().setAttribute("groupName", groupName);

						if(gameGroupService.isGroupCreator(userId)){ // 用户是创建者
                            map.put("status", ReturnStatus.JOINGROUPBUTNOTBEGINPAGE);
                            map = ReturnMapUtils.putIntoReturnMap(
                                    ReturnStatus.GAMECREATORBUTNOTSTARTPAGE, "登录成功", approvedUser);
						}else{ // 用户不是创建者
                            map.put("status", ReturnStatus.JOINGROUPBUTNOTBEGINPAGE);
                            map = ReturnMapUtils.putIntoReturnMap(
                                    ReturnStatus.JOINGROUPBUTNOTBEGINPAGE, "登录成功", approvedUser);
						}
					}
				}
			}
		}
        return  map;
	}

	/**
	 * 为了让破产过得用户继续游戏多加的逻辑判断. 
	 * @param userId
	 * @return
	 */
	private boolean isAlreadyPlayer(String userId){
		return gameGroupService.isGameRunning(userId);
	}

	@Override
	public  int isExistsApprover(String userId, String password) {
		ApprovedUserInfo approvedUser = approvedUserDao.findApprovedUserByUserId(userId);
		if(approvedUser != null){
			if(password.equals(approvedUser.getPassword())){
				return 1;
			}else 
				return 0;
		}else 
			return -1;
	}

	@Override
	public int isExistsRegister(String userId, String password) {
		RegisterInfo registerInfo = registerUserDao.findRegisterByUserId(userId);
		if(registerInfo != null){
			if(password.equals(registerInfo.getPassword())){
				return 1;
			}else 
				return 0;
		}else 
			return -1;
	}

	@Override
	public RegisterInfo findRegisterByUserId(String userId) {
		return registerUserDao.findRegisterByUserId(userId);
	}

	@Override
	public ApprovedUserInfo findApprovedUserByUserId(String userId) {
		return approvedUserDao.findApprovedUserByUserId(userId);
	}

	@Override
	public void showMenuList(){
		HttpSession session = WebContextFactory.get().getSession();
		String userId = (String)session.getAttribute("userId");
//System.out.println(userId);
		int result = loginStatus(userId);
		if(result==3 || result ==4 || result ==5){
			session.setAttribute("groupName", gameGroupMemberService.findGroupNameByUserId(userId));
		}
		ScriptSession scriptSession = DWRScriptSessionListener.getScriptSessionByHttpSession(session.getId());
		scriptSession.setAttribute("tag", "showMenuList"+userId);
		DWRPush.pushMessageWithFilter("displayMenuList", result+"","showMenuList"+userId);
		
	}

	@Override
	public int loginStatus(String userId){
		if(isExistsRegister(userId,"")!=-1){
			//是注册用户.
			return 1;
		}else{
			if(isExistsApprover(userId,"")!=-1){
				if(gameGroupService.isGroupCreator(userId)){
					return 3;
				}else if(gameGroupMemberService.isGroupMember(userId)){
					return 4;
				}else if(gameGroupService.isGameRunning(userId)){
					return 5;
				}
				return 2;
			}
		}
		
		return -404;//出错.
	}
}
