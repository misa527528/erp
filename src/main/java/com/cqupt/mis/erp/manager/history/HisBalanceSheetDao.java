package com.cqupt.mis.erp.manager.history;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("hisBalanceSheetDao")
public interface HisBalanceSheetDao {
    /**
     * 计算所有者权益合计
     * @param userUnique
     * @param period
     * @return
     */
    // TODO: 2016/8/24 添加测试
    double CalOwnerBenifit(@Param("userUnique") String userUnique,
                           @Param("period") int period);
}
