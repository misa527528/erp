package com.cqupt.mis.erp.model.factory;

public class ProductLineCommonInfo {
    // PRODUCTLINE
    private String userUnique;// 用户在竞赛中的唯一标识
    private String productLineId;// 生产线的ID
    private String factoryId;// 厂房编号，表明该生产线位于哪个厂房内
    private String factorytype;// 工厂类型
    private String productLineType;// 生产线类型的名称，如“手工”，“半自动”，“全自动”等
    private int intstatus;// 保存status的整形形式。
    private String status;// 生产线的状态，0表示当前生产线并未启用，即为停产状态，1表示生产线正在进行生产，即运行状态
    private int finishPeriod;// 生产线已完成安装的周期数或者转产完成的周期数
    private String rem;// 对productFinishPeriod和finishPeriod的说明。
    private String productName;// 正在生产的产品ID
    private int productFinishPeriod;// 表示生产线上，在制产品已经完成的生产周期数
    private float sellPrice;// 生产线当前的价值。
    // PRODUCTLINEBASIC
    private float setupPeriodPrice;// 生产线每个安装周期的金额
    private int setupPeriod;// 生产线的安装周期
    private int producePeriod;// 生产线生产产品的效率，即生产1个产品需要的周期
    private int changePeriod;// 生产线的转产需要的周期
    private float changeCost;// 生产线的转产需要的费用
    private float mainCost;// 生产线每期的维修费用
    private float stumpCost;// 生产线的残值。即折旧到一定阶段后，无论怎么再折旧，都不再减少的价值
    private float depreciation;// 生产线投入使用后，每期折旧的价值。完工当期不折旧。
    private int delayTime;// 卖掉生产线后，卖生产线的钱需要延长几个账期到账


    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getFactorytype() {
        return factorytype;
    }

    public void setFactorytype(String factorytype) {
        this.factorytype = factorytype;
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

    public float getSetupPeriodPrice() {
        return setupPeriodPrice;
    }

    public void setSetupPeriodPrice(float setupPeriodPrice) {
        this.setupPeriodPrice = setupPeriodPrice;
    }

    public int getSetupPeriod() {
        return setupPeriod;
    }

    public void setSetupPeriod(int setupPeriod) {
        this.setupPeriod = setupPeriod;
    }

    public int getProducePeriod() {
        return producePeriod;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setProducePeriod(int producePeriod) {
        this.producePeriod = producePeriod;
    }

    public int getChangePeriod() {
        return changePeriod;
    }

    public void setChangePeriod(int changePeriod) {
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

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getFinishPeriod() {
        return finishPeriod;
    }

    public void setFinishPeriod(int finishPeriod) {
        this.finishPeriod = finishPeriod;
    }

    public int getIntstatus() {
        return intstatus;
    }

    public void setIntstatus(int intstatus) {
        this.intstatus = intstatus;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(String factoryId) {
        this.factoryId = factoryId;
    }

    public String getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(String productLineId) {
        this.productLineId = productLineId;
    }
}
