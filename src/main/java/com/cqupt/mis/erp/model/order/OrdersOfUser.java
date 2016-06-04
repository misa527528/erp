package com.cqupt.mis.erp.model.order;

public class OrdersOfUser {

    private String userUnique;
    private String orderID;//订单编号
    private String productName;//产品名称，外码
    private Integer pNumber;//产品数量
    private Double price;//单价
    private String marketName;//市场名称
    private Integer needTime;//需要的交货的日期，为总第几期
    private Integer moneyTime;//订单交货后，需要几个账期，货款可以到账
    private Double penalPercent;//违约金比率，查过1期交纳1期的违约金，超过多期，进行累计。
    private String specialRem;//特殊要求，如加急或者ISO认证
    private Integer endTime;//表示  订单交货  的日期，当订单未交货时，其值为NULL

    public OrdersOfUser() {
    }

    public OrdersOfUser(String userUnique, String orderId, String productName, Double price, Integer pNumber, String marketName, Integer needTime, Integer moneyTime, Double penalPercent, String specialRem, Integer endTime) {
        this.userUnique = userUnique;
        this.orderID = orderId;
        this.productName = productName;
        this.price = price;
        this.pNumber = pNumber;
        this.marketName = marketName;
        this.needTime = needTime;
        this.moneyTime = moneyTime;
        this.penalPercent = penalPercent;
        this.specialRem = specialRem;
        this.endTime = endTime;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(Integer moneyTime) {
        this.moneyTime = moneyTime;
    }


}
