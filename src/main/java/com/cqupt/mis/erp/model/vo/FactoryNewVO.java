package com.cqupt.mis.erp.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.cqupt.mis.erp.model.market.DevelopedMarket;

public class FactoryNewVO {
    private List<String> factoryType;//新建厂房的类型
    private List<String> place;
    private String payMode;
    private Integer currentPeriod;

    public FactoryNewVO() {
        this.factoryType = new ArrayList<String>();
        this.place = new ArrayList<String>();
        this.payMode = "按期支付建造资金";
        this.currentPeriod = 0;
    }

    public List<String> getFactoryType() {
        return factoryType;
    }

    public void setFactoryType(List<String> factoryType) {
        this.factoryType = factoryType;
    }

    public List<String> getPlace() {
        return place;
    }

    public void setPlace(List<DevelopedMarket> market) {
        for (DevelopedMarket m : market) {
            this.getPlace().add(m.getMarketName());
        }
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public Integer getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(Integer currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

}
