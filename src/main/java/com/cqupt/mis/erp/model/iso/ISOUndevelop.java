package com.cqupt.mis.erp.model.iso;

public class ISOUndevelop {
    private String userUnique;
    private String isoName;
    private int researchPeriod;//开发需要的周期数
    private Double researchCost;//每周期需要的费用
    private Double maintainCost;//每周期维护需要的费用

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

    public Integer getResearchPeriod() {
        return researchPeriod;
    }

    public void setResearchPeriod(Integer researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    public Double getResearchCost() {
        return researchCost;
    }

    public void setResearchCost(Double researchCost) {
        this.researchCost = researchCost;
    }

    public Double getMaintainCost() {
        return maintainCost;
    }

    public void setMaintainCost(Double maintainCost) {
        this.maintainCost = maintainCost;
    }
}
