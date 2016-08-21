package com.cqupt.mis.erp.manager.market;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("marketBasicDao")
public interface MarketBasicDao {
    List<String> findMarketName();
}
