package com.cqupt.mis.erp.model.enterpriseevaluate;

public class MemberSaleOfProduct {
	private Member member;
	private String productName;
	private double sale;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

}
