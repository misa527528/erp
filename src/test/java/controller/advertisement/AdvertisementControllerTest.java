package controller.advertisement;

import com.cqupt.mis.erp.controller.advertisement.AdvertisementController;
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
public class AdvertisementControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private AdvertisementController advertisementController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void allUserAdvertisementStatus() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = advertisementController.allUserAdvertisementStatus(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void allUserAdvertisementStatus_SessionNoUserUnique() throws Exception {
        /*Map map = advertisementController.allUserAdvertisementStatus(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getAdByMarket(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "亚洲市场";

        /*Map map = advertisementController.getAdByMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void getAdByMarket_NullMarketName(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "";

        /*Map map = advertisementController.getAdByMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void getAdByMarket_SessionNoUserUnique_Error(){
        String marketName = "亚洲市场";

        /*Map map = advertisementController.getAdByMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getAlreadAd(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = advertisementController.getAlreadAd(request, response);

        Assert.assertNotNull(map.get("data"));
        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void getAlreadAd_SessionNoUserUnique_Error(){
        /*Map map = advertisementController.getAlreadAd(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void putIntoAdvertisement(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        int advertisementId = 13102;
        double money = 2.5;

        /*Map map = advertisementController.putIntoAdvertisement(advertisementId, money, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void putIntoAdvertisement_SessionNoUserUnique_Error(){
        int advertisementId = 13102;
        double money = 2.5;

        /*Map map = advertisementController.putIntoAdvertisement(advertisementId, money, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
}