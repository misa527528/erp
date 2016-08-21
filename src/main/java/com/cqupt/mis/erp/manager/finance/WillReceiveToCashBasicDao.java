package com.cqupt.mis.erp.manager.finance;

import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("willReceiveToCashBasicDao")
public interface WillReceiveToCashBasicDao {
    Double getRate();
}
