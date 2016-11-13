package com.cqupt.mis.erp.manager.finance;

import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("taxRateBasicDao")
public interface TaxRateBasicDao {
    /**
     * 取出税金比率
     * @return
     */
    float findTaxRate();
}
