package com.cqupt.mis.erp.model.factory;

public class FactoryBasicInfo {
    // 数据表FACTORYBASIC 和FACTORY_USING 中的beginTime
    private String factoryType;// 厂房类型的名称，如“大厂房”，“小厂房”
    private int makePeriod;// 厂房建造周期，即新建一个厂房需要的周期数
    private float makeCost;// 在建造过程中，每个周期需要支付的建造费用
    private float cashPrice;// 使用现金一次性支付时厂房的造价
    private String makeDescription;// 对一次性支付建造资金和支付一期资金建造一期资金两种方式进行说明。
    private float depreciation;// 厂房建造后，每期折旧的价值。完工当期不折旧。
    private float stumpCost;// 残值，即折旧到一定阶段后，无论怎么再折旧，都不可在减少的价值。
    private int capacity;// 厂房可以容纳的生产线数量
    private int rentcost;// 租用该厂房时，每期需要交纳的租金
    private int rentAvailablePeriod;// 从开始租用厂房起到租用厂房可以实际使用所需要等待的期数。
    private float sellDifferentPrice;// 卖厂房时采取一次性收取现金时，卖价与厂房剩余价值之间的差价。
    private int delayTime;// 出售发生后延迟给全部现金的账期.
    private String sellDescription;// 对一次性现金出售厂房，或出售厂房等一段账期到账两种出售方式进行说明。
    private float maintainCost;// 厂房建好后每期的维护费用，完工当期不支付维护费用
    private int begintime;// 开始建造厂房的时间

    public String getFactoryType() {
        return factoryType;
    }

    public void setFactoryType(String factoryType) {
        this.factoryType = factoryType;
    }

    public int getMakePeriod() {
        return makePeriod;
    }

    public void setMakePeriod(int makePeriod) {
        this.makePeriod = makePeriod;
    }

    public float getMakeCost() {
        return makeCost;
    }

    public void setMakeCost(float makeCost) {
        this.makeCost = makeCost;
    }

    public float getCashPrice() {
        return cashPrice;
    }

    public void setCashPrice(float cashPrice) {
        this.cashPrice = cashPrice;
    }

    public String getMakeDescription() {
        return makeDescription;
    }

    public void setMakeDescription(String makeDescription) {
        this.makeDescription = makeDescription;
    }

    public float getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(float depreciation) {
        this.depreciation = depreciation;
    }

    public float getStumpCost() {
        return stumpCost;
    }

    public void setStumpCost(float stumpCost) {
        this.stumpCost = stumpCost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRentcost() {
        return rentcost;
    }

    public void setRentcost(int rentcost) {
        this.rentcost = rentcost;
    }

    public int getRentAvailablePeriod() {
        return rentAvailablePeriod;
    }

    public void setRentAvailablePeriod(int rentAvailablePeriod) {
        this.rentAvailablePeriod = rentAvailablePeriod;
    }

    public float getSellDifferentPrice() {
        return sellDifferentPrice;
    }

    public void setSellDifferentPrice(float sellDifferentPrice) {
        this.sellDifferentPrice = sellDifferentPrice;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public String getSellDescription() {
        return sellDescription;
    }

    public void setSellDescription(String sellDescription) {
        this.sellDescription = sellDescription;
    }

    public float getMaintainCost() {
        return maintainCost;
    }

    public void setMaintainCost(float maintainCost) {
        this.maintainCost = maintainCost;
    }

    public int getBegintime() {
        return begintime;
    }

    public void setBegintime(int begintime) {
        this.begintime = begintime;
    }

}
