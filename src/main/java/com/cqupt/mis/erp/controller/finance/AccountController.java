package com.cqupt.mis.erp.controller.finance;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.finance.AccountHead;
import com.cqupt.mis.erp.service.finance.account.AccountHeadService;
import com.cqupt.mis.erp.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/15.
 */
@Controller("accountController")
@RequestMapping("/account")
public class AccountController {
    @Resource
    private AccountHeadService accountHeadService;

    // TODO: 2016/9/15 接口的返回值比较特殊，和前端沟通一下
    // 查询会计分录表
    @RequestMapping("/findAccount.do")
    public void findAccount(Integer minYear, Integer minPeriod, Integer maxYear, Integer maxPeriod, Integer pageIndex,
                            HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        List<AccountHead> accountList =
                accountHeadService.findAccount(userUnique, minYear, minPeriod, maxYear, maxPeriod, pageIndex);
        Map<String, Object> map =
                accountHeadService.findPageMsg(userUnique, minYear, minPeriod, maxYear, maxPeriod, pageIndex);

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("accountList", accountList);
        JSONUtils.toJSON(map, response);
    }
}
