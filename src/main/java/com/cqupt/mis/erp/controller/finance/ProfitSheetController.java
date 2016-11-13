package com.cqupt.mis.erp.controller.finance;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.finance.ProfitSheet;
import com.cqupt.mis.erp.service.finance.profitsheet.ProfitSheetService;
import com.cqupt.mis.erp.utils.JSONUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/16.
 */
@Controller("profitSheetController")
@RequestMapping("/profitSheet")
public class ProfitSheetController {
    @Resource
    private ProfitSheetService profitSheetService;

    // 查看利润表
    @RequestMapping("/findProfitSheet.do")
    public void findProfitSheet(int year, int season, HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        List<ProfitSheet> profitSheetList = profitSheetService.findProfitSheet(userUnique, year, season);
        Map<String, Object> map = profitSheetService.findPageMsg(userUnique, year, season);
        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("profitSheetList", profitSheetList);
        JSONUtils.toJSON(map, response);
    }
}
