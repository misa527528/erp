package com.cqupt.mis.erp.controller.advertisement;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.advertisement.Advertisement;
import com.cqupt.mis.erp.model.advertisement.AdvertisementStatusOfUser;
import com.cqupt.mis.erp.model.vo.AdvertisementUserStatusVO;
import com.cqupt.mis.erp.service.advertisement.AdvertisementService;
import com.cqupt.mis.erp.service.advertisement.AdvertisementStatusOfUserService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
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
 * Created by 杨青 on 2016/9/14.
 */
@Controller("advertisementController")
@RequestMapping("/advertisement")
public class AdvertisementController {
    @Resource
    private AdvertisementService advertisementService;
    @Resource
    private AdvertisementStatusOfUserService advertisementStatusOfUserService;
    @Resource
    private GameGroupMemberService gameGroupMemberService;

    @RequestMapping("/allUserAdvertisementStatus.do")
    public void allUserAdvertisementStatus(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<AdvertisementUserStatusVO> advertisementUserStatusList =
                advertisementStatusOfUserService.findAllStatusVOByUserUnique(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, advertisementUserStatusList);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/finishAdvertisement.do")
    public void finishAdvertisement(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        int result = advertisementService.updateAdvertisementFinish(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, request);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getAdByMarket.do")
    public void getAdByMarket(String marketName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        if (marketName == null || "".equals(userUnique)){
            marketName = "本地市场";
        }
        List<Advertisement> advertisements = advertisementService.findAdvertisementByMarketName(marketName, userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, advertisements);
        JSONUtils.toJSON(map, response);
    }

    // 确认投放广告前再次确认
    @RequestMapping("/getAlreadAd.do")
    public void getAlreadAd(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<Advertisement> advertisements = advertisementService.findAlreadyAdvertisement(userUnique);
        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, advertisements);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/putIntoAdvertisement.do")
    public void putIntoAdvertisement(int advertisementId, double money,
                                     HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = advertisementService.updateAdvertisementForProduct(advertisementId, money);
        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    /**
     * 根据status值来控制跳转用户进入的页面还有进度条的展示.
     * @param request
     * @param response
     * 返回1:就是还没有投放广告成功
     * 返回3:就是开始等待所有用户投放广告
     * 返回4:进入选单的页面
     * 返回5:选择订单成功,或者是不在年初,不能选择订单
     */
    @RequestMapping("/userStatusOfAdvertisement.do")
    public void userStatusOfAdvertisement(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        int status = 5;
        int period = gameGroupMemberService.findCurrentPeriod(userUnique);
        AdvertisementStatusOfUser advertisementStatusOfUser =
                advertisementStatusOfUserService.findAdvertisementStatusOfUserByUserUnique(userUnique, period);

        if (advertisementStatusOfUser == null){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "当前不是年初", status);
            JSONUtils.toJSON(map, response);
            return;
        }

        if (advertisementStatusOfUser.getFinishOrderFlag() == 1){ // 已经结束了选择订单了. 直接返回状态5
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "选择订单成功", status);
            JSONUtils.toJSON(map, response);
            return;
        }

        if (advertisementStatusOfUser.getFinishAdvertiseFlag() == 1){ // 已经结束了投放广告了
            if (advertisementStatusOfUser.getChooseOrderFlag() == 1){ // 已经可以开始选单了 就进入选订单的页面4
                status = 4;
            } else { // 还没有开始选单,就是进入等待界面3
                status = 3;
            }
        } else { // 还没有结束投放广告 应该是状态1
            status = 1;
        }

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, status);
        JSONUtils.toJSON(map, response);
    }

}

