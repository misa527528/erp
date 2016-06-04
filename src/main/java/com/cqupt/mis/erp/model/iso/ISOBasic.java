package com.cqupt.mis.erp.model.iso;

public class ISOBasic {
    private String isoName;
    private Integer status;
    private Integer researchPeriod;
    private Double researchCost;
    private Double maintainCost;

    public void setISOBasic(String isoName, Integer status,
                            Integer researchPeriod, Double researchCost,
                            Double maintainCost) {
        this.isoName = isoName;
        this.status = status;
        this.researchCost = researchCost;
        this.researchPeriod = researchPeriod;
        this.maintainCost = maintainCost;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResearchPeriod() {
        return researchPeriod;
    }

    public void setResearchPeriod(Integer researchPeriod) {
        this.researchPeriod = researchPeriod;
    }

    public Double getResearchCost() {
        return researchCost;
    }

    public void setResearchCost(Double researchCost) {
        this.researchCost = researchCost;
    }

    public Double getMaintainCost() {
        return maintainCost;
    }

    public void setMaintainCost(Double maintainCost) {
        this.maintainCost = maintainCost;
    }
}
