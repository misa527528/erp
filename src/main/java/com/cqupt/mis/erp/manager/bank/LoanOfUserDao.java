package com.cqupt.mis.erp.manager.bank;

import com.cqupt.mis.erp.model.bank.LoanOfUser;

import java.util.List;

public interface LoanOfUserDao {

    /**
     * 查询一个贷款记录
     *
     * @param userUnique 用户唯一码
     * @param loanID     贷款码
     * @return
     * @author 毛家杰
     */
    public LoanOfUser findALoanOfUser(String userUnique, Integer loanID);

    /**
     * 查询全部贷款记录
     *
     * @param userUnique 用户唯一码
     * @return
     * @author 毛家杰
     */
    public List<LoanOfUser> findLoanOfUser(String userUnique);

    /**
     * 查询贷款记录
     *
     * @param userUnique
     * @param status
     * @return
     * @author 毛家杰
     */
    public List<LoanOfUser> findLoanOfUser(String userUnique, int status);

    /**
     * 根据用户唯一码和贷款状态查看指定贷款类型的贷款记录
     *
     * @param userUnique   用户唯一码
     * @param status       贷款状态,0表示贷款没有归还,1表示贷款已经归还
     * @param loanTypeName 贷款类型,'长期贷款','短期贷款','高利贷',如果给一个null就会查询所有贷款记录
     * @return List<LoanOfUser>
     * @author 毛家杰
     */
    public List<LoanOfUser> findLoanOfUser(String userUnique, int status,
                                           String loanTypeName);

    /**
     * 查询贷款记录
     *
     * @param userUnique
     * @param loanTypeName
     * @return
     * @author 毛家杰
     */
    public List<LoanOfUser> findLoanOfUser(String userUnique,
                                           String loanTypeName);


    /**
     * 查询endTime到期的长期贷款记录
     *
     * @param userUnique 用户唯一码
     * @param endTime    回水期限
     * @return
     * @author 毛家杰
     */
    public List<LoanOfUser> findLoanOfUserWithEndTime(String userUnique, Integer endTime);

    /**
     * 插入一条记录进表LOANOFUSER
     *
     * @param userUnique 用户唯一码
     * @param beginTime  开始时间,建议使用current
     * @param endTime    还款时间
     * @param status     0:欠款 1:已还款
     * @param money      款项金额
     * @author 毛家杰
     */
    public void insertLoanOfUser(String userUnique, String loanTypeName, Integer beginTime,
                                 Integer endTime, Integer status, Double money);


    /**
     * 更新一个贷款记录的status为1
     *
     * @param userUnique 用户唯一码
     * @param loanID     贷款码
     * @author 毛家杰
     */
    public void updateLoanOfUser(String userUnique, Integer loanID);
}