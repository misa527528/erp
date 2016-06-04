package com.cqupt.mis.erp.model.vo;

/**
 * 用于市场预测JS生成图形需要的VO
 *
 * @author lx
 */
public class PredictionOfGroupVO {
    private String productName;
    private int period;
    private int mount;
    private float price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getMount() {
        return mount;
    }

    public void setMount(int mount) {
        this.mount = mount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
