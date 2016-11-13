package controller.market;

import com.cqupt.mis.erp.controller.market.MarketController;
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
 * Created by 杨青 on 2016/9/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class MarketControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private MarketController marketController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void getAllMarkets(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = marketController.getAllMarkets(request, response);
        List<DevelopedMarket> developedMarkets = (List<DevelopedMarket>) map.get("developedMarkets");
        DevelopedMarket developedMarket = developedMarkets.get(0);

        Assert.assertEquals("本地市场", developedMarket.getMarketName());*/
    }
    @Test
    public void getAllMarkets_SessionNoUserUnique_Error(){
        /*Map map = marketController.getAllMarkets(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getDevelopedMarket(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");

        /*Map map = marketController.getDevelopedMarket(request, response);
        List<String> marketNames = (List<String>) map.get("data");

        Assert.assertEquals("本地市场", marketNames.get(0));*/
    }
    @Test
    public void getDevelopedMarket_SessionNoUserUnique_Error(){
        /*Map map = marketController.getDevelopedMarket(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void startDevelopedMarket(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "本地市场";

       /* Map map = marketController.startDevelopedMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void startDevelopedMarket_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "区域市场";

        /*Map map = marketController.startDevelopedMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void startDevelopedMarket_SessionNoUserUnique_Error(){
        String marketName = "本地市场";

        /*Map map = marketController.startDevelopedMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void stopDevelopedMarket(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "本地市场";

        /*Map map = marketController.stopDevelopedMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void stopDevelopedMarket_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "区域市场";

        /*Map map = marketController.stopDevelopedMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void stopDevelopedMarket_SessionNoUserUnique_Error(){
        String marketName = "本地市场";

        /*Map map = marketController.stopDevelopedMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void startDevelopingMarket(){
        //request.getSession().setAttribute("userUnique", "qing147089562272423");
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String marketName = "亚洲市场";

        /*Map map = marketController.startDevelopingMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void startDevelopingMarket_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "本地市场";

        /*Map map = marketController.startDevelopingMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void startDevelopingMarket_SessionNoUserUnique_Error(){
        String marketName = "亚洲市场";

        /*Map map = marketController.startDevelopingMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void stopDevelopingMarket(){
        // request.getSession().setAttribute("userUnique", "qing147089562272423");
         request.getSession().setAttribute("userUnique", "qing147254658863377");
        String marketName = "亚洲市场";

       /* Map map = marketController.stopDevelopingMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void stopDevelopingMarket_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "区域市场";

        /*Map map = marketController.stopDevelopingMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void stopDevelopingMarket_SessionNoUserUnique_Error(){
        String marketName = "国内市场";

        /*Map map = marketController.stopDevelopingMarket(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void startUndevelopMarketToDeveloping(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "亚洲市场";

        /*Map map = marketController.startUndevelopMarketToDeveloping(marketName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void startUndevelopMarketToDeveloping_Error(){
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "本地市场";

       /* Map map = marketController.startUndevelopMarketToDeveloping(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void startUndevelopMarketToDeveloping_SessionNoUserUnique_Error(){
        String marketName = "亚洲市场";

        /*Map map = marketController.startUndevelopMarketToDeveloping(marketName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void findPrediction(){
        request.getSession().setAttribute("userUnique", "qing147254658863377");
        String marketName = "本地市场";

        /*Map map = marketController.findPrediction(marketName, request, response);
        Map priceMap = (Map) map.get("priceMap");

        Assert.assertSame(6, priceMap.size());*/
    }

}