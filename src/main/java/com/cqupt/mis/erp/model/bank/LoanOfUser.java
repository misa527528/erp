package com.cqupt.mis.erp.model.bank;

/**
 * Created by yangqing on 2016/6/2.
 */
public class LoanOfUser {
    private String userUnique;// 用户在竞赛中的唯一标识，外码，参照 GAMEGROUPMEMBER表中的UserUnique字段， 主码
    private Integer loanID;// 主码，贷款编号
    private String loanTypeName;// 贷款类型
    private int beginTime;// 开始贷款的时间
    private int endTime;// 归还贷款的时间
    private int status;// 0，表示贷款还没有归还，1表示贷款已经归还了
    Double money;// 贷款的数量

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public Integer getLoanID() {
        return loanID;
    }

    public void setLoanID(Integer loanID) {
        this.loanID = loanID;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public int getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(int beginTime) {
        this.beginTime = beginTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
