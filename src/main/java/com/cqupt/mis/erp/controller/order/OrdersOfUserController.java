package com.cqupt.mis.erp.controller.order;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.order.OrdersOfUser;
import com.cqupt.mis.erp.service.order.OrdersOfUserService;
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
 * Created by 杨青 on 2016/9/15.
 */
@Controller("ordersOfUserController")
@RequestMapping("/OrdersOfUser")
public class OrdersOfUserController {
    @Resource
    private OrdersOfUserService ordersOfUserService;

    // 交货订单
    @RequestMapping("/findDeliveredOrdersOfUser.do")
    public Map<String, Object> findDeliveredOrdersOfUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<OrdersOfUser> ordersOfUserList = ordersOfUserService.findDeliveredOrdersOfUser(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, ordersOfUserList);
        JSONUtils.toJSON(map, response);
        return map;
    }

    // 未交货订单
    @RequestMapping("/findNotDeliverOrdersOfUser.do")
    public Map<String, Object> findNotDeliverOrdersOfUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<OrdersOfUser> ordersOfUserList = ordersOfUserService.findNotDeliverOrdersOfUser(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, ordersOfUserList);
        JSONUtils.toJSON(map, response);
        return map;
    }

    // 所有交货订单
    @RequestMapping("/findOrdersOfUserByUserUnique.do")
    public Map<String, Object> findOrdersOfUserByUserUnique(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<OrdersOfUser> ordersOfUserList = ordersOfUserService.findOrdersOfUserByUserUnique(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, ordersOfUserList);
        JSONUtils.toJSON(map, response);
        return map;
    }
}
