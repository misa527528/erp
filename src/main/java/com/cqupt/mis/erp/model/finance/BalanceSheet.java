package com.cqupt.mis.erp.model.finance;

public class BalanceSheet {
    private String userUnique;// 用户在竞赛中的唯一标识，外码，参照GAMEGROUPMEMBER
    private int period;// 时间，即总第几期
    private String oneItem;// 最终的账目
    private double beginValue;// 期初数
    private double endValue;// 期末数

    //下面三个属性纯粹是为了填坑才设置的,艾玛
    private String allValue;//数值
    private Integer rows;//行标
    private Integer cols;//列表

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getAllValue() {
        return allValue;
    }

    public void setAllValue(String allValue) {
        this.allValue = allValue;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getOneItem() {
        return oneItem;
    }

    public void setOneItem(String oneItem) {
        this.oneItem = oneItem;
    }

    public double getBeginValue() {
        return beginValue;
    }

    public void setBeginValue(double beginValue) {
        this.beginValue = beginValue;
    }

    public double getEndValue() {
        return endValue;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }
}
