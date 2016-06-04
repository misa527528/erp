package com.cqupt.mis.erp.model.finance;

public class AccountDetail {
    private String userUnique;//用户在竞赛中的唯一标识，外码，参照 GAMEGROUPMEMBER表中的UserUnique字段
    private Integer accountId;//会计分录的编号
    private String item;//科目的名称, 外码，参照 ACCOUNTTWOTOONEBASIC表的TwoItem
    private Double money;//金额
    private String itemType;//科目对应的借或贷类型，其值只能是“借”或者“贷”
    private Double calValue;
    //CalValue是为了方便计算“资产负债表”和“利润”表而设立的值，其数值大小与Money字段相同，但是带有符号。
    //当Item对应的账目为资产时，ItemType为“借”符号为“+”，贷为“-”；
    //当Item对应的账目为负债时，ItemType为“借”符号为“-”，贷为“+”；
    //当Item对应的账目为收入时，ItemType为“借”符号为“-”，贷为“+”；
    //当Item对应的账目为成本时，ItemType为“借”符号为“+”，贷为“-”；
    //当Item对应的账目为利润时，ItemType为“借”符号为“-”，贷为“+”；
    //当Item对应的账目为所有者权益时，ItemType为“借”符号为“-”，贷为“+”；

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Double getCalValue() {
        return calValue;
    }

    public void setCalValue(Double calValue) {
        this.calValue = calValue;
    }
}
