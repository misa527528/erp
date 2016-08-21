package com.cqupt.mis.erp.manager.finance;

import org.springframework.stereotype.Repository;

/**
 * Created by 杨青 on 2016/8/14.
 */
@Repository("profitSheetTextBasicDao")
public interface ProfitSheetTextBasicDao {
    /**
     * 查询显示在视图中的利润表的最大行数,参考了表BALANCESHEETTEXTBASIC
     * @return
     */
    Integer findMaxRowNum();

    /**
     * 查询显示在视图中的利润表的最大列数,参考了表BALANCESHEETTEXTBASIC
     * @return
     */
    Integer findMaxColNum();
}
