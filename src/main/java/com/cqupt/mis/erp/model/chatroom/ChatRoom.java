package com.cqupt.mis.erp.model.chatroom;

/**
 * @author 毛家杰
 */
public class ChatRoom {
    private Integer id;//id自增
    private String userName;//说话的人的名字
    private String record;//说了些啥
    private String groupName;//他所在组的名字

    public String getGroupName() {
        return groupName;
    }

    public Integer getId() {
        return id;
    }

    public String getRecord() {
        return record;
    }

    public String getUserName() {
        return userName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
