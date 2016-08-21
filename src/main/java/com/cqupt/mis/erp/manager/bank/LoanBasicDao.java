package com.cqupt.mis.erp.manager.bank;

import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("loanBasicDao")
public interface LoanBasicDao {
    /**
     * 查询利率
     * @param loanTypeName
     * @return
     */
    Double findLoanBasicRate(String loanTypeName);
}
