package com.cqupt.mis.erp.model.order;

public class ChooseOrder {
    private String userUnique;
    private Integer chooseID;
    private Integer period;
    private String marketName;
    private String productName;
    private String groupName;
    private Integer chooseValue;

    public ChooseOrder() {
    }

    public ChooseOrder(String userUnique, Integer period, String marketName, String productName, String groupName, Integer sequence) {
        this.userUnique = userUnique;
        this.period = period;
        this.marketName = marketName;
        this.productName = productName;
        this.groupName = groupName;
        this.chooseValue = sequence;
    }

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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getChooseValue() {
        return chooseValue;
    }

    public void setChooseValue(Integer chooseValue) {
        this.chooseValue = chooseValue;
    }


}
