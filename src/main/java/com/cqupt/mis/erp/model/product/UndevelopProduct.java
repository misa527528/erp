package com.cqupt.mis.erp.model.product;

public class UndevelopProduct {

    private String userUnique;
    private String productName;
    private int researchPeriod;
    private float researchCost;


    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getResearchPeriod() {
        return researchPeriod;
    }

    public void setResearchPeriod(int researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    public float getResearchCost() {
        return researchCost;
    }

    public void setResearchCost(float researchCost) {
        this.researchCost = researchCost;
    }
}
