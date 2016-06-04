package com.cqupt.mis.erp.model.product;

public class DevelopingProduct {

    private String userUnique;
    private String productName;
    private int researchPeriod;
    private float researchCost;
    private int finishedPeriod;
    private int beginTime;
    private int status;

    public void iniDevelopingProduct(String userUnique,
                                     String productName,
                                     int researchPeriod,
                                     float researchCost,
                                     int finishedPeriod,
                                     int beginTime,
                                     int status) {
        this.setUserUnique(userUnique);
        this.setProductName(productName);
        this.setResearchPeriod(researchPeriod);
        this.setResearchCost(researchCost);
        this.setFinishedPeriod(finishedPeriod);
        this.setBeginTime(beginTime);
        this.setStatus(status);

    }

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

    public int getFinishedPeriod() {
        return finishedPeriod;
    }

    public void setFinishedPeriod(int finishedPeriod) {
        this.finishedPeriod = finishedPeriod;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
