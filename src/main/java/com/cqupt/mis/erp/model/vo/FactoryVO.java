package com.cqupt.mis.erp.model.vo;

import java.io.Serializable;
import java.util.List;

import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;

public class FactoryVO implements Serializable {
    /**
     * serialVersionUID:TODO（用一句话描述这个变量表示什么）
     *
     * @since 1.0.0
     */
    private static final long serialVersionUID = -972456185236541497L;

    private String status;
    private String factoryId;
    private String place;
    private String factoryType;
    private Float sellPrice;
    private Integer beginTime;
    private Integer finishTime;
    private Integer productLineNumber;
    private Integer capacity;
    private Integer rentCost;
    private Integer needPeriod;
    private List<ProductLineInfo> productLines;

    public void setFactoryCommonInfo(FactoryCommonInfo f) {
        this.beginTime = f.getBeginTime();
        this.capacity = f.getCapacity();
        this.factoryId = f.getFactoryId();
        this.factoryType = f.getFactoryType();
        this.finishTime = f.getFinishTime();
        this.place = f.getPlace();
        this.productLineNumber = f.getProductLineNumber();
        this.sellPrice = f.getSellPrice();
        this.status = f.getStatus();
        this.rentCost = f.getRentCost();
        this.needPeriod = f.getNeedPeriod();
    }

    public List<ProductLineInfo> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<ProductLineInfo> productLines) {
        this.productLines = productLines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFactoryType() {
        return factoryType;
    }

    public void setFactoryType(String factoryType) {
        this.factoryType = factoryType;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Integer finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getProductLineNumber() {
        return productLineNumber;
    }

    public void setProductLineNumber(Integer productLineNumber) {
        this.productLineNumber = productLineNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRentCost() {
        return rentCost;
    }

    public void setRentCost(Integer rentCost) {
        this.rentCost = rentCost;
    }

    public Integer getNeedPeriod() {
        return needPeriod;
    }

    public void setNeedPeriod(Integer needPeriod) {
        this.needPeriod = needPeriod;
    }
}
