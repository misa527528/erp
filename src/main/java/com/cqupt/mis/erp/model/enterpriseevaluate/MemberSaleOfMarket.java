package com.cqupt.mis.erp.model.enterpriseevaluate;

public class MemberSaleOfMarket {
    private Member member;
    private String marketname;
    private double sale;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getMarketname() {
        return marketname;
    }

    public void setMarketname(String marketname) {
        this.marketname = marketname;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

}
