package com.cqupt.mis.erp.service.chatroom.impl;

import com.cqupt.mis.erp.manager.chatroom.ChatRoomDao;
import com.cqupt.mis.erp.manager.registerlogin.ApprovedUserDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.chatroom.ChatRoom;
import com.cqupt.mis.erp.service.chatroom.ChatRoomService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.dwr.DWRPush;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("chatRoomService")
public class ChatRoomServiceImpl implements ChatRoomService{
	
	@Resource
	private ChatRoomDao chatRoomDao;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
	private ApprovedUserDao approvedUserDao;
	
	private static final Integer numOfRecords = 50;//如果输入50 , 那么就会返回50条记录



	@Override
	public List<ChatRoom> findChatRecords(String userUnique) {
		String groupName = gameGroupMemberDao.findGameGroupNameByUserUnique(userUnique);
		if(groupName == null){
			groupName = gameGroupMemberDao.findCurrentGroupName(userUnique);
			if(groupName == null){
				groupName = "";
			}
		}
		return chatRoomDao.findChatRecords(groupName,numOfRecords);
	}

	/**
	 * @author yangqing
	 * 将chatRoomDao.refreshRecord();从chatRoomDao.addRecord(userName, record, groupName);
	 * 中提取到这里，避免DAO层感染
	 */
	@Override
	public void addChatRecordByUserId(String groupName, String userName, String record) {
		refreshRecord();
		chatRoomDao.addRecord(userName, record, groupName);
		this.apendDialogGameStart(userName, record, groupName);
		return;
	}
	/**
	 * @author yangqing
	 * 将chatRoomDao.refreshRecord();从chatRoomDao.addRecord(userName, record, groupName);
	 * 中提取到这里，避免DAO层感染
	 */
    // TODO: 2016/8/22 为什么返回类型是void？？
    @Override
	public void addChatRecordByUserUnique(String userUnique, String groupName,String record) {
		String userName = approvedUserDao.findUsernameByUserUnique(userUnique);
		refreshRecord();
		chatRoomDao.addRecord(userName, record, groupName);
		this.apendDialog(userName, record, groupName);
		return;
	}

	@Override
	//@CacheEvict(value="chatrecords",allEntries=true)
	public void refreshRecord() {
	}

	public void apendDialog(String userName, String record,String groupName){
		ChatRoom chat = new ChatRoom();
		chat.setRecord(record);
		chat.setUserName(userName);
		chat.setGroupName(groupName);
		DWRPush.pushMessageWithFilter("apendDialog",JSONUtils.toJSONString(chat),"adAndOrderFlag"+groupName);
	}
	
	public void apendDialogGameStart(String userName, String record,String groupName){
		ChatRoom chat = new ChatRoom();
		chat.setRecord(record);
		chat.setUserName(userName);
		chat.setGroupName(groupName);
		//这个标记是还没有开始游戏的时候分的.
		DWRPush.pushMessageWithFilter("apendDialog",JSONUtils.toJSONString(chat),"showGameGroupMemberList"+groupName);
	}

	
}
