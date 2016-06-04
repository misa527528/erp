package com.cqupt.mis.erp.model.market;

public class UndevelopMarket {
    private String marketName; //市场名称
    private Integer researchPeriod;//开拓该市场需要的周期数
    private Double researchCost;//在开拓过程中，每个周期需要支付的费用
    private Double maintainCost;//市场开拓好后，维护该市场每个周期需要支付的费用
    private String userUnique;


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

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }


}
