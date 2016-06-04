package com.cqupt.mis.erp.model.finance;

public class TaxSheet {
    private String userUnique;
    private float tax;//税金

    public String getUserUnique() {
        return userUnique;
    }

    public void setUserUnique(String userUnique) {
        this.userUnique = userUnique;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
}
