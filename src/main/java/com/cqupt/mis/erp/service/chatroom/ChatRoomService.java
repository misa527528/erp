package com.cqupt.mis.erp.service.chatroom;

import com.cqupt.mis.erp.model.chatroom.ChatRoom;

import java.util.List;

public interface ChatRoomService {
	
	/**
	 * 寻找聊天记录
	 * 
	 * @author 毛家杰
	 * @param userUnique 用户唯一码
	 * @return
	 */
	List<ChatRoom> findChatRecords(String userUnique);
	
	/**
	 * 
	 * addChatRecordByUserId  
	 * 何海源重新修改 
	 * @param groupName 组名	
	 * @param userName 用户名
	 * @param record 聊天记录
	 *void
	 * @exception 
	 * 
	 */
	void addChatRecordByUserId(String groupName, String userName, String record);

	/**
	 * addChatRecordByUserUnique 针对 开始游戏之后的方法.
	 * 
	 * @param userUnique
	 * @param groupName
	 * @param record 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void addChatRecordByUserUnique(String userUnique, String groupName, String record);

	/**
	 * 清空缓存
	 * 原来是在DAO的Impl，重构时把它往上提到service层
	 */
	void refreshRecord();
}
