package com.cqupt.mis.erp.manager.chatroom;

import com.cqupt.mis.erp.model.chatroom.ChatRoom;

import java.util.List;

public interface ChatRoomDao {

    public List<ChatRoom> findChatRecords(String GroupName, Integer numOfRecords);

    public void addRecord(String userName, String record, String groupName);

    void refreshRecord();
}
