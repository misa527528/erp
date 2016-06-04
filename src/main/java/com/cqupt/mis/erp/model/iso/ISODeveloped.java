package com.cqupt.mis.erp.model.iso;

public class ISODeveloped {
    private String userUnique;
    private String isoName;
    private int status;//状态 1 正在维护，0 暂停维护
    private int beginTime;//开发开始时间
    private int endTime;//开发完成时间
    private int lastStatus;//状态 ：上一周期是否支付了维持费，1 支付，0 未支付
    private Double maintainCost;//每周期维护费用

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(int lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Double getMaintainCost() {
        return maintainCost;
    }

    public void setMaintainCost(Double maintainCost) {
        this.maintainCost = maintainCost;
    }
}
