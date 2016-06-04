package com.cqupt.mis.erp.model.registerlogin;

public class GameGroupInfo {
    private String groupName;
    private String groupCreaterId;
    private int userNumbers;//允许容纳的人数
    private int years;//总的比赛年数
    private int currentPeriod;//分组当前正处于什么周期
    private int periodsOfOneYear;//每年所包含的周期数,约束条件为”>=2”,默认值为4
    private int autoPeriodFresh;//隔多长时间自动将竞赛推向下一个周期
    private int atuoYearFresh;//隔多长时间自动将竞赛推向下一年
    private String enableAutoPeriodFresh;//是否让自动刷新周期生效
    private String enableAutoYearFresh;//是否让自动刷新年生效
    private int joinNumber;//当前已经加入的人数,剩余的人数
    private int remandNumber;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCreaterId() {
        return groupCreaterId;
    }

    public void setGroupCreaterId(String groupCreaterId) {
        this.groupCreaterId = groupCreaterId;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public int getPeriodsOfOneYear() {
        return periodsOfOneYear;
    }

    public void setPeriodsOfOneYear(int periodsOfOneYear) {
        this.periodsOfOneYear = periodsOfOneYear;
    }

    public int getAutoPeriodFresh() {
        return autoPeriodFresh;
    }

    public void setAutoPeriodFresh(int autoPeriodFresh) {
        this.autoPeriodFresh = autoPeriodFresh;
    }

    public int getAtuoYearFresh() {
        return atuoYearFresh;
    }

    public void setAtuoYearFresh(int atuoYearFresh) {
        this.atuoYearFresh = atuoYearFresh;
    }

    public String getEnableAutoPeriodFresh() {
        return enableAutoPeriodFresh;
    }

    public void setEnableAutoPeriodFresh(String enableAutoPeriodFresh) {
        this.enableAutoPeriodFresh = enableAutoPeriodFresh;
    }

    public String getEnableAutoYearFresh() {
        return enableAutoYearFresh;
    }

    public void setEnableAutoYearFresh(String enableAutoYearFresh) {
        this.enableAutoYearFresh = enableAutoYearFresh;
    }

    public int getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(int joinNumber) {
        this.joinNumber = joinNumber;
    }

    public int getRemandNumber() {
        return remandNumber;
    }

    public void setRemandNumber(int remandNumber) {
        this.remandNumber = remandNumber;
    }

    public int getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(int userNumbers) {
        this.userNumbers = userNumbers;
    }
}
