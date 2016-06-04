package com.cqupt.mis.erp.model.vo;

/**
 * ChooseOrderVO
 * <p>
 * hhy
 * vo是值传递的对象 只是单纯为了展示而使用的 没有任何经济价值.
 * 2014年10月27日 上午9:28:00
 *
 * @version 1.0.0
 */
public class ChooseOrderVO {
    //这里的userUnique 指的是能够选择这张单子的人。
    private String userUnique;
    private Integer chooseID;
    /*	private Integer period;
        private String marketName;
        private String productName;
        private String groupName;*/
    private Integer chooseValue;
    //下面是allOrderOfGroup
    private String orderID;
    private String productName;
    private Integer pNumber;
    private String marketName;
    private Integer needTime;
    private Integer MoneyTime;
    private Double penalPercent;
    private String specialRem;
    private String groupName;
    private Integer status;
    private Integer period;
    private Double price;


    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public Integer getChooseID() {
        return chooseID;
    }

    public void setChooseID(Integer chooseID) {
        this.chooseID = chooseID;
    }

    public Integer getChooseValue() {
        return chooseValue;
    }

    public void setChooseValue(Integer chooseValue) {
        this.chooseValue = chooseValue;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getpNumber() {
        return pNumber;
    }

    public void setpNumber(Integer pNumber) {
        this.pNumber = pNumber;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public Integer getMoneyTime() {
        return MoneyTime;
    }

    public void setMoneyTime(Integer moneyTime) {
        MoneyTime = moneyTime;
    }

    public Double getPenalPercent() {
        return penalPercent;
    }

    public void setPenalPercent(Double penalPercent) {
        this.penalPercent = penalPercent;
    }

    public String getSpecialRem() {
        return specialRem;
    }

    public void setSpecialRem(String specialRem) {
        this.specialRem = specialRem;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
