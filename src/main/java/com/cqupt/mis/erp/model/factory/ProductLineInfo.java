package com.cqupt.mis.erp.model.factory;

public class ProductLineInfo {
    // PRODUCTLINE
    private String userUnique;// 用户在竞赛中的唯一标识
    private String productLineId;// 生产线的ID
    private String factoryId;// 厂房编号，表明该生产线位于哪个厂房内
    private String productLineType;// 生产线类型的名称，如“手工”，“半自动”，“全自动”等
    private String status;// 生产线的状态，0表示当前生产线并未启用，即为停产状态，1表示生产线正在进行生产，即运行状态
    private String productName;// 正在生产的产品ID
    private int productFinishPeriod;// 表示生产线上，在制产品已经完成的生产周期数
    private int finishPeriod;// 生产线已完成安装的周期数或者转产完成的周期数
    private float sellPrice;// 生产线当前的价值。
    private String factoryType;// 工厂类型,FACTORYBASIC

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductFinishPeriod() {
        return productFinishPeriod;
    }

    public void setProductFinishPeriod(int productFinishPeriod) {
        this.productFinishPeriod = productFinishPeriod;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getFinishPeriod() {
        return finishPeriod;
    }

    public void setFinishPeriod(int finishPeriod) {
        this.finishPeriod = finishPeriod;
    }


    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getFactoryType() {
        return factoryType;
    }

    public void setFactoryType(String factoryType) {
        this.factoryType = factoryType;
    }

}
