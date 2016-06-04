package com.cqupt.mis.erp.manager.finance.fund;

import com.cqupt.mis.erp.manager.tool.BaseDao;

public interface CashDao extends BaseDao {

    /**
     * 根据userUnique找出现金金额
     *
     * @param userUnique 用户唯一码
     * @return 现金金额
     * @author 毛家杰
     */
    public Double findCash(String userUnique);

    /**
     * 增加用户的现金
     *
     * @param userUnique 用户唯一码
     * @param cash       现金金额
     * @author 毛家杰
     */
    public void addCash(String userUnique, Double cash);

    /**
     * 减少用户的现金
     *
     * @param userUnique 用户唯一码
     * @param cash       现金金额
     * @author 毛家杰
     */
    public void subtractCash(String userUnique, Double cash);
}
