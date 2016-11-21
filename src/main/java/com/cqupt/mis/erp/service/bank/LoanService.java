package com.cqupt.mis.erp.service.bank;

import com.cqupt.mis.erp.model.bank.LoanOfUser;

import java.util.List;

/**
 * @author 毛家杰
 */
public interface LoanService {

	/**
	 * 处理长期贷款的申请
	 * @param userUnique 用户唯一码
	 * @param years 长期贷款的年数
	 * @param loan_money 长期贷款的金额
     */
	void addApplyLongLoan(String userUnique, Integer years,
								 Double loan_money);

	/**
	 * 处理短期贷款的申请
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param quarter
	 *            短期贷款的周期
	 * @param loan_money
	 *            短期贷款的金额
	 */
	void addApplyShortLoan(String userUnique, Integer quarter,
								  Double loan_money);

	/**
	 * 处理高利贷的申请
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param quarter
	 *            高利贷的周期
	 * @param loan_money
	 *            高利贷的金额
	 */
    void addApplyUsury(String userUnique, Integer quarter,
							  Double loan_money);

	/**
	 * 查询借贷信息,借助LoanOfUserDao实现
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param stringStatus
	 *            0:待还,1:已还
	 * @param loanTypeName
	 *            贷款类型
	 * @return 一个神奇的表
	 */
	List<LoanOfUser> findLoanOfUser(String userUnique, String stringStatus,
										   String loanTypeName);

	/**
	 * 查询一年内到期的贷款记录列表
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @return
	 */
	List<LoanOfUser> findLongLoanEndInOneYear(String userUnique);

	/**
	 * 获得长期/短期贷款金额上限
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param loanType
	 *            "长期贷款"/"短期贷款"
	 * @return 长期贷款上限
	 */
	double findMaxOfLoan(String userUnique, String loanType);

	/**
	 * 根据用户唯一码获得竞赛剩余的期数,服务于短期贷款和高利贷
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @return 竞赛剩余的期数
	 */
	int findRestOfPeriod(String userUnique);

	/**
	 * 查询竞赛剩余的年数,服务于长期贷款
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @return 竞赛剩余的年数
	 */
	 int findRestOfYear(String userUnique);

	/**
	 * 根据userUnique判断是否可以申请长期贷款
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @return boolean 行不行,你一个字
	 */
	 int isAllowedLongLoan(String userUnique);
	
	/**
	 * 根据userUnique判断是否可以申请短期贷款
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @return boolean 借不借,你一个字
	 */
	boolean isAllowedShortLoan(String userUnique);

	/**
	 * 归还贷款
	 * 
	 * @author 毛家杰
	 * @param userUnique
	 *            用户唯一码
	 * @param loanID
	 *            贷款码
	 */
	void updateReturnLoan(String userUnique, Integer loanID);

}