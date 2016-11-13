package controller.factory;

import com.cqupt.mis.erp.controller.factory.FactoryController;
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
 * Created by 杨青 on 2016/9/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class FactoryControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private FactoryController factoryController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void addNewFactory() throws Exception {
        request.getSession().setAttribute("userUnique", "qing147204583045433");
        String factoryType = "大厂房";
        String place = "本地市场";

        /*Map map = factoryController.addNewFactory(factoryType, place, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addNewFactory_SessionNoUserUnique_Error() throws Exception {
        String factoryType = "大厂房";
        String place = "本地市场";

        /*Map map = factoryController.addNewFactory(factoryType, place, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void addNewRentFactory(){
        request.getSession().setAttribute("userUnique", "qing147204583045433");
        String factoryType = "大厂房";
        String place = "本地市场";
        int needTime = 4;

        /*Map map = factoryController.addNewRentFactory(factoryType, place, needTime, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void addNewRentFactory_SessionNoUserUnique_Error(){
        String factoryType = "大厂房";
        String place = "本地市场";
        int needTime = 4;

       /* Map map = factoryController.addNewRentFactory(factoryType, place, needTime, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void addNewRentFactory_Error(){
        request.getSession().setAttribute("userUnique", "");// 这样写是因为只有userUnique出错时才会添加失败

        String factoryType = "大厂房";
        String place = "本地市场";
        int needTime = 4;

       /* Map map = factoryController.addNewRentFactory(factoryType, place, needTime, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getAllFactoryAndProductLine(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");

        /*Map map = factoryController.getAllFactoryAndProductLine(request, response);

       Assert.assertNotNull(map.get("factoryMade"));*/
    }
    @Test
    public void getAllFactoryAndProductLine_SessionNoUserUnique_Error(){
        /*Map map = factoryController.getAllFactoryAndProductLine(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getDevelopedProduct(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");

        /*Map map = factoryController.getDevelopedProduct(request, response);

        Assert.assertNotNull(map.get("data"));*/
    }
    @Test
    public void getDevelopedProduct_SessionNoUserUnique_Error(){
       /* Map map = factoryController.getDevelopedProduct(request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getFactoryByWorkshopStatusAndMarketArea(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");
        String worshopStatus = "allStatus";
        String marketArea = "本地市场";

        /*Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);

        Assert.assertNotNull(map.get("factoryMade"));*/
    }
    @Test
    public void getFactoryByWorkshopStatusAndMarketArea_marketArea_All(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");
        String worshopStatus = "allStatus";
        String marketArea = "全部";

    /*    Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);
        List<FactoryVO> factoryMade = (List<FactoryVO>) map.get("factoryMade");

        Assert.assertNotNull(factoryMade);*/
    }
    @Test
    public void getFactoryByWorkshopStatusAndMarketArea_worshopStatus_building(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");
        String worshopStatus = "building";
        String marketArea = "本地市场";

        /*Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);
        List<FactoryCommonInfo> factoryMaking = (List<FactoryCommonInfo>) map.get("factoryMaking");

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void getFactoryByWorkshopStatusAndMarketArea_worshopStatus_rent(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");
        String worshopStatus = "rent";
        String marketArea = "本地市场";

        /*Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);
        List<FactoryVO> factoryMade = (List<FactoryVO>) map.get("factoryMade");
        List<FactoryVO> factoryRent = (List<FactoryVO>) map.get("factoryRent");
        List<FactoryCommonInfo> factoryMaking = (List<FactoryCommonInfo>) map.get("factoryMaking");

        Assert.assertSame(0, factoryMade.size());
        Assert.assertSame(0, factoryRent.size());
        Assert.assertSame(0, factoryMaking.size());*/

    }
    @Test
    public void getFactoryByWorkshopStatusAndMarketArea_worshopStatus_built(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");
        String worshopStatus = "built";
        String marketArea = "本地市场";

       /* Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);
        List<FactoryVO> factoryMade = (List<FactoryVO>) map.get("factoryMade");
        List<FactoryVO> factoryRent = (List<FactoryVO>) map.get("factoryRent");
        List<FactoryCommonInfo> factoryMaking = (List<FactoryCommonInfo>) map.get("factoryMaking");

        Assert.assertNotNull(factoryMade.get(0));
        Assert.assertSame(0, factoryRent.size());
        Assert.assertSame(0, factoryMaking.size());*/
    }
    @Test
    public void getFactoryByWorkshopStatusAndMarketArea_worshopStatus_NullError(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");
        String worshopStatus = "asoiedjfpa";
        String marketArea = "本地市场";

        /*Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void getFactoryByWorkshopStatusAndMarketArea_SessionNoUserUnique_Error(){
        String worshopStatus = "本地市场";
        String marketArea = "全部";

        /*Map map = factoryController.getFactoryByWorkshopStatusAndMarketArea(
                worshopStatus, marketArea, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void getNewFactoryDetail(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");

       /* Map map = factoryController.getNewFactoryDetail(request, response);
        FactoryNewVO factory = (FactoryNewVO) map.get("data");

        Assert.assertSame(1, factory.getCurrentPeriod());*/
    }

    @Test
    public void getNewRentFactory(){
        request.getSession().setAttribute("userUnique", "qing147089562272423");

        /*Map map = factoryController.getNewRentFactory(request, response);

        Assert.assertNotNull(map.get("developedMarket"));*/
    }

    @Test
    public void sellUsingFactory(){
        request.getSession().setAttribute("userUnique", "雷小东20151009083255");
        String factoryId = "351";

        /*Map map = factoryController.sellUsingFactory(factoryId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void startFactoryMaking(){
        request.getSession().setAttribute("userUnique", "777147098568477800");
        int factoryId = 10;

        /*Map map = factoryController.startFactoryMaking(factoryId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void startFactoryMaking_insert_Error(){
        request.getSession().setAttribute("userUnique", "777147098568477800");
        int factoryId = 5;

       /* Map map = factoryController.startFactoryMaking(factoryId, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void startFactoryMaking_SessionNoUserUnique_Error(){
        int factoryId = 5;

        /*Map map = factoryController.startFactoryMaking(factoryId, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void stopFactoryMaking(){
        request.getSession().setAttribute("userUnique","777147098568477800");
        int factory = 12;

        /*Map map = factoryController.stopFactoryMaking(factory, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void stopFactoryMaking_SessionNoUserUnique_Error(){
        int factory = 12;

/*        Map map = factoryController.stopFactoryMaking(factory, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }
    @Test
    public void stopFactoryMaking_update_Error(){
        request.getSession().setAttribute("userUnique","周子渝20151008100727");
        int factory = 12;

        /*Map map = factoryController.stopFactoryMaking(factory, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }

    @Test
    public void stopFactoryRent(){
        request.getSession().setAttribute("userUnique", "panjun20151009090254");
        String factoryId = "R1";

        /*Map map = factoryController.stopFactoryRent(factoryId, request, response);

        Assert.assertSame("停止租用厂房", map.get("message"));*/
    }
    @Test
    public void stopFactoryRent_delete_Error(){
        request.getSession().setAttribute("userUnique", "11120160426134707");
        String factoryId = "U73";

       /* Map map = factoryController.stopFactoryRent(factoryId, request, response);

        Assert.assertSame("厂房内有生产线，不能停止租用", map.get("message"));*/
    }
    @Test
    public void stopFactoryRent_SessionNoUserUnique_Error(){
        String factoryId = "";

        /*Map map = factoryController.stopFactoryRent(factoryId, request, response);

        Assert.assertSame("无法停止租借", map.get("message"));*/
    }
}