package com.cqupt.mis.erp.manager.finance;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("cashSheetDao")
public interface CashSheetDao {
    /**
     * 根据userUnique找出现金金额
     * @param userUnique
     * @return 现金金额
     */
    Double findCash(String userUnique);

    /**
     * 增加用户的现金
     * @param userUnique
     * @param cash 现金金额
     * @return
     */
    int addCash(@Param("userUnique") String userUnique,
                @Param("cash") Double cash);

    /**
     * 减少用户的现金
     * @param userUnique
     * @param cash 现金金额
     * @return
     */
    int subtractCash(@Param("userUnique") String userUnique,
                     @Param("cash") Double cash);
}
