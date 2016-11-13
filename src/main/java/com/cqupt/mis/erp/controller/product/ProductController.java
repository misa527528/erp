package com.cqupt.mis.erp.controller.product;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.materialpurchase.MaterialInventory;
import com.cqupt.mis.erp.model.product.*;
import com.cqupt.mis.erp.service.materialpurchase.MaterialInventoryService;
import com.cqupt.mis.erp.service.product.DevelopedProductService;
import com.cqupt.mis.erp.service.product.DevelopingProductService;
import com.cqupt.mis.erp.service.product.ProductBasicService;
import com.cqupt.mis.erp.service.product.UndevelopProductService;
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
 * Created by 杨青 on 2016/9/11.
 */
@Controller("productController")
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductBasicService productBasicService;
    @Resource
    private DevelopedProductService developedProductService;
    @Resource
    private DevelopingProductService developingProductService;
    @Resource
    private UndevelopProductService undevelopProductService;
    @Resource
    private MaterialInventoryService materialInventoryService;

    // 产品库存
    @RequestMapping("/showProductInventory.do")
    public void showProductInventory(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<ProductOfUser> productOfUserList = productBasicService.findProductInventories(userUnique);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, productOfUserList);
        JSONUtils.toJSON(map, response);
    }

    // 改变产品的研发状态
    @RequestMapping("/modifySearchStarus.do")
    public void modifySearchStarus(String productName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        developingProductService.updateDevelopingProductStatus(userUnique, productName);

        map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/startSearch.do")
    public void startSearch(String productName, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        if (productName == null || "".equals(productName)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "产品名称错误", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        try {
            undevelopProductService.updateDevelopProduct(userUnique, productName);

            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "开始研发", null);
        } catch (Exception e){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "无法开始研发", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // TODO: 2016/9/12 这个接口的放回值比较特殊，和前端沟通一下 
    // 产品研发管理
    @RequestMapping("/showAllDevelopProduct.do")
    public void showAllProduct(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<DevelopedProduct> developedProducts = developedProductService.findDevelopedProductsByUserUnique(userUnique);
        List<DevelopingProduct> developingProducts = developingProductService.findDevelopingProductsByUserUnique(userUnique);
        List<UndevelopProduct> undevelopProducts = undevelopProductService.findUndevelopProductsByUserUnique(userUnique);

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        map.put("developedProducts", developedProducts);
        map.put("developingProducts", developingProducts);
        map.put("undevelopProducts", undevelopProducts);
        JSONUtils.toJSON(map, response);
    }

    // TODO: 2016/9/12 这个接口的放回值比较特殊，和前端沟通一下
    // 产品原料构成说明
    @RequestMapping("/showProductBasic.do")
    public void showProductBasic(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        List<ProductDetailBasic> productDetailBasics = productBasicService.findProductDetail();
        List<MaterialInventory> materialInventories = materialInventoryService.findMaterialInventories(userUnique);

        // 计算成本价
        List<String> productNames = productBasicService.findProductName();
        for (String name:productNames){
            map.put(name + "Cost", productBasicService.findProductPrice(name));
        }

        map.put("status", ReturnStatus.SUCCESS);
        map.put("message", null);
        JSONUtils.toJSON(map, response);
    }
}
