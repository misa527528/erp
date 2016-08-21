package com.cqupt.mis.erp.manager.bank;

import com.cqupt.mis.erp.model.bank.LoanOfUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨青 on 2016/8/13.
 */
@Repository("loanOfUserDao")
public interface LoanOfUserDao {
    /**
     * 查询一个贷款记录
     * @param userUnique
     * @param loanID 贷款码
     * @return
     * 老版本：findALoanOfUser
     */
    LoanOfUser findALoanOfUserByUserUniqueAndLoanID
                    (@Param("userUnique") String userUnique,
                     @Param("loanID") Integer loanID);

    /**
     * 查询全部贷款记录
     * @param userUnique
     * @return
     * 老版本：findLoanOfUser
     */
    List<LoanOfUser> findLoanOfUserByUserUnique(@Param("userUnique") String userUnique);

    /**
     * 查询贷款记录
     * @param userUnique 贷款类型,'长期贷款','短期贷款','高利贷',如果给一个null就会查询所有贷款记录
     * @param status 贷款状态,0表示贷款没有归还,1表示贷款已经归还
     * @return
     * 老版本：findLoanOfUser
     */
    List<LoanOfUser> findLoanOfUserByUserUniqueAndStatus
                    (@Param("userUnique") String userUnique,
                     @Param("status") int status);

    /**
     * 根据用户唯一码和贷款状态查看指定贷款类型的贷款记录
     * @param userUnique
     * @param status
     * @param loanTypeName
     * @return
     * 老版本：findLoanOfUser
     */
    List<LoanOfUser> findLoanOfUserByUserUniqueAndStatusAndLoanTypeName
                    (@Param("userUnique") String userUnique,
                     @Param("status") int status,
                     @Param("loanTypeName") String loanTypeName);

    /**
     * 查询贷款记录
     * @param userUnique
     * @param loanTypeName
     * @return
     * 老版本：findLoanOfUser
     */
    List<LoanOfUser> findLoanOfUserByUserUniqueAndLoanTypeName
                    (@Param("userUnique") String userUnique,
                     @Param("loanTypeName") String loanTypeName);

    /**
     * 查询endTime到期的长期贷款记录
     * @param userUnique
     * @param endTime
     * @return
     * 老版本：findLoanOfUser
     */
    List<LoanOfUser> findLoanOfUserWithEndTime
                    (@Param("userUnique") String userUnique,
                     @Param("endTime") Integer endTime);

    /**
     * 插入一条记录进表LOANOFUSER
     * @param userUnique
     * @param loanTypeName
     * @param beginTime 开始时间,建议使用current
     * @param endTime 还款时间
     * @param status  0:欠款 1:已还款
     * @param money  款项金额
     * @return
     */
    int insertLoanOfUser(@Param("userUnique") String userUnique,
                         @Param("loanTypeName") String loanTypeName,
                         @Param("beginTime") Integer beginTime,
                         @Param("endTime") Integer endTime,
                         @Param("status") Integer status,
                         @Param("money") Double money);

    /**
     * 更新一个贷款记录的status为1
     * @param userUnique
     * @param loanID
     * @return 贷款码
     * 老版本：updateLoanOfUser(String userUnique, Integer loanID)
     * 原来是把status=1直接写在sql里面，现在把它提出到方法的参数列表中
     */
    int updateLoanOfUser(@Param("userUnique") String userUnique,
                         @Param("loanID") Integer loanID,
                         @Param("status") int status);
}
