package com.cqupt.mis.erp.controller.finance;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.service.finance.fund.CashSheetService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/15.
 */
@Controller("cashController")
@RequestMapping("/cash")
public class CashController {
    @Resource
    private CashSheetService cashSheetService;

    // 盘点现金
    @RequestMapping("/findCash.do")
    public void findCash(HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        Double cash = cashSheetService.findCash(userUnique);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, cash);
        JSONUtils.toJSON(map, response);
    }
}
