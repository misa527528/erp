package com.cqupt.mis.erp.manager.finance.account;

import com.cqupt.mis.erp.model.finance.AccountHead;

import java.util.List;

public interface AccountHeadDao {

    /**
     * 填写分录头表(ACCOUNTHEAD)
     *
     * @param userUnique           用户在竞赛中的唯一标识
     * @param happenTime           发生此事件的时间，即第几期
     * @param accountIdDescription 产生此分录的说明
     * @author 毛家杰
     */
    public void addAccountHead(String userUnique, int happenTime, String accountIdDescription);

    /**
     * 查询贷款码
     *
     * @param userUnique 用户唯一码
     * @return 贷款码
     * @author 毛家杰
     */
    public Integer findAccountID(String userUnique);

    /**
     * 查询最近一条会计头表记录,配合findAccountID使用
     *
     * @param accountID 最大的凭证号
     * @return 一条会计头表记录
     * @author 毛家杰
     */
    public AccountHead findLastAccountHead(Integer accountID);

    /**
     * 查询会计头表记录,带有分页色彩
     *
     * @param userUnique 用户唯一码
     * @param minNum     最小rowNum
     * @param maxNum     最大rowNum
     * @return
     * @author 毛家杰
     */
    public List<AccountHead> findWithPageNum(String userUnique, Integer minNum, Integer maxNum);

    /**
     * 查询会计头表,带有分页色彩
     *
     * @param userUnique 用户唯一码
     * @param minNum     最小rowNum
     * @param maxNum     最大rowNum
     * @return
     * @author 毛家杰
     */
    public List<AccountHead> findWithPageNumAndTime(String userUnique, Integer minNum, Integer maxNum, Integer minTime, Integer maxTime);

    /**
     * 返回页码总数
     *
     * @param userUnique
     * @param minTime
     * @param maxTime
     * @return
     */
    public Integer findRecordCount(String userUnique, Integer minTime, Integer maxTime);
}
