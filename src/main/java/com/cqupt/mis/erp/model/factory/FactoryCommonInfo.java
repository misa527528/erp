package com.cqupt.mis.erp.model.factory;

public class FactoryCommonInfo {
    // FACTORY_USING
    private String userUnique;// 用户在竞赛中的唯一标识
    private String factoryId;// 在建的厂房编号
    private String factoryType;// 厂房类型的名称，如“大厂房”，“小厂房”
    private float sellPrice;// 厂房的剩余价值，即厂房的卖价
    private int beginTime;// 开始建造厂房(租赁厂房,在建)的时间，总第几期
    private int finishTime;// 厂房建造完工的时间，为总第多少期
    // FACTORYBASIC
    private int makePeriod;// 厂房建造周期，即新建一个厂房需要的周期数
    private String sellDescription;// 对一次性现金出售厂房，或出售厂房等一段账期到账两种出售方式进行说明。
    private int capacity;// 厂房可以容纳的生产线数量
    private int productLineNumber;// 已安装的生产线数量,计算字段
    // FACTORY_RENTING
    private int rentCost;// 租用该厂房时，每期需要交纳的租金.
    private int needPeriod;// 还需要等待多少周期该租赁的厂房才可以使用。当其值为0时，表示租赁的厂房可用。
    // FACTORY_MAKING
    private String status;// 厂房的状态，0表示当前停止对厂房进行建设，1表示当前对厂房进行建设
    private String payMode;// 0表示按期支付建造资金的建造方式，1表示一次性付全款的建造方式
    private String place;// 厂房建造(租赁厂房，在建厂房)在什么地点，如本地市场、国际市场
    private int finishedPeriod;// 已经完成的建设周期数

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
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

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getMakePeriod() {
        return makePeriod;
    }

    public void setMakePeriod(int makePeriod) {
        this.makePeriod = makePeriod;
    }

    public String getSellDescription() {
        return sellDescription;
    }

    public void setSellDescription(String sellDescription) {
        this.sellDescription = sellDescription;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getProductLineNumber() {
        return productLineNumber;
    }

    public void setProductLineNumber(int productLineNumber) {
        this.productLineNumber = productLineNumber;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public int getNeedPeriod() {
        return needPeriod;
    }

    public void setNeedPeriod(int needPeriod) {
        this.needPeriod = needPeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getFinishedPeriod() {
        return finishedPeriod;
    }

    public void setFinishedPeriod(int finishedPeriod) {
        this.finishedPeriod = finishedPeriod;
    }

}
