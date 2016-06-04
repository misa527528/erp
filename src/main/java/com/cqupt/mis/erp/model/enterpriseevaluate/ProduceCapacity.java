package com.cqupt.mis.erp.model.enterpriseevaluate;

public class ProduceCapacity {
    private Member member;
    private double capacity;
    private int producePeriod;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public int getProducePeriod() {
        return producePeriod;
    }

    public void setProducePeriod(int producePeriod) {
        this.producePeriod = producePeriod;
    }

}