package com.cqupt.mis.erp.model.finance;

public class ProfitSheet {
    private String userUnique;// 用户在竞赛中的唯一标识
    private Integer period;// 时间，即总第几期
    private String oneItem;// 最终的账目
    private Double itemValue;// 账目对应的数值

    //为了填坑,只能创建下面这几个属性了
    private String allValue;
    private Integer rows;
    private Integer cols;

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getOneItem() {
        return oneItem;
    }

    public void setOneItem(String oneItem) {
        this.oneItem = oneItem;
    }

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public String getAllValue() {
        return allValue;
    }

    public void setAllValue(String allValue) {
        this.allValue = allValue;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }
}
