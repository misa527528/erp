package com.cqupt.mis.erp.controller.tax;

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
 * Created by 杨青 on 2016/9/11.
 */
@Controller("taxController")
@RequestMapping("/tax")
public class TaxController {
    @Resource
    private TaxSheetService taxSheetService;

    @RequestMapping("/getTax.do")
    public void getTax(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        TaxSheet taxSheet = taxSheetService.findTaxSheetByUserUnique(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, taxSheet);
        JSONUtils.toJSON(map, response);
    }
}
