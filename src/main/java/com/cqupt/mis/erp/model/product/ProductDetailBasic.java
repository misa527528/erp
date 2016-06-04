package com.cqupt.mis.erp.model.product;

public class ProductDetailBasic {

    private String productName;//产品名称
    private String materialName;//原材料名称
    private int mNumber;//原材料数量

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public int getmNumber() {
        return mNumber;
    }

    public void setmNumber(int mNumber) {
        this.mNumber = mNumber;
    }

}
