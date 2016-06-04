package com.cqupt.mis.erp.model.factory;

public class ProductLineBasic {
    private String productLineType;// 生产线类型的名称，如“手工”，“半自动”，“全自动”等
    private float setupPeriodPrice;// 生产线每个安装周期的金额
    private Integer setupPeriod;// 生产线的安装周期
    private Integer producePeriod;// 生产线生产产品的效率，即生产1个产品需要的周期
    private Integer changePeriod;// 生产线的转产需要的周期
    private float changeCost;// 生产线的转产需要的费用
    private float mainCost;// 生产线每期的维修费用
    private float stumpCost;// 生产线的残值。即折旧到一定阶段后，无论怎么再折旧，都不再减少的价值
    private float depreciation;// 生产线投入使用后，每期折旧的价值。完工当期不折旧。
    private Integer delayTime;// 卖掉生产线后，卖生产线的钱需要延长几个账期到账

    public String getProductLineType() {
        return productLineType;
    }

    public void setProductLineType(String productLineType) {
        this.productLineType = productLineType;
    }

    public float getSetupPeriodPrice() {
        return setupPeriodPrice;
    }

    public void setSetupPeriodPrice(float setupPeriodPrice) {
        this.setupPeriodPrice = setupPeriodPrice;
    }

    public Integer getSetupPeriod() {
        return setupPeriod;
    }

    public void setSetupPeriod(Integer setupPeriod) {
        this.setupPeriod = setupPeriod;
    }

    public Integer getProducePeriod() {
        return producePeriod;
    }

    public void setProducePeriod(Integer producePeriod) {
        this.producePeriod = producePeriod;
    }

    public Integer getChangePeriod() {
        return changePeriod;
    }

    public void setChangePeriod(Integer changePeriod) {
        this.changePeriod = changePeriod;
    }

    public float getChangeCost() {
        return changeCost;
    }

    public void setChangeCost(float changeCost) {
        this.changeCost = changeCost;
    }

    public float getMainCost() {
        return mainCost;
    }

    public void setMainCost(float mainCost) {
        this.mainCost = mainCost;
    }

    public float getStumpCost() {
        return stumpCost;
    }

    public void setStumpCost(float stumpCost) {
        this.stumpCost = stumpCost;
    }

    public float getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(float depreciation) {
        this.depreciation = depreciation;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }


}
