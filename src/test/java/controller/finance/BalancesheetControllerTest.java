package controller.finance;

import com.cqupt.mis.erp.controller.finance.BalancesheetController;
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
public class BalancesheetControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private BalancesheetController balancesheetController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void findBalanceSheet() throws Exception {
        request.getSession().setAttribute("userUnique", "bb14630512995485");
        int year = 1;
        int season = 1;

       /* Map map = balancesheetController.findBalanceSheet(year, season, request, response);

        Assert.assertTrue(map.get("periodOfYear") == 7);*/
    }

}