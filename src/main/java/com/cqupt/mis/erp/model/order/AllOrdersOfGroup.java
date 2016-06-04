package com.cqupt.mis.erp.model.order;

public class AllOrdersOfGroup {
    private String orderID;
    private String productName;
    private Integer pNumber;
    private String marketName;
    private Integer needTime;
    private Integer MoneyTime;
    private Double penalPercent;
    private String specialRem;
    private String groupName;
    private Integer status;//表示该订单是否已经被选择用户获取了。0表示未被获取，1表示被获取了
    private Integer period;
    //单价
    private Double price;


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

    public void setAllOrdersOfGroup(String productName, Integer pNumber, Double price,
                                    String marketName, Integer needTime, Integer MoneyTime,
                                    Double penalPercent, String specialRem, String groupName,
                                    Integer status, Integer period) {
        this.productName = productName;
        this.pNumber = pNumber;
        this.price = price;
        this.marketName = marketName;
        this.needTime = needTime;
        this.MoneyTime = MoneyTime;
        this.penalPercent = penalPercent;
        this.specialRem = specialRem;
        this.groupName = groupName;
        this.status = status;
        this.period = period;
    }


}
