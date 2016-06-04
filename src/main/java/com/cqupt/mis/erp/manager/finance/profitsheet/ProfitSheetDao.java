package com.cqupt.mis.erp.manager.finance.profitsheet;

import com.cqupt.mis.erp.model.finance.ProfitSheet;

import java.util.List;

public interface ProfitSheetDao {
    /**
     * 查询利润表
     *
     * @param userUnique 用户唯一码
     * @param period     当前的期数
     * @return
     * @author 毛家杰
     */
    public List<ProfitSheet> findProfitSheet(String userUnique, int period);

    /**
     * 查询显示在视图中的利润表的最大行数,参考了表BALANCESHEETTEXTBASIC
     *
     * @return
     * @author 毛家杰
     */
    public Integer findMaxRowNum();

    /**
     * 查询显示在视图中的利润表的最大列数,参考了表BALANCESHEETTEXTBASIC
     *
     * @return
     * @author 毛家杰
     */
    public Integer findMaxColNum();
}
