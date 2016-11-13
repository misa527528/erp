package controller.materialpurchase;

import com.cqupt.mis.erp.controller.materialpurchase.MaterialpurchaseController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by 杨青 on 2016/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class MaterialpurchaseControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private MaterialpurchaseController materialpurchaseController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void showMaterialBasic(){
        /*Map map = materialpurchaseController.showMaterialBasic(response);
        List<MaterialBasic> materialBasics = (List<MaterialBasic>) map.get("data");

        Assert.assertSame(4, materialBasics.size());*/
    }

    @Test
    public void showMaterialInventory(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = materialpurchaseController.showMaterialInventory(request, response);
        List<MaterialInventory> materialInventories = (List<MaterialInventory>) map.get("data");

        Assert.assertSame(4, materialInventories.size());*/
    }
    @Test
    public void showMaterialInventory_SessionNoUserUnique_Error(){
        /*Map map = materialpurchaseController.showMaterialInventory(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void findMaterialBasicAndInventory(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = materialpurchaseController.findMaterialBasicAndInventory(request, response);
        List<MaterialInventory> materialInventories = (List<MaterialInventory>) map.get("materialInventories");

        Assert.assertSame(4, materialInventories.size());*/
    }
    @Test
    public void findMaterialBasicAndInventory_SessionNoUserUnique_Error(){
        /*Map map = materialpurchaseController.findMaterialBasicAndInventory(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void addMaterial(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String materialName = "R3";
        int materialNumber = 1;
        String wareHouseName = "1号仓库";

        /*Map map = materialpurchaseController.addMaterial(materialName, materialNumber, wareHouseName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addMaterial_Error(){
        request.getSession().setAttribute("userUnique", "");
        String materialName = "R3";
        int materialNumber = 1;
        String wareHouseName = "1号仓库";

        /*Map map = materialpurchaseController.addMaterial(materialName, materialNumber, wareHouseName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void addMaterial_SessionNoUserUnique_Error(){
        String materialName = "";
        int materialNumber = 0;
        String wareHouseName = "";

        /*Map map = materialpurchaseController.addMaterial(materialName, materialNumber, wareHouseName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void findMaterialOrders_Item_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        int item = 5;
        int pageSize = 14;
        int pageNumber = 1;

        /*Map map = materialpurchaseController.findMaterialOrders(item, pageSize, pageNumber, request, response);

        Assert.assertSame("参数错误", map.get("message"));*/
    }
    @Test
    public void findMaterialOrders_SessionNoUserUnique_Error(){
        int item = 0;
        int pageSize = 1;
        int pageNumber = 1;

        /*Map map = materialpurchaseController.findMaterialOrders(item, pageSize, pageNumber, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

}