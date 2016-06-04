package com.cqupt.mis.erp.model.product;

public class ProductBasic {
    private String productName;//产品的名称
    private Integer status; //产品开发的初始化状态，1表示初始时产品已经开发完成，直接就能生产，0表示初始时没有进行产品开发
    private Double researchPeriod;//完成该产品开发需要的周期数
    private Double researchCost;//在研发产品过程中，每个周期需要支付的开发费用


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getResearchPeriod() {
        return researchPeriod;
    }

    public void setResearchPeriod(Double researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    public Double getResearchCost() {
        return researchCost;
    }

    public void setResearchCost(Double researchCost) {
        this.researchCost = researchCost;
    }


}
