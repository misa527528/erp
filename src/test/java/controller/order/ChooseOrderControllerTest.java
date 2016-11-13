package controller.order;

import com.cqupt.mis.erp.controller.order.ChooseOrderController;
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
 * Created by 杨青 on 2016/9/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ChooseOrderControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private ChooseOrderController chooseOrderController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }
    @Test
    public void chooseOrderList() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        String marketName = "本地市场";
        String productName = "P2";

        /*Map map = chooseOrderController.chooseOrderList(marketName, productName, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }
    @Test
    public void chooseOrderList_SessionNoUserUnique_Error() throws Exception {
        String marketName = "本地市场";
        String productName = "P2";

        /*Map map = chooseOrderController.chooseOrderList(marketName, productName, request, response);

        Assert.assertSame(ReturnStatus.ERROR, map.get("status"));*/
    }


}