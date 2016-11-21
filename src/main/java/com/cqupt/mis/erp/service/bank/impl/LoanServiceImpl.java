package com.cqupt.mis.erp.service.bank.impl;

import com.cqupt.mis.erp.manager.bank.LoanBasicDao;
import com.cqupt.mis.erp.manager.bank.LoanOfUserDao;
import com.cqupt.mis.erp.manager.finance.WillReceiveDao;
import com.cqupt.mis.erp.manager.finance.WillReceiveToCashBasicDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupDao;
import com.cqupt.mis.erp.manager.registerlogin.GameGroupMemberDao;
import com.cqupt.mis.erp.model.bank.LoanOfUser;
import com.cqupt.mis.erp.service.bank.LoanService;
import com.cqupt.mis.erp.service.finance.account.AccountDetailService;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.service.finance.balancesheet.BalancesheetService;
import com.cqupt.mis.erp.service.finance.fund.CashSheetService;
import com.cqupt.mis.erp.utils.ERPUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("loanService")
public class LoanServiceImpl implements LoanService {
	@Resource
	private LoanOfUserDao loanOfUserDao;
	@Resource
	private LoanBasicDao loanBasicDao;
	@Resource
	private WillReceiveDao willReceiveDao;
	@Resource
	private WillReceiveToCashBasicDao willReceiveToCashBasicDao;
	@Resource
	private BalancesheetService balancesheetServie;
	@Resource
	private AccountDetailService accountDetailService;
	@Resource
	private AccountHeadService accountHeadService;
	@Resource
	private CashSheetService cashSheetService;
	@Resource
	private GameGroupMemberDao gameGroupMemberDao;
	@Resource
    private GameGroupDao gameGroupDao;

	@Override
	public void addApplyLongLoan(String userUnique, Integer loan_years,
			Double loan_money) {
		Integer currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		Integer periodsOfOneYear = gameGroupDao.findPeriodOfYear(userUnique);
		Integer endTime = currentPeriod + periodsOfOneYear * loan_years;
		// 在LOANOFUSER表中添加新的记录
		loanOfUserDao.insertLoanOfUser(userUnique, "长期贷款", currentPeriod,
				endTime, 0, loan_money);
		//执行会计处理
		accountHeadService
				.addAccountHead(userUnique, currentPeriod, "申请长期贷款生效");
		accountDetailService.addAccountDetail(userUnique, "现金", "长期贷款", "借",
				"贷", loan_money, loan_money, loan_money);
		cashSheetService.updateAddCash(userUnique, loan_money);
	}

	@Override
	public void addApplyShortLoan(String userUnique, Integer quarter,
			Double loan_money) {
		Integer currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		Integer endTime = currentPeriod + quarter;
		// 在LOANOFUSER表中添加新的记录
		loanOfUserDao.insertLoanOfUser(userUnique, "短期贷款", currentPeriod,
				endTime, 0, loan_money);
		//执行会计处理
		accountHeadService
				.addAccountHead(userUnique, currentPeriod, "申请短期贷款生效");
		accountDetailService.addAccountDetail(userUnique, "现金", "短期贷款", "借",
				"贷", loan_money, loan_money, loan_money);
		cashSheetService.updateAddCash(userUnique, loan_money);

	}

	@Override
	public void addApplyUsury(String userUnique, Integer quarter,
			Double loan_money) {
		Integer currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		Integer endTime = currentPeriod + quarter;
		// 在LOANOFUSER表中添加新的记录
		loanOfUserDao.insertLoanOfUser(userUnique, "高利贷", currentPeriod,
				endTime, 0, loan_money);

		//执行会计处理
		accountHeadService.addAccountHead(userUnique, currentPeriod, "申请高利贷生效");
		// FIXME 这里有错
		accountDetailService.addAccountDetail(userUnique, "现金", "高利贷", "借", "贷",
                loan_money, loan_money, loan_money);
        System.out.println("错啦");
		cashSheetService.updateAddCash(userUnique, loan_money);
	}

	private Double findBasicRate(String userUnique, String loanTypeName) {
		Double basicRate = loanBasicDao.findLoanBasicRate(loanTypeName);

		// 贷款每期利率为basicRate/periodsOfOneYear
		Integer periodsOfOneYear = gameGroupDao.findPeriodOfYear(userUnique);
		basicRate = basicRate / periodsOfOneYear;
		
		return ERPUtils.round(basicRate);//lx：处理一下double变量，四舍五入
	}

	@Override
	public List<LoanOfUser> findLoanOfUser(String userUnique,
			String stringStatus, String loanTypeName) {
		int status = 0;
		if ("全部".equals(stringStatus)) {
			if ("全部".equals(loanTypeName)) {
				return loanOfUserDao.findLoanOfUserByUserUnique(userUnique);
			} else {
				return loanOfUserDao.findLoanOfUserByUserUniqueAndLoanTypeName(userUnique, loanTypeName);
			}
		} else if ("已还贷款".equals(stringStatus)) {
			status = 1;
		} else {
			status = 0;
		}
		if ("全部".equals(loanTypeName)) {
			return loanOfUserDao.findLoanOfUserByUserUniqueAndStatus(userUnique, status);
		}
		return loanOfUserDao.findLoanOfUserByUserUniqueAndStatusAndLoanTypeName(userUnique, status, loanTypeName);
	}

	@Override
	public List<LoanOfUser> findLongLoanEndInOneYear(String userUnique) {
		// endTime = 当前的期数 + 一年包含的期数
		Integer endTime = gameGroupMemberDao.findCurrentTime(userUnique)
				+ gameGroupDao.findPeriodOfYear(userUnique);

		return loanOfUserDao.findLoanOfUserWithEndTime(userUnique, endTime);
	}

	@Override
	public double findMaxOfLoan(String userUnique, String loanType) {
		Double s = 0.0;
		Double a = 0.0;
		// 根据userUnique得到当前周期获得期初资产
		List<Double> list_s = balancesheetServie.findBeginValue(
                userUnique,gameGroupMemberDao.findCurrentTime(userUnique));
		for (Double t : list_s) {
			s += t;
		}
		// 获得已经在借的贷款
		int status = 0;
		List<LoanOfUser> list_a = loanOfUserDao.findLoanOfUserByUserUniqueAndStatusAndLoanTypeName(
                userUnique, status, loanType);
		for (LoanOfUser t : list_a) {
			a += t.getMoney();
		}
		return ERPUtils.round( 2 * s - a);//lx：处理一下double变量，四舍五入
	}

	@Override
	public int findRestOfPeriod(String userUnique) {
		int currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		int peroidOfYear = gameGroupDao.findPeriodOfYear(userUnique);
		int totalYear = gameGroupDao.findTotalYear(userUnique);
		if (currentPeriod > (totalYear - 1) * peroidOfYear) {
			return totalYear * peroidOfYear - currentPeriod;
		} else {
			return peroidOfYear;
		}
	}

	@Override
	public int findRestOfYear(String userUnique) {
		int currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		int peroidOfYear = gameGroupDao.findPeriodOfYear(userUnique);
		int totalYear = gameGroupDao.findTotalYear(userUnique);
		return totalYear - currentPeriod / peroidOfYear - 1;
	}

	@Override
	public int isAllowedLongLoan(String userUnique) {
		int currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		int periodOfYear = gameGroupDao.findPeriodOfYear(userUnique);
		Double maxOfLongLoan = findMaxOfLoan(userUnique, "长期贷款");

		// 1.判断用户到游戏结束所剩年数是否大于等于2
		if (findRestOfYear(userUnique) >= 2) {
			// 2.判断是否处于年初
			if (currentPeriod % periodOfYear == 1) {
				// 3.判断是否具有存在符合条件的长期贷款金额
				if (maxOfLongLoan > 0) {
					return 0;
				} else {
					return 3;
				}
			} else {
				return 2;
			}
		} else {
			return 1;
		}
	}

	@Override
	public boolean isAllowedShortLoan(String userUnique) {
		Double maxOfLongLoan = findMaxOfLoan(userUnique, "短期贷款");
		if (maxOfLongLoan > 0) {
			return true;
		} else {
			return false;
		}
	}

	private void updateLoanOfUser(String userUnique, Integer loanID) {
		int status = 1;
		loanOfUserDao.updateLoanOfUser(userUnique, loanID, status);
	}

	@Override
	public void updateReturnLoan(String userUnique, Integer loanID) {
		LoanOfUser loanOfUser = new LoanOfUser();

		// 根据userUnique 和 loanID 取得loanTypeName beginTime money
		loanOfUser = loanOfUserDao.findALoanOfUserByUserUniqueAndLoanID(userUnique, loanID);
		Integer currentPeriod = gameGroupMemberDao.findCurrentTime(userUnique);
		Integer periodsOfOneYear = gameGroupDao.findPeriodOfYear(userUnique);
		String loanTypeName = loanOfUser.getLoanTypeName();
		Integer beginTime = loanOfUser.getBeginTime();
		Double money = loanOfUser.getMoney();

		// 会计操作,小心还债还到破产~
		// 根据loanTypeName的值，如果为"长期贷款"则判断是否为一年内到期的长期贷款
		if ("长期贷款".endsWith(loanTypeName)
				&& loanOfUser.getEndTime() <= currentPeriod + periodsOfOneYear
						+ 1) {
			// 为一年内到期的长期贷款
			accountHeadService.addAccountHead(userUnique, currentPeriod,
					"归还一年内到期的长期贷款" + loanID);
			accountDetailService.addAccountDetail(userUnique, "一年内到期的长期贷款",
					"现金", "借", "贷", money, -money, -money);
			cashSheetService.updateSubtractCash(userUnique, money);

		} else {
			accountHeadService.addAccountHead(userUnique, currentPeriod, "归还"
					+ loanTypeName);
			accountDetailService.addAccountDetail(userUnique, loanTypeName,
					"现金", "借", "贷", money, -money, -money);
			cashSheetService.updateSubtractCash(userUnique, money);
		}
		// 判断loanTypeName的值，如果为"短期贷款"则计算利息
		if ("短期贷款".endsWith(loanTypeName)) {
			Double basicRate = this.findBasicRate(userUnique, "短期贷款");
			//lx：处理一下double变量，四舍五入
			Double interest = ERPUtils.round((currentPeriod - beginTime) * basicRate * money);
			
			if (interest > 0) {
				accountHeadService.addAccountHead(userUnique, currentPeriod,
						"归还短期贷款利息" + loanID);
				accountDetailService.addAccountDetail(userUnique, "财务费用", "现金",
						"借", "贷", interest, interest, -interest);
				cashSheetService.updateSubtractCash(userUnique, money);
			}
		}
		// 修改status的值为1
		updateLoanOfUser(userUnique, loanID);
	}
}
