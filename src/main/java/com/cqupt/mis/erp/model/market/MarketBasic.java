package com.cqupt.mis.erp.model.market;

public class MarketBasic {
    private String marketName;
    private Integer researchPeriod;
    private Double researchCost;
    private Double maintainCost;
    private Integer status;


    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
