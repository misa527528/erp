package com.cqupt.mis.erp.model.materialpurchase;

public class MaterialBasic {
    private String materialName;//原材料名称
    private float price;//原材料价格
    private int delayTime;//延迟入库时间

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

}
