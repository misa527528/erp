package com.cqupt.mis.erp.model.market;


public class DevelopingMarket {
    private Integer researchPeriod; //开拓该市场需要的周期数
    private Double researchCost; //在开拓过程中，每个周期需要支付的费用
    private Integer finishedPeriod; //已经完成的开拓周期数
    private Integer beginTime;//开始开拓该市场的时间，使用总第几个季度来表示  1,2,3,4,5,6....
    private Integer status;//市场开拓的状态，0表示当前暂停了对该市场的开拓，1表示当前继续对该市场进行开拓
    private String userUnique;
    private String marketName;

    public DevelopingMarket(UndevelopMarket market, int status, int beginTime, int finishedPeriod) {
        this.userUnique = market.getUserUnique();
        this.marketName = market.getMarketName();
        this.researchPeriod = market.getResearchPeriod();
        this.researchCost = market.getResearchCost();
        this.status = status;
        this.beginTime = beginTime;
        this.finishedPeriod = finishedPeriod;
    }

    public DevelopingMarket() {
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

    public Integer getFinishedPeriod() {
        return finishedPeriod;
    }

    public void setFinishedPeriod(Integer finishedPeriod) {
        this.finishedPeriod = finishedPeriod;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}
