package com.cqupt.mis.erp.controller.bank;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.bank.LoanOfUser;
import com.cqupt.mis.erp.service.bank.impl.LoanServiceImpl;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/5.
 */
@Controller("loanController")
@RequestMapping("/loan")
public class LoanController {
    @Resource
    private LoanServiceImpl loanService;
    @Resource
    private GameGroupService gameGroupService;

    // TODO: 2016/9/5 这个方法的返回值比较特殊，和前端沟通一下,能不能把接口名称改为loanPermission
    @RequestMapping("/isAllowLoan.do")  
    public void isAllowLoan(String loanType, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if ("长期贷款".equals(loanType)){
            map = this.findLongLoanPermission(userUnique);
        } else if ("短期贷款".equals(loanType)){
            map = this.findShortLoanPermission(userUnique);
        } else {
            map = this.findUsuryPermission(userUnique);
        }

        JSONUtils.toJSON(map, response);
    }
    private Map findShortLoanPermission(String userUnique){
        String loanType = "短期贷款";
        String rate = "5.0%";
        boolean isAllowed = false;
        // 警告,在前台会以红色的形式显示
        String warning;

        // 金额上限
        Double maxMoney = loanService.findMaxOfLoan(userUnique, loanType);
        // 时间上限
        int maxPeriod = loanService.findRestOfPeriod(userUnique);
        boolean isAllowedShortLoan = loanService.isAllowedShortLoan(userUnique);

        if (isAllowedShortLoan){
            isAllowed = true;
            warning = "贷款金额必须在0.0万 - "+maxMoney+"万之间！ \n"+"贷款期限必须在"+maxPeriod+"期之内！";
        } else {
            warning = "不存在符合条件的短期贷款金额不能申请长期贷款！";
        }

        Map<String, Object> data = jsonHandeler(loanType, rate, maxMoney, isAllowed, null, maxPeriod, warning);
        return data;
    }
    private Map findLongLoanPermission(String userUnique){
        String loanType = "长期贷款";
        String rate = "10.0%";
        boolean isAllowed = false;
        // 警告，在前台会以红色的形式显示
        String warning;

        int loanCode = loanService.isAllowedLongLoan(userUnique);
        // 金额上限
        Double maxMoney = loanService.findMaxOfLoan(userUnique, loanType);
        // 时间上限
        int maxYear = loanService.findRestOfYear(userUnique);

        if (loanCode == 0){
            isAllowed = true;
            warning = "贷款金额必须在0.0万 - " + maxMoney + "万之间！\n" + "贷款期限必须为" + maxYear + "年之内！";
        } else if (loanCode == 1){
            warning = "到游戏结束所剩年数小于2不能申请长期贷款！";
        } else if (loanCode == 2){
            warning = "当期不是处于年初不能申请长期贷款！";
        } else {
            warning = "不存在符合条件的长期贷款金额不能申请长期贷款！";
        }

        Map data = jsonHandeler(loanType, rate, maxMoney, isAllowed, maxYear, null, warning);

        return data;
    }
    private Map findUsuryPermission(String userUnique){
        String loanType = "高利贷";
        String rate = "20%";
        boolean isAllowed = true;

        int maxPeriod = loanService.findRestOfPeriod(userUnique);
        // 警告,在前台会以红色的形式显示
        String warning = "贷款金额必须大于0.0万！" + "贷款期限必须在" + maxPeriod + "期之内！";

        Map<String, Object> data = jsonHandeler(loanType, rate, null, isAllowed, null, maxPeriod, warning);
        return data;
    }

    @RequestMapping("/applyLoan.do")
    public void applyLoan(String loanType, Integer loanTime, Double loanMoney,
                          HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;

        String userUnique = (String) request.getSession().getAttribute("userUnique");
        boolean isAllowed = this.findLoanIsAllowed(loanType, userUnique);

        if ("长期贷款".equals(loanType) && isAllowed){
            loanService.addApplyLongLoan(userUnique, loanTime, loanMoney);
        } else if ("短期贷款".equals(loanType) && isAllowed){
            loanService.addApplyShortLoan(userUnique, loanTime, loanMoney);
        } else if ("高利贷".equals(loanType) && isAllowed){
            loanService.addApplyUsury(userUnique, loanTime, loanMoney);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "当前不允许贷款", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "贷款成功", null);
        JSONUtils.toJSON(map, response);
    }
    private boolean findLoanIsAllowed(String loanType, String userUnique){
        boolean isAllowed;

        if ("长期贷款".equals(loanType)){
            isAllowed = (loanService.isAllowedLongLoan(userUnique)== 0);
        } else if ("短期贷款".equals(loanType)){
            isAllowed = loanService.isAllowedShortLoan(userUnique);
        } else {
            isAllowed = true;
        }

        return isAllowed;
    }

    // 工具方法,减少冗余
    private Map jsonHandeler(String loanType, String rate, Double maxMoney, boolean isAllowed, Integer maxYear,
                             Integer maxPeriod, String warning) {
        Map<String, Object> map = new HashMap<>();

        map.put("loanType", loanType);// 返回loanType
        map.put("rate", rate);// 返回年利率
        map.put("maxMoney", maxMoney);// 返回金额上限
        map.put("isAllowed", isAllowed);// 返回boolean isAllow
        map.put("maxYear", maxYear);// 返回时间上限
        map.put("maxPeriod", maxPeriod);// 返回时间上限
        map.put("warning", warning);// 返回警告

        return map;
    }

    // TODO: 2016/9/17 这个方法的返回值比较特殊，和前端沟通一下,接口名称改为loanPermission
    // 贷款管理
    @RequestMapping("/findLoan.do")
    public void findLoan(String loanStatus, String loanType,
                                        HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        List<LoanOfUser> loanList = loanService.findLoanOfUser(userUnique, loanStatus, loanType);

        Map<String, Object> map =  new HashMap<>();
        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("loanList", loanList);
        map.put("userUnique", userUnique);
        map.put("status", loanStatus);
        map.put("loanTypeName", loanType);

        JSONUtils.toJSON(map,response);
    }

    // 归还贷款
    @RequestMapping("/returnLoan.do")
    public void returnLoan(int loanId, HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        loanService.updateReturnLoan(userUnique, loanId);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "归还贷款", null);
        JSONUtils.toJSON(map, response);
    }
}
