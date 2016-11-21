package com.cqupt.mis.erp.service.registerlogin;

import com.cqupt.mis.erp.model.registerlogin.GameGroupInfo;
import com.cqupt.mis.erp.model.registerlogin.GameGroupMemberStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface GameGroupService {

	void showGameGroupList();

	/**
	 * 重写了上面的方法. 同时会记录一个tag标签
	 * @param session
	 * @return
	 */
	List<GameGroupInfo> showGameGroupList(HttpSession session);

	boolean addGameGroup(GameGroupInfo gameGoupInfo);

	GameGroupInfo findGameGroupInfoByGroupName(String groupName);

	/**
	 * 
	 * deleteGameGroupByGroupName 删除游戏组
	 * 
	 * @param groupName
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	boolean deleteGameGroupByGroupName(String groupName);

	/**
	 * 取出游戏年数
	 * 
	 * @author LX
	 * @param groupName
	 * @return
	 */
	int findYearsByGroupName(String groupName);

	/**
	 * 判断该用户当前周期是否是年初？
	 * 
	 * @author LX
	 */
	boolean isYearBegin(String userUnique);

	/**
	 * 拿出所有游戏组
	 * 
	 * @author zjs
	 */
	List<GameGroupInfo> showGameGroups();

	/**
	 * 连表查询用户基本信息和游戏状态信息
	 * 
	 * @param groupName
	 */
	List<GameGroupMemberStatus> findGameGroupMemberStatusByGroupName(
			String groupName);
	/**
	 * 结束经营
	 * @param userUnique
	 * @param groupName
	 */
	int endPlayGame(String userUnique, String groupName);

	/**
	 * delete_GameGroupByGroupName 删除游戏组 
	 * @author 张金胜
	 * @param groupName
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean delete_GameGroupByGroupName(String groupName);

	/**
	 * changeToHistory 将小组记录转化成历史记录同时并删除. 
	 * @param groupName
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean addChangeToHistory(String groupName);

	boolean isGameRunning(String userId);

	boolean isGroupCreator(String userId);

	Integer findPeriodOfYear(String userUnique);

}
