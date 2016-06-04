package com.cqupt.mis.erp.manager.finance.balancesheet;

import com.cqupt.mis.erp.model.finance.BalanceSheet;
import com.cqupt.mis.erp.model.vo.BalancesheetVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("balancesheetDao")
public interface BalancesheetDao {

    /**
     * 根据用户唯一码和当前的期数获得用户期初资产表
     *
     * @param userUnique 用户唯一码
     * @param period     当前的期数
     * @return 用户期初资产数
     * @author 毛家杰
     */
    public List<Double> findBeginValue(String userUnique, int period);

    /**
     * 查询资产负债表
     *
     * @param userUnique 用户唯一码
     * @param period     当前的期数
     * @return
     * @author 毛家杰
     */
    public List<BalanceSheet> findBalanceSheet(String userUnique, int period);

    /**
     * 查询显示在视图中的资产负债表的最大行数,参考了表BALANCESHEETTEXTBASIC
     *
     * @return
     * @author 毛家杰
     */
    public Integer findMaxRowNum();

    /**
     * 查询显示在视图中的资产负债表的最大列数,参考了表BALANCESHEETTEXTBASIC
     *
     * @return
     * @author 毛家杰
     */
    public Integer findMaxColNum();

    /**
     * 批量更新ENDVALUE字段,使 endValue += sumValue
     *
     * @param userUnique 用户唯一码
     * @param period     期数
     * @param sumValue   暂时不知道是啥
     * @param oneItem    最终的账目
     * @author 毛家杰
     */
    public void updateBalanceEndValues(String userUnique, Integer period, Double sumValue, String oneItem);

    /**
     * 批量更新ENDVALUE字段，使endValue = beginValue
     *
     * @param userUnique 用户唯一码
     * @param period     期数
     * @author 毛家杰
     */
    public void updateBalanceEndValues(String userUnique, Integer period);

    /**
     * 初始化下一期期初数据（要求：期初数=上期期末数）
     *
     * @param userUnique 用户唯一码
     * @param period     期数
     * @author 毛家杰
     */
    public void addBalanceSheets(String userUnique, Integer period);

    /**
     * 更新某条记录的endValue字段
     *
     * @param userUnique 用户唯一码
     * @param period     期数
     * @param oneItem    最终的账目
     * @param endValue   期末数
     * @author 毛家杰
     */
    public void updateOneBalanceEndValue(String userUnique, Integer period, String oneItem, Double endValue);

    /**
     * 根据period,userUnique查询某条记录的期初值
     *
     * @param userUnique 用户唯一码
     * @param period     期数
     * @param oneItem    最终的账目
     * @return
     * @author 毛家杰
     */
    public Double findBeginValue(String userUnique, Integer period, String oneItem);

    /**
     * 取出某用户的期末资产和负债，用于判断是否破产
     *
     * @param userUnique
     * @return
     * @author lx
     */
    public List<BalancesheetVO> findBalancesheetVO(String userUnique);

}
