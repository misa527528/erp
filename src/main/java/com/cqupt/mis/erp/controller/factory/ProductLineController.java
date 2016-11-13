package com.cqupt.mis.erp.controller.factory;

import com.cqupt.mis.erp.model.ReturnStatus;
import com.cqupt.mis.erp.model.factory.ProductLineBasic;
import com.cqupt.mis.erp.model.factory.ProductLineCommonInfo;
import com.cqupt.mis.erp.model.factory.ProductLineInfo;
import com.cqupt.mis.erp.model.product.DevelopedProduct;
import com.cqupt.mis.erp.service.factory.ProductLineService;
import com.cqupt.mis.erp.service.product.DevelopedProductService;
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
 * Created by 杨青 on 2016/9/10.
 */
@Controller("productLineController")
@RequestMapping("/productLine")
public class ProductLineController {
    @Resource
    private ProductLineService productLineService;
    @Resource
    private DevelopedProductService developedProductService;

    @RequestMapping("/addProductLine.do")
    public void addProductLine(String factoryId, String lineType, String productName,
                               HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        int result = 0;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            ProductLineCommonInfo productLine =
                    this.generteProductLine4Add(userUnique, factoryId, lineType, productName);

            result = productLineService.addProductLine(userUnique, factoryId, productLine);
        }

        if (result == 1){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "添加生产线成功", null);
        } else if (result == 2){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "厂房容量已满，不能新增生产线", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "添加失败", null);
        }

        JSONUtils.toJSON(map, response);
    }
    private ProductLineCommonInfo generteProductLine4Add(String userUnique, String factoryId,
                                                         String lineType, String productName){
        ProductLineCommonInfo productLine = new ProductLineCommonInfo();
        productLine.setUserUnique(userUnique);
        productLine.setFactoryId(factoryId);
        productLine.setProductLineType(lineType);
        productLine.setProductName(productName);

        return productLine;
    }

    // 对生产线的配置和转产
    @RequestMapping("/allConfigOperate.do")
    public void allConfigOperate(String status, String productLineId,
                                 HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");
        boolean result;

        if ("开始生产".equals(status)){
            result = productLineService.updateStartProduct(userUnique, productLineId);
        } else if ("暂停生产".equals(status)){
            result = productLineService.updateStopProduct(userUnique, productLineId);
        } else if ("恢复生产".equals(status)){
            result = productLineService.updateReStartProduct(userUnique, productLineId);
        } else if ("暂停安装".equals(status)){
            result = productLineService.updateStopstall(userUnique, productLineId);
        } else if ("恢复安装".equals(status)){
            result = productLineService.updateRestall(userUnique, productLineId);
        } else if ("暂停转产".equals(status)){
            result = productLineService.updateStopChangeProduct(userUnique, productLineId);
        } else if ("恢复转产".equals(status)){
            result = productLineService.updateReChangeProduct(userUnique, productLineId);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "指令错误", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        if (result){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "操作成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
        }
        JSONUtils.toJSON(map, response);
    }

    // 新建生产线时获取各生产线的信息
    @RequestMapping("/getAllProductLineBasic.do")
    public void getAllProductLineBasic(HttpServletRequest request, HttpServletResponse response){
        Map<String , Object> map = new HashMap<>();
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            List<DevelopedProduct> productName = developedProductService.findDevelopedProductsByUserUnique(userUnique);
            List<ProductLineBasic> productLineBasic = productLineService.findProductLineBasics();

            map.put("status", ReturnStatus.SUCCESS);
            map.put("message", null);
            map.put("productName", productName);
            map.put("productLineBasic", productLineBasic);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/getProductLineDetail.do")
    public void getProductLineDetail(String productLineId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique != null && !"".equals(userUnique)){
            if (productLineId != null && !"".equals(productLineId)){
                ProductLineCommonInfo productLineInfo =
                        productLineService.findProductLineDetailExceptStatus(userUnique, productLineId);
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, null, productLineInfo);
            } else {
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "该生产线不存在", null);
            }
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/sellProductLine.do")
    public void sellProductLine(String productLineId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        int status = productLineService.findProductLineStatus(userUnique, productLineId);

        if (status == 4){// “待生产”状态，即生产线完成了安装或转产，但是当前的生产线上没有商品在进行生产待生产”状态，
            // 即生产线完成了安装或转产，但是当前的生产线上没有商品在进行生产
            ProductLineBasic productLineBasic = productLineService.findProductLineBasic(productLineId);
            String productLineType = productLineBasic.getProductLineType();

            productLineService.deleteSaleProductLine(userUnique, productLineId, productLineType);

            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "出售成功", null);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "生产线不是待生产状态,不能出售!", null);
        }
        JSONUtils.toJSON(map, response);
    }

    @RequestMapping("/startChangeProduct.do")
    public void startChangeProduct(String productLineId, String productName,
                                   HttpServletRequest request, HttpServletResponse response ){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        boolean result = productLineService.updateStartChangeProduct(userUnique, productLineId, productName);
        if (result){
            ProductLineInfo productLine = productLineService.findProductLineInfo(productLineId);

            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "转产成功", productLine);
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "转产失败", null);
        }

        JSONUtils.toJSON(map, response);
    }

    // TODO: 2016/9/11 该方法虽然可以通过测试，但是不清楚建造生产线用不用涉及到资金的修改，该方法也就没有修改资金方面，所以不能保证完全正确
    @RequestMapping("/withdrawProductLine.do")
    public void withdrawProductLine(String productLineId, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map;
        String userUnique = (String) request.getSession().getAttribute("userUnique");

        if (userUnique == null || "".equals(userUnique)){
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "操作失败", null);
            JSONUtils.toJSON(map, response);
            return;
        }

        int productLineStatus = productLineService.findProductLineStatus(userUnique, productLineId);
        if (productLineStatus == 0 ||  productLineStatus == 1){
            boolean result = productLineService.withdrawProductLine(userUnique, productLineId);
            
            if (result){
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "撤回成功", null);
            } else {
                map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.ERROR, "撤回失败", null);
            }
        } else {
            map = ReturnMapUtils.putIntoReturnMap(ReturnStatus.SUCCESS, "当前状态不能撤回", null);
        }
        
        JSONUtils.toJSON(map, response);
    }
}
