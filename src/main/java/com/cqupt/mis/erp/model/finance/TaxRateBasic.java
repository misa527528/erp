package com.cqupt.mis.erp.model.finance;

public class TaxRateBasic {
    private String taxRate;//税率
    private String rem;//主营业务收入的税率。

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }
}
