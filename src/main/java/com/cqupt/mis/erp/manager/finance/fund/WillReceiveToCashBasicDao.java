package com.cqupt.mis.erp.manager.finance.fund;

import org.springframework.stereotype.Repository;

@Repository("willReceiveToCashBasicDao")
public interface WillReceiveToCashBasicDao {

    /**
     * 顾名思义
     *
     * @return
     * @author 毛家杰
     */
    public Double getRate();
}
