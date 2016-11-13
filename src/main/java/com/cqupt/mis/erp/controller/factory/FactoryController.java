package com.cqupt.mis.erp.controller.factory;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.factory.FactoryBasicInfo;
import com.cqupt.mis.erp.model.factory.FactoryCommonInfo;
import com.cqupt.mis.erp.model.market.DevelopedMarket;
import com.cqupt.mis.erp.model.product.DevelopedProduct;
import com.cqupt.mis.erp.model.vo.FactoryNewVO;
import com.cqupt.mis.erp.model.vo.FactoryVO;
import com.cqupt.mis.erp.service.factory.FactoryMakingService;
import com.cqupt.mis.erp.service.factory.FactoryRentService;
import com.cqupt.mis.erp.service.factory.FactoryUsingService;
import com.cqupt.mis.erp.service.market.MarketService;
import com.cqupt.mis.erp.service.product.DevelopedProductService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
import com.cqupt.mis.erp.utils.JSONUtils;
import com.cqupt.mis.erp.utils.ReturnMapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 杨青 on 2016/9/7.
 */
@Controller("factoryController")
@RequestMapping("/factory")
public class FactoryController {
    @Resource
    private GameGroupMemberService gameGroupMemberService;
    @Resource
    private FactoryMakingService factoryMakingService;
    @Resource
    private FactoryRentService factoryRentService;
    @Resource
    private FactoryUsingService factoryUsingService;
    @Resource
    private DevelopedProductService developedProductService;
    @Resource
    private MarketService marketService;

    @RequestMapping("/addNewFactory.do")
    public void addNewFactory(String factoryType, String place,
                              HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            int currentPeriod = gameGroupMemberService.findCurrentPeriod(userUnique);

            FactoryCommonInfo factory = this.generateFactory(currentPeriod, factoryType, place, userUnique);

            boolean result = factoryMakingService.addMakingFactory(factory, userUnique);

            if (result){ // 这样userUnique不错，基本上是不会出错的了
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "新建厂房成功", null);
                JSONUtils.toJSON(map, response);
                return;
            }
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "新建厂房失败", null);
        JSONUtils.toJSON(map, response);
        return;
    }
    private FactoryCommonInfo generateFactory(int currentPeriod, String factoryType, String place, String userUnique){
        FactoryCommonInfo factory = new FactoryCommonInfo();

        factory.setBeginTime(currentPeriod);
        factory.setFactoryType(factoryType);
        factory.setPlace(place);
        factory.setUserUnique(userUnique);

        return factory;
    }

    // TODO: 2016/9/7 考虑要不要让前端传一个rentCost过来就好
    @RequestMapping("/addNewRentFactory.do")
    public void addNewRentFactory(String factoryType, String place, int needPeriod,
                                  HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            int currentPeriod = gameGroupMemberService.findCurrentPeriod(userUnique);
            FactoryBasicInfo factoryBasicInfo = factoryRentService.findFactoryBasics(factoryType).get(0);
            int rentCost = factoryBasicInfo.getRentcost();

            FactoryCommonInfo factory = this.generateRentFactory(userUnique, currentPeriod, factoryType,
                    place, needPeriod, rentCost);

            boolean result = factoryRentService.addRentFactory(factory);

            if (result){
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "租借厂房成功", null);
                JSONUtils.toJSON(map, response);
                return;
            }
        }
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "租借厂房失败", null);
        JSONUtils.toJSON(map, response);
    }
    private FactoryCommonInfo generateRentFactory(String userUnique, int currentPeriod, String factoryType,
                                                  String place, int needPeriod, int rentCost){
        FactoryCommonInfo factory = new FactoryCommonInfo();

        factory.setUserUnique(userUnique);
        factory.setBeginTime(currentPeriod);
        factory.setFactoryType(factoryType);
        factory.setPlace(place);
        factory.setNeedPeriod(needPeriod);
        factory.setRentCost(rentCost);

        return factory;
    }

    @RequestMapping("/getAllFactoryAndProductLine.do") // TODO: 2016/9/8 这个接口的返回值比较特殊
    public void getAllFactoryAndProductLine(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            List<FactoryVO> factoryMade = factoryUsingService.findFactoryUsing(userUnique);
            List<FactoryVO> factoryRent = factoryRentService.findFactoryRentVO(userUnique);
            List<FactoryCommonInfo> factoryMaking = factoryMakingService.findFactoryMakings(userUnique);

            map.put("factoryMade", factoryMade);
            map.put("factoryRent", factoryRent);
            map.put("factoryMaking", factoryMaking);

            map.put("status", ReturnStatus.SUCCESS);
            map.put("message", "获取成功");
            JSONUtils.toJSON(map, response);
            return;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "获取失败", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getDevelopedProduct.do")
    public void getDevelopedProduct(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            List<DevelopedProduct> developedProducts =
                    developedProductService.findDevelopedProductsByUserUnique(userUnique);

            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "获取成功", developedProducts);
            JSONUtils.toJSON(map, response);
            return;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "获取失败", null);
        JSONUtils.toJSON(map, response);
    }

    // TODO: 2016/9/9 这个接口的返回值也比较特殊，和前端沟通一下
    @RequestMapping("/getFactoryByWorkshopStatusAndMarketArea.do")
    public void getFactoryByWorkshopStatusAndMarketArea(String worshopStatus, String marketArea,
            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        List<FactoryVO> factoryMade = new ArrayList<>();
        List<FactoryVO> factoryRent = new ArrayList<>();
        List<FactoryCommonInfo> factoryMaking = new ArrayList<>();

        if ("全部".equals(marketArea)){
            marketArea = "%";
        }

        if (worshopStatus != null && ! "".equals(worshopStatus)){
            if ("allStatus".equals(worshopStatus)){
                factoryMade = factoryUsingService.findFactoryUsing(userUnique, marketArea);
                factoryRent = factoryRentService.findFactoryRentVO(userUnique, marketArea);
                factoryMaking = factoryMakingService.findFactoryMakings(userUnique, marketArea);
            } else if ("building".equals(worshopStatus)){
                factoryMaking = factoryMakingService.findFactoryMakings(userUnique, marketArea);
            } else if ("rent".equals(worshopStatus)){
                factoryRent = factoryRentService.findFactoryRentVO(userUnique, marketArea);
            } else if ("built".equals(worshopStatus)){
                factoryMade = factoryUsingService.findFactoryUsing(userUnique, marketArea);
            } else {
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "worshopStatus错误", null);
                JSONUtils.toJSON(map, response);
                return;
            }

            map.put("status", ReturnStatus.SUCCESS);
            map.put("factoryMade", factoryMade);
            map.put("factoryRent", factoryRent);
            map.put("factoryMaking", factoryMaking);
            JSONUtils.toJSON(map, response);
            return;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "查询失败", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getNewFactoryDetail.do")
    public void getNewFactoryDetail(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        FactoryNewVO factory = new FactoryNewVO();

        List<DevelopedMarket> place = marketService.findDevelopedMarketsByUserUnique(userUnique);
        List<String> allFactoryType = factoryMakingService.findAllFactoryType();
        int currentPeriod = gameGroupMemberService.findCurrentPeriod(userUnique);

        factory.setPlace(place);
        factory.setFactoryType(allFactoryType);
        factory.setCurrentPeriod(currentPeriod);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, factory);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getNewRentFactory.do")
    public void getNewRentFactory(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        List<FactoryBasicInfo> allFactoryRentBasics = factoryRentService.findFactoryBasics("");
        List<DevelopedMarket> developedMarkets = marketService.findDevelopedMarketsByUserUnique(userUnique);

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("factoryRent", allFactoryRentBasics);
        map.put("developedMarket", developedMarkets);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/sellUsingFactory.do")
    public void sellUsingFactory(String factoryId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            factoryUsingService.deleteSaleUsingFactory(userUnique, factoryId);
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "出售成功", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/startFactoryMaking.do")
    public void startFactoryMaking(int factoryId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            boolean result = factoryMakingService.updateStatusToOne(userUnique, factoryId);

            if (result){
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "暂停建造成功", null);
                JSONUtils.toJSON(map, response);
                return;
            }
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "暂停建造失败", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/stopFactoryMaking.do")
    public void stopFactoryMaking(int factoryId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && ! "".equals(userUnique)){
            boolean result = factoryMakingService.updateStatusToZero(userUnique, factoryId);

            if (result){
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "暂停建造工厂", null);
                JSONUtils.toJSON(map, response);
                return;
            }
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "暂停失败", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/stopFactoryRent.do")
    public void stopFactoryRent(String factoryId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        int result = 0;

        if (userUnique != null && ! "".equals(userUnique)){
            result = factoryRentService.deleteRentedFactory(userUnique, factoryId);
        }

        if (result == 1){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "停止租用厂房", null);
        } else if (result == 2){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "厂房内有生产线，不能停止租用", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法停止租借", null);
        }

        JSONUtils.toJSON(map, response);
    }
}
