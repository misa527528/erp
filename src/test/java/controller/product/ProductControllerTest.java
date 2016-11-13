package controller.product;

import com.cqupt.mis.erp.controller.product.ProductController;
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
 * Created by 杨青 on 2016/9/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ProductControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private ProductController productController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void showProductInventory() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = productController.showProductInventory(request, response);
        List<ProductOfUser> productOfUserList = (List<ProductOfUser>) map.get("data");

        Assert.assertNotNull(productOfUserList.get(3));*/
    }
    @Test
    public void showProductInventory_SessionNoUserUnique_Error() throws Exception {
        /*Map map = productController.showProductInventory(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void modifySearchStarus(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productName = "P3";

       /* Map map = productController.modifySearchStarus(productName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void modifySearchStarus_SessionNoUserUnique_Error(){
        String productName = "P3";

        /*Map map = productController.modifySearchStarus(productName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void showAllProduct(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = productController.showAllProduct(request, response);
        List<DevelopingProduct> developingProducts = (List<DevelopingProduct>) map.get("developingProducts");
        DevelopingProduct developingProduct = developingProducts.get(0);

        Assert.assertEquals("P3", developingProduct.getProductName());*/
    }
    @Test
    public void showAllProduct_SessionNoUserUnique_Error(){
        /*Map map = productController.showAllProduct(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void showProductBasic(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = productController.showProductBasic(request, response);
        float P1Cost = (float) map.get("P1Cost");

        Assert.assertTrue(P1Cost == 1);*/
    }
    @Test
    public void showProductBasic_SessionNoUserUnique_Error(){
        /*Map map = productController.showProductBasic(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void startSearch(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productName = "P2";

        /*Map map = productController.startSearch(productName, request, response);

        Assert.assertSame("开始研发", map.get("message"));*/
    }
    @Test
    public void startSearch_Search_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productName = "P3"; // P3已经在研发了

        /*Map map = productController.startSearch(productName, request, response);

        Assert.assertSame("无法开始研发", map.get("message"));*/
    }
    @Test
    public void startSearch_NullProductName_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productName = "";

        /*Map map = productController.startSearch(productName, request, response);

        Assert.assertSame("产品名称错误", map.get("message"));*/

    }
    @Test
    public void startSearch_SessionNoUserUnique_Error(){
        String productName = "P3";

        /*Map map = productController.startSearch(productName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
}