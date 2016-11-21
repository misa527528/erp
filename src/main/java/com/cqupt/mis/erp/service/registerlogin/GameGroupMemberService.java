package com.cqupt.mis.erp.service.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.ApprovedUserInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberInfo;

import java.util.List;

public interface GameGroupMemberService{
	
	/**
	 * 
	 * showGameGroupMemberList dwr框架直接访问的方法 用来提取 gamegroupmember列表的
	 *void
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	void showGameGroupMemberList();
	
	/**
	 * pageTagForGamegroupMember 为了优化上面的方法. ....唉唉
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void pageTagForGamegroupMember();
	/**
	 * 
	 * joinInGroup 加入已经创建的分组 //这里的名字可能有点问题
	 * @param gameGroupName
	 * @param userId
	 * @return 
	 *boolean
	 * @exception 
	 * @author hhy
	 */
	boolean addJoinInGroup(String gameGroupName, String userId);

	/**
	 * 
	 * exitGroup 退出游戏组  //这里的名字可能有点问题
	 * @param groupName
	 * @param userId
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean deleteExitGroup(String groupName, String userId);

	/**
	 * 初始化用户唯一名和当前时期
	 * @param groupName
	 * @return
	 * @author lx
	 * 
	 */
	boolean updateUserUnqiqueAndCurrentPeriod(String groupName);

	/**
	 * 
	 * findGroupNameByUserId 查找组名根据 userid
	 * @param userId
	 * @return 
	 *String
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	String findGroupNameByUserId(String userId);

	/**
	 * 
	 * findGameGroupUserMessageByGroupName 查找游戏组的详细信息并且以第一个是创建者的形式表现出来.
	 * @param groupName
	 * @return 
	 *List<ApprovedUserInfo>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<ApprovedUserInfo> findGameGroupUserMessageByGroupName(String groupName);
	
	/**
	 * 根据userId来查找出userunique
	 * @author LX
	 * @param userId
	 * @return
	 */
	String findUserUniqueByUserId(String userId);
	
	/**
	 * 
	 * findCurrentPeriod 在GameGroupMember 中根据userUnique 来找到 CurrentPeriod(当前周期)
	 * @param userUnique
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 * 
	 */
	int findCurrentPeriod(String userUnique);
	
	/**
	 * 取出当前用户的状态值
	 * @author LX
	 * @param userUnique
	 * @return
	 */
	int findStatusByUserUnique(String userUnique);

	/**
	 * 
	 * startGameForward 控制跳转到指定的loading 页面
	 * @param groupName 
	 *void
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	void startGameForward(String groupName);

	/**
	 * startGameFilter 加入相应的filter 
	 *void
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	void startGameFilter();

	/**
	 * startGameForwardToMain 跳转到main.html
	 * @param groupName 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void startGameForwardToMain(String groupName);

	/**
	 * updateGameGroupMemberNumber 减少组里面的人数 减少一个单位
	 * @param groupName 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void updateGameGroupMemberNumber(String groupName);

	/**
	 * 
	 * exitGroupReload 为了刷新而生
	 * @param groupName 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void exitGroupReload(String groupName);
	
	/**
	 * findUsersByGroupName 找到组里面相应的名字
	 * @param groupName
	 * @return 
	 *List<GameGroupMemberInfo>
	 * @exception 
	 * @since  1.0.0
	 */
	List<GameGroupMemberInfo> findUsersByGroupName(String groupName);

	boolean isGroupMember(String userId);

    void updateBankruptcyUserStatus(String userUnique,int status);
}
