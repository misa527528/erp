package com.cqupt.mis.erp.model.order;

/**
 * 辅助产生订单和市场预测的order基础
 *
 * @author Administrator
 */
public class OrderPredictionBasic {
    private String productName;
    private double price;
    private int mount;
    private int priceDifference;
    private int mountDifference;
    private double priceFloat;
    private double mountFloat;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public int getPriceDifference() {
        return priceDifference;
    }

    public void setPriceDifference(int priceDifference) {
        this.priceDifference = priceDifference;
    }

    public int getMountDifference() {
        return mountDifference;
    }

    public void setMountDifference(int mountDifference) {
        this.mountDifference = mountDifference;
    }

    public double getPriceFloat() {
        return priceFloat;
    }

    public void setPriceFloat(double priceFloat) {
        this.priceFloat = priceFloat;
    }

    public double getMountFloat() {
        return mountFloat;
    }

    public void setMountFloat(double mountFloat) {
        this.mountFloat = mountFloat;
    }
}
