package com.cqupt.mis.erp.model.enterpriseevaluate;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MemberCost {

    private Member member;
    private double productCost;
    private double adCost;
    private double operationCost;
    private double managementCost;
    private double depreciationCost;
    private double interestCost;
    private double totalSale;
    private int year;

    public double getAdCost() {
        return adCost;
    }

    public double getDepreciationCost() {
        return depreciationCost;
    }

    public double getInterestCost() {
        return interestCost;
    }

    public double getManagementCost() {
        return managementCost;
    }

    public Member getMember() {
        return member;
    }

    public double getOperationCost() {
        return operationCost;
    }

    public double getProductCost() {
        return productCost;
    }

    public double getRate(double cost) {
        double rate = cost / totalSale;
        if (!Double.isNaN(rate) && !Double.isInfinite(rate)) {
            NumberFormat numFormat = new DecimalFormat("#.####"); // ������λС��Ҳ���ǰٷֺź�����λС��
            return Double.parseDouble(numFormat.format(rate));
        }
        return 0;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public int getYear() {
        return year;
    }

    public void setAdCost(double adCost) {
        this.adCost = adCost;
    }

    public void setDepreciationCost(double depreciationCost) {
        this.depreciationCost = depreciationCost;
    }

    public void setInterestCost(double interestCost) {
        this.interestCost = interestCost;
    }

    public void setManagementCost(double managementCost) {
        this.managementCost = managementCost;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setOperationCost(double operationCost) {
        this.operationCost = operationCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
