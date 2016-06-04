package com.cqupt.mis.erp.manager.bank;

public interface LoanBasicDao {

    /**
     * 查询利率
     *
     * @param loanTypeName 贷款类型
     * @return 利率
     * @author 毛家杰
     */
    public Double findLoanBasicRate(String loanTypeName);
}
