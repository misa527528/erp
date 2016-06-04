package com.cqupt.mis.erp.model.finance;

/**
 * @author 毛家杰
 */
public class WillReceive {
    private String userUnique;
    private Integer willReceiveID;
    private Double money;
    private Integer beginTime;
    private Integer endTime;
    private String willReceiveDescription;
    private String note;

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public Integer getWillReceiveID() {
        return willReceiveID;
    }

    public void setWillReceiveID(Integer willReceiveID) {
        this.willReceiveID = willReceiveID;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getWillReceiveDescription() {
        return willReceiveDescription;
    }

    public void setWillReceiveDescription(String willReceiveDescription) {
        this.willReceiveDescription = willReceiveDescription;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
