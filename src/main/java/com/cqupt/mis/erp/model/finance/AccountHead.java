package com.cqupt.mis.erp.model.finance;

import java.util.List;

public class AccountHead {

    private String userUnique;//用户在竞赛中的唯一标识，外码，参照 GAMEGROUPMEMBER表中的UserUnique字段
    private Integer accountID;//会计分录的编号，使用序列产生。
    private Integer happenTime;//发生此事件的时间，即第几期
    private String accountIdDescription;//产生此分录的说明
    private List<AccountDetail> accountDetailList;//罪过,为了显示会计分录不得不这样做了

    public List<AccountDetail> getAccountDetailList() {
        return accountDetailList;
    }

    public Integer getAccountID() {
        return accountID;
    }

    public String getAccountIdDescription() {
        return accountIdDescription;
    }

    public Integer getHappenTime() {
        return happenTime;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setAccountDetailList(List<AccountDetail> accountDetailList) {
        this.accountDetailList = accountDetailList;
    }

    public void setAccountID(Integer accountID) {
        this.accountID = accountID;
    }

    public void setAccountIdDescription(String accountIdDescription) {
        this.accountIdDescription = accountIdDescription;
    }

    public void setHappenTime(Integer happenTime) {
        this.happenTime = happenTime;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

}
