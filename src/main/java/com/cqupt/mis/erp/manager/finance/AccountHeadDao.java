package com.cqupt.mis.erp.manager.finance;

import com.cqupt.mis.erp.model.finance.AccountHead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("accountHeadDao")
public interface AccountHeadDao {
    /**
     * 填写分录头表(ACCOUNTHEAD)
     * @param userUnique 用户在竞赛中的唯一标识
     * @param happenTime 发生此事件的时间，即第几期
     * @param accountIdDescription 产生此分录的说明
     * @return
     */
    int addAccountHead(@Param("userUnique") String userUnique,
                       @Param("happenTime") int happenTime,
                       @Param("accountIdDescription") String accountIdDescription);

    /**
     * 查询贷款码(查询符合条件中最大的ACCOUNTID)
     * @param userUnique
     * @return 贷款码
     */
    Integer findAccountID(String userUnique);

    /**
     * 查询最近一条会计头表记录,配合findAccountID使用
     * @param accountID 最大的凭证号
     * @return 一条会计头表记录
     */
    AccountHead findLastAccountHead(Integer accountID);

    /**
     * 查询会计头表记录,带有分页色彩
     * @param userUnique
     * @param minNum 最小rowNum
     * @param maxNum 最大rowNum
     * @return
     */
    List<AccountHead> findWithPageNum(@Param("userUnique") String userUnique,
                                      @Param("minNum") Integer minNum,
                                      @Param("maxNum") Integer maxNum);

    /**
     * 查询会计头表,带有分页色彩
     * @param userUnique
     * @param minNum 最小rowNum
     * @param maxNum 最大rowNum
     * @param minTime 用户选取的开始时间
     * @param maxTime 用户选取的结束时间
     * @return
     */
    // TODO: 2016/8/16 看起来要重命名，主要是不明白sql的写法
    List<AccountHead> findWithPageNumAndTime(@Param("userUnique") String userUnique,
                                             @Param("minNum") Integer minNum,
                                             @Param("maxNum") Integer maxNum,
                                             @Param("minTime") Integer minTime,
                                             @Param("maxTime") Integer maxTime);

    /**
     * 返回页码总数
     * @param userUnique
     * @param minTime
     * @param maxTime
     * @return
     */
    Integer findRecordCount(@Param("userUnique") String userUnique,
                            @Param("minTime") Integer minTime,
                            @Param("maxTime") Integer maxTime);
}
