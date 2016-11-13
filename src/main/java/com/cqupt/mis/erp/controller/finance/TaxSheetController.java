package com.cqupt.mis.erp.controller.finance;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.finance.TaxSheet;
import com.cqupt.mis.erp.service.finance.fund.TaxSheetService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/16.
 */
@Controller("taxSheetController")
@RequestMapping("/taxSheet")
public class TaxSheetController {
    @Resource
    private TaxSheetService taxSheetService;

    // 税金管理
    @RequestMapping("/findTaxSheetByUserUnique.do")
    public void findTaxSheetByUserUnique(HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        TaxSheet taxSheet = taxSheetService.findTaxSheetByUserUnique(userUnique);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, taxSheet);
        JSONUtils.toJSON(map, response);
    }
}
