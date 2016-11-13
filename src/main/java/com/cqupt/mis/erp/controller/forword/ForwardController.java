package com.cqupt.mis.erp.controller.forword;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.service.forwardquarter.ForwardQuarterService;
import com.cqupt.mis.erp.service.registerlogin.ApprovedUserService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/4.
 */
@Controller("forwardController")
@RequestMapping("/forward")
public class ForwardController {
    @Resource
    private ForwardQuarterService forwardQuarterService;
    @Resource
    private GameGroupService gameGroupService;
    @Resource
    private GameGroupMemberService gameGroupMemberService;
    @Resource
    private ApprovedUserService approvedUserService;

    // 进入下一个周期
    @RequestMapping("/forward.do")
    public void forward(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        Integer status = forwardQuarterService.ForwardStatus(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, status);
        JSONUtils.toJSON(map, response);
    }

    // 查询当前的年数还有期数
    @RequestMapping("/yearAndPeriod.do")
    public void yearAndPeriod(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        String userId = (String) request.getSession().getAttribute("userId");
        String userName = approvedUserService.findUsernameByUserUnique(userUnique);
        int totalPeriod = gameGroupMemberService.findCurrentPeriod(userUnique);
        int periodOfYear = gameGroupService.findPeriodOfYear(userUnique);

        int quarter = totalPeriod % periodOfYear;
        int year;

        if (quarter == 0){
            year = totalPeriod / periodOfYear;
            quarter = periodOfYear;
        } else {
            year = totalPeriod / periodOfYear + 1;
            quarter = totalPeriod % periodOfYear;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("year", year);
        data.put("period", quarter);
        data.put("userName", userName);
        data.put("userId", userId);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, data);
        JSONUtils.toJSON(map, response);
    }
}
