package com.cqupt.mis.erp.model.registerlogin;

public class GameGroupMemberInfo {
    private String groupName;//竞赛分组名称
    private String userID;//参与该竞赛分组的用户ID
    private String userUnique;//用户参与某个竞赛分组后，为该用户产生的唯一标识符
    private int currentPeriod;//成员当前正处于什么周期。初始值为0表示竞赛尚未开始
    private int status;//用户状态—0表示用户已经破产，1表示用户仍然在经营.2表示完成竞赛。10是还没有开始比赛，但是已经进入分组了的。一开始的时候应该是null
    private String stringStatus;//字符串形式的status。

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStringStatus() {
        return stringStatus;
    }

    public void setStringStatus(String stringStatus) {
        this.stringStatus = stringStatus;
    }

}
