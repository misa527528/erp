package controller.registerlogin;

import com.cqupt.mis.erp.controller.forword.ForwardController;
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
 * Created by 杨青 on 2016/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-commons.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ForwardControllerTest {
    // 模拟request, response
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Resource
    private ForwardController forwardController;

    // 执行测试方法之前初始化模拟的request， response
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }

    @Test
    public void forward(){
        request.getSession().setAttribute("userUnique", "lj20151009083255");
/*
        Map map = forwardController.forward(request, response);

        Assert.assertSame(2, map.get("data"));*/
    }

    @Test
    public void yearAndPeriod(){
        request.getSession().setAttribute("userId", "11");
        request.getSession().setAttribute("userUnique", "1120160426153336");

        /*Map map = forwardController.yearAndPeriod(request, response);
        Map data = (Map) map.get("data");

        Assert.assertSame(2, data.get("period"));*/
    }
}