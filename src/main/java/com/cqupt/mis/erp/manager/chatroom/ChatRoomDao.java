package com.cqupt.mis.erp.manager.chatroom;

import com.cqupt.mis.erp.model.chatroom.ChatRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("chatRoomDao")
public interface ChatRoomDao {
    //@Cacheable(value="chatrecords",key="#groupName")
    List<ChatRoom> findChatRecords(@Param("groupName") String groupName,
                                   @Param("numOfRecords") Integer numOfRecords);

    int addRecord(@Param("userName") String userName,
                  @Param("record") String record,
                  @Param("groupName") String groupName);

}
