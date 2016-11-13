package com.cqupt.mis.erp.controller.materialpurchase;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.materialpurchase.MaterialBasic;
import com.cqupt.mis.erp.model.materialpurchase.MaterialInventory;
import com.cqupt.mis.erp.model.materialpurchase.MaterialPurchaseRecord;
import com.cqupt.mis.erp.service.materialpurchase.MaterialBasicService;
import com.cqupt.mis.erp.service.materialpurchase.MaterialInventoryService;
import com.cqupt.mis.erp.service.materialpurchase.MaterialPurchaseService;
import com.cqupt.mis.erp.service.registerlogin.GameGroupMemberService;
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
 * Created by 杨青 on 2016/9/13.
 */
@Controller("materialpurchaseController")
@RequestMapping("/materialpurchase")
public class MaterialpurchaseController {
    @Resource
    private MaterialBasicService materialBasicService;
    @Resource
    private MaterialPurchaseService materialPurchaseService;
    @Resource
    private MaterialInventoryService materialInventoryService;
    @Resource
    private GameGroupMemberService gameGroupMemberService;

    @RequestMapping("/showMaterialBasic.do")
    public void showMaterialBasic(HttpServletResponse response){
        Map<String, Object> map;
        List<MaterialBasic> materialBasics = materialBasicService.findAllMaterialBasic();

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, materialBasics);
        JSONUtils.toJSON(map, response);
    }

    // 原料库存
    @RequestMapping("/showMaterialInventory.do")
    public void showMaterialInventory(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<MaterialInventory> materialInventories = materialInventoryService.findMaterialInventories(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, materialInventories);
        JSONUtils.toJSON(map, response);
    }

    // TODO: 2016/9/14 这个接口返回值比较特殊， 和前端交流一下
    // 购买原料前先列出原料的信息
    @RequestMapping("/findMaterialBasicAndInventory.do")
    public void findMaterialBasicAndInventory(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<MaterialInventory> materialInventories = materialInventoryService.findMaterialInventories(userUnique);
        List<MaterialBasic> materialBasics = materialBasicService.findAllMaterialBasic();

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("materialInventories", materialInventories);
        map.put("materialBasics", materialBasics);
        JSONUtils.toJSON(map, response);
    }

    // 购买原材料
    @RequestMapping("/addMaterial.do")
    public void addMaterial(String materialName, int materialNumber, String wareHouseName,
                            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        MaterialPurchaseRecord newOrder =
                this.generateMaterialPurchaseRecord(materialName, materialNumber, wareHouseName, userUnique);

        boolean result = materialPurchaseService.addMaterialPurchaseRecord(newOrder);
        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "原料订货成功,点击查看原料采购单查询详情", null);
        } else{
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "原料订货失败", null);
        }

        JSONUtils.toJSON(map, response);
    }
    private MaterialPurchaseRecord generateMaterialPurchaseRecord(String materialName, int materialNumber,
                                                                  String wareHouseName, String userUnique){
        MaterialPurchaseRecord newOrder = new MaterialPurchaseRecord();

        newOrder.setMaterialName(materialName);
        newOrder.setMaterialNumber(materialNumber);
        newOrder.setWareHouseName(wareHouseName);
        newOrder.setUserUnique(userUnique);

        return newOrder;

    }

    // TODO: 2016/9/14 和前端沟通一下item各个值代表的意义,返回值
    // 查看原料采购单
    @RequestMapping("/findMaterialOrders.do")
    public void findMaterialOrders(int item, int pageSize, int pageNumber,
            HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        int sum;
        int currentTime = gameGroupMemberService.findCurrentPeriod(userUnique);
        if (item == 0){ // 已经到货订单
            sum = materialPurchaseService.findMaterialOrdersReachCount(userUnique, currentTime);
        } else if (item == 1){ // 未经到货订单
            sum = materialPurchaseService.findMaterialOrdersUnReachCount(userUnique, currentTime);
        } else if (item == 2){
            int a = materialPurchaseService.findMaterialOrdersReachCount(userUnique, currentTime);
            int b = materialPurchaseService.findMaterialOrdersUnReachCount(userUnique, currentTime);
            sum = a + b;
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "参数错误", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<MaterialPurchaseRecord> materialOrders =
                materialPurchaseService.findMaterialOrders(userUnique, pageSize, item, pageNumber);

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("materialOrders", materialOrders);
        map.put("sum", sum);
        JSONUtils.toJSON(map, response);
    }
}
