package com.cqupt.mis.erp.model.iso;

public class ISODeveloping {
    private String userUnique;
    private String isoName;
    private int researchPeriod;//开发需要的周期数
    private Double researchCost;//每周期需要的费用
    private int finishedPeriod;//已经开发的周期数
    private int beginTime;//开发开始时间
    private int status;//状态 1 正在开发，0 暂停开发

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

    public int getResearchPeriod() {
        return researchPeriod;
    }

    public void setResearchPeriod(int researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    public Double getResearchCost() {
        return researchCost;
    }

    public void setResearchCost(Double researchCost) {
        this.researchCost = researchCost;
    }

    public int getFinishedPeriod() {
        return finishedPeriod;
    }

    public void setFinishedPeriod(int finishedPeriod) {
        this.finishedPeriod = finishedPeriod;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
