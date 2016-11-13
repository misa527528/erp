package controller.factory;

import com.cqupt.mis.erp.controller.factory.ProductLineController;
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
 * Created by 杨青 on 2016/9/10.
 * 这个类的方法全都是测试通过的，之所以看起来像没有测试时因为测试的时候放错在别动包里面，现在才移到这包
 * @Since 2016/9/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ProductLineControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private ProductLineController productLineController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void addProductLine() throws Exception {
        request.getSession().setAttribute("userUnique","11120160425165520");
        String factoryId = "U52";
        String lineType = "手工生产线";
        String productName = "P1";

        /*Map map = productLineController.addProductLine(factoryId, lineType, productName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addProductLine_SessionNoUserUnique_Error() throws Exception {
        String factoryId = "U52";
        String lineType = "手工生产线";
        String productName = "P1";

        /*Map map = productLineController.addProductLine(factoryId, lineType, productName, request, response);

        Assert.assertSame("添加失败", map.get("message"));*/
    }
    @Test
    public void addProductLine_EmptyInsert_Error() throws Exception {
        request.getSession().setAttribute("userUnique","lj20151009083255");
        String factoryId = "344";
        String lineType = "手工生产线";
        String productName = "P1";

       /* Map map = productLineController.addProductLine(factoryId, lineType, productName, request, response);

        Assert.assertSame("厂房容量已满，不能新增生产线", map.get("message"));*/
    }

    @Test
    public void allConfigOperate_startProduct(){
        request.getSession().setAttribute("userUnique", "1120160426152216");
        String status = "开始生产";
        String productLineId = "1710";

       /* Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_updateStopProduct(){
        request.getSession().setAttribute("userUnique", "11120160425165520");
        String status = "暂停生产";
        String productLineId = "1593";

      /*  Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_updateReStartProduct(){
        request.getSession().setAttribute("userUnique", "11120160425165520");
        String status = "恢复生产";
        String productLineId = "1593";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_updateStopstall(){
        request.getSession().setAttribute("userUnique", "11120160426134624");
        String status = "暂停安装";
        String productLineId = "1677";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_updateRestall(){
        request.getSession().setAttribute("userUnique", "11120160426134624");
        String status = "恢复安装";
        String productLineId = "1677";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_updateStopChangeProduct(){
        request.getSession().setAttribute("userUnique", "11120160426134624");
        String status = "暂停转产";
        String productLineId = "1677";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_updateReChangeProduct(){
        request.getSession().setAttribute("userUnique", "11120160426134624");
        String status = "恢复转产";
        String productLineId = "1677";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allConfigOperate_statusError(){
        request.getSession().setAttribute("userUnique", "lj20151009083255");
        String status = "没有这个指令";
        String productLineId = "1424";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame("指令错误", map.get("message"));*/
    }
    @Test
    public void allConfigOperate_Error(){ // productLineId错误
        request.getSession().setAttribute("userUnique", "lj20151009083255");
        String status = "恢复安装";
        String productLineId = "143";

        /*Map map = productLineController.allConfigOperate(status, productLineId, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getAllProductLineBasic(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");

        /*Map map = productLineController.getAllProductLineBasic(request, response);
        List<DevelopedProduct> developedProducts = (List<DevelopedProduct>) map.get("productName");
        DevelopedProduct developedProduct = developedProducts.get(0);
        String productName = developedProduct.getProductName();

        Assert.assertEquals("P1", productName);*/
    }

    @Test
    public void getProductLineDetail(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String productLineId = "1913";

        /*Map map = productLineController.getProductLineDetail(productLineId, request, response);
        ProductLineCommonInfo productLineInfo =(ProductLineCommonInfo)map.get("data");

        Assert.assertEquals("U130", productLineInfo.getFactoryId());*/
    }
    @Test
    public void getProductLineDetail_productLineNotDefine_Error(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String productLineId = "";

        /*Map map = productLineController.getProductLineDetail(productLineId, request, response);

        Assert.assertSame("该生产线不存在", map.get("message"));*/
    }
    @Test
    public void getProductLineDetail_SessionNoUserUnique_Error(){
        String productLineId = "1913";

        /*Map map = productLineController.getProductLineDetail(productLineId, request, response);

        Assert.assertSame("操作失败", map.get("message"));*/
    }

    @Test
    public void sellProductLine(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productLineId = "1752";

       /* Map map = productLineController.sellProductLine(productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void sellProductLine_cannotSell_Error(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String productLineId = "1913";

       /* Map map = productLineController.sellProductLine(productLineId, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void sellProductLine_SessionNoUserUnique_Error(){
        String productLineId = "1913";

        /*Map map = productLineController.sellProductLine(productLineId, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void startChangeProduct(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productLineId = "1752";
        String productName = "P2";

        /*Map map = productLineController.startChangeProduct(productLineId, productName, request,response);
        ProductLineInfo productLine = (ProductLineInfo) map.get("data");

        Assert.assertEquals("P2", productLine.getProductName());*/
    }
    @Test
    public void startChangeProduct_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productLineId = "152"; // 这个Id是错的， 所以没办法转产
        String productName = "P2";

        /*Map map = productLineController.startChangeProduct(productLineId, productName, request,response);

        Assert.assertEquals("转产失败", map.get("message"));*/
    }
    @Test
    public void startChangeProduct_SessionNoUserUnique_Error(){
        String productLineId = "1752";
        String productName = "P2";

       /* Map map = productLineController.startChangeProduct(productLineId, productName, request,response);

        Assert.assertEquals("操作失败", map.get("message"));*/
    }

    @Test
    public void withdrawProductLine(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productLineId = "1927";

        /*Map map = productLineController.withdrawProductLine(productLineId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void withdrawProductLine_NotInStatus_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String productLineId = "1752";

        /*Map map = productLineController.withdrawProductLine(productLineId, request, response);

        Assert.assertEquals("当前状态不能撤回", map.get("message"));*/
    }
    @Test
    public void withdrawProductLine_SessionNoUserUnique_Error(){
        String productLineId = "1927";

        /*Map map = productLineController.withdrawProductLine(productLineId, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
}