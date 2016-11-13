package controller.finance;

import com.cqupt.mis.erp.controller.finance.WillReceiveController;
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
 * Created by 杨青 on 2016/9/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class WillReceiveControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private WillReceiveController willReceiveController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void discount() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        int willReceiveId = 1123;

        /*Map map = willReceiveController.discount(willReceiveId, request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

    @Test
    public void findWillReceive() throws Exception{
        request.getSession().setAttribute("userUnique", "bb14630512995485");

        /*Map map = willReceiveController.findWillReceive(request, response);

        Assert.assertSame(ReturnStatus.SUCCESS, map.get("status"));*/
    }

}