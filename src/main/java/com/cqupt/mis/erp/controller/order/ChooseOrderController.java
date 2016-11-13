package com.cqupt.mis.erp.controller.order;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.order.AllOrdersOfGroup;
import com.cqupt.mis.erp.model.order.ChooseOrder;
import com.cqupt.mis.erp.model.vo.ChooseOrderVO;
import com.cqupt.mis.erp.service.advertisement.AdAndOrderRefreshService;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.order.AllOrdersOfGroupService;
import com.cqupt.mis.erp.service.order.ChooseOrderService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/15.
 */
@Controller("chooseOrderController")
@RequestMapping("/chooseOrder")
public class ChooseOrderController {
    @Resource
    private AdvertisementService advertisementService;
    @Resource
    private ChooseOrderService chooseOrderService;
    @Resource
    private AllOrdersOfGroupService allOrdersOfGroupService;
    @Resource
    private AdAndOrderRefreshService adAndOrderRefreshService;

    // TODO: 2016/9/15 和前端沟通一下，如果marketName和productName是“全部”的话，用"all"代替;返回值也比较特殊
    @RequestMapping("/chooseOrderList.do")
    public void chooseOrderList(String marketName, String productName,
                                HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<ChooseOrderVO> chooseOrders =
                chooseOrderService.findChooseOrderByMarketNameAndProductName(userUnique, marketName, productName);

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("chooseOrders", chooseOrders);
        map.put("userUnique", userUnique);
        JSONUtils.toJSON(map, response);
    }

    /**
     * 正在选择的订单的操作
     * @param orderId
     * @param request
     * @param response
     * 根据返回值来判断处于什么状态：1-成功选择；2-还没轮到当前用户选择；3-iso没有研发成功；4-市场没有开发；5-这张订单已经被取走了
     */
    @RequestMapping("/choosingOrder.do")
    public Map choosingOrder(String orderId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        if (orderId == null || "".equals(orderId)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "请先选择订单", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        int result = chooseOrderService.updateChoosingOrder(userUnique, orderId);
        adAndOrderRefreshService.changeOrder(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, result);
        JSONUtils.toJSON(map, response);
        return map;
    }

    @RequestMapping("/endAllMarketAndProduct.do")
    public Map endAllMarketAndProduct(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        // TODO: 2016/9/15 这里应该有控制语句块
        chooseOrderService.updateEndChooseOrder(userUnique);
        adAndOrderRefreshService.changeOrder(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, null);
        JSONUtils.toJSON(map, response);
        return map;
    }

    @RequestMapping("/endChoosingMarketAndProductName.do")
    public Map<String, Object> endChoosingMarketAndProductName(String orderId, HttpServletRequest request,
                                                               HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        try {
            chooseOrderService.updateChooseOrderValue(orderId, 10000);
            boolean result = chooseOrderService.udpateIsClooseAll(userUnique);
            adAndOrderRefreshService.changeOrder(userUnique);
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, result);
        } catch (Exception e){
            e.printStackTrace();
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, null, false);
        }

        JSONUtils.toJSON(map, response);
        return map;
    }

    @RequestMapping("/getAllChooseOrderDetail.do")
    public Map getAllChooseOrderDetail(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<ChooseOrder> chooseOrders = chooseOrderService.findAllChooseOrderByUserUnique(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, chooseOrders);
        JSONUtils.toJSON(map, response);
        return map;
    }

    @RequestMapping("getProductNameByMarket.do")
    public Map<String, Object> getProductNameByMarket(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<String> productNames = advertisementService.findProductNameByMarket(userUnique, marketName);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, productNames);
        JSONUtils.toJSON(map, response);
        return map;
    }

    @RequestMapping("/putIntoMoneymarket.do")
    public Map<String, Object> putIntoMoneymarket(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<String> markets = advertisementService.findMoneyIntoMarket(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, markets);
        JSONUtils.toJSON(map, response);
        return map;
    }

    @RequestMapping("/showAllOrderOfUser.do")
    public Map showAllOrderOfUser(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return map;
        }

        List<AllOrdersOfGroup> allOrdersOfGroups = allOrdersOfGroupService.findOrderOfGroupByUserUnique(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, allOrdersOfGroups);
        JSONUtils.toJSON(map, response);
        return map;
    }

}
