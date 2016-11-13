package com.cqupt.mis.erp.controller.order;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.service.order.OrderDeliverService;
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
@Controller("deliverOrderController")
@RequestMapping("/deliverOrder")
public class DeliverOrderController {
    @Resource
    private OrderDeliverService orderDeliverService;

    // TODO: 2016/9/15 这个方法可以重构
    @RequestMapping("/deliverCheck.do")
    public Map deliverCheck(String orderId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        int statusCode = 2;

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        if (orderId == null || "".equals(orderId)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "订单号为空",null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        try {
            statusCode = orderDeliverService.updateDeliverCheck(userUnique, orderId);
        } catch (Exception e){
            e.printStackTrace();
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "交货失败，订单不存在或后台原因！", statusCode);
            JSONUtils.toJSON(map, response);
            return map;
        }

        if (statusCode == 1){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "交货成功", statusCode);
        } else if (statusCode == 2){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "交货失败，请先处理延误时间最长的订单", statusCode);
        } else if (statusCode == 3){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "交货失败，没有足够的交货产品", statusCode);
        } else if (statusCode == 0){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "会计分录填写失败，交货失败", statusCode);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "交货失败", statusCode);
        }

        JSONUtils.toJSON(map, response);
        return map;
    }
}
