package com.cqupt.mis.erp.model.product;

public class ProductOfUser {
    private String userUnique;//用户唯一标识
    private String productName;//产品名称
    private int pNumber;//产品数量
    private String wareHouseName;//仓库名称

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getpNumber() {
        return pNumber;
    }

    public void setpNumber(int pNumber) {
        this.pNumber = pNumber;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }
}
