package com.cqupt.mis.erp.manager.chatroom;

import com.cqupt.mis.erp.model.chatroom.ChatRoom;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
public interface ChatRoomDao {
    @Cacheable(value="chatrecords",key="#groupName")
    List<ChatRoom> findChatRecords(@Param("GroupName") String GroupName,
                                   @Param("numOfRecords") Integer numOfRecords);

    int addRecord(@Param("userName") String userName,
                  @Param("record") String record,
                  @Param("groupName") String groupName);

}
