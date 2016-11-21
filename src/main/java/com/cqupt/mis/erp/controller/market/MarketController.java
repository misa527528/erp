package com.cqupt.mis.erp.controller.market;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.market.DevelopedMarket;
import com.cqupt.mis.erp.model.market.DevelopingMarket;
import com.cqupt.mis.erp.model.market.UndevelopMarket;
import com.cqupt.mis.erp.service.market.MarketService;
import com.cqupt.mis.erp.service.prediction.PredictionOfGroupService;
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
 * Created by 杨青 on 2016/9/12.
 */
@Controller("marketController")
@RequestMapping("/market")
public class MarketController {
    @Resource
    private MarketService marketService;
    @Resource
    private PredictionOfGroupService predictionOfGroupService;

    // TODO: 2016/9/12 这个接口的返回值比较特殊，和前端交流一下
    @RequestMapping("/getAllMarkets.do")
    public void getAllMarkets(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<DevelopedMarket> developedMarkets = marketService.findDevelopedMarketsByUserUnique(userUnique);
        List<DevelopingMarket> developingMarkets = marketService.findDevelopingMarketsByUserUnique(userUnique);
        List<UndevelopMarket> undevelopMarkets = marketService.findUndevelopMarketsByUserUnique(userUnique);

        map = this.generateMap(developedMarkets, developingMarkets, undevelopMarkets);
        JSONUtils.toJSON(map, response);
    }
    private Map generateMap(List<DevelopedMarket> developedMarkets,
                            List<DevelopingMarket> developingMarkets,
                            List<UndevelopMarket> undevelopMarkets){
        Map<String, Object> map = new HashMap<>();

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("developedMarkets", developedMarkets);
        map.put("developingMarkets", developingMarkets);
        map.put("undevelopMarkets", undevelopMarkets);

        return map;
    }

    // 选择订单时使用
    @RequestMapping("/getDevelopedMarket.do")
    public void getDevelopedMarket(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        Map developedMarketNamesMap = new HashMap();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<DevelopedMarket> developedMarkets = marketService.findDevelopedMarketsByUserUnique(userUnique);
        /*DevelopedMarket developedMarket;
        for (int i=0; i<developedMarkets.size(); i++){
            developedMarket = developedMarkets.get(i);
            developedMarketNamesMap.put(developedMarket.getMarketName(), developedMarket.getMarketName());
        }*/

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, developedMarkets);
        JSONUtils.toJSON(map, response);
    }

    // 开发市场
    @RequestMapping("/startUndevelopMarketToDeveloping.do")
    public void startUndevelopMarketToDeveloping(String marketName,
                                                 HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = marketService.updateUndevelopMarketToDeveloping(userUnique, marketName);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
        }
        JSONUtils.toJSON(map, response);
    }

    // 继续开拓开发中的市场
    @RequestMapping("/startDevelopingMarket.do")
    public void startDevelopingMarket(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = marketService.updateStartDevelopingMarket(userUnique, marketName);
        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "继续开拓", null);
            System.out.println("result" + result);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法继续开拓", null);
            System.out.println("result" + result);
        }
        JSONUtils.toJSON(map, response);
    }

    // 暂停开拓 开发中的市场
    @RequestMapping("/stopDevelopingMarket.do")
    public void stopDevelopingMarket(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = marketService.updateStopDevelopingMarket(userUnique, marketName);
        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
            System.out.println("result" + result);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法暂停开拓", null);
            System.out.println("result" + result);
        }
        JSONUtils.toJSON(map, response);
    }

    // 开始维护市场
    @RequestMapping("/startDevelopedMarket.do")
    public void startDevelopedMarket(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = marketService.updateStartMaintainDevelopedMarket(userUnique, marketName);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "成功维护市场", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法维护市场", null);
        }
        JSONUtils.toJSON(map, response);
    }

    // 停止维护市场
    @RequestMapping("/stopDevelopedMarket.do")
    public void stopDevelopedMarket(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = marketService.updateStopMaintainDevelopedMarket(userUnique, marketName);

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "暂停维护市场", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法暂停市场", null);
        }
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/findPrediction.do")
    public void findPrediction(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", "操作成功");
        map = predictionOfGroupService.findPredictionOfGroupByMarketName(marketName, userUnique);
        JSONUtils.toJSON(map, response);
    }
}
