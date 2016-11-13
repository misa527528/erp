package com.cqupt.mis.erp.controller.finance;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.finance.BalanceSheet;
import com.cqupt.mis.erp.service.finance.balancesheet.BalancesheetService;
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
@Controller("/balancesheetController")
@RequestMapping("/balancesheet")
public class BalancesheetController {
    @Resource
    private BalancesheetService balancesheetService;

    // TODO: 2016/9/15 接口的返回值比较特殊，和前端沟通一下
    // 查询资产负债表
    @RequestMapping("/findBalanceSheet.do")
    public Map findBalanceSheet(Integer year, Integer season, HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        List<BalanceSheet> balanceSheetList = balancesheetService.findBalanceSheet(userUnique, year, season);

        Map<String, Object> map = balancesheetService.findPageMsg(userUnique, year, season);
        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("balanceSheetList", balanceSheetList);
        JSONUtils.toJSON(map, response);
        return map;
    }
}
