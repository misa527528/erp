package com.cqupt.mis.erp.controller.finance;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.finance.WillReceive;
import com.cqupt.mis.erp.service.finance.fund.WillReceiveService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
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
@Controller("willReceiveController")
@RequestMapping("/willReceive")
public class WillReceiveController {
    @Resource
    private WillReceiveService willReceiveService;

    // TODO: 2016/9/16 接口的请求参数列表改变了，和前端沟通一下
    // 贴现
    @RequestMapping("/discount.do")
    public void discount(int willReceiveId, HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        willReceiveService.discount(userUnique, willReceiveId);

        Map<String, Object> map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "贴现成功", null);
        JSONUtils.toJSON(map, response);
    }

    // 查询应收账款
    @RequestMapping("/findWillReceive.do")
    public void findWillReceive(HttpServletRequest request, HttpServletResponse response){
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        List<WillReceive> willReceiveList = willReceiveService.findWillReceive(userUnique);

        Map map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "查询成功", willReceiveList);
        JSONUtils.toJSON(map, response);
    }
}
