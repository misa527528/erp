package com.cqupt.mis.erp.model.prediction;

public class PredictionOfGroup {
    private String groupName;
    private Integer period;
    private String marketName;
    private String productName;
    private Double price;
    private Integer mount;
    private Integer predictionID;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

    public Integer getMount() {
        return mount;
    }

    public void setMount(Integer mount) {
        this.mount = mount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPredictionID() {
        return predictionID;
    }

    public void setPredictionID(Integer predictionID) {
        this.predictionID = predictionID;
    }


}
