package com.cqupt.mis.erp.manager.finance;

import com.cqupt.mis.erp.model.finance.BalanceSheet;
import com.cqupt.mis.erp.model.vo.BalancesheetVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("balanceSheetDao")
public interface BalanceSheetDao {
    /**
     * 根据用户唯一码和当前的期数获得用户期初资产表
     * @param userUnique
     * @param period  当前的期数
     * @return 用户期初资产数
     */
    List<Double> findBeginValue(@Param("userUnique") String userUnique ,
                                @Param("period") int period);

    /**
     * 根据period,userUnique查询某条记录的期初值
     * @param userUnique
     * @param period 期数
     * @param oneItem 最终的账目
     * @return
     * 老版本：findBeginValue
     */
    Double findBeginValueOneItem(@Param("userUnique") String userUnique,
                          @Param("period") Integer period,
                          @Param("oneItem") String oneItem);



    /**
     * 查询资产负债表
     * @param userUnique
     * @param period 当前的期数
     * @return
     */
    List<BalanceSheet> findBalanceSheet(@Param("userUnique") String userUnique,
                                        @Param("period") int period);

    /**
     * 批量更新ENDVALUE字段,使 endValue += sumValue
     * @param userUnique
     * @param period 期数
     * @param sumValue 暂时不知道是啥
     * @param oneItem
     * @return 最终的账目
     */
    int updateBalanceEndValues(@Param("userUnique") String userUnique,
                               @Param("period") Integer period,
                               @Param("sumValue") Double sumValue ,
                               @Param("oneItem") String oneItem);

    /**
     * 初始化下一期期初数据（要求：期初数=上期期末数）
     * @param userUnique
     * @param period 期数
     * @return
     */
    int addBalanceSheets(@Param("userUnique") String userUnique,
                         @Param("period") Integer period);

    /**
     * 更新某条记录的endValue字段
     * 注意：调用该方法时，要先对endValue做四舍五入处理
     * @param userUnique
     * @param period
     * @param oneItem
     * @param endValue
     * @return
     */
    int updateOneBalanceEndValue(@Param("userUnique") String userUnique,
                                 @Param("period") Integer period,
                                 @Param("oneItem") String oneItem,
                                 @Param("endValue") Double endValue);

    /**
     * 取出某用户的期末资产和负债，用于判断是否破产
     * @param userUnique
     * @return
     */
    List<BalancesheetVO> findBalancesheetVO(String userUnique);

    /**
     * 计算所有者权益合计
     * @param userUnique
     * @param period
     * @return
     */
    // TODO: 2016/8/24 添加测试：sql中添加了nullif函数
    double CalOwnerBenifit(@Param("userUnique") String userUnique, @Param("period") int period);

}
