package com.cqupt.mis.erp.model.vo;

public class BalancesheetVO {

    private String type1;//类型表示，资产or负债
    private float cvalue;//type1期末的总值


    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public float getCvalue() {
        return cvalue;
    }

    public void setCvalue(float cvalue) {
        this.cvalue = cvalue;
    }

}
