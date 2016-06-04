package com.cqupt.mis.erp.model.prediction;

public class MarketPrediction {

    private String id;
    private Integer years;
    private String matketName;
    private String productName;
    private Double price;
    private Integer mount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public String getMatketName() {
        return matketName;
    }

    public void setMatketName(String matketName) {
        this.matketName = matketName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMount() {
        return mount;
    }

    public void setMount(Integer mount) {
        this.mount = mount;
    }


}
