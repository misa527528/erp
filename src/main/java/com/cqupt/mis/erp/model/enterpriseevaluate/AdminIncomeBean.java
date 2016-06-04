package com.cqupt.mis.erp.model.enterpriseevaluate;

public class AdminIncomeBean {
    private String userUnique;
    private String groupName;
    private String userId;
    private int currentPeriod;
    private double interestRate;//毛利率
    private double businessProfitRate;//营业利润率
    private double totalAssestsIncomeRate;//总资产收益率
    private double assestsIncomeRate;//资产收益率
    private double ownerBenifit;//所有者权益

    public double getOwnerBenifit() {
        return ownerBenifit;
    }

    public void setOwnerBenifit(double ownerBenifit) {
        this.ownerBenifit = ownerBenifit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getBusinessProfitRate() {
        return businessProfitRate;
    }

    public void setBusinessProfitRate(double businessProfitRate) {
        this.businessProfitRate = businessProfitRate;
    }

    public double getTotalAssestsIncomeRate() {
        return totalAssestsIncomeRate;
    }

    public void setTotalAssestsIncomeRate(double totalAssestsIncomeRate) {
        this.totalAssestsIncomeRate = totalAssestsIncomeRate;
    }

    public double getAssestsIncomeRate() {
        return assestsIncomeRate;
    }

    public void setAssestsIncomeRate(double assestsIncomeRate) {
        this.assestsIncomeRate = assestsIncomeRate;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
