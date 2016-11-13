package com.cqupt.mis.erp.model.advertisement;

/**
 * Created by yangqing on 2016/6/2.
 */
public class Advertisement {
    private String id; //投广告费的编号
    private Integer period; //投广告费的时间，总第几期
    private String marketName; //市场名称
    private String userUnique;
    private Double money; //投入的广告费用
    private String productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getUserUnique() {

        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getMarketName() {

        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Integer getPeriod() {

        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
