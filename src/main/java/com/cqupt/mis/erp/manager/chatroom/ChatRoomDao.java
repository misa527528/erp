package com.cqupt.mis.erp.manager.chatroom;

import com.cqupt.mis.erp.model.chatroom.ChatRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chatRoomDao")
public interface ChatRoomDao {

    public List<ChatRoom> findChatRecords(String GroupName, Integer numOfRecords);

    public void addRecord(String userName, String record, String groupName);

    void refreshRecord();
}
